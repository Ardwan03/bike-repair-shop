package se.kth.iv1350.bikeRepairShop.model.businessLogic;

/**
 * Represents a bike with basic identification details.
 */
public class Bike {
    private String brand;
    private String model;
    private String serialNumber;

    /**
     * Creates a new bike.
     * @param brand the brand of the bike.
     * @param model the model of the bike.
     * @param serialNumber the serial number of the bike.
     */
    public Bike(String brand, String model, String serialNumber) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
    }

    /**
     * Gets the brand of the bike.
     * @return the brand of the bike.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Gets the model of the bike.
     * @return the model of the bike.
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the serial number of the bike.
     * @return the serial number of the bike.
     */
    public String getSerialNumber() {
        return serialNumber;
    }
}
