package com.bad_walden_stadtwerke.ui.controller.initialSignUp;

import com.bad_walden_stadtwerke.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.logic.BillingAddress;
import com.bad_walden_stadtwerke.sales.types.Tariff;
import com.bad_walden_stadtwerke.ui.controller.FXMLUtility;
import com.bad_walden_stadtwerke.ui.controller.LanguageChangeObserver;
import com.bad_walden_stadtwerke.ui.controller.LanguageController;
import com.bad_walden_stadtwerke.ui.controller.SignUpManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.bad_walden_stadtwerke.ui.components.errorHandling.ExceptionPopup.showErrorPopup;

public class InitialSignUpControllerStep1  {

    private static final String FXML_PATH = "/com/bad_walden_stadtwerke/initialSignUp/signup-dialog-2.fxml";
    private static final String BUNDLE_NAME = SignUpManager.BUNDLE_NAME;
    private ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());
    private ArrayList<Tariff> water = new ArrayList<>();

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
    public void initialize() {
        water = (ArrayList<Tariff>) StandardOutboundRequestHandler.makeTariffOutboundRequest("electricity");
    }

    @FXML
    public void next(ActionEvent event) {
        try{
            BillingAddress billingAddress = new BillingAddress(firstName.getText(), lastName.getText(), street.getText(), houseNumber.getText(), postalCode.getText(), city.getText());
            StandardOutboundRequestHandler.makeUpdateBillingAddressForUserOutboundRequest(billingAddress);
        }catch(Exception e){
            showErrorPopup(bundle.getString("signUpErrorTitle"), String.valueOf(e));
            return;
        }

        FXMLUtility fxmlUtility = new FXMLUtility(FXML_PATH, BUNDLE_NAME, event);
        fxmlUtility.loadAndSetScene();
    }
}