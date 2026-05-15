package se.kth.iv1350.bikeRepairShop.logging;

/**
 * Contains a method for writing log messages.
 */
public interface Logger <T>{

    /**
     * Writes a message to the log.
     * @param message the message to log.
     */
    void logMessage(T message);
}