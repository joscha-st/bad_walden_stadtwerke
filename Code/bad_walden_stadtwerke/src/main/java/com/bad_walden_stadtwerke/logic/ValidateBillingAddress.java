package com.bad_walden_stadtwerke.logic;
public class ValidateBillingAddress {
    // Constants for regular expressions used in validations
    private static final String ALPHABETIC_PATTERN = "[a-zA-Z]+";
    private static final String ALPHABETIC_SPACE_DASH_PATTERN = "[a-zA-Z\\s\\-]+";
    private static final String HOUSE_NUMBER_PATTERN = "^\\d+[A-Za-z]?$";
    private static final String POSTAL_CODE_PATTERN = "\\d{5}(-\\d{4})?";

    public enum ValidationResult {
        VALID, EMPTY_FIELDS, INVALID_FIRST_NAME, INVALID_LAST_NAME, INVALID_STREET, INVALID_CITY, INVALID_HOUSE_NUMBER, INVALID_POSTAL_CODE
    }

    // Method to validate the BillingAddress before a Customer Object is Created.
    public static ValidationResult validateBillingAddress(String firstName, String lastName, String street, String houseNumber, String postalCode, String city) {
        if (isNullOrEmpty(firstName, lastName, street, houseNumber, postalCode, city)) {
            return ValidationResult.EMPTY_FIELDS;
        }

        if (!firstName.matches(ALPHABETIC_PATTERN)) {
            return ValidationResult.INVALID_FIRST_NAME;
        }

        if (!lastName.matches(ALPHABETIC_PATTERN)) {
            return ValidationResult.INVALID_LAST_NAME;
        }

        if (!street.matches(ALPHABETIC_SPACE_DASH_PATTERN)) {
            return ValidationResult.INVALID_STREET;
        }

        if (!city.matches(ALPHABETIC_SPACE_DASH_PATTERN)) {
            return ValidationResult.INVALID_CITY;
        }

        if (!houseNumber.matches(HOUSE_NUMBER_PATTERN)) {
            return ValidationResult.INVALID_HOUSE_NUMBER;
        }

        if (!postalCode.matches(POSTAL_CODE_PATTERN)) {
            return ValidationResult.INVALID_POSTAL_CODE;
        }

        return ValidationResult.VALID;
    }

    private static boolean isNullOrEmpty(String... fields) {
        for (String field : fields) {
            if (field == null || field.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
