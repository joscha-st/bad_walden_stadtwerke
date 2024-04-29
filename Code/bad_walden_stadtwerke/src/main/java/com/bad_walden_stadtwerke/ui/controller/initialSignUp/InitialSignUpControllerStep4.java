package com.bad_walden_stadtwerke.ui.controller.initialSignUp;

import com.bad_walden_stadtwerke.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.mock.MockActiveSession;
import com.bad_walden_stadtwerke.ui.controller.FXMLUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class InitialSignUpControllerStep4 {
	@FXML
	public void close(ActionEvent event) {
		StandardOutboundRequestHandler.makeStandardPostOutboundRequest("{\"initialSignUpComplete\": true}", "https://request-handling.int.bad-walden-stadtwerke.com/user-data/");
		MockActiveSession.refreshCurrentSessionDataFromWebServer();
		Stage stage = FXMLUtility.getStageFromEvent(event);
		stage.close();
	}
}