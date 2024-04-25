package com.bad_walden_stadtwerke;

import com.bad_walden_stadtwerke.communication.StandardOutboundRequestHandler;
import com.bad_walden_stadtwerke.ui.controller.LanguageChangeObserver;
import com.bad_walden_stadtwerke.ui.controller.LanguageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import com.bad_walden_stadtwerke.ui.controller.SignUpManager;


public class MainApplication extends Application implements LanguageChangeObserver {

    private ResourceBundle bundle = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
    private Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        SignUpManager signUpController = new SignUpManager(new Stage(), new FXMLLoader(), new LanguageController());
        switchScene(this.stage);
        this.stage.setTitle(bundle.getString("mainApplicationTitle"));

        LanguageController.addObserver(this);

        signUpController.checkAndOpenForSignUp();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void switchScene(Stage stage) throws IOException {
        ResourceBundle languageBundle = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mainApplication/main-application.fxml"));

        fxmlLoader.setResources(languageBundle);

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void onLanguageChange(Locale newLocale) {
        bundle = ResourceBundle.getBundle("Bundle", newLocale);
        stage.setTitle(bundle.getString("mainApplicationTitle"));
        String actualResponse = StandardOutboundRequestHandler.makeStandardOutboundRequest("test", "https://request-handling.int.bad-walden-stadtwerke.com/test");

    }
}