package com.bad_walden_stadtwerke.model.types.language;

import java.util.Locale;

/**
 * The {@code Language} enum represents the supported languages within the application,
 * encapsulating each with its respective {@link Locale}.
 * This enum facilitates language management by providing a structured way to handle
 * different languages supported by the application.
 */

public enum Language {
    /**
     * Represents the German language setting.
     */

    GERMAN(Locale.GERMAN),

    /**
     * Represents the English language setting.
     */

    ENGLISH(Locale.ENGLISH);


    private final Locale locale;


    /**
     * Constructs a new {@code Language} instance with the specified locale.
     *
     * @param locale The {@link Locale} associated with this language.
     */

    Language(Locale locale) {
        this.locale = locale;
    }

    /**
     * Returns the locale associated with this language.
     *
     * @return The {@link Locale} of this language.
     */

    public static boolean isSupported(Locale locale) {
        for (Language language : Language.values()) {
            if (language.getLocale().equals(locale)) {
                return true;
            }
        }
        return false;
    }

    public Locale getLocale() {
        return locale;
    }
}