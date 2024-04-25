package com.bad_walden_stadtwerke.ui.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class LanguageController {

    static Locale language = Locale.GERMAN;
    private static final List<LanguageChangeObserver> observers = new ArrayList<>();

    public static Locale getLanguage() {
        return language;
    }

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

    public static void addObserver(LanguageChangeObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
        if (observers.contains(observer)) {
            throw new IllegalArgumentException("Observer is already added");
        }
        observers.add(observer);
    }

    public static void removeObserver(LanguageChangeObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
        if (!observers.contains(observer)) {
            throw new IllegalArgumentException("Observer is not added");
        }
        observers.remove(observer);
    }

    public static void notifyObservers() {
        List<LanguageChangeObserver> observersCopy = new ArrayList<>(observers);
        for (LanguageChangeObserver observer : observersCopy) {
            observer.onLanguageChange(getLanguage());
        }
    }

    public static Collection<LanguageChangeObserver> getObservers() {
        return observers;
    }
}