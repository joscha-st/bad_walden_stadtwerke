package com.bad_walden_stadtwerke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.bad_walden_stadtwerke.ui.components.errorHandling.ExceptionPopup;
import org.junit.jupiter.api.Test;

public class ExceptionPopupTest{

    @Test
    void testProcessMessageWithColon() {
        String input = "Java.Illegal.Argument.Exception:Bitte das Feld: 'Name' richtig ausfüllen";
        String expected = "Bitte das Feld: 'Name' richtig ausfüllen";
        String actual = ExceptionPopup.processMessage(input);
        assertEquals(expected, actual);
    }
    @Test
    void testProcessMessageWithMultipleColonsDirectlyAfterFirstColon() {
        String input = "Java.Illegal.Argument.Exception:::::";
        String expected = "::::";
        String actual = ExceptionPopup.processMessage(input);
        assertEquals(expected, actual);
    }
}
