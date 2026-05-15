package se.kth.iv1350.bikeRepairShop.logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Writes log messages to a file.
 */
public class FileLogger implements Logger <String> {
    private PrintWriter logWriter;

    /**
     * Creates a new file logger.
     */
    public FileLogger() {
        try {
            logWriter = new PrintWriter(new FileWriter("log.txt", true), true);
        } catch (IOException ioe) {
            System.out.println("Can not log.");
            ioe.printStackTrace();
        }
    }

    /**
     * Writes a message to the log file.
     * @param message the message to log.
     */
    public void logMessage(String message) {
        logWriter.println(message);
    }
}