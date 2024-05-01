/**
 * Handles language settings and notifications for language changes within the application.
 * This package provides the {@code LanguageController} class which manages application-wide language settings
 * and coordinates the notification of language change events to registered observers.
 */
package com.bad_walden_stadtwerke.controller.language;

import com.bad_walden_stadtwerke.model.types.language.Language;
import com.bad_walden_stadtwerke.model.types.language.LanguageChangeObserver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Manages the language settings for the application and notifies observers about language changes.
 */
public class LanguageController {

    private static final List<LanguageChangeObserver> observers = new ArrayList<>();
    static Locale language = Locale.GERMAN;

    /**
     * Retrieves the current application language setting.
     * 
     * @return The current {@link Locale} representing the application's language.
     */
    public static Locale getLanguage() {
        return language;
    }

    /**
     * Sets the application's language and notifies all registered observers about the language change if it is new.
     * Throws IllegalArgumentException if the language is not supported or null.
     * 
     * @param language The new {@link Locale} to set as the application's language.
     */
    public static void setLanguage(Locale language) {
        if (language == null) {
            throw new IllegalArgumentException("Language must not be null");
        }
        if (!Language.isSupported(language)) {
            throw new IllegalArgumentException("Language is not supported");
        }

        if (!LanguageController.language.equals(language)) {
            LanguageController.language = language;
            notifyObservers();
        }
    }

    /**
     * Registers an observer to be notified of language changes.
     * Throws IllegalArgumentException if the observer is already added or is null.
     * 
     * @param observer The {@link LanguageChangeObserver} to add.
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
     * Unregisters an observer so it no longer receives notifications of language changes.
     * Throws IllegalArgumentException if the observer is not added or is null.
     * 
     * @param observer The {@link LanguageChangeObserver} to remove.
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
     * Notifies all registered observers about a change in the application's language setting.
     */
    private static void notifyObservers() {
        List<LanguageChangeObserver> observersCopy = new ArrayList<>(observers);
        for (LanguageChangeObserver observer : observersCopy) {
            observer.onLanguageChange(getLanguage());
        }
    }

    /**
     * Provides a collection of all registered observers.
     * 
     * @return A collection of {@link LanguageChangeObserver} objects.
     */
    public static Collection<LanguageChangeObserver> getObservers() {
        return observers;
    }
}
