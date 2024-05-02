/**
 * The InitialSignUpControllerStep0 class is the controller responsible for handling the initial sign-up step 0 in the application.
 * It manages the user interface elements and actions related to selecting tariffs and changing the language.
 * <p>
 * This controller interacts with the {@link com.bad_walden_stadtwerke.model.communication.StandardOutboundRequestHandler}
 * for retrieving tariff information, the {@link com.bad_walden_stadtwerke.mock.MockActiveSession} for managing user sessions,
 * the {@link com.bad_walden_stadtwerke.utility.FXMLUtility} for loading FXML files, and the
 * {@link com.bad_walden_stadtwerke.controller.language.LanguageController} for language localization.
 * </p>
 * <p>
 * It displays tariff information in a table format using the {@link com.bad_walden_stadtwerke.components.TariffTableDisplay}.
 * The controller also handles language change events and navigates to the next sign-up step.
 * </p>
 * <p>
 * This class supports internationalization through resource bundles to retrieve localized messages.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
package com.bad_walden_stadtwerke.controller.initialSignUp;

import com.bad_walden_stadtwerke.model.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.mock.MockActiveSession;
import com.bad_walden_stadtwerke.model.types.Tariff;
import com.bad_walden_stadtwerke.utility.FXMLUtility;
import com.bad_walden_stadtwerke.controller.language.LanguageController;
import com.bad_walden_stadtwerke.model.initialSignUp.SignUpManager;
import com.bad_walden_stadtwerke.components.TariffTableDisplay;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class InitialSignUpControllerStep0 {

	private static final String SIGN_UP_DIALOG_PATH = "/com/bad_walden_stadtwerke/view/initialSignUp/signup-dialog-1.fxml";
	private static final String BUNDLE_NAME = SignUpManager.BUNDLE_NAME;
	private ArrayList<Tariff> water = new ArrayList<>();
	private TariffTableDisplay waterDisplay;


	@FXML
	private Label signUpWelcomeLabel;

	@FXML
	private Button changeLanguageToGermanButton;

	@FXML
	private Button changeLanguageToEnglishButton;

	@FXML
	private Button signUpNextButton;

	@FXML
	private Label signUpWelcomeText;
	@FXML
	private Label signUpWelcomeText2;

	@FXML
	private Label signUpHeadlineLabel;

	@FXML
	private Label signUpLanguageLabel;

	@FXML
	private VBox headerWater;

	@FXML
	private ScrollPane scrollPaneWater;


	private ResourceBundle messages;


	/**
     * Initializes the controller.
     */
	@FXML
	public void initialize() {
		refreshUI();
	}

	 /**
     * Changes the language to German.
     */
	@FXML
	public void changeLanguageToGerman() {
		LanguageController.setLanguage(Locale.GERMAN);
		refreshUI();
	}

	/**
     * Changes the language to English.
     */
	@FXML
	public void changeLanguageToEnglish() {
		LanguageController.setLanguage(Locale.ENGLISH);
		refreshUI();
	}

	private void refreshUI() {
		messages = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());
		updateTexts();
	}

	private void displayTariffs() {
		water = (ArrayList<Tariff>) StandardOutboundRequestHandler.makeTariffOutboundRequest("water");
		if (water != null) {
			waterDisplay = new TariffTableDisplay(scrollPaneWater, water, headerWater, true);
		}
	}

	private void updateTexts() {
		Platform.runLater(() -> {
			String welcomeText = messages.getString("signUpWelcomeLabel");
			String formattedWelcomeText = welcomeText.replace("{0}", MockActiveSession.getActiveUser().getFriendlyDisplayName());
			signUpWelcomeLabel.setText(formattedWelcomeText);
			signUpWelcomeText.setText(messages.getString("signUpWelcomeText"));
			signUpWelcomeText2.setText(messages.getString("signUpWelcomeText2"));
			signUpHeadlineLabel.setText(messages.getString("signUpHeadlineLabel"));
			signUpNextButton.setText(messages.getString("signUpNextButton"));
			changeLanguageToGermanButton.setText(messages.getString("languageGerman"));
			changeLanguageToEnglishButton.setText(messages.getString("languageEnglish"));
			signUpLanguageLabel.setText(messages.getString("signUpLanguageLabel"));
			displayTariffs();
		});
	}

	/**
     * Handles the action when the "Next" button is clicked.
     *
     * @param event The ActionEvent triggered by clicking the "Next" button.
     */
	@FXML
	public void next(ActionEvent event) {
		FXMLUtility fxmlUtility = new FXMLUtility(SIGN_UP_DIALOG_PATH, BUNDLE_NAME, event);
		fxmlUtility.loadAndSetScene();
	}
}