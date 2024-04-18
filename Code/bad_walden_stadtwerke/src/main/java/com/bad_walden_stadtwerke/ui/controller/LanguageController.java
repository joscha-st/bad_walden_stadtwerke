package com.bad_walden_stadtwerke.ui.controller;

import java.util.Locale;

public class LanguageController {

    static Locale language = Locale.GERMAN;

    public static Locale getLanguage() {
        return language;
    }

    public static void setLanguage(Locale language) {
        LanguageController.language = language;
    }
}
