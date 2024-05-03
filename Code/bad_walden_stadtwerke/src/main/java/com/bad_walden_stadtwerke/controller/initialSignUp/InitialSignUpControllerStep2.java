/**
 * The InitialSignUpControllerStep2 class is the controller responsible for handling the initial sign-up step 2 in the application.
 * It manages the user interface elements and actions related to selecting electricity tariffs and navigating to the next step.
 * <p>
 * This controller interacts with the {@link com.bad_walden_stadtwerke.components.TariffTableDisplay} to display electricity tariffs,
 * the {@link com.bad_walden_stadtwerke.model.communication.StandardOutboundRequestHandler} for sending tariff selection information to the backend,
 * the {@link com.bad_walden_stadtwerke.controller.language.LanguageController} for language localization, and the
 * {@link com.bad_walden_stadtwerke.utility.FXMLUtility} for loading FXML files.
 * </p>
 * <p>
 * It initializes by retrieving electricity tariffs from the server and displays them using the {@link com.bad_walden_stadtwerke.components.TariffTableDisplay}.
 * </p>
 * <p>
 * It allows the user to either select an electricity tariff or opt for an external heating tariff by clicking the corresponding checkboxes.
 * When the "Next" button is clicked, it validates the selected option and sends the relevant information to the server.
 * In case of any exceptions during the process, it displays an error popup using {@link com.bad_walden_stadtwerke.components.errorHandling.ExceptionPopup}.
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
import com.bad_walden_stadtwerke.utility.CentralLoggingUtility;
import com.bad_walden_stadtwerke.utility.FXMLUtility;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.ResourceBundle;


public class InitialSignUpControllerStep2 {

	private static final String FXML_PATH = "/com/bad_walden_stadtwerke/view/initialSignUp/signup-dialog-3.fxml";
	private static final String CURRENT_FXML_PATH = "/com/bad_walden_stadtwerke/view/initialSignUp/signup-dialog-2.fxml";
	private static final String BUNDLE_NAME = SignUpManager.BUNDLE_NAME;
	private static final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());

	private ArrayList<Tariff> electricity = new ArrayList<>();
	private TariffTableDisplay electricityDisplay;
	private Tariff selectedTariff;


	@FXML
	private ScrollPane scrollPaneElectricity;

	@FXML
	private VBox headerElectricity;

	@FXML
	private CheckBox checkboxElectricity;

	/**
     * Initializes the controller by retrieving electricity tariffs from the server and displaying them.
     */
	@FXML
	public void initialize() {
		electricity = (ArrayList<Tariff>) StandardOutboundRequestHandler.makeTariffOutboundRequest("electricity");
		if (electricity != null) {
			displayElectricityTariffs();
		}
		if (electricity == null) {
			ExceptionPopup.showRefreshPopup(bundle.getString("signUpErrorTitle"), bundle.getString("signUpErrorText"));
			Platform.runLater(() -> {
				Stage stage = SignUpManager.getDialogStage();
				FXMLUtility fxmlUtility = new FXMLUtility(CURRENT_FXML_PATH, BUNDLE_NAME, stage);
				fxmlUtility.loadAndSetScene();
			});
		}
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
		if (checkboxElectricity.isSelected()) {
			acceptTariff = openConfirmationPopUp(null);
			if (!acceptTariff) {
				return;
			}
			successOfRequest = StandardOutboundRequestHandler.makeStandardUpdateRequest("{\"externalHeatingTariff\": true}", "https://request-handling.int.bad-walden-stadtwerke.com/user-data/");
		} else {
			selectedTariff = electricityDisplay.getSelectedTariff();
			try {
				checkSelectedTariff();
				acceptTariff = openConfirmationPopUp(selectedTariff);
				if (!acceptTariff) {
					return;
				}
				successOfRequest = StandardOutboundRequestHandler.makeTariffSelectionForUserOutboundRequest(selectedTariff);
			} catch (IllegalArgumentException e) {
				ExceptionPopup.showErrorPopup(bundle.getString("signUpErrorTitle"), String.valueOf(e));
				CentralLoggingUtility.handleException("Controller", e);
			}
		}
		if (successOfRequest) {
			loadNextStep(event);
		}
	}

	public boolean openConfirmationPopUp(Tariff tariff) {
		TariffSelectionConfirmationPopUp tariffSelectionConfirmationPopUp = new TariffSelectionConfirmationPopUp(tariff);
		tariffSelectionConfirmationPopUp.show();
		return tariffSelectionConfirmationPopUp.getCheckboxValue();
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
     * Checks if a tariff is selected before proceeding to the next step.
     * If no tariff is selected and the electricity checkbox is not selected, it throws an IllegalArgumentException.
     */
	private void checkSelectedTariff() {
		if (selectedTariff == null) {
			if (!checkboxElectricity.isSelected()) {
				throw new IllegalArgumentException(bundle.getString("signUpElectricityTariffText"));
			}
		}
	}

	/**
     * Displays the electricity tariffs in the tariff table display.
     */
	private void displayElectricityTariffs() {
		electricityDisplay = new TariffTableDisplay(scrollPaneElectricity, electricity, headerElectricity);
	}

	/**
     * Handles the action when the electricity checkbox is clicked.
     * If the checkbox is selected, it hides the select button for electricity tariffs.
     * If the checkbox is deselected, it shows the select button for electricity tariffs.
     */
	@FXML
	public void onElectricityCheckBoxClicked() {
		if (checkboxElectricity.isSelected()) {
			electricityDisplay.setHideSelectButton(true);
		} else {
			electricityDisplay.setHideSelectButton(false);
		}
	}
}