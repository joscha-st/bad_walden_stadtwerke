/**
 * This package contains controllers that manage the user sign-up workflow. The {@code InitialSignUpControllerStep4}
 * class handles the finalization of the sign-up process, ensuring all data is updated before closing the sign-up window.
 */
package com.bad_walden_stadtwerke.controller.initialSignUp;

import com.bad_walden_stadtwerke.model.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.mock.MockActiveSession;
import com.bad_walden_stadtwerke.utility.FXMLUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Controls the closure of the sign-up process, ensuring all user data is finalized and the session is updated.
 */
public class InitialSignUpControllerStep4 {

    /**
     * Handles the closure of the sign-up process. This method is called when the user completes the initial sign-up steps.
     * It sends a final update to the server to mark the sign-up as complete and refreshes the user's session data.
     *
     * @param event The event triggered by the user action to close the sign-up process.
     */
    @FXML
    public void close(ActionEvent event) {
        boolean successOfRequest = StandardOutboundRequestHandler.makeStandardUpdateRequest("{\"initialSignUpComplete\": true}", "https://request-handling.int.bad-walden-stadtwerke.com/user-data/");
        MockActiveSession.refreshCurrentSessionDataFromWebServer();
        if (successOfRequest){
            Stage stage = FXMLUtility.getStageFromEvent(event);
            stage.close();
        }
    }
}
