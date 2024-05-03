/**
 * The LanguageController class is responsible for managing the language settings of the application.
 * It allows setting and retrieving the current language, adding and removing language change observers, and notifying observers
 * when the language is changed.
 * <p>
 * This controller interacts with {@link com.bad_walden_stadtwerke.model.types.language.Language} to check if a language is supported
 * and with {@link com.bad_walden_stadtwerke.model.types.language.LanguageChangeObserver} to notify observers when the language changes.
 * </p>
 *
 * @version 1.0
 * @since 1.0
 */
package com.bad_walden_stadtwerke.controller.language;

import com.bad_walden_stadtwerke.model.types.language.Language;
import com.bad_walden_stadtwerke.model.types.language.LanguageChangeObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class LanguageController {

	private static final List<LanguageChangeObserver> observers = new ArrayList<>();
	static Locale language = Locale.GERMAN;

	/**
     * Retrieves the current language.
     *
     * @return The current language as a Locale object.
     */
	public static Locale getLanguage() {
		return language;
	}

	/**
     * Sets the language to the specified Locale.
     *
     * @param language The new language to set.
     * @throws IllegalArgumentException if the provided language is null or not supported.
     */
	public static void setLanguage(Locale language) {
		if (language == null) {
			throw new IllegalArgumentException("Language must not be null");
		}
		//check if language is allowed
		if (!Language.isSupported(language)) {
			throw new IllegalArgumentException("Language is not supported");
		}

		if (LanguageController.language.equals(language)) {
			return;
		}
		LanguageController.language = language;
		notifyObservers();
	}

	/**
     * Adds a language change observer.
     *
     * @param observer The observer to add.
     * @throws IllegalArgumentException if the provided observer is null or already added.
     */
	public static void addObserver(LanguageChangeObserver observer) {
		if (observer == null) {
			throw new IllegalArgumentException("Observer cannot be null");
		}
		if (observers.contains(observer)) {
			throw new IllegalArgumentException("Observer is already added");
		}
		observers.add(observer);
	}

	/**
     * Removes a language change observer.
     *
     * @param observer The observer to remove.
     * @throws IllegalArgumentException if the provided observer is null or not added.
     */
	public static void removeObserver(LanguageChangeObserver observer) {
		if (observer == null) {
			throw new IllegalArgumentException("Observer cannot be null");
		}
		if (!observers.contains(observer)) {
			throw new IllegalArgumentException("Observer is not added");
		}
		observers.remove(observer);
	}

	 /**
     * Notifies all language change observers.
     */
	public static void notifyObservers() {
		List<LanguageChangeObserver> observersCopy = new ArrayList<>(observers);
		for (LanguageChangeObserver observer : observersCopy) {
			observer.onLanguageChange(getLanguage());
		}
	}

	/**
     * Retrieves all language change observers.
     *
     * @return A collection of language change observers.
     */
	public static Collection<LanguageChangeObserver> getObservers() {
		return observers;
	}
}