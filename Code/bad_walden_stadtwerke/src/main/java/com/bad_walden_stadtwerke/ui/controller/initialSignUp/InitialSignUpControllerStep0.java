package com.bad_walden_stadtwerke.ui.controller.initialSignUp;

import com.bad_walden_stadtwerke.mock.MockActiveSession;
import com.bad_walden_stadtwerke.ui.controller.FXMLUtility;
import com.bad_walden_stadtwerke.ui.controller.LanguageController;
import com.bad_walden_stadtwerke.ui.controller.SignUpManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import java.util.Locale;
import java.util.ResourceBundle;

public class InitialSignUpControllerStep0 {

    private static final String SIGN_UP_DIALOG_PATH = "/com/bad_walden_stadtwerke/initialSignUp/signup-dialog-1.fxml";
    private static final String BUNDLE_NAME = SignUpManager.BUNDLE_NAME;

    @FXML
    private javafx.scene.control.Label signUpWelcomeLabel;

    @FXML
    private javafx.scene.control.Button changeLanguageToGermanButton;

    @FXML
    private javafx.scene.control.Button changeLanguageToEnglishButton;

    @FXML
    private javafx.scene.control.Button signUpNextButton;

    @FXML
    private javafx.scene.control.Label signUpWelcomeText;
    @FXML
    private javafx.scene.control.Label signUpWelcomeText2;

    @FXML
    private javafx.scene.control.Label signUpHeadlineLabel;

    @FXML
    private javafx.scene.control.Label signUpLanguageLabel;

    private ResourceBundle messages;

    @FXML
    public void initialize() {
        refreshUI();
    }

    @FXML
    public void changeLanguageToGerman(ActionEvent event) {
        LanguageController.setLanguage(Locale.GERMAN);
        refreshUI();
    }

    @FXML
    public void changeLanguageToEnglish(ActionEvent event) {
        LanguageController.setLanguage(Locale.ENGLISH);
        refreshUI();
    }

    private void refreshUI() {
        messages = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());
        updateTexts();
    }

    private void updateTexts() {
        Platform.runLater(() -> {
            String welcomeText = messages.getString("signUpWelcomeLabel");
            String formattedWelcomeText = welcomeText.replace("{0}",  MockActiveSession.getActiveUser().getFriendlyDisplayName());
            signUpWelcomeLabel.setText(formattedWelcomeText);
            signUpWelcomeText.setText(messages.getString("signUpWelcomeText"));
            signUpWelcomeText2.setText(messages.getString("signUpWelcomeText2"));
            signUpHeadlineLabel.setText(messages.getString("signUpHeadlineLabel"));
            signUpNextButton.setText(messages.getString("signUpNextButton"));
            changeLanguageToGermanButton.setText(messages.getString("languageGerman"));
            changeLanguageToEnglishButton.setText(messages.getString("languageEnglish"));
            signUpLanguageLabel.setText(messages.getString("signUpLanguageLabel"));
        });
    }

    @FXML
    public void next(ActionEvent event) {
        FXMLUtility fxmlUtility = new FXMLUtility(SIGN_UP_DIALOG_PATH, BUNDLE_NAME, event);
        fxmlUtility.loadAndSetScene();
    }
}