package se.kth.iv1350.bikeRepairShop.exceptions;

/**
 * Thrown when no customer is found for a searched phone number.
 */
public class CustomerNotFoundException extends Exception {

    /**
     * Creates a new exception with a message describing the error.
     * @param phoneNumber the phone number that could not be found.
     */
    public CustomerNotFoundException(String phoneNumber) {
        super("No customer found with phone number: " + phoneNumber);
    }
}