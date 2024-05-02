/**
 * The SignUpManager class manages the sign-up process for new users.
 * <p>
 * It initializes and displays the sign-up dialog, listens for language changes,
 * and handles the opening of the sign-up dialog based on whether the user has already signed up.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
package com.bad_walden_stadtwerke.model.initialSignUp;

import com.bad_walden_stadtwerke.mock.MockActiveSession;
import com.bad_walden_stadtwerke.model.types.language.LanguageChangeObserver;
import com.bad_walden_stadtwerke.controller.language.LanguageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class SignUpManager implements LanguageChangeObserver {

	public static final String BUNDLE_NAME = "Bundle";
	private static final String SIGN_UP_DIALOG_PATH = "/com/bad_walden_stadtwerke/view/initialSignUp/signup-dialog-0.fxml";
	private static final int SCENE_WIDTH = 1000;
	private static final int SCENE_HEIGHT = 550;

	private final Stage dialogStage;
	private final FXMLLoader fxmlLoader;
	private ResourceBundle bundle;


	/**
     * Constructs a SignUpManager with the specified stage and FXMLLoader.
     *
     * @param stage      The stage to display the sign-up dialog.
     * @param fxmlLoader The FXMLLoader for loading FXML files.
     */
	public SignUpManager(Stage stage, FXMLLoader fxmlLoader) {
		LanguageController.addObserver(this);
		this.dialogStage = stage;
		this.fxmlLoader = fxmlLoader;
		bundle = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());
		fxmlLoader.setResources(ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage()));
		initializeDialogStage();
	}

	private void initializeDialogStage() {
		dialogStage.initModality(Modality.APPLICATION_MODAL);
		dialogStage.initStyle(StageStyle.UNDECORATED);
		dialogStage.setTitle(bundle.getString("signUpPopUpTitle"));
	}

	/**
     * Checks if the user has already signed up and opens the sign-up dialog if not.
     */
	public void checkAndOpenForSignUp() {
		if (!hasUserSignedUp()) {
			openSignUpDialog();
		}
	}


	private void openSignUpDialog() {
		Parent dialogRoot = loadFXML();
		if (dialogRoot != null) {
			Scene dialogScene = new Scene(dialogRoot, SCENE_WIDTH, SCENE_HEIGHT);
			dialogStage.setScene(dialogScene);
			dialogStage.showAndWait();
			dialogStage.requestFocus();
		}
	}

	private Parent loadFXML() {
		fxmlLoader.setLocation(getClass().getResource(SIGN_UP_DIALOG_PATH));
		try {
			return fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean hasUserSignedUp() {
		return MockActiveSession.getActiveUser().getHasDoneInitialSignUp();
	}

	@Override
	public void onLanguageChange(Locale newLocale) {
		fxmlLoader.setResources(ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage()));
		bundle = ResourceBundle.getBundle(BUNDLE_NAME, LanguageController.getLanguage());
		dialogStage.setTitle(bundle.getString("signUpPopUpTitle"));
	}
}