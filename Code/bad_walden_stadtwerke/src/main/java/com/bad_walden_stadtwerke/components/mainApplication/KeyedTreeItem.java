/**
 * The KeyedTreeItem class represents a tree item with a key, typically used for localization purposes.
 * It extends the JavaFX TreeItem class and includes a key associated with the item.
 * <p>
 * Example usage:
 * <pre>
 *    ResourceBundle messages = ResourceBundle.getBundle("Messages");
 *    KeyedTreeItem item = new KeyedTreeItem("item_key", messages);
 * </pre>
 * </p>
 * @author com.bad_walden_stadtwerke.components.mainApplication
 * @version 1.0
 */


package com.bad_walden_stadtwerke.components.mainApplication;

import javafx.scene.control.TreeItem;

import java.util.ResourceBundle;
 /**
     * Constructs a KeyedTreeItem with the specified key and message bundle.
     *
     * @param key      the key associated with the tree item
     * @param messages the ResourceBundle containing localized messages
     */
public class KeyedTreeItem extends TreeItem<String> {
	private final String key;

	public KeyedTreeItem(String key, ResourceBundle messages) {
		super(messages.getString(key));
		this.key = key;
	}
/**
     * Gets the key associated with this tree item.
     *
     * @return the key associated with this tree item
     */
	public String getKey() {
		return key;
	}
}