/**
 * Handles the second step of the initial sign-up process in the application, where users select their desired
 * electricity tariffs.
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller for handling the electricity tariff selection during the initial sign-up process.
 * This class manages the display of tariffs and interaction with user selections.
 */
public class InitialSignUpControllerStep2 {

    private static final String FXML_PATH = "/com/bad_walden_stadtwerke/view/initialSignUp/signup-dialog-3.fxml";
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
     * Initializes the controller by fetching available electricity tariffs and displaying them.
     */
    @FXML
    public void initialize() {
        electricity = (ArrayList<Tariff>) StandardOutboundRequestHandler.makeTariffOutboundRequest("electricity");
        if (electricity != null) {
            displayElectricityTariffs();
        }
    }

    /**
     * Processes the selection of tariffs and moves the user to the next step of the sign-up process.
     *
     * @param event The event triggered by clicking the 'Next' button.
     */
    @FXML
    public void next(ActionEvent event) {
        boolean successOfRequest = false;
        if (checkboxElectricity.isSelected()){
            successOfRequest = StandardOutboundRequestHandler.makeStandardUpdateRequest("{\"externalHeatingTariff\": true}", "https://request-handling.int.bad-walden-stadtwerke.com/user-data/");
        } else {
            selectedTariff = electricityDisplay.getSelectedTariff();
            try {
                checkSelectedTariff();
                successOfRequest = StandardOutboundRequestHandler.makeTariffSelectionForUserOutboundRequest(selectedTariff);
            } catch (IllegalArgumentException e) {
                ExceptionPopup.showErrorPopup(bundle.getString("signUpErrorTitle"), String.valueOf(e));
            }
        }
        if (successOfRequest){
            loadNextStep(event);
        }
    }

    /**
     * Loads the next step in the sign-up dialog sequence.
     *
     * @param event The event object associated with the action.
     */
    private void loadNextStep(ActionEvent event) {
        FXMLUtility fxmlUtility = new FXMLUtility(FXML_PATH, BUNDLE_NAME, event);
        fxmlUtility.loadAndSetScene();
    }

    /**
     * Validates the selected tariff. Throws IllegalArgumentException if no tariff is selected
     * and the external heating checkbox is not checked.
     */
    private void checkSelectedTariff() {
        if (selectedTariff == null) {
            if (!checkboxElectricity.isSelected()) {
                throw new IllegalArgumentException(bundle.getString("signUpElectricityTariffText"));
            }
        }
    }

    /**
     * Displays the available electricity tariffs within a scrollable view.
     */
    private void displayElectricityTariffs() {
        electricityDisplay = new TariffTableDisplay(scrollPaneElectricity, electricity, headerElectricity);
    }

    /**
     * Handles the click event on the electricity checkbox. Enables or disables the selection button
     * based on whether the checkbox is checked.
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
