package com.bad_walden_stadtwerke.model.types.language;

import java.util.Locale;

public interface LanguageChangeObserver {
	void onLanguageChange(Locale newLocale);
}