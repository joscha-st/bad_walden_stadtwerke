/**
 * The InitialSignUpControllerStep3 class is the controller responsible for handling the initial sign-up step 3 in the application.
 * It manages the user interface elements and actions related to selecting gas and heating tariffs and navigating to the next step.
 * <p>
 * This controller interacts with the {@link com.bad_walden_stadtwerke.components.TariffTableDisplay} to display gas and heating tariffs,
 * the {@link com.bad_walden_stadtwerke.model.communication.StandardOutboundRequestHandler} for sending tariff selection information to the backend,
 * the {@link com.bad_walden_stadtwerke.controller.language.LanguageController} for language localization, and the
 * {@link com.bad_walden_stadtwerke.utility.FXMLUtility} for loading FXML files.
 * </p>
 * <p>
 * It initializes by retrieving gas and heating tariffs from the server and displays them using the respective tariff table displays.
 * </p>
 * <p>
 * It allows the user to select either a gas or a heating tariff based on the selected tab. When the "Next" button is clicked,
 * it validates the selected tariff option and sends the relevant information to the server. In case of any exceptions during the process,
 * it displays an error popup using {@link com.bad_walden_stadtwerke.components.errorHandling.ExceptionPopup}.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
package com.bad_walden_stadtwerke.controller.initialSignUp;

import com.bad_walden_stadtwerke.components.TariffSelectionConfirmationPopUp;
import com.bad_walden_stadtwerke.components.TariffTableDisplay;
import com.bad_walden_stadtwerke.components.errorHandling.ExceptionPopup;
import com.bad_walden_stadtwerke.controller.language.LanguageController;
import com.bad_walden_stadtwerke.model.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.model.initialSignUp.SignUpManager;
import com.bad_walden_stadtwerke.model.types.Tariff;
import com.bad_walden_stadtwerke.utility.FXMLUtility;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class InitialSignUpControllerStep3 {

	private static final String FXML_PATH = "/com/bad_walden_stadtwerke/view/initialSignUp/signup-dialog-4.fxml";
	private static final String CURRENT_FXML_PATH = "/com/bad_walden_stadtwerke/view/initialSignUp/signup-dialog-3.fxml";
	private static final String BUNDLE_NAME = SignUpManager.BUNDLE_NAME;
	private final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());
	private Tariff tariff = null;
	private ArrayList<Tariff> gas = new ArrayList<>();
	private ArrayList<Tariff> heating = new ArrayList<>();
	private TariffTableDisplay gasDisplay;
	private TariffTableDisplay heatingDisplay;

	@FXML
	private TabPane tabPane;

	@FXML
	private ScrollPane scrollPaneGas;

	@FXML
	private VBox headerGas;

	@FXML
	private ScrollPane scrollPaneHeatPump;

	@FXML
	private VBox headerHeatPump;

	/**
     * Initializes the controller by retrieving gas and heating tariffs from the server and displaying them.
     */
	@FXML
	public void initialize() {
		gas = (ArrayList<Tariff>) StandardOutboundRequestHandler.makeTariffOutboundRequest("gas");
		heating = (ArrayList<Tariff>) StandardOutboundRequestHandler.makeTariffOutboundRequest("heatpump");

		if (gas == null || heating == null) {
			ExceptionPopup.showRefreshPopup(bundle.getString("signUpErrorTitle"), bundle.getString("signUpErrorText"));
			Platform.runLater(() -> {
				Stage stage = SignUpManager.getDialogStage();
				FXMLUtility fxmlUtility = new FXMLUtility(CURRENT_FXML_PATH, BUNDLE_NAME,stage);
				fxmlUtility.loadAndSetScene();
			});
		}

		displayTariffs();
	}


	/**
     * Handles the action when the "Next" button is clicked.
     *
     * @param event The ActionEvent triggered by clicking the "Next" button.
     */
	@FXML
	public void next(ActionEvent event) {
		boolean successOfRequest = false;
		boolean acceptTariff = false;
		try {
			if (isFirstTabSelected()) {
				acceptTariff = openConfirmationPopUp(null);
				successOfRequest = StandardOutboundRequestHandler.makeStandardUpdateRequest("{\"externalHeatingTariff\": true}", "https://request-handling.int.bad-walden-stadtwerke.com/user-data/");
			} else {
				int tabIndex = getSelectedTabIndex();
				if (tabIndex == 1) {
					tariff = gasDisplay.getSelectedTariff();
					checkTariffIsSelected();
					acceptTariff = openConfirmationPopUp(tariff);
					successOfRequest = StandardOutboundRequestHandler.makeTariffSelectionForUserOutboundRequest(tariff);
				} else if (tabIndex == 2) {
					tariff = heatingDisplay.getSelectedTariff();
					checkTariffIsSelected();
					acceptTariff = openConfirmationPopUp(tariff);
					successOfRequest = StandardOutboundRequestHandler.makeTariffSelectionForUserOutboundRequest(tariff);
				}
			}
			if (successOfRequest && acceptTariff) {
				loadNextStep(event);
			}
		} catch (IllegalArgumentException e) {
			ExceptionPopup.showErrorPopup(bundle.getString("signUpErrorTitle"), String.valueOf(e));
		}
	}

	public boolean openConfirmationPopUp(Tariff tariff) {
		TariffSelectionConfirmationPopUp tariffSelectionConfirmationPopUp = new TariffSelectionConfirmationPopUp(tariff);
		tariffSelectionConfirmationPopUp.show();
		return tariffSelectionConfirmationPopUp.getCheckboxValue();
	}

/**
     * Displays the gas and heating tariffs in their respective tariff table displays.
     */
	private void displayTariffs() {
		gasDisplay = new TariffTableDisplay(scrollPaneGas, gas, headerGas);
		heatingDisplay = new TariffTableDisplay(scrollPaneHeatPump, heating, headerHeatPump);
	}

/**
     * Checks if a tariff is selected before proceeding to the next step.
     * If no tariff is selected, it throws an IllegalArgumentException.
     */
	private void checkTariffIsSelected() {
		if (tariff == null) {
			throw new IllegalArgumentException(bundle.getString("signUpHeatingText"));
		}
	}

/**
     * Loads the next step of the sign-up process.
     *
     * @param event The ActionEvent triggered by clicking the "Next" button.
     */
	private void loadNextStep(ActionEvent event) {
		FXMLUtility fxmlUtility = new FXMLUtility(FXML_PATH, BUNDLE_NAME, event);
		fxmlUtility.loadAndSetScene();
	}

	/**
     * Checks if the first tab is selected.
     *
     * @return true if the first tab is selected, false otherwise.
     */
	private boolean isFirstTabSelected() {
		return getSelectedTabIndex() == 0;
	}

	/**
     * Gets the index of the currently selected tab.
     *
     * @return The index of the selected tab.
     */
	private int getSelectedTabIndex() {
		return tabPane.getSelectionModel().getSelectedIndex();
	}
}