package com.bad_walden_stadtwerke.logic;
public class ValidateBillingAddress {
    // Constants for regular expressions used in validations
    private static final String ALPHABETIC_PATTERN = "[a-zA-Z]+";
    private static final String ALPHABETIC_SPACE_DASH_PATTERN = "[a-zA-Z\\s\\-]+";
    private static final String HOUSE_NUMBER_PATTERN = "^\\d+[A-Za-z]?$";
    private static final String POSTAL_CODE_PATTERN = "\\d{5}(-\\d{4})?";

    // Enum for possible validation results
    public enum ValidationResult {
        VALID, EMPTY_FIELDS, INVALID_FIRST_NAME, INVALID_LAST_NAME, INVALID_STREET, INVALID_CITY, INVALID_HOUSE_NUMBER, INVALID_POSTAL_CODE
    }

    // Method to validate the BillingAddress before a Customer Object is Created.
    // Returns an enum that indicates what was filled out wrong to give the user some information in the error handling later.
    public static ValidationResult validateBillingAddress(String firstName, String lastName, String street, String houseNumber, String postalCode, String city) {
        // Check if any field is null or empty using varargs for flexibility
        if (isNullOrEmpty(firstName, lastName, street, houseNumber, postalCode, city)) {
            return ValidationResult.EMPTY_FIELDS;
        }

        // Validate firstName (only alphabetic characters allowed)
        if (!firstName.matches(ALPHABETIC_PATTERN)) {
            return ValidationResult.INVALID_FIRST_NAME;
        }

        // Validate lastName (only alphabetic characters allowed)
        if (!lastName.matches(ALPHABETIC_PATTERN)) {
            return ValidationResult.INVALID_LAST_NAME;
        }

        // Validate street (only alphabetic characters, spaces, and dashes allowed)
        if (!street.matches(ALPHABETIC_SPACE_DASH_PATTERN)) {
            return ValidationResult.INVALID_STREET;
        }

        // Validate city (only alphabetic characters, spaces, and dashes allowed)
        if (!city.matches(ALPHABETIC_SPACE_DASH_PATTERN)) {
            return ValidationResult.INVALID_CITY;
        }

        // Validate houseNumber (positive integer, optionally followed by a letter)
        if (!houseNumber.matches(HOUSE_NUMBER_PATTERN)) {
            return ValidationResult.INVALID_HOUSE_NUMBER;
        }

        // Validate postalCode (format: XXXXX or XXXXX-XXXX, where X is a digit)
        if (!postalCode.matches(POSTAL_CODE_PATTERN)) {
            return ValidationResult.INVALID_POSTAL_CODE;
        }

        // All validations passed
        return ValidationResult.VALID;
    }

    // Helper method to check if any of a group of strings are null or empty
    private static boolean isNullOrEmpty(String... fields) {
        for (String field : fields) {
            if (field == null || field.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
