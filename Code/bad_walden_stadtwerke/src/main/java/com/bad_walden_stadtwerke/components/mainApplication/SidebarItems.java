package com.bad_walden_stadtwerke.components.mainApplication;

import com.bad_walden_stadtwerke.model.types.language.LanguageChangeObserver;
import com.bad_walden_stadtwerke.controller.language.LanguageController;
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

	public SidebarItems() {
		super("Root", messages);
		this.getChildren().addAll(Arrays.asList(createHomeItem(), createCityServicesItem(), createAdminItem()));
		this.setExpanded(true);
		LanguageController.addObserver(this);

	}

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
		return createTreeItem("sidebarCityServices", createServiceItems("sidebarWater", "sidebarElectricity", "sidebarGas", "sidebarBills"));
	}

	private KeyedTreeItem createAdminItem() {
		return createTreeItem("sidebarAdmin", List.of(createCityServicesAdminItem()));
	}

	private KeyedTreeItem createCityServicesAdminItem() {
		return createTreeItem("sidebarCityServices", Arrays.asList(createContractsItem(), createTreeItem("sidebarCustomers", List.of())));
	}

	private KeyedTreeItem createContractsItem() {
		return createTreeItem("sidebarTariff", createServiceItems("sidebarWater", "sidebarElectricity", "sidebarGas"));
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

	@Override
	public void onLanguageChange(Locale newLocale) {
		updateLanguage();
	}
}