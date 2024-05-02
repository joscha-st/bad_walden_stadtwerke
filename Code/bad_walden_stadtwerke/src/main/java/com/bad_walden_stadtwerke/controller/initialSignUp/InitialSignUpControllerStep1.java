package com.bad_walden_stadtwerke.controller.initialSignUp;

import com.bad_walden_stadtwerke.components.errorHandling.ExceptionPopup;
import com.bad_walden_stadtwerke.controller.language.LanguageController;
import com.bad_walden_stadtwerke.mock.MockHttpClient;
import com.bad_walden_stadtwerke.model.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.model.initialSignUp.SignUpManager;
import com.bad_walden_stadtwerke.model.types.billingAddress.BillingAddress;
import com.bad_walden_stadtwerke.utility.CentralLoggingUtility;
import com.bad_walden_stadtwerke.utility.FXMLUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ResourceBundle;

public class InitialSignUpControllerStep1 {

	private static final String FXML_PATH = "/com/bad_walden_stadtwerke/view/initialSignUp/signup-dialog-2.fxml";
	private static final String BUNDLE_NAME = SignUpManager.BUNDLE_NAME;
	private final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());

	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField street;
	@FXML
	private TextField houseNumber;
	@FXML
	private TextField postalCode;
	@FXML
	private TextField city;


	@FXML
	public void next(ActionEvent event) {
		boolean successOfRequest = false;
		try {
			BillingAddress billingAddress = new BillingAddress(firstName.getText(), lastName.getText(), street.getText(), houseNumber.getText(), postalCode.getText(), city.getText());
			doNotMockIssueOnPaul(billingAddress);
			successOfRequest = StandardOutboundRequestHandler.makeUpdateBillingAddressForUserOutboundRequest(billingAddress);
		} catch (Exception e) {
			CentralLoggingUtility.handleException("Controller", e);
			ExceptionPopup.showErrorPopup(bundle.getString("signUpErrorTitle"), String.valueOf(e));
			return;
		}
		if (successOfRequest){
		FXMLUtility fxmlUtility = new FXMLUtility(FXML_PATH, BUNDLE_NAME, event);
		fxmlUtility.loadAndSetScene();
		}
	}

	// This method is used to demonstrate the issue with the MockHttpClient remove for production
	private void doNotMockIssueOnPaul(BillingAddress billingAddress) {
		MockHttpClient.doMockIssues = true;
	}
}