package com.bad_walden_stadtwerke.controller.initialSignUp;

import com.bad_walden_stadtwerke.model.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.mock.MockActiveSession;
import com.bad_walden_stadtwerke.utility.FXMLUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class InitialSignUpControllerStep4 {
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