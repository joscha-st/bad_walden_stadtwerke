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

	@FXML
	public void initialize() {
		electricity = (ArrayList<Tariff>) StandardOutboundRequestHandler.makeTariffOutboundRequest("electricity");
		if (electricity != null) {
			displayElectricityTariffs();
		}
		if (electricity == null) {
			ExceptionPopup.showRefreshPopup(bundle.getString("signUpErrorTitle"), bundle.getString("signUpErrorText"));
			Platform.runLater(() -> {
				FXMLUtility fxmlUtility = new FXMLUtility(CURRENT_FXML_PATH, BUNDLE_NAME, (Stage) checkboxElectricity.getScene().getWindow());
				fxmlUtility.loadAndSetScene();
			});
		}
	}

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

	private void loadNextStep(ActionEvent event) {
		FXMLUtility fxmlUtility = new FXMLUtility(FXML_PATH, BUNDLE_NAME, event);
		fxmlUtility.loadAndSetScene();
	}

	private void checkSelectedTariff() {
		if (selectedTariff == null) {
			if (!checkboxElectricity.isSelected()) {
				throw new IllegalArgumentException(bundle.getString("signUpElectricityTariffText"));
			}
		}
	}

	private void displayElectricityTariffs() {
		electricityDisplay = new TariffTableDisplay(scrollPaneElectricity, electricity, headerElectricity);
	}

	@FXML
	public void onElectricityCheckBoxClicked() {
		if (checkboxElectricity.isSelected()) {
			electricityDisplay.setHideSelectButton(true);
		} else {
			electricityDisplay.setHideSelectButton(false);
		}
	}
}