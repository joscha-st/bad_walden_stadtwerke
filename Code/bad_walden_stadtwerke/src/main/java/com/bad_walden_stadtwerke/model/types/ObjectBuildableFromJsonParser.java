/**
 * The ObjectBuildableFromJsonParser interface represents an object that can be filled from JSON data.
 * <p>
 * Classes implementing this interface should provide an implementation for the {@code fillFromJson} method,
 * which takes a JSON string as input and populates the object's fields accordingly.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
package com.bad_walden_stadtwerke.model.types;

public interface ObjectBuildableFromJsonParser {
	
	/**
     * Fills the object's fields from JSON data.
     *
     * @param json The JSON string containing data to populate the object.
     */
	void fillFromJson(String json);
}
