package com.bad_walden_stadtwerke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;


public class HelloApplication extends Application {

    static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        HelloApplication.stage = stage;
        switchScene();
        stage.setTitle("Hello!");
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void switchScene() throws IOException {
        ResourceBundle languageBundle = getBundle("Bundle",LanguageController.getLanguage());

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        fxmlLoader.setResources(languageBundle);

        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.show();

    }

}