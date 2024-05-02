package com.bad_walden_stadtwerke.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CentralLoggingUtility {

	private static final String logFilePath = System.getProperty("user.dir") + "/log.txt";

	public static void handleException(String category, Exception e) {
		logAction("Error ("+category+"): " + e.getCause().toString());
	}

	public static void handleEvent(String category, String event) {
		logAction("Event ("+category+"): " + event);
	}

	private static void logAction(String action) {
		LocalDateTime timestamp = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTimestamp = timestamp.format(formatter);
		String logMessage = "[" + formattedTimestamp + "] " + action;
		writeLogMessageToFile(logMessage);
	}

	private static void writeLogMessageToFile(String logMessage) {
		try (FileWriter fileWriter = new FileWriter(logFilePath, true)) {
			fileWriter.write(logMessage + "\n");
			System.out.println("Logged action: " + logMessage);
		} catch (IOException e) {
			CentralLoggingUtility.handleException("Utility", e);
			System.err.println("Error writing to log file: " + e.getMessage());
		}
	}
}
