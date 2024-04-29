package com.bad_walden_stadtwerke.ui.controller;

import java.util.Locale;

public interface LanguageChangeObserver {
	void onLanguageChange(Locale newLocale);
}