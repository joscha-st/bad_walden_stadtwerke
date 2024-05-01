/**
 * Provides components for the main application interface in JavaFX.
 * The {@code KeyedTreeItem} class extends JavaFX's {@link TreeItem} to associate items with unique keys.
 */
package com.bad_walden_stadtwerke.components.mainApplication;

import javafx.scene.control.TreeItem;
import java.util.ResourceBundle;

/**
 * Extends {@link TreeItem} to store items with a string identifier key, which are
 * typically used for localization or other mapping purposes. Each item fetches its
 * display text from a {@link ResourceBundle} based on the provided key.
 */
public class KeyedTreeItem extends TreeItem<String> {
    /**
     * The key associated with this tree item, used to fetch the localized text.
     */
    private final String key;

    /**
     * Constructs a new {@code KeyedTreeItem} with the specified key and resource bundle.
     * The text for the tree item is fetched using the key from the provided {@link ResourceBundle}.
     *
     * @param key      the key used to retrieve the localized text from the {@code messages} resource bundle.
     * @param messages the resource bundle containing localized strings.
     */
    public KeyedTreeItem(String key, ResourceBundle messages) {
        super(messages.getString(key));
        this.key = key;
    }

    /**
     * Returns the key associated with this tree item.
     * The key is used to retrieve the item's text and can be used for other identification or lookup purposes.
     *
     * @return the key associated with this tree item.
     */
    public String getKey() {
        return key;
    }
}
