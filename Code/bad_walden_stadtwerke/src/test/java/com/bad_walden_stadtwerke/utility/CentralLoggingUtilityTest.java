package com.bad_walden_stadtwerke.utility;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class CentralLoggingUtilityTest {

    private static final String logFilePath = System.getProperty("user.dir") + "/log.txt";

    @BeforeEach
    public void setup() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFilePath))) {
            bw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHandleException() {
        String category = "Testing";
        Exception exception = new IOException("Sample exception");
        CentralLoggingUtility.handleException(category, exception);

        try (BufferedReader br = new BufferedReader(new FileReader(logFilePath))) {
            String lastLine = "";
            String line;

            while ((line = br.readLine()) != null) {
                lastLine = line;
            }

            assertTrue(lastLine.contains("Error (Testing): java.io.IOException: Sample exception"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHandleEvent() {
        String category = "Testing";
        String event = "Sample event";
        CentralLoggingUtility.handleEvent(category, event);

        try (BufferedReader br = new BufferedReader(new FileReader(logFilePath))) {
            String lastLine = "";
            String line;

            while ((line = br.readLine()) != null) {
                lastLine = line;
            }

            assertTrue(lastLine.contains("Event (Testing): Sample event"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void cleanUpResources() {
        File logFile = new File(logFilePath);
        logFile.delete();
    }
}
