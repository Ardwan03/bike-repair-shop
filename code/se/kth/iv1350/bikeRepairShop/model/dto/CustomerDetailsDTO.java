package se.kth.iv1350.bikeRepairShop.model.dto;

/**
 * Represents a data transfer object containing customer and bike details.
 * This object is used to transfer read-only data to the view layer.
 */
public class CustomerDetailsDTO {
    private final String name;
    private final String phoneNumber;
    private final String email;
    private final String brand;
    private final String model;
    private final String serialNumber;

    /**
     * Creates a new customer details data transfer object.
     * @param name the customer's name
     * @param phoneNumber the customer's phone number
     * @param email the customer's email address
     * @param brand the brand of the customer's bike
     * @param model the model of the customer's bike
     * @param serialNumber the serial number of the customer's bike
     */
    public CustomerDetailsDTO(String name, String phoneNumber, String email, 
                              String brand, String model, String serialNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    /**
     * @return the customer's name
     */
    public String getName(){ 
        return name;
    }

    /**
     * @return the customer's phone number
     */
    public String getPhoneNumber(){ 
        return phoneNumber;
    }

    /**
     * @return the customer's email address
     */
    public String getEmail(){ 
        return email;
    }

    /**
     * @return the brand of the customer's bike
     */
    public String getBrand(){ 
        return brand;
    }

    /**
     * @return the model of the customer's bike
     */
    public String getModel(){ 
        return model;
    }

    /**
     * @return the serial number of the customer's bike
     */
    public String getSerialNumber(){ 
        return serialNumber;
    }
}
