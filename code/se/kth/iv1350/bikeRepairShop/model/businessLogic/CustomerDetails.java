package se.kth.iv1350.bikeRepairShop.model.businessLogic;

/**
 * Represents customer details including contact information and associated bike.
 */
public class CustomerDetails {
    private String name;
    private String phoneNumber;
    private String email;
    private Bike bike;

    /**
     * Creates a new set of customer details.
     * @param name the customer's name.
     * @param phoneNumber the customer's phone number.
     * @param email the customer's email address.
     * @param bike the customer's bike.
     */
    public CustomerDetails(String name, String phoneNumber, String email, Bike bike) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bike = bike;
    }

    /**
     * Gets the bike details.
     * @return the customer's bike.
     */
    public Bike getBike() {
        return bike;
    }

    /**
     * Gets the customer's name.
     * @return the customer's name.
     */
    public String getName(){ 
        return name;
    }

    /**
     * Gets the customer's phone number.
     * @return the customer's phone number.
     */
    public String getPhoneNumber(){ 
        return phoneNumber;
    }

    /**
     * Gets the customer's email address.
     * @return the customer's email address.
     */
    public String getEmail(){ 
        return email;
    }
}