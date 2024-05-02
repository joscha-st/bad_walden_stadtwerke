/**
 * The InitialSignUpControllerStep4 class is the controller responsible for handling the final step of the initial sign-up process in the application.
 * It manages the user interface elements and actions related to closing the sign-up dialog after the process is completed.
 * <p>
 * This controller interacts with the {@link com.bad_walden_stadtwerke.model.communication.StandardOutboundRequestHandler} for sending a request
 * to mark the initial sign-up as complete, the {@link com.bad_walden_stadtwerke.mock.MockActiveSession} for refreshing the current session data,
 * and the {@link com.bad_walden_stadtwerke.utility.FXMLUtility} for accessing the stage to close the dialog window.
 * </p>
 * <p>
 * When the "Close" button is clicked, it sends a request to the server to mark the initial sign-up as complete. If the request is successful,
 * it closes the sign-up dialog window. It also refreshes the current session data from the server using MockActiveSession.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
package com.bad_walden_stadtwerke.controller.initialSignUp;

import com.bad_walden_stadtwerke.model.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.mock.MockActiveSession;
import com.bad_walden_stadtwerke.utility.FXMLUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class InitialSignUpControllerStep4 {


	/**
     * Handles the action when the "Close" button is clicked.
     *
     * @param event The ActionEvent triggered by clicking the "Close" button.
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