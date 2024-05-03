package com.bad_walden_stadtwerke.model.types.language;

import java.util.Locale;

/**
 * The {@code LanguageChangeObserver} interface defines a method for receiving
 * notifications about language changes within an application. Implementing
 * this interface allows an object to be informed of locale changes,
 * typically triggered by user actions or system settings changes.
 */
public interface LanguageChangeObserver {
    /**
     * Invoked when the application's language has been changed.
     *
     * @param newLocale The new {@link Locale} that has been set, representing
     *                  the new language and regional settings. This locale is
     *                  used to adapt the application's language-related aspects
     *                  such as labels, instructions, and system messages.
     */
    void onLanguageChange(Locale newLocale);
}