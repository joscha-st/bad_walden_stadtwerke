package com.bad_walden_stadtwerke.ui.controller.initialSignUp;

import com.bad_walden_stadtwerke.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.sales.types.Tariff;
import com.bad_walden_stadtwerke.ui.controller.FXMLUtility;
import com.bad_walden_stadtwerke.ui.controller.LanguageController;
import com.bad_walden_stadtwerke.ui.controller.SignUpManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.bad_walden_stadtwerke.ui.components.errorHandling.ExceptionPopup.showErrorPopup;

public class InitialSignUpControllerStep2 {

    private static final String FXML_PATH = "/com/bad_walden_stadtwerke/initialSignUp/signup-dialog-3.fxml";
    private static final String BUNDLE_NAME = SignUpManager.BUNDLE_NAME;
    private static final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());
    @FXML
    private VBox header;

    @FXML
    private ScrollPane scrollPane;

    private ArrayList<Tariff> electricity = new ArrayList<>();
    private TariffDisplay electricityDisplay;
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
        displayElectricityTariffs();
    }

    @FXML
    public void next(ActionEvent event) {
        selectedTariff = electricityDisplay.getSelectedTariff();
        try {
            checkSelectedTariff();
            //TODO: implement logic to save tariff @joscha-st
            StandardOutboundRequestHandler.makeTariffSelectionForUserOutboundRequest(selectedTariff);
            loadNextStep(event);
        } catch (IllegalArgumentException e) {
            showErrorPopup(bundle.getString("signUpErrorTitle"), String.valueOf(e));
        }
    }

    private void loadNextStep(ActionEvent event) {
        FXMLUtility fxmlUtility = new FXMLUtility(FXML_PATH, BUNDLE_NAME, event);
        fxmlUtility.loadAndSetScene();
    }

    private void checkSelectedTariff() {
        if (selectedTariff == null) {
            //check if checkbox is selected
            if (!checkboxElectricity.isSelected()) {
                throw new IllegalArgumentException(bundle.getString("signUpElectricityTariffText"));
            }
        }
    }

    private void setupElectricityMock() {
        for (int i = 1; i < 20; i++) {
            electricity.add(new Tariff(i, "Electricity " + i, "Electricity " + i + " Description", 100 * i, "€", 12 * i, 1, "electricity"));
        }
    }

    private void displayElectricityTariffs() {
        electricityDisplay = new TariffDisplay(scrollPaneElectricity, electricity, headerElectricity);
    }
}