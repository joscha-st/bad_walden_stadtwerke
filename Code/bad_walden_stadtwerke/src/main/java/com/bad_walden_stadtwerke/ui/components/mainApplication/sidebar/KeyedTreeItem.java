package com.bad_walden_stadtwerke.ui.components.mainApplication.sidebar;

import javafx.scene.control.TreeItem;

import java.util.ResourceBundle;

public class KeyedTreeItem extends TreeItem<String> {
	private final String key;

	public KeyedTreeItem(String key, ResourceBundle messages) {
		super(messages.getString(key));
		this.key = key;
	}

	public String getKey() {
		return key;
	}
}