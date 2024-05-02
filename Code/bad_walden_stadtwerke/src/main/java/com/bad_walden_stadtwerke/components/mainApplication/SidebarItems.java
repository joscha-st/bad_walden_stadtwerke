/**
 * The SidebarItems class represents the sidebar items displayed in the main application.
 * It provides methods to create and manage the sidebar tree items dynamically based on the selected language.
 * It extends the KeyedTreeItem class and implements the LanguageChangeObserver interface.
 * <p>
 * Example usage:
 * <pre>
 *    SidebarItems sidebarItems = new SidebarItems();
 * </pre>
 * </p>
 * @author com.bad_walden_stadtwerke.components.mainApplication
 * @version 1.0
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


public class SidebarItems extends KeyedTreeItem implements LanguageChangeObserver {

	private static ResourceBundle messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());

 /**
     * Constructs a SidebarItems object with default root item and initializes the sidebar items.
     */
	public SidebarItems() {
		super("Root", messages);
		this.getChildren().addAll(Arrays.asList(createHomeItem(), createCityServicesItem(), createAdminItem()));
		this.setExpanded(true);
		LanguageController.addObserver(this);

	}
/**
     * Updates the language of the sidebar items when the language changes.
     */
	public void updateLanguage() {
		messages = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());
		updateTreeItem(this);
	}


/**
     * Updates the text of the tree item and its children recursively based on the new language.
     *
     * @param item the tree item to update
     */
	private void updateTreeItem(KeyedTreeItem item) {
		String newText = messages.getString(item.getKey());
		item.setValue(newText);
		for (TreeItem<String> child : item.getChildren()) {
			updateTreeItem((KeyedTreeItem) child);
		}
	}

	/**
     * Creates the "Home" tree item.
     *
     * @return the "Home" tree item
     */
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
		return createTreeItem("sidebarCityServices", Arrays.asList(createContractsItem(), createTreeItem("sidebarCustomers", List.of())));
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

	
/**
     * Checks if the selected page is an admin page.
     *
     * @param item the selected tree item
     * @return true if the selected page is an admin page, false otherwise
     */
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
     * Sets the action listener for the tree view to log the selected page.
     *
     * @param treeView the tree view to set the listener on
     * @return the change listener for the tree view
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

	/**
     * Logs the selected page in the sidebar.
     *
     * @param newValue the newly selected tree item
     */
	private void logSelectedPage(TreeItem<String> newValue) {
		String selectedPage = newValue.getValue();
		String typePage = isAdminPage(newValue) ? "Admin" : "User";
		System.out.println("Selected Page: " + typePage + " - " + selectedPage);
	}

	@Override
	public void onLanguageChange(Locale newLocale) {
		updateLanguage();
	}
}