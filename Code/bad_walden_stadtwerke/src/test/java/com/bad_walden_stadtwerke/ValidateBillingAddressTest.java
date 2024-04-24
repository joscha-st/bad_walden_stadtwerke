package com.bad_walden_stadtwerke;

import static org.junit.jupiter.api.Assertions.*;

import com.bad_walden_stadtwerke.logic.ValidateBillingAddress.ValidationResult;
import com.bad_walden_stadtwerke.logic.ValidateBillingAddress;

import org.junit.jupiter.api.Test;

public class ValidateBillingAddressTest {

    @Test
    void testValidAddress() {
        assertEquals(ValidationResult.VALID, ValidateBillingAddress.validateBillingAddress("John", "Doe", "Main Street", "123A", "12345", "Springfield"),
                "Valid input should return VALID.");
    }

    @Test
    void testEmptyFields() {
        assertEquals(ValidationResult.EMPTY_FIELDS, ValidateBillingAddress.validateBillingAddress("", "", "", "", "", ""),
                "All empty fields should return EMPTY_FIELDS.");
    }

    @Test
    void testInvalidFirstName() {
        assertEquals(ValidationResult.INVALID_FIRST_NAME, ValidateBillingAddress.validateBillingAddress("John123", "Doe", "Main Street", "123A", "12345", "Springfield"),
                "First name with numbers should return INVALID_FIRST_NAME.");
    }

    @Test
    void testInvalidLastName() {
        assertEquals(ValidationResult.INVALID_LAST_NAME, ValidateBillingAddress.validateBillingAddress("John", "Doe1", "Main Street", "123A", "12345", "Springfield"),
                "Last name with numbers should return INVALID_LAST_NAME.");
    }

    @Test
    void testInvalidStreet() {
        assertEquals(ValidationResult.INVALID_STREET, ValidateBillingAddress.validateBillingAddress("John", "Doe", "Main Street 123", "123A", "12345", "Springfield"),
                "Street with numbers should return INVALID_STREET.");
    }

    @Test
    void testInvalidCity() {
        assertEquals(ValidationResult.INVALID_CITY, ValidateBillingAddress.validateBillingAddress("John", "Doe", "Main Street", "123A", "12345", "Springfield1"),
                "City with numbers should return INVALID_CITY.");
    }

    @Test
    void testInvalidHouseNumber() {
        assertEquals(ValidationResult.INVALID_HOUSE_NUMBER, ValidateBillingAddress.validateBillingAddress("John", "Doe", "Main Street", "One23", "12345", "Springfield"),
                "House number with invalid format should return INVALID_HOUSE_NUMBER.");
    }

    @Test
    void testInvalidPostalCode() {
        assertEquals(ValidationResult.INVALID_POSTAL_CODE, ValidateBillingAddress.validateBillingAddress("John", "Doe", "Main Street", "123A", "ABCDE", "Springfield"),
                "Postal code with letters should return INVALID_POSTAL_CODE.");
    }
}
