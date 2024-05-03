/**
 * The BillingAddressValidator class provides methods for validating billing address fields.
 * <p>
 * It contains regular expressions and validation rules for fields such as first name, last name,
 * street, house number, postal code, and city. Additionally, it checks the length constraints
 * for each field and ensures that none of the fields are null or empty.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
package com.bad_walden_stadtwerke.model.types.billingAddress;

import com.bad_walden_stadtwerke.controller.language.LanguageController;

import java.util.ResourceBundle;

public class BillingAddressValidator {
	/**
     * Regular expression pattern for alphabetic characters, spaces, dashes, and certain special characters.
     */
	public static final String ALPHABETIC_SPACE_DASH_PATTERN = "[a-zA-Z\\s\\-'äöüßÄÖÜéàè]+";
	/**
     * Regular expression pattern for alphabetic characters, spaces, dashes, dots, and certain special characters.
     */
	public static final String ALPHABETIC_SPACE_DASH_DOT_PATTERN = "[a-zA-Z\\s\\-.'äöüßÄÖÜéàè]+";
	/**
     * Regular expression pattern for house numbers.
     */
	public static final String HOUSE_NUMBER_PATTERN = "^\\d+[a-z]?$";
	/**
     * Regular expression pattern for postal codes.
     */
	public static final String POSTAL_CODE_PATTERN = "\\d{5}(-\\d{4})?";
	 /**
     * Minimum length for first name, last name, city, and street.
     */
	public static final int MIN_FIRSTNAME_LASTNAME_CITY_STREET_LENGTH = 2;
	/**
     * Minimum length for house number.
     */
	public static final int MIN_HOUSE_NUMBER_LENGTH = 1;
	/**
     * Maximum length for name fields (first name, last name).
     */
	public static final int MAX_NAME_LENGTH = 50;
	/**
     * Maximum length for street.
     */
	public static final int MAX_STREET_LENGTH = 100;
	/**
     * Maximum length for city.
     */
	public static final int MAX_CITY_LENGTH = 50;
	/**
     * Maximum length for house number.
     */
	public static final int MAX_HOUSE_NUMBER_LENGTH = 4;


	  /**
     * Validates the given billing address.
     *
     * @param billingAddress The billing address to validate.
     * @throws IllegalArgumentException If any field of the billing address is invalid.
     */
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

		validateLength(billingAddress.getFirstName(), MIN_FIRSTNAME_LASTNAME_CITY_STREET_LENGTH, MAX_NAME_LENGTH, "signUpFirstNameLabel", bundle);
		validateLength(billingAddress.getLastName(), MIN_FIRSTNAME_LASTNAME_CITY_STREET_LENGTH, MAX_NAME_LENGTH, "signUpLastNameLabel", bundle);
		validateLength(billingAddress.getStreet(), MIN_FIRSTNAME_LASTNAME_CITY_STREET_LENGTH, MAX_STREET_LENGTH, "signUpStreetLabel", bundle);
		validateLength(billingAddress.getCity(), MIN_FIRSTNAME_LASTNAME_CITY_STREET_LENGTH, MAX_CITY_LENGTH, "signUpCityLabel", bundle);
		validateLength(billingAddress.getHouseNumber(), MIN_HOUSE_NUMBER_LENGTH, MAX_HOUSE_NUMBER_LENGTH, "signUpPostalCodeLabel", bundle);
	}

/**
     * Checks if any of the fields of the billing address is null or empty.
     *
     * @param billingAddress The billing address to check.
     * @return {@code true} if any field is null or empty, otherwise {@code false}.
     */
	private static boolean areAnyFieldsNullOrEmpty(BillingAddress billingAddress) {
		return isNullOrEmpty(billingAddress.getFirstName(), billingAddress.getLastName(), billingAddress.getStreet(), billingAddress.getHouseNumber(), billingAddress.getPostalCode(), billingAddress.getCity());
	}

	/**
     * Validates the first name field of the billing address.
     *
     * @param firstName The first name to validate.
     * @param bundle    The resource bundle for retrieving error messages.
     * @throws IllegalArgumentException If the first name is invalid.
     */
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

	/**
     * Validates the length of a field.
     *
     * @param field     The field to validate.
     * @param minLength The minimum length allowed.
     * @param maxLength The maximum length allowed.
     * @param fieldName The name of the field (used for retrieving error messages).
     * @param bundle    The resource bundle for retrieving error messages.
     * @throws IllegalArgumentException If the length of the field is outside the specified range.
     */
	private static void validateLength(String field, int minLength, int maxLength, String fieldName, ResourceBundle bundle) {
		if (field.length() < minLength) {
			throw new IllegalArgumentException(bundle.getString(fieldName) + "\n" + bundle.getString("signUpTooShortErrorLabel"));
		}
		if (field.length() > maxLength) {
			throw new IllegalArgumentException(bundle.getString(fieldName) + "\n" + bundle.getString("signUpTooLongErrorLabel"));
		}
	}
}