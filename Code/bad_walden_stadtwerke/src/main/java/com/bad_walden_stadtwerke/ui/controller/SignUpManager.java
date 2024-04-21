package com.bad_walden_stadtwerke.ui.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class SignUpManager {

    private Stage dialogStage;

    public SignUpManager() {
        this.dialogStage = new Stage();
        this.dialogStage.initModality(Modality.APPLICATION_MODAL);
        // hide the native window bar
        this.dialogStage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        this.dialogStage.setTitle("Sign Up");
    }

    public void checkAndOpenForSignUp() {
        if (!hasUserSignedUp()) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/bad_walden_stadtwerke/initialSignUp/signup-dialog-0.fxml"));
            Parent dialogRoot = null;
            try {
                dialogRoot = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert dialogRoot != null;
            Scene dialogScene = new Scene(dialogRoot, 1000, 550);
            dialogStage.setScene(dialogScene);

            dialogStage.showAndWait();
            dialogStage.requestFocus();
        }
    }

    private boolean hasUserSignedUp() {
        return false;
    }
}