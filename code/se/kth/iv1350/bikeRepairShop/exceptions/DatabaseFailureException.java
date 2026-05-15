package se.kth.iv1350.bikeRepairShop.exceptions;

/**
 * Thrown when the customer registry database can not be reached.
 */
public class DatabaseFailureException extends RuntimeException {

    /**
     * Creates a new database failure exception.
     */
    public DatabaseFailureException() {
        super("Could not connect to customer registry database.");
    }
}
