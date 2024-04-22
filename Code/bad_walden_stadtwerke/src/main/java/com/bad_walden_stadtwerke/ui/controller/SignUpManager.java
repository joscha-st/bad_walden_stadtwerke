package com.bad_walden_stadtwerke.ui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ResourceBundle;

public class SignUpManager {

    private static final String SIGN_UP_DIALOG_PATH = "/com/bad_walden_stadtwerke/initialSignUp/signup-dialog-0.fxml";
    public static final String BUNDLE_NAME = "Bundle";
    private static final int SCENE_WIDTH = 1000;
    private static final int SCENE_HEIGHT = 550;

    private final Stage dialogStage;

    public SignUpManager() {
        dialogStage = new Stage();
        initializeDialogStage();
    }

    private void initializeDialogStage() {
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setTitle("Sign Up");
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(SIGN_UP_DIALOG_PATH));
        fxmlLoader.setResources(ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage()));
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean hasUserSignedUp() {
        //TODO: Replace with the user's signup status @joscha-st from mock
        return false;
    }
}