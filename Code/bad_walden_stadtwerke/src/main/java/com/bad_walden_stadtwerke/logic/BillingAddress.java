package com.bad_walden_stadtwerke.logic;

import static com.bad_walden_stadtwerke.communication.BadWJsonParser.createJsonPair;

public class BillingAddress {

    private String firstName;
    private String lastName;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;

    public BillingAddress(String firstName, String lastName, String street,String houseNumber, String postalCode, String city){
        this.firstName = firstName.trim(); //.trim() removes any extra spaces from the end or the beginning of the string.
        this.lastName = lastName.trim();
        this.street = street.trim();
        this.houseNumber = houseNumber.trim();
        this.postalCode = postalCode.trim();
        this.city = city.trim();

        validate();
    }

    private void validate() {
        BillingAddressValidator.validateBillingAddress(this);

    }

    public String toJson() {
        return "{"
                + createJsonPair("firstName", firstName) + ","
                + createJsonPair("lastName", lastName) + ","
                + createJsonPair("street", street) + ","
                + createJsonPair("houseNumber", houseNumber) + ","
                + createJsonPair("postalCode", postalCode) + ","
                + createJsonPair("city", city)
                + "}";
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