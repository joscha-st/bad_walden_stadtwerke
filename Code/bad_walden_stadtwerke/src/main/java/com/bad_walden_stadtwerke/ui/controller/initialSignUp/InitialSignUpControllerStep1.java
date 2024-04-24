package com.bad_walden_stadtwerke.ui.controller.initialSignUp;

import com.bad_walden_stadtwerke.logic.Customer;
import com.bad_walden_stadtwerke.ui.controller.FXMLUtility;
import com.bad_walden_stadtwerke.ui.controller.SignUpManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static com.bad_walden_stadtwerke.ui.components.errorHandling.ExceptionPopup.showErrorPopup;

//BillingAddressInput
public class InitialSignUpControllerStep1 {

    private static final String FXML_PATH = "/com/bad_walden_stadtwerke/initialSignUp/signup-dialog-2.fxml";
    private static final String BUNDLE_NAME = SignUpManager.BUNDLE_NAME;

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
        try{
            Customer newCustomer = new Customer(firstName.getText(), lastName.getText(), street.getText(), houseNumber.getText(), postalCode.getText(), city.getText());
        }catch(Exception e){
            showErrorPopup("Please Check your address details again.", String.valueOf(e));
            return;
        }

        FXMLUtility fxmlUtility = new FXMLUtility(FXML_PATH, BUNDLE_NAME, event);
        fxmlUtility.loadAndSetScene();
    }
}