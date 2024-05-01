package com.bad_walden_stadtwerke.model.types.billingAddress;

/**
 * The BillingAddress class represents a billing address.
 * It provides methods to create and manipulate billing address objects.
 */
public class BillingAddress {

    private final String firstName;
    private final String lastName;
    private final String street;
    private final String houseNumber;
    private final String postalCode;
    private final String city;

    /**
     * Constructs a new BillingAddress object with the specified details.
     *
     * @param firstName   The first name of the recipient.
     * @param lastName    The last name of the recipient.
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
     * Converts the billing address object to its JSON representation.
     *
     * @return The JSON representation of the billing address.
     */
    public String toJson() {
        return "{" + JsonParserUtility.createJsonPair("firstName", firstName) + ","
                + JsonParserUtility.createJsonPair("lastName", lastName) + ","
                + JsonParserUtility.createJsonPair("street", street) + ","
                + JsonParserUtility.createJsonPair("houseNumber", houseNumber) + ","
                + JsonParserUtility.createJsonPair("postalCode", postalCode) + ","
                + JsonParserUtility.createJsonPair("city", city) + "}";
    }

    /**
     * Gets the first name of the recipient.
     *
     * @return The first name of the recipient.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Gets the last name of the recipient.
     *
     * @return The last name of the recipient.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Gets the street of the billing address.
     *
     * @return The street of the billing address.
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * Gets the house number of the billing address.
     *
     * @return The house number of the billing address.
     */
    public String getHouseNumber() {
        return this.houseNumber;
    }

    /**
     * Gets the postal code of the billing address.
     *
     * @return The postal code of the billing address.
     */
    public String getPostalCode() {
        return this.postalCode;
    }

    /**
     * Gets the city of the billing address.
     *
     * @return The city of the billing address.
     */
    public String getCity() {
        return this.city;
    }
}
