package com.bad_walden_stadtwerke.components;

import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

public class Sidebar extends VBox {
    public Sidebar() {
        this.setPrefWidth(270); // Set the width of the sidebar to 270
        this.setPrefHeight(720);
        this.setStyle("-fx-border-color: black; -fx-border-width: 0 2 0 0;");

        TreeItem<String> rootItem = new TreeItem<>("Root");

        TreeItem<String> homeItem = new TreeItem<>("Home");
        rootItem.getChildren().add(homeItem);
        homeItem.setExpanded(true);

        TreeItem<String> stadtwerkeItem = new TreeItem<>("Stadtwerke");
        stadtwerkeItem.getChildren().addAll(
                new TreeItem<>("Wasser"),
                new TreeItem<>("Strom"),
                new TreeItem<>("Gas"),
                new TreeItem<>("Rechnungen")
        );
        rootItem.getChildren().add(stadtwerkeItem);
        stadtwerkeItem.setExpanded(true);

        TreeItem<String> adminItem = new TreeItem<>("Admin");
        TreeItem<String> stadtwerkeAdminItem = new TreeItem<>("Stadtwerke");
        TreeItem<String> vertraegeItem = new TreeItem<>("Verträge");
        vertraegeItem.getChildren().addAll(
                new TreeItem<>("Wasser"),
                new TreeItem<>("Strom"),
                new TreeItem<>("Gas")
        );
        stadtwerkeAdminItem.getChildren().addAll(vertraegeItem, new TreeItem<>("Kunden"));
        adminItem.getChildren().add(stadtwerkeAdminItem);
        rootItem.getChildren().add(adminItem);
        adminItem.setExpanded(true);
        stadtwerkeAdminItem.setExpanded(true);
        vertraegeItem.setExpanded(true);

        rootItem.setExpanded(true);

        TreeView<String> treeView = new TreeView<>(rootItem);
        treeView.setShowRoot(false);
        treeView.setStyle("-fx-font-size: 16;");
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Clicked on: " + newValue.getValue());
            }
        });
        treeView.setPrefHeight(690);

        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-font-size: 16;");
        logoutButton.setOnAction(event -> System.out.println("Clicked on: Logout"));

        this.getChildren().addAll(treeView, logoutButton);


    }
}