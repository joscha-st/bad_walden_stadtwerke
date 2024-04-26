package com.bad_walden_stadtwerke.logic;

import com.bad_walden_stadtwerke.ui.controller.LanguageController;

import java.util.Locale;
import java.util.ResourceBundle;

public class BillingAddressValidator {
    public static final String ALPHABETIC_PATTERN = "[a-zA-Z]+";
    public static final String ALPHABETIC_SPACE_DASH_PATTERN = "[a-zA-Z\\s\\-]+";
    public static final String ALPHABETIC_SPACE_DASH_DOT_PATTERN = "[a-zA-Z\\s\\-\\.]+";
    public static final String HOUSE_NUMBER_PATTERN = "^\\d+[a-z]?$";
    public static final String POSTAL_CODE_PATTERN = "\\d{5}(-\\d{4})?";
    public static final int MAX_NAME_LENGTH = 50;
    public static final int MAX_STREET_LENGTH = 100;
    public static final int MAX_CITY_LENGTH = 50;
    public static final int MAX_POSTAL_CODE_LENGTH = 10;

    public static void validateBillingAddress(BillingAddress billingAddress) {
        ResourceBundle bundle = ResourceBundle.getBundle("Bundle", LanguageController.getLanguage());

        if (areAnyFieldsNullOrEmpty(billingAddress)) {
            throw new IllegalArgumentException(bundle.getString("signUpAddressEmptyErrorLabel"));
        }

        validateFirstName(billingAddress.getFirstName(), bundle);
        validateLastName(billingAddress.getLastName(), bundle);
        validateStreet(billingAddress.getStreet(), bundle);
        validateCity(billingAddress.getCity(), bundle);
        validateHouseNumber(billingAddress.getHouseNumber(), bundle);
        validatePostalCode(billingAddress.getPostalCode(), bundle);

        validateLength(billingAddress.getFirstName(), MAX_NAME_LENGTH, "signUpFirstNameLabel", bundle);
        validateLength(billingAddress.getLastName(), MAX_NAME_LENGTH, "signUpLastNameLabel", bundle);
        validateLength(billingAddress.getStreet(), MAX_STREET_LENGTH, "signUpStreetLabel", bundle);
        validateLength(billingAddress.getCity(), MAX_CITY_LENGTH, "signUpCityLabel", bundle);
        validateLength(billingAddress.getPostalCode(), MAX_POSTAL_CODE_LENGTH, "signUpPostalCodeLabel", bundle);
    }


    private static boolean areAnyFieldsNullOrEmpty(BillingAddress billingAddress) {
        return isNullOrEmpty(billingAddress.getFirstName(), billingAddress.getLastName(), billingAddress.getStreet(),
                billingAddress.getHouseNumber(), billingAddress.getPostalCode(), billingAddress.getCity());
    }

    private static void validateFirstName(String firstName, ResourceBundle bundle) {
        if (!firstName.matches(ALPHABETIC_SPACE_DASH_PATTERN)) {
            throw new IllegalArgumentException(bundle.getString("signUpFirstNameLabel") + "\n" + bundle.getString("signUpAddressErrorLabel"));
        }
    }

    private static void validateLastName(String lastName, ResourceBundle bundle) {
        if (!lastName.matches(ALPHABETIC_SPACE_DASH_PATTERN)) {
            throw new IllegalArgumentException(bundle.getString("signUpLastNameLabel") + "\n" + bundle.getString("signUpAddressErrorLabel"));
        }
    }

    private static void validateStreet(String street, ResourceBundle bundle) {
        if (!street.matches(ALPHABETIC_SPACE_DASH_DOT_PATTERN)) {
            throw new IllegalArgumentException(bundle.getString("signUpStreetLabel") + "\n" + bundle.getString("signUpAddressErrorLabel"));
        }
    }

    private static void validateCity(String city, ResourceBundle bundle) {
        if (!city.matches(ALPHABETIC_SPACE_DASH_PATTERN)) {
            throw new IllegalArgumentException(bundle.getString("signUpCityLabel") + "\n" + bundle.getString("signUpAddressErrorLabel"));
        }
    }

    private static void validateHouseNumber(String houseNumber, ResourceBundle bundle) {
        if (!houseNumber.matches(HOUSE_NUMBER_PATTERN)) {
            throw new IllegalArgumentException(bundle.getString("signUpNumberLabel") + "\n" + bundle.getString("signUpAddressErrorLabel"));
        }
    }

    private static void validatePostalCode(String postalCode, ResourceBundle bundle) {
        if (!postalCode.matches(POSTAL_CODE_PATTERN)) {
            throw new IllegalArgumentException(bundle.getString("signUpPostalCodeLabel") + "\n" + bundle.getString("signUpAddressErrorLabel"));
        }
    }

    private static boolean isNullOrEmpty(String... fields) {
        for (String field : fields) {
            if (field == null || field.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static boolean validateLength(String field, int maxLength, String fieldName, ResourceBundle bundle) {
        if (field.length() > maxLength) {
            throw new IllegalArgumentException(bundle.getString(fieldName) + "\n" + bundle.getString("signUpTooLongErrorLabel"));
        }
        return true;
    }
}