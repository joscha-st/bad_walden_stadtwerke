package com.bad_walden_stadtwerke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Locale;

import static com.example.demo.HelloApplication.switchScene;
import static com.example.demo.LanguageController.setLanguage;

public class HelloController {

    public Button ButtonDeusch;
    public Button ButtonEnglish;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    protected void onButtonDeutschClick() throws IOException {
        setLanguage(Locale.GERMAN);
        switchScene();
    }
    @FXML
    protected void onButtonEnglishClick() throws IOException {
        setLanguage(Locale.ENGLISH);
        switchScene();
    }
}