package com.bad_walden_stadtwerke.ui.components.mainApplication;

import com.bad_walden_stadtwerke.ui.controller.LanguageController;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sidebar {

    private static ResourceBundle messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());

    public static void updateLanguage() {
        messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
    }

    public static TreeItem<String> createRootItem() {
        return createTreeItem("Root", Arrays.asList(createHomeItem(), createCityServicesItem(), createAdminItem()));
    }

    private static TreeItem<String> createHomeItem() {
        return createTreeItem(messages.getString("sidebarHome"), List.of());
    }

    private static TreeItem<String> createCityServicesItem() {
        return createTreeItem(messages.getString("sidebarCityServices"), createServiceItems(messages.getString("sidebarWater"), messages.getString("sidebarElectricity"), messages.getString("sidebarGas"), messages.getString("sidebarBills")));
    }

    private static TreeItem<String> createAdminItem() {
        return createTreeItem(messages.getString("sidebarAdmin"), List.of(createCityServicesAdminItem()));
    }

    private static TreeItem<String> createCityServicesAdminItem() {
        return createTreeItem(messages.getString("sidebarCityServices"), Arrays.asList(createContractsItem(), createTreeItem(messages.getString("sidebarCustomers"), List.of())));
    }

    private static TreeItem<String> createContractsItem() {
        return createTreeItem(messages.getString("sidebarContracts"), createServiceItems(messages.getString("sidebarWater"), messages.getString("sidebarElectricity"), messages.getString("sidebarGas")));
    }

    private static TreeItem<String> createTreeItem(String name, List<TreeItem<String>> children) {
        TreeItem<String> item = new TreeItem<>(name);
        item.getChildren().addAll(children);
        item.setExpanded(true);
        return item;
    }

    private static List<TreeItem<String>> createServiceItems(String... services) {
        return Stream.of(services).map(TreeItem::new).collect(Collectors.toList());
    }

    private static boolean isAdminPage(TreeItem<String> item) {
        while (item != null) {
            if (item.getValue().equals(messages.getString("sidebarAdmin"))) {
                return true;
            }
            item = item.getParent();
        }
        return false;
    }

    public static void setTreeViewActionListener(TreeView<String> treeView) {
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                logSelectedPage(newValue);
            }
        });
    }

    private static void logSelectedPage(TreeItem<String> newValue) {
        String selectedPage = newValue.getValue();
        String typePage = isAdminPage(newValue) ? "Admin" : "User";
        System.out.println("Selected Page: " + typePage + " - " + selectedPage);
    }
}