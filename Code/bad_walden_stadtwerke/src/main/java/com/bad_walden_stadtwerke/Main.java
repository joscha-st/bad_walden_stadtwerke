package com.bad_walden_stadtwerke;

import com.bad_walden_stadtwerke.components.Sidebar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Sidebar sidebar = new Sidebar();

        Label mainContent = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        mainContent.setStyle("-fx-padding: 10;");

        HBox contentArea = new HBox(sidebar, mainContent);

        stage.setScene(new Scene(contentArea, 1280, 720));

        stage.show();
    }
}