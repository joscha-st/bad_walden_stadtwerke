package com.bad_walden_stadtwerke.utility;

public class CentralLoggingUtility {

	public static void handleError(String category, Error e) {
		System.out.println("Error logged ("+category+"): " + e.getCause().toString());
	}

	public static void handleEvent(String category, String event) {
		System.out.println("Event logged ("+category+"): " + event);
	}
}
