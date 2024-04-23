package com.bad_walden_stadtwerke.logic;

public class ValidateBillingAddress {

    //Method to validate the BillingAddress before a Customer Object is Created.
    // Returns a String that indicates what was filled out wrong to give the user some information in the error handling later.
    static public String validateBillingAddress(String firstName, String lastName, String street, String houseNumber, String postalCode, String city){
        // Check if any field is null or empty
        if (firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty() ||
                street == null || street.isEmpty() ||
                houseNumber == null || houseNumber.isEmpty() ||
                postalCode == null || postalCode.isEmpty() ||
                city == null || city.isEmpty()) {
            return "EmptyFields";
        }

        // Validate firstName (only alphabetic characters allowed)
        if (!firstName.matches("[a-zA-Z]+")) {
            return "FirstName";
        }

        // Validate lastName (only alphabetic characters allowed)
        if (!lastName.matches("[a-zA-Z]+")) {
            return "LastName";
        }

        // Validate street (only alphabetic characters, spaces, and dashes allowed)
        if (!street.matches("[a-zA-Z\\s\\-]+")) {
            return "Street";
        }

        // Validate city (only alphabetic characters, spaces, and dashes allowed)
        if (!city.matches("[a-zA-Z\\s\\-]+")) {
            return "City";
        }

        // Validate houseNumber (positive integer)
        if (!houseNumber.matches("^\\d+[A-Za-z]?$")) {
            return "HouseNumber";
        }

        // Validate postalCode (format: XXXXX or XXXXX-XXXX, where X is a digit)
        if (!postalCode.matches("\\d{5}(-\\d{4})?")) {
            return "PostalCode";
        }

        // All validations passed
        return "Valid";
    }
}
