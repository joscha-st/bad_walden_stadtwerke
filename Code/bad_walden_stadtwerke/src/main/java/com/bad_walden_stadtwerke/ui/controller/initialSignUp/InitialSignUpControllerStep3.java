package com.bad_walden_stadtwerke.ui.controller.initialSignUp;

import com.bad_walden_stadtwerke.ui.controller.FXMLUtility;
import com.bad_walden_stadtwerke.ui.controller.SignUpManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InitialSignUpControllerStep3 {

    private static final String FXML_PATH = "/com/bad_walden_stadtwerke/initialSignUp/signup-dialog-4.fxml";
    private static final String BUNDLE_NAME = SignUpManager.BUNDLE_NAME;

    @FXML
    public void next(ActionEvent event) {
        FXMLUtility fxmlUtility = new FXMLUtility(FXML_PATH, BUNDLE_NAME, event);
        fxmlUtility.loadAndSetScene();
    }
}