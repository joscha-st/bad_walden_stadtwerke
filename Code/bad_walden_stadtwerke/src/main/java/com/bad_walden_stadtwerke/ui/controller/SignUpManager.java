package com.bad_walden_stadtwerke.ui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import com.bad_walden_stadtwerke.mock.MockActiveSession;

public class SignUpManager implements LanguageChangeObserver{

    private static final String SIGN_UP_DIALOG_PATH = "/com/bad_walden_stadtwerke/initialSignUp/signup-dialog-0.fxml";
    public static final String BUNDLE_NAME = "Bundle";
    private static final int SCENE_WIDTH = 1000;
    private static final int SCENE_HEIGHT = 550;

    private final Stage dialogStage;
    private final FXMLLoader fxmlLoader;
    private final LanguageController languageController;
    private ResourceBundle bundle;

    public SignUpManager(Stage stage, FXMLLoader fxmlLoader, LanguageController languageController) {
        LanguageController.addObserver(this);
        this.dialogStage = stage;
        this.fxmlLoader = fxmlLoader;
        this.languageController = languageController;
        bundle = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());
        fxmlLoader.setResources(ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage()));
        initializeDialogStage();
    }

    private void initializeDialogStage() {
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setTitle(bundle.getString("signUpPopUpTitle"));
    }

    public void checkAndOpenForSignUp() {
        if (!hasUserSignedUp()) {
            openSignUpDialog();
        }
    }



    private void openSignUpDialog() {
        Parent dialogRoot = loadFXML();
        if (dialogRoot != null) {
            Scene dialogScene = new Scene(dialogRoot, SCENE_WIDTH, SCENE_HEIGHT);
            dialogStage.setScene(dialogScene);
            dialogStage.showAndWait();
            dialogStage.requestFocus();
        }
    }

    private Parent loadFXML() {
        fxmlLoader.setLocation(getClass().getResource(SIGN_UP_DIALOG_PATH));
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean hasUserSignedUp() {
        return MockActiveSession.getActiveUser().getHasDoneInitialSignUp();
    }

    @Override
    public void onLanguageChange(Locale newLocale) {
        fxmlLoader.setResources(ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage()));
        bundle = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());
        dialogStage.setTitle(bundle.getString("signUpPopUpTitle"));
    }
}