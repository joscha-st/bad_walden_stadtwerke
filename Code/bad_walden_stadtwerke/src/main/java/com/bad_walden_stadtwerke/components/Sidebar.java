package com.bad_walden_stadtwerke.components;

import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sidebar extends VBox {
    private static final String TREE_VIEW_FONT_SIZE = "-fx-font-size: 16;";
    private static double SIDEBAR_TREE_VIEW_HEIGHT;
    private static final String SIDEBAR_TREE_BORDER = "-fx-border-color: black; -fx-border-width: 0 2 0 0;";

    public Sidebar() {
        setPrefSize(270, 720);
        SIDEBAR_TREE_VIEW_HEIGHT = getPrefHeight() - 30;

        setStyle(SIDEBAR_TREE_BORDER);

        TreeItem<String> rootItem = createRootItem();

        getChildren().addAll(createTreeView(rootItem), createLogoutButton());
    }

    private TreeItem<String> createRootItem() {
        return createTreeItem("Root", Arrays.asList(createHomeItem(), createCityServicesItem(), createAdminItem()));
    }

    private TreeItem<String> createHomeItem() {
        return createTreeItem("Home", List.of());
    }

    private TreeItem<String> createCityServicesItem() {
        return createTreeItem("City Services", createServiceItems("Water", "Electricity", "Gas", "Bills"));
    }

    private TreeItem<String> createAdminItem() {
        return createTreeItem("Admin", List.of(createCityServicesAdminItem()));
    }

    private TreeItem<String> createCityServicesAdminItem() {
        return createTreeItem("City Services", Arrays.asList(createContractsItem(), createTreeItem("Customers", List.of())));
    }

    private TreeItem<String> createContractsItem() {
        return createTreeItem("Contracts", createServiceItems("Water", "Electricity", "Gas"));
    }

    private TreeItem<String> createTreeItem(String name, List<TreeItem<String>> children) {
        TreeItem<String> item = new TreeItem<>(name);
        item.getChildren().addAll(children);
        item.setExpanded(true);
        return item;
    }

    private List<TreeItem<String>> createServiceItems(String... services) {
        return Stream.of(services).map(TreeItem::new).collect(Collectors.toList());
    }

    private boolean isAdminPage(TreeItem<String> item) {
        while (item != null) {
            if (item.getValue().equals("Admin")) {
                return true;
            }
            item = item.getParent();
        }
        return false;
    }

    private TreeView<String> createTreeView(TreeItem<String> rootItem) {
        TreeView<String> treeView = new TreeView<>(rootItem);
        treeView.setShowRoot(false);
        treeView.setStyle(TREE_VIEW_FONT_SIZE);
        treeView.setPrefHeight(SIDEBAR_TREE_VIEW_HEIGHT);
        setTreeViewActionListener(treeView);
        return treeView;
    }

    private void setTreeViewActionListener(TreeView<String> treeView) {
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String selectedPage = newValue.getValue();
                String typePage = isAdminPage(newValue) ? "Admin" : "User";
                System.out.println("Selected page "+typePage+": " + selectedPage);
            }
        });
    }

    private Button createLogoutButton() {
        Button logoutButton = new Button("Logout");
        logoutButton.setStyle(TREE_VIEW_FONT_SIZE);
        logoutButton.setOnAction(event -> System.out.println("Clicked on: Logout"));
        return logoutButton;
    }
}