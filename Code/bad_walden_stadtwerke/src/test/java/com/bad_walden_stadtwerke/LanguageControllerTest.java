package com.bad_walden_stadtwerke;

import com.bad_walden_stadtwerke.ui.controller.Language;
import com.bad_walden_stadtwerke.ui.controller.LanguageChangeObserver;
import com.bad_walden_stadtwerke.ui.controller.LanguageController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class TestObserver implements LanguageChangeObserver {
    boolean wasNotified = false;

    @Override
    public void onLanguageChange(Locale newLocale) {
        wasNotified = true;
    }

    public boolean wasNotified() {
        return wasNotified;
    }
}

class LanguageControllerTest {

    private static final Locale DEFAULT_LANGUAGE = Locale.GERMAN;
    private static final Locale SUPPORTED_LANGUAGE = Locale.ENGLISH;
    private static final Locale UNSUPPORTED_LANGUAGE = Locale.FRENCH;

    private TestObserver testObserver;

    @BeforeEach
    void setUp() {
        LanguageController.setLanguage(DEFAULT_LANGUAGE);
        testObserver = new TestObserver();
        LanguageController.addObserver(testObserver);
    }

    @Test
    void whenSetLanguage_thenLanguageIsSet() {
        LanguageController.setLanguage(SUPPORTED_LANGUAGE);
        assertEquals(SUPPORTED_LANGUAGE, LanguageController.getLanguage());
    }

    @Test
    void whenSetLanguageNull_thenThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> LanguageController.setLanguage(null));
    }

    @Test
    void whenAddObserver_thenObserverIsAdded() {
        int initialSize = LanguageController.getObservers().size();
        LanguageController.addObserver(new TestObserver());
        assertEquals(initialSize + 1, LanguageController.getObservers().size());
    }

    @Test
    void whenRemoveObserver_thenObserverIsRemoved() {
        int initialSize = LanguageController.getObservers().size();
        LanguageController.removeObserver(testObserver);
        assertEquals(initialSize - 1, LanguageController.getObservers().size());
    }

    @Test
    void whenNotifyObservers_thenObserversAreNotified() {
        LanguageController.setLanguage(SUPPORTED_LANGUAGE);
        assertTrue(testObserver.wasNotified());
    }

    @Test
    void whenSetUnsupportedLanguage_thenThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> LanguageController.setLanguage(UNSUPPORTED_LANGUAGE));
    }

    @Test
    void whenCheckSupportedLanguage_thenReturnsTrue() {
        assertTrue(Language.isSupported(SUPPORTED_LANGUAGE));
    }

    @Test
    void whenCheckUnsupportedLanguage_thenReturnsFalse() {
        assertFalse(Language.isSupported(UNSUPPORTED_LANGUAGE));
    }

    @Test
    void whenAddNullObserver_thenThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> LanguageController.addObserver(null));
    }

    @Test
    void whenAddSameObserver_thenThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> LanguageController.addObserver(testObserver));
    }

    @Test
    void whenRemoveNullObserver_thenThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> LanguageController.removeObserver(null));
    }

    @Test
    void whenRemoveNonExistentObserver_thenThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> LanguageController.removeObserver(new TestObserver()));
    }

    @Test
    void whenSetSameLanguage_thenObserversNotNotified() {
        LanguageController.setLanguage(SUPPORTED_LANGUAGE);
        testObserver.wasNotified = false;
        LanguageController.setLanguage(SUPPORTED_LANGUAGE);
        assertFalse(testObserver.wasNotified());
    }
}