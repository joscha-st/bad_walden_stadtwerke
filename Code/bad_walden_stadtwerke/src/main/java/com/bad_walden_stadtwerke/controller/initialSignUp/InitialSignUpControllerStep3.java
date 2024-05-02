package com.bad_walden_stadtwerke.controller.initialSignUp;

import com.bad_walden_stadtwerke.components.TariffSelectionConfirmationPopUp;
import com.bad_walden_stadtwerke.components.TariffTableDisplay;
import com.bad_walden_stadtwerke.components.errorHandling.ExceptionPopup;
import com.bad_walden_stadtwerke.controller.language.LanguageController;
import com.bad_walden_stadtwerke.model.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.model.initialSignUp.SignUpManager;
import com.bad_walden_stadtwerke.model.types.Tariff;
import com.bad_walden_stadtwerke.utility.FXMLUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class InitialSignUpControllerStep3 {

	private static final String FXML_PATH = "/com/bad_walden_stadtwerke/view/initialSignUp/signup-dialog-4.fxml";
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

	@FXML
	public void initialize() {
		gas = (ArrayList<Tariff>) StandardOutboundRequestHandler.makeTariffOutboundRequest("gas");
		heating = (ArrayList<Tariff>) StandardOutboundRequestHandler.makeTariffOutboundRequest("heatpump");
		displayTariffs();
	}

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

	private void displayTariffs() {
		gasDisplay = new TariffTableDisplay(scrollPaneGas, gas, headerGas);
		heatingDisplay = new TariffTableDisplay(scrollPaneHeatPump, heating, headerHeatPump);
	}


	private void checkTariffIsSelected() {
		if (tariff == null) {
			throw new IllegalArgumentException(bundle.getString("signUpHeatingText"));
		}
	}


	private void loadNextStep(ActionEvent event) {
		FXMLUtility fxmlUtility = new FXMLUtility(FXML_PATH, BUNDLE_NAME, event);
		fxmlUtility.loadAndSetScene();
	}

	private boolean isFirstTabSelected() {
		return getSelectedTabIndex() == 0;
	}

	private int getSelectedTabIndex() {
		return tabPane.getSelectionModel().getSelectedIndex();
	}
}