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

/**
 * The SignUpManager class manages the initial sign-up process.
 * It checks if the user has already signed up, and if not, opens the sign-up dialog.
 */
public class SignUpManager implements LanguageChangeObserver {

    public static final String BUNDLE_NAME = "Bundle";
    private static final String SIGN_UP_DIALOG_PATH = "/com/bad_walden_stadtwerke/view/initialSignUp/signup-dialog-0.fxml";
    private static final int SCENE_WIDTH = 1000;
    private static final int SCENE_HEIGHT = 550;

    private final Stage dialogStage;
    private final FXMLLoader fxmlLoader;
    private ResourceBundle bundle;

    /**
     * Constructs a new SignUpManager with the specified stage and FXMLLoader.
     *
     * @param stage      The stage to use for displaying the sign-up dialog.
     * @param fxmlLoader The FXMLLoader to load the FXML file for the sign-up dialog.
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
     * Checks if the user has already signed up. If not, opens the sign-up dialog.
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
