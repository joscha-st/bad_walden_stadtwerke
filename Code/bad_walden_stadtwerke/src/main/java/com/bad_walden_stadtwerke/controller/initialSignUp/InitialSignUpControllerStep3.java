/**
 * This package manages the controllers involved in the initial sign-up process, specifically handling
 * the selection of gas and heat pump tariffs in the third step.
 */
package com.bad_walden_stadtwerke.controller.initialSignUp;

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

/**
 * Controls the logic for selecting gas and heat pump tariffs as part of the user's sign-up process.
 * The controller handles tab selection and tariff display, and progresses the user to the next step upon selection.
 */
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

    /**
     * Initializes the controller by loading available gas and heat pump tariffs and setting up the display.
     */
    @FXML
    public void initialize() {
        gas = (ArrayList<Tariff>) StandardOutboundRequestHandler.makeTariffOutboundRequest("gas");
        heating = (ArrayList<Tariff>) StandardOutboundRequestHandler.makeTariffOutboundRequest("heatpump");
        displayTariffs();
    }

    /**
     * Advances the user to the next step of the sign-up process, handling tariff selection validation.
     *
     * @param event The event triggered by clicking the 'Next' button.
     */
    @FXML
    public void next(ActionEvent event) {
        boolean successOfRequest = false;
        try {
            if (isFirstTabSelected()) {
                successOfRequest = StandardOutboundRequestHandler.makeStandardUpdateRequest("{\"externalHeatingTariff\": true}", "https://request-handling.int.bad-walden-stadtwerke.com/user-data/");
            } else {
                int tabIndex = getSelectedTabIndex();
                if (tabIndex == 1) {
                    tariff = gasDisplay.getSelectedTariff();
                    checkTariffIsSelected();
                    successOfRequest = StandardOutboundRequestHandler.makeTariffSelectionForUserOutboundRequest(tariff);
                } else if (tabIndex == 2) {
                    tariff = heatingDisplay.getSelectedTariff();
                    checkTariffIsSelected();
                    successOfRequest = StandardOutboundRequestHandler.makeTariffSelectionForUserOutboundRequest(tariff);
                }
            }
            if (successOfRequest){
                loadNextStep(event);
            }
        } catch (IllegalArgumentException e) {
            ExceptionPopup.showErrorPopup(bundle.getString("signUpErrorTitle"), String.valueOf(e));
        }
    }

    /**
     * Displays the tariffs for gas and heat pump in their respective tab panels.
     */
    private void displayTariffs() {
        gasDisplay = new TariffTableDisplay(scrollPaneGas, gas, headerGas);
        heatingDisplay = new TariffTableDisplay(scrollPaneHeatPump, heating, headerHeatPump);
    }

    /**
     * Validates that a tariff has been selected before proceeding.
     * Throws IllegalArgumentException if no tariff is selected.
     */
    private void checkTariffIsSelected() {
        if (tariff == null) {
            throw new IllegalArgumentException(bundle.getString("signUpHeatingText"));
        }
    }

    /**
     * Loads the next step in the sign-up process.
     *
     * @param event The event object associated with the action.
     */
    private void loadNextStep(ActionEvent event) {
        FXMLUtility fxmlUtility = new FXMLUtility(FXML_PATH, BUNDLE_NAME, event);
        fxmlUtility.loadAndSetScene();
    }

    /**
     * Determines if the first tab is selected, typically indicating a specific condition or choice.
     *
     * @return true if the first tab is selected, false otherwise.
     */
    private boolean isFirstTabSelected() {
        return getSelectedTabIndex() == 0;
    }

    /**
     * Retrieves the index of the currently selected tab.
     *
     * @return The index of the selected tab.
     */
    private int getSelectedTabIndex() {
        return tabPane.getSelectionModel().getSelectedIndex();
    }
}
