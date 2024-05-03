/**
 * The MainApplication class serves as the entry point for the application. It extends the {@link Application} class and implements the {@link LanguageChangeObserver} interface.
 * <p>
 * This class initializes the primary stage, sets up the initial scene, and manages language changes by observing the {@link LanguageController}.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
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



public class MainApplication extends Application implements LanguageChangeObserver {

	private ResourceBundle bundle = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
	private Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	/**
     * The start method of the JavaFX application, responsible for initializing the primary stage and setting up the initial scene.
     *
     * @param stage The primary stage for this application.
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
     * Switches the scene of the primary stage to the main application scene.
     *
     * @param stage The primary stage for this application.
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
     * Callback method invoked when the language is changed. It updates the resource bundle and the stage title accordingly.
     *
     * @param newLocale The new locale representing the changed language.
     */
	@Override
	public void onLanguageChange(Locale newLocale) {
		bundle = ResourceBundle.getBundle("Bundle", newLocale);
		stage.setTitle(bundle.getString("mainApplicationTitle"));
	}
}