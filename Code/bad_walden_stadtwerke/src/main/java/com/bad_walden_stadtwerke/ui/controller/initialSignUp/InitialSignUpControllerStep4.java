package com.bad_walden_stadtwerke.ui.controller.initialSignUp;

import com.bad_walden_stadtwerke.ui.controller.FXMLUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class InitialSignUpControllerStep4 {
    @FXML
    public void close(ActionEvent event) {
        // Save that user has completed the initial sign up @joscha-st
        Stage stage = FXMLUtility.getStageFromEvent(event);
        stage.close();
    }
}