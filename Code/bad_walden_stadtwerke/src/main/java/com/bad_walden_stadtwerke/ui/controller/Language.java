package com.bad_walden_stadtwerke.ui.controller;

import java.util.Locale;

public enum Language {
    GERMAN(Locale.GERMAN),
    ENGLISH(Locale.ENGLISH);

    private final Locale locale;

    Language(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public static boolean isSupported(Locale locale) {
        for (Language language : Language.values()) {
            if (language.getLocale().equals(locale)) {
                return true;
            }
        }
        return false;
    }
}