package com.bad_walden_stadtwerke;

import java.util.Locale;

public class LanguageController {

    static Locale language = Locale.ENGLISH;

    public static Locale getLanguage() {
        return language;
    }

    public static void setLanguage(Locale language) {
        LanguageController.language = language;
    }
}
