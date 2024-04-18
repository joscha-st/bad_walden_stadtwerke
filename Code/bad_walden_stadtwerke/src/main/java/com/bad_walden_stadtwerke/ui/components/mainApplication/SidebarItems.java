package com.bad_walden_stadtwerke.ui.components.mainApplication;

import com.bad_walden_stadtwerke.ui.controller.LanguageController;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.value.ChangeListener;

public class SidebarItems extends TreeItem<String> {

    private static ResourceBundle messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());

    public SidebarItems() {
        super("Root");
        this.getChildren().addAll(Arrays.asList(createHomeItem(), createCityServicesItem(), createAdminItem()));
        this.setExpanded(true);
    }

    public static void updateLanguage() {
        messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
    }

    private TreeItem<String> createHomeItem() {
        return createTreeItem(messages.getString("sidebarHome"), List.of());
    }

    private TreeItem<String> createCityServicesItem() {
        return createTreeItem(messages.getString("sidebarCityServices"), createServiceItems(messages.getString("sidebarWater"), messages.getString("sidebarElectricity"), messages.getString("sidebarGas"), messages.getString("sidebarBills")));
    }

    private TreeItem<String> createAdminItem() {
        return createTreeItem(messages.getString("sidebarAdmin"), List.of(createCityServicesAdminItem()));
    }

    private TreeItem<String> createCityServicesAdminItem() {
        return createTreeItem(messages.getString("sidebarCityServices"), Arrays.asList(createContractsItem(), createTreeItem(messages.getString("sidebarCustomers"), List.of())));
    }

    private TreeItem<String> createContractsItem() {
        return createTreeItem(messages.getString("sidebarContracts"), createServiceItems(messages.getString("sidebarWater"), messages.getString("sidebarElectricity"), messages.getString("sidebarGas")));
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
            if (item.getValue().equals(messages.getString("sidebarAdmin"))) {
                return true;
            }
            item = item.getParent();
        }
        return false;
    }


    public ChangeListener<TreeItem<String>> setTreeViewActionListener(TreeView<String> treeView) {
        ChangeListener<TreeItem<String>> listener = (observable, oldValue, newValue) -> {
            if (newValue != null) {
                logSelectedPage(newValue);
            }
        };
        treeView.getSelectionModel().selectedItemProperty().addListener(listener);
        return listener;
    }

    private void logSelectedPage(TreeItem<String> newValue) {
        String selectedPage = newValue.getValue();
        String typePage = isAdminPage(newValue) ? "Admin" : "User";
        System.out.println("Selected Page: " + typePage + " - " + selectedPage);
    }
}