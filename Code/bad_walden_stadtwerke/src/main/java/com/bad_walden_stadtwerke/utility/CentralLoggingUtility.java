package com.bad_walden_stadtwerke.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The CentralLoggingUtility class provides methods for handling exceptions and events and logging them to a central log file.
 * It utilizes a static log file path and standard date-time formatting for log entries.
 */
public class CentralLoggingUtility {

	/**
	 * The path to the log file.
	 */
	private static final String logFilePath = System.getProperty("user.dir") + "/log.txt";

	/**
	 * Handles exceptions by logging the error message along with the specified category.
	 *
	 * @param category The category of the exception.
	 * @param e        The exception to be handled.
	 */
	public static void handleException(String category, Exception e) {
		logAction("Error ("+category+"): " + e);
	}

	/**
	 * Handles events by logging the event message along with the specified category.
	 *
	 * @param category The category of the event.
	 * @param event    The event message to be logged.
	 */
	public static void handleEvent(String category, String event) {
		logAction("Event ("+category+"): " + event);
	}

	/**
	 * Logs a specified action along with a timestamp in the format "yyyy-MM-dd HH:mm:ss".
	 *
	 * @param action The action to be logged.
	 */
	private static void logAction(String action) {
		LocalDateTime timestamp = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTimestamp = timestamp.format(formatter);
		String logMessage = "[" + formattedTimestamp + "] " + action;
		writeLogMessageToFile(logMessage);
	}

	/**
	 * Writes the specified log message to the log file.
	 *
	 * @param logMessage The log message to be written to the file.
	 */
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
