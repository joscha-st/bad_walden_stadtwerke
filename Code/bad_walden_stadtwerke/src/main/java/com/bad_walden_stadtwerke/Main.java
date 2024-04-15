package com.bad_walden_stadtwerke;

import com.bad_walden_stadtwerke.components.MainContent;
import com.bad_walden_stadtwerke.components.Sidebar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    private static final double SCENE_WIDTH = 1280;
    private static final double SCENE_HEIGHT = 720;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        HBox root = createRoot();
        setupStage(stage, root);
    }

    private HBox createRoot() {
        Sidebar sidebar = new Sidebar();
        MainContent mainContent = new MainContent();
        HBox root = new HBox();
        root.getChildren().addAll(sidebar, mainContent);
        return root;
    }

    private void setupStage(Stage stage, HBox root) {
        stage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
        stage.show();
    }
}