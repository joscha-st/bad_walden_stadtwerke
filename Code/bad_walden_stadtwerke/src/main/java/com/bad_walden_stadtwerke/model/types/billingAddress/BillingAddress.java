/**
 * The BillingAddress class represents a billing address.
 * <p>
 * It stores information such as first name, last name, street, house number, postal code, and city.
 * The class provides methods to access and manipulate this information, as well as to convert the
 * billing address object to JSON format.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
package com.bad_walden_stadtwerke.model.types.billingAddress;
import com.bad_walden_stadtwerke.utility.JsonParserUtility;
public class BillingAddress {

	private final String firstName;
	private final String lastName;
	private final String street;
	private final String houseNumber;
	private final String postalCode;
	private final String city;

	/**
     * Constructs a BillingAddress object with the specified details.
     *
     * @param firstName   The first name associated with the billing address.
     * @param lastName    The last name associated with the billing address.
     * @param street      The street of the billing address.
     * @param houseNumber The house number of the billing address.
     * @param postalCode  The postal code of the billing address.
     * @param city        The city of the billing address.
     */
	public BillingAddress(String firstName, String lastName, String street, String houseNumber, String postalCode, String city) {
		this.firstName = (firstName != null) ? firstName.trim() : "";
		this.lastName = (lastName != null) ? lastName.trim() : "";
		this.street = (street != null) ? street.trim() : "";
		this.houseNumber = (houseNumber != null) ? houseNumber.trim() : "";
		this.postalCode = (postalCode != null) ? postalCode.trim() : "";
		this.city = (city != null) ? city.trim() : "";

		validate();
	}

	/**
     * Validates the billing address.
     */
	private void validate() {
		BillingAddressValidator.validateBillingAddress(this);

	}

	/**
     * Converts the billing address object to JSON format.
     *
     * @return A JSON representation of the billing address.
     */
	public String toJson() {
		return "{" + JsonParserUtility.createJsonPair("firstName", firstName) + "," + JsonParserUtility.createJsonPair("lastName", lastName) + "," + JsonParserUtility.createJsonPair("street", street) + "," + JsonParserUtility.createJsonPair("houseNumber", houseNumber) + "," + JsonParserUtility.createJsonPair("postalCode", postalCode) + "," + JsonParserUtility.createJsonPair("city", city) + "}";
	}

	/**
     * Returns the first name associated with the billing address.
     *
     * @return The first name.
     */
	public String getFirstName() {
		return this.firstName;
	}

	/**
     * Returns the last name associated with the billing address.
     *
     * @return The last name.
     */
	public String getLastName() {
		return this.lastName;
	}

	 /**
     * Returns the street of the billing address.
     *
     * @return The street.
     */
	public String getStreet() {
		return this.street;
	}

	/**
     * Returns the house number of the billing address.
     *
     * @return The house number.
     */
	public String getHouseNumber() {
		return this.houseNumber;
	}

	/**
     * Returns the postal code of the billing address.
     *
     * @return The postal code.
     */
	public String getPostalCode() {
		return this.postalCode;
	}

	/**
     * Returns the city of the billing address.
     *
     * @return The city.
     */
	public String getCity() {
		return this.city;
	}
}