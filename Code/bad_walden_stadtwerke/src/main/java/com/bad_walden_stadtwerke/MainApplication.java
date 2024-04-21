package com.bad_walden_stadtwerke;

import com.bad_walden_stadtwerke.ui.controller.LanguageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;
import com.bad_walden_stadtwerke.ui.controller.SignUpManager;


public class MainApplication extends Application {

    static Stage stage;
    private final SignUpManager signUpController = new SignUpManager();

    @Override
    public void start(Stage stage) throws IOException {
        MainApplication.stage = stage;
        switchScene();
        stage.setTitle("Stadtwerke Bad Walden");

        signUpController.checkAndOpenForSignUp();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void switchScene() throws IOException {
        ResourceBundle languageBundle = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mainApplication/main-application.fxml"));

        fxmlLoader.setResources(languageBundle);

        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setScene(scene);
        stage.show();

    }


}