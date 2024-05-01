/**
 * Provides components for constructing and managing the main application sidebar in a JavaFX interface.
 * This package leverages language localization and dynamic UI updates to reflect changes in the selected language.
 */
package com.bad_walden_stadtwerke.components.mainApplication;

import com.bad_walden_stadtwerke.controller.language.LanguageController;
import com.bad_walden_stadtwerke.model.types.language.LanguageChangeObserver;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents the sidebar navigation items of the application, with localization support.
 * This class extends {@link KeyedTreeItem} to handle dynamic language changes and populate the sidebar
 * with appropriate labels fetched from a {@link ResourceBundle}.
 */
public class SidebarItems extends KeyedTreeItem implements LanguageChangeObserver {

    private static ResourceBundle messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());

    /**
     * Constructs the root item of the sidebar with initial child items set up and registers for language change notifications.
     */
    public SidebarItems() {
        super("Root", messages);
        this.getChildren().addAll(Arrays.asList(createHomeItem(), createCityServicesItem(), createAdminItem()));
        this.setExpanded(true);
        LanguageController.addObserver(this);
    }

    /**
     * Updates the text of sidebar items to reflect the current language setting.
     */
    public void updateLanguage() {
        messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
        updateTreeItem(this);
    }

    private void updateTreeItem(KeyedTreeItem item) {
        String newText = messages.getString(item.getKey());
        item.setValue(newText);
        for (TreeItem<String> child : item.getChildren()) {
            updateTreeItem((KeyedTreeItem) child);
        }
    }

    private KeyedTreeItem createHomeItem() {
        return createTreeItem("sidebarHome", List.of());
    }

    private KeyedTreeItem createCityServicesItem() {
        return createTreeItem("sidebarCityServices", createServiceItems("sidebarWater", "sidebarElectricity", "sidebarHeatpump", "sidebarGas", "sidebarBills"));
    }

    private KeyedTreeItem createAdminItem() {
        return createTreeItem("sidebarAdmin", List.of(createCityServicesAdminItem()));
    }

    private KeyedTreeItem createCityServicesAdminItem() {
        return createTreeItem("sidebarCityServicesAdmin", Arrays.asList(createContractsItem(), createTreeItem("sidebarCustomers", List.of())));
    }

    private KeyedTreeItem createContractsItem() {
        return createTreeItem("sidebarTariff", createServiceItems("sidebarWater", "sidebarElectricity", "sidebarHeatpump", "sidebarGas"));
    }

    private KeyedTreeItem createTreeItem(String key, List<KeyedTreeItem> children) {
        KeyedTreeItem item = new KeyedTreeItem(key, messages);
        item.getChildren().addAll(children);
        item.setExpanded(true);
        return item;
    }

    private List<KeyedTreeItem> createServiceItems(String... keys) {
        return Stream.of(keys).map(key -> new KeyedTreeItem(key, messages)).collect(Collectors.toList());
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

    /**
     * Sets a listener on a {@link TreeView} that logs the selected page when changed.
     *
     * @param treeView the {@link TreeView} to monitor for selection changes.
     * @return the {@link ChangeListener} attached to the tree view.
     */
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

    /**
     * Responds to language changes by updating all labels in the sidebar according to the new locale.
     *
     * @param newLocale the new locale to apply.
     */
    @Override
    public void onLanguageChange(Locale newLocale) {
        updateLanguage();
    }
}
