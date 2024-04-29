package com.bad_walden_stadtwerke.model.types.billingAddress;
import com.bad_walden_stadtwerke.utility.JsonParserUtility;
public class BillingAddress {

	private final String firstName;
	private final String lastName;
	private final String street;
	private final String houseNumber;
	private final String postalCode;
	private final String city;

	public BillingAddress(String firstName, String lastName, String street, String houseNumber, String postalCode, String city) {
		this.firstName = (firstName != null) ? firstName.trim() : "";
		this.lastName = (lastName != null) ? lastName.trim() : "";
		this.street = (street != null) ? street.trim() : "";
		this.houseNumber = (houseNumber != null) ? houseNumber.trim() : "";
		this.postalCode = (postalCode != null) ? postalCode.trim() : "";
		this.city = (city != null) ? city.trim() : "";

		validate();
	}

	private void validate() {
		BillingAddressValidator.validateBillingAddress(this);

	}

	public String toJson() {
		return "{" + JsonParserUtility.createJsonPair("firstName", firstName) + "," + JsonParserUtility.createJsonPair("lastName", lastName) + "," + JsonParserUtility.createJsonPair("street", street) + "," + JsonParserUtility.createJsonPair("houseNumber", houseNumber) + "," + JsonParserUtility.createJsonPair("postalCode", postalCode) + "," + JsonParserUtility.createJsonPair("city", city) + "}";
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getStreet() {
		return this.street;
	}

	public String getHouseNumber() {
		return this.houseNumber;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public String getCity() {
		return this.city;
	}
}