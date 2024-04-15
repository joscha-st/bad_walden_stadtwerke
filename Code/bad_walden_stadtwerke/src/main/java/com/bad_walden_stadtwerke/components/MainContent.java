package com.bad_walden_stadtwerke.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainContent extends HBox {
    private static final double PREFERRED_WIDTH = 1010;
    private static final double PREFERRED_HEIGHT = 720;
    private static final Insets PADDING = new Insets(10, 10, 10, 10);

    public MainContent() {
        setPreferredSize();
        setPadding();
        addChildren();
    }

    private void setPreferredSize() {
        setPrefSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
    }

    private void setPadding() {
        this.setPadding(PADDING);
    }

    private void addChildren() {
        VBox content = new VBox();
        Label loremIpsumLabel = createLabel("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        Label additionalLabel = createLabel("Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        content.getChildren().addAll(loremIpsumLabel, additionalLabel);
        this.getChildren().add(content);
    }

    private Label createLabel(String text) {
        return new Label(text);
    }
}