package com.bad_walden_stadtwerke.ui.controller.initialSignUp;

import com.bad_walden_stadtwerke.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.sales.types.Tariff;
import com.bad_walden_stadtwerke.ui.controller.FXMLUtility;
import com.bad_walden_stadtwerke.ui.controller.LanguageController;
import com.bad_walden_stadtwerke.ui.controller.SignUpManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.bad_walden_stadtwerke.ui.components.errorHandling.ExceptionPopup.showErrorPopup;

public class InitialSignUpControllerStep3 {

    private static final String FXML_PATH = "/com/bad_walden_stadtwerke/initialSignUp/signup-dialog-4.fxml";
    private static final String BUNDLE_NAME = SignUpManager.BUNDLE_NAME;
    private final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());
    private Tariff tariff = null;
    private ArrayList<Tariff> gas = new ArrayList<>();
    private ArrayList<Tariff> heating = new ArrayList<>();
    private TariffDisplay gasDisplay;
    private TariffDisplay heatingDisplay;

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
        //TODO: remove mock later @joscha-st
        gas = (ArrayList<Tariff>) StandardOutboundRequestHandler.makeTariffOutboundRequest("gas");
        heating = (ArrayList<Tariff>) StandardOutboundRequestHandler.makeTariffOutboundRequest("heatpump");
        displayTariffs();
    }

    @FXML
    public void next(ActionEvent event) {
        if (isFirstTabSelected()) {
            loadNextStep(event);
            //TODO: implement logic to save "other" tariff @joscha-st
        } else {
            int tabIndex = getSelectedTabIndex();
            if (tabIndex == 1) {
                tariff = gasDisplay.getSelectedTariff();
                //TODO: implement logic to save gas tariff @joscha-st
                StandardOutboundRequestHandler.makeTariffSelectionForUserOutboundRequest(tariff);
            } else if (tabIndex == 2) {
                tariff = heatingDisplay.getSelectedTariff();
                //TODO: implement logic to save heating tariff @joscha-st
                StandardOutboundRequestHandler.makeTariffSelectionForUserOutboundRequest(tariff);
            }
            try {
                checkTariffIsSelected();
                loadNextStep(event);
            } catch (IllegalArgumentException e) {
                showErrorPopup(bundle.getString("signUpErrorTitle"), String.valueOf(e));
            }
        }
    }

    private void displayTariffs() {
        gasDisplay = new TariffDisplay(scrollPaneGas, gas, headerGas);
        heatingDisplay = new TariffDisplay(scrollPaneHeatPump, heating, headerHeatPump);
    }

    private void setupMock() {
        for (int i = 1; i < 20; i++) {
            gas.add(new Tariff(i, "Gas " + i, "Gas " + i + " Beschreibung Loremupsummmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm", 100 * i, "€", 12 * i, 1, "gas"));
        }

        for (int i = 1; i < 20; i++) {
            heating.add(new Tariff(i, "Heizung " + i, "Heizung " + i + " Beschreibung", 100 * i, "€", 12 * i, 1, "heating"));
        }
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