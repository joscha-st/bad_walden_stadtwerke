package com.bad_walden_stadtwerke.components.errorHandling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Unit test {@link ExceptionPopup}
 */
public class ExceptionPopupTest {

	@Test
	void whenMessageWithColon_thenSubstringAfterFirstColonIsReturned() {
		String input = "Java.Illegal.Argument.Exception:Bitte das Feld: 'Name' richtig ausfüllen";
		String expected = "Bitte das Feld: 'Name' richtig ausfüllen";
		String actual = ExceptionPopup.processMessage(input);
		assertEquals(expected, actual);
	}

	@Test
	void whenMessageWithMultipleColonsDirectlyAfterFirstColon_thenSubstringAfterFirstColonIsReturned() {
		String input = "Java.Illegal.Argument.Exception:::::abc::";
		String expected = "::::abc::";
		String actual = ExceptionPopup.processMessage(input);
		assertEquals(expected, actual);
	}

	@Test
	void whenMessageWithoutColon_thenSameMessageIsReturned() {
		String input = "Java.Illegal.Argument.Exception";
		String expected = "Java.Illegal.Argument.Exception";
		String actual = ExceptionPopup.processMessage(input);
		assertEquals(expected, actual);
	}
}
