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

	@Override
	public void start(Stage stage) throws IOException {
		this.stage = stage;
		SignUpManager signUpController = new SignUpManager(new Stage(), new FXMLLoader());
		switchScene(this.stage);
		this.stage.setTitle(bundle.getString("mainApplicationTitle"));

		LanguageController.addObserver(this);

		signUpController.checkAndOpenForSignUp();
	}

	public void switchScene(Stage stage) throws IOException {
		ResourceBundle languageBundle = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());

		FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/com/bad_walden_stadtwerke/view/mainApplication/main-application.fxml"));

		fxmlLoader.setResources(languageBundle);

		Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void onLanguageChange(Locale newLocale) {
		bundle = ResourceBundle.getBundle("Bundle", newLocale);
		stage.setTitle(bundle.getString("mainApplicationTitle"));
	}
}