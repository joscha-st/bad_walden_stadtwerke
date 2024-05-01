/**
 * This package contains controllers related to the initial sign-up process in the application.
 * Specifically, the {@code InitialSignUpControllerStep0} class orchestrates the first step of the sign-up
 * process, managing the UI state based on user interactions and language preferences.
 */
package com.bad_walden_stadtwerke.controller.initialSignUp;

import com.bad_walden_stadtwerke.model.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.mock.MockActiveSession;
import com.bad_walden_stadtwerke.model.types.Tariff;
import com.bad_walden_stadtwerke.utility.FXMLUtility;
import com.bad_walden_stadtwerke.controller.language.LanguageController;
import com.bad_walden_stadtwerke.model.initialSignUp.SignUpManager;
import com.bad_walden_stadtwerke.components.TariffTableDisplay;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controls the initial stage of the sign-up process, handling language changes and displaying tariff information.
 */
public class InitialSignUpControllerStep0 {

    private static final String SIGN_UP_DIALOG_PATH = "/com/bad_walden_stadtwerke/view/initialSignUp/signup-dialog-1.fxml";
    private static final String BUNDLE_NAME = SignUpManager.BUNDLE_NAME;
    private ArrayList<Tariff> water = new ArrayList<>();
    private TariffTableDisplay waterDisplay;

    @FXML
    private Label signUpWelcomeLabel;
    @FXML
    private Button changeLanguageToGermanButton;
    @FXML
    private Button changeLanguageToEnglishButton;
    @FXML
    private Button signUpNextButton;
    @FXML
    private Label signUpWelcomeText;
    @FXML
    private Label signUpWelcomeText2;
    @FXML
    private Label signUpHeadlineLabel;
    @FXML
    private Label signUpLanguageLabel;
    @FXML
    private VBox headerWater;
    @FXML
    private ScrollPane scrollPaneWater;

    private ResourceBundle messages;

    /**
     * Initializes the controller. This method sets up the UI state based on the current language and
     * loads the initial tariff data.
     */
    @FXML
    public void initialize() {
        refreshUI();
    }

    /**
     * Handles the event to change the application language to German.
     */
    @FXML
    public void changeLanguageToGerman() {
        LanguageController.setLanguage(Locale.GERMAN);
        refreshUI();
    }

    /**
     * Handles the event to change the application language to English.
     */
    @FXML
    public void changeLanguageToEnglish() {
        LanguageController.setLanguage(Locale.ENGLISH);
        refreshUI();
    }

    /**
     * Refreshes the user interface elements based on the current language settings and re-displays the tariffs.
     */
    private void refreshUI() {
        messages = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());
        updateTexts();
    }

    /**
     * Displays the tariffs available for water services by requesting them from a backend service.
     */
    private void displayTariffs() {
        water = (ArrayList<Tariff>) StandardOutboundRequestHandler.makeTariffOutboundRequest("water");
        if (water != null) {
            waterDisplay = new TariffTableDisplay(scrollPaneWater, water, headerWater, true);
        }
    }

    /**
     * Updates the text elements of the UI to reflect the selected language and the active user's information.
     */
    private void updateTexts() {
        Platform.runLater(() -> {
            String welcomeText = messages.getString("signUpWelcomeLabel");
            String formattedWelcomeText = welcomeText.replace("{0}", MockActiveSession.getActiveUser().getFriendlyDisplayName());
            signUpWelcomeLabel.setText(formattedWelcomeText);
            signUpWelcomeText.setText(messages.getString("signUpWelcomeText"));
            signUpWelcomeText2.setText(messages.getString("signUpWelcomeText2"));
            signUpHeadlineLabel.setText(messages.getString("signUpHeadlineLabel"));
            signUpNextButton.setText(messages.getString("signUpNextButton"));
            changeLanguageToGermanButton.setText(messages.getString("languageGerman"));
            changeLanguageToEnglishButton.setText(messages.getString("languageEnglish"));
            signUpLanguageLabel.setText(messages.getString("signUpLanguageLabel"));
            displayTariffs();
        });
    }

    /**
     * Advances the user to the next step of the sign-up process when the next button is clicked.
     *
     * @param event The event triggered by clicking the 'Next' button.
     */
    @FXML
    public void next(ActionEvent event) {
        FXMLUtility fxmlUtility = new FXMLUtility(SIGN_UP_DIALOG_PATH, BUNDLE_NAME, event);
        fxmlUtility.loadAndSetScene();
    }
}
