package com.bad_walden_stadtwerke;

import com.bad_walden_stadtwerke.logic.BillingAddressValidator;
import com.bad_walden_stadtwerke.logic.BillingAddress;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BillingAddressValidatorTest {

    private static final String VALID_FIRST_NAME = "John";
    private static final String VALID_LAST_NAME = "Doe";
    private static final String VALID_STREET = "Main Street";
    private static final String VALID_HOUSE_NUMBER = "123";
    private static final String VALID_POSTAL_CODE = "12345";
    private static final String VALID_CITY = "Bad Walden";

    @Test
    public void whenFirstNameIsInvalid_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress("123", VALID_LAST_NAME, VALID_STREET, VALID_HOUSE_NUMBER, VALID_POSTAL_CODE, VALID_CITY));
    }

    @Test
    public void whenLastNameIsInvalid_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, "123", VALID_STREET, VALID_HOUSE_NUMBER, VALID_POSTAL_CODE, VALID_CITY));
    }

    @Test
    public void whenStreetIsInvalid_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, "123", VALID_HOUSE_NUMBER, VALID_POSTAL_CODE, VALID_CITY));
    }

    @Test
    public void whenHouseNumberIsInvalid_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, "-1", VALID_POSTAL_CODE, VALID_CITY));
    }

    @Test
    public void whenHouseNumberIsInvalidWithOnlyLetters_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, "ABC", VALID_POSTAL_CODE, VALID_CITY));
    }

    @Test
    public void whenPostalCodeIsInvalid_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, VALID_HOUSE_NUMBER, "1234", VALID_CITY));
    }

    @Test
    public void whenCityIsInvalid_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, VALID_HOUSE_NUMBER, VALID_POSTAL_CODE, "123"));
    }

    @Test
    public void whenBillingAddressIsValid_thenValidationSucceeds() {
        assertDoesNotThrow(()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, VALID_HOUSE_NUMBER, VALID_POSTAL_CODE, VALID_CITY));
    }

    @Test
    public void whenBillingAddressIsValidWithHouseNumberAndLetters_thenValidationSucceeds() {
        assertDoesNotThrow(()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, "1a", VALID_POSTAL_CODE, VALID_CITY));
    }

    @Test
    public void whenAllFieldsAreNull_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(null, null, null, null, null, null));
    }

    @Test
    public void whenAllFieldsAreEmpty_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress("", "", "", "", "", ""));
    }

    @Test
    public void whenHouseNumberIsNull_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, null, VALID_POSTAL_CODE, VALID_CITY));
    }

    @Test
    public void whenHouseNumberIsEmpty_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, "", VALID_POSTAL_CODE, VALID_CITY));
    }

    @Test
    public void whenPostalCodeIsNull_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, VALID_HOUSE_NUMBER, null, VALID_CITY));
    }

    @Test
    public void whenPostalCodeIsEmpty_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, VALID_HOUSE_NUMBER, "", VALID_CITY));
    }

    @Test
    public void whenCityIsNull_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, VALID_HOUSE_NUMBER, VALID_POSTAL_CODE, null));
    }

    @Test
    public void whenCityIsEmpty_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, VALID_HOUSE_NUMBER, VALID_POSTAL_CODE, ""));
    }

    @Test
    public void whenHouseNumberHasMoreThanOneLetter_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, "123aa", VALID_POSTAL_CODE, VALID_CITY));
    }

    @Test
    public void whenHouseNumberIsOnlyLetter_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, "a", VALID_POSTAL_CODE, VALID_CITY));
    }

    @Test
    public void whenPostalCodeIsMoreThanFiveDigits_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, VALID_HOUSE_NUMBER, "123456", VALID_CITY));
    }

    @Test
    public void whenCityContainsNumbers_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, VALID_LAST_NAME, VALID_STREET, VALID_HOUSE_NUMBER, VALID_POSTAL_CODE, "Bad123"));
    }

    @Test
    public void whenFirstNameContainsNonAlphabeticCharacters_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress("John123", VALID_LAST_NAME, VALID_STREET, VALID_HOUSE_NUMBER, VALID_POSTAL_CODE, VALID_CITY));
    }

    @Test
    public void whenLastNameContainsNonAlphabeticCharacters_thenValidationFails() {
        assertThrows(IllegalArgumentException.class, ()->new BillingAddress(VALID_FIRST_NAME, "Doe123", VALID_STREET, VALID_HOUSE_NUMBER, VALID_POSTAL_CODE, VALID_CITY));
    }
}