package com.bad_walden_stadtwerke.logic;
import com.bad_walden_stadtwerke.logic.BillingAddressValidator;

public class BillingAddress {

    private String firstName;
    private String lastName;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;

    public BillingAddress(String firstName, String lastName, String street,String houseNumber, String postalCode, String city){
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;

        validate();
    }

    private void validate(BillingAddress this) {
        BillingAddressValidator.validateBillingAddress(this);

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