package com.bad_walden_stadtwerke;

import com.bad_walden_stadtwerke.model.types.language.LanguageChangeObserver;
import com.bad_walden_stadtwerke.controller.language.LanguageController;
import com.bad_walden_stadtwerke.model.initialSignUp.SignUpManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The MainApplication class is the main entry point for the Bad Walden Stadtwerke application.
 * It extends the JavaFX Application class and implements the LanguageChangeObserver interface.
 * This class is responsible for initializing the application, setting up the main stage, and managing scene switching.
 */
public class MainApplication extends Application implements LanguageChangeObserver {

    private ResourceBundle bundle = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
    private Stage stage;

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method, overridden from the Application class, initializes the application.
     * It sets up the main stage, loads the main application scene, and checks if the user needs to sign up.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        SignUpManager signUpController = new SignUpManager(new Stage(), new FXMLLoader());
        switchScene(this.stage);
        this.stage.setTitle(bundle.getString("mainApplicationTitle"));

        LanguageController.addObserver(this);

        signUpController.checkAndOpenForSignUp();
    }

    /**
     * Switches the scene to the main application scene.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    public void switchScene(Stage stage) throws IOException {
        ResourceBundle languageBundle = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/bad_walden_stadtwerke/view/mainApplication/main-application.fxml"));

        fxmlLoader.setResources(languageBundle);

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Callback method invoked when the language is changed.
     * Updates the resource bundle and the stage title accordingly.
     *
     * @param newLocale The new locale representing the changed language.
     */
    @Override
    public void onLanguageChange(Locale newLocale) {
        bundle = ResourceBundle.getBundle("Bundle", newLocale);
        stage.setTitle(bundle.getString("mainApplicationTitle"));
    }
}
