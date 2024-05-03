/**
 * The InitialSignUpControllerStep1 class is the controller responsible for handling the initial sign-up step 1 in the application.
 * It manages the user interface elements and actions related to providing billing address information and navigating to the next step.
 * <p>
 * This controller interacts with the {@link com.bad_walden_stadtwerke.model.communication.StandardOutboundRequestHandler}
 * for sending billing address information to the backend, the {@link com.bad_walden_stadtwerke.controller.language.LanguageController}
 * for language localization, and the {@link com.bad_walden_stadtwerke.utility.FXMLUtility} for loading FXML files.
 * </p>
 * <p>
 * It validates and sends the billing address information to the server when the user clicks the "Next" button.
 * In case of any exceptions during the process, it displays an error popup using {@link com.bad_walden_stadtwerke.components.errorHandling.ExceptionPopup}.
 * </p>
 * <p>
 * This class supports internationalization through resource bundles to retrieve localized messages.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
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

 /**
     * Handles the action when the "Next" button is clicked.
     *
     * @param event The ActionEvent triggered by clicking the "Next" button.
     */
	@FXML
	public void next(ActionEvent event) {
		boolean successOfRequest = false;
		try {
			BillingAddress billingAddress = new BillingAddress(firstName.getText(), lastName.getText(), street.getText(), houseNumber.getText(), postalCode.getText(), city.getText());
			successOfRequest = StandardOutboundRequestHandler.makeUpdateBillingAddressForUserOutboundRequest(billingAddress);
		} catch (Exception e) {
			CentralLoggingUtility.handleException("Controller", e);
			ExceptionPopup.showErrorPopup(bundle.getString("signUpErrorTitle"), String.valueOf(e));
			return;
		}
		if (successOfRequest) {
			FXMLUtility fxmlUtility = new FXMLUtility(FXML_PATH, BUNDLE_NAME, event);
			fxmlUtility.loadAndSetScene();
		}
	}
}