package se.kth.iv1350.bikeRepairShop.integration;

import se.kth.iv1350.bikeRepairShop.model.businessLogic.CustomerDetails;
import se.kth.iv1350.bikeRepairShop.exceptions.CustomerNotFoundException;
import se.kth.iv1350.bikeRepairShop.exceptions.DatabaseFailureException;
import se.kth.iv1350.bikeRepairShop.model.businessLogic.Bike;

import java.util.ArrayList;

/**
 * Stores and provides access to customer details.
 */
public class CustomerRegistry {
    private ArrayList<CustomerDetails> storedCustomers = new ArrayList<>();
    private static CustomerRegistry customerRegistry;

    /**
     * Creates a new registry and initializes it with fake data.
     * Only the CustomerRegistry class can create an instance.
     */
    private CustomerRegistry(){
        addFakeData();
    }

    /**
     * Returns the single instance of the customer registry.
     * @return the customer registry instance.
     */
    public static CustomerRegistry getInstance() {
        if(customerRegistry == null)
            customerRegistry = new CustomerRegistry();

        return customerRegistry;
    }

    /**
     * Retrieves customer details using a phone number.
     * @param phoneNumber the customer's phone number
     * @return the matching customer details
     * @throws CustomerNotFoundException if no customer exists for the phone number.
     */
    public CustomerDetails getCustomerDetails(String phoneNumber) 
    throws CustomerNotFoundException{
        if(phoneNumber.equals("FAIL"))
            throw new DatabaseFailureException();

        for (int i = 0; i < storedCustomers.size(); i++){
            CustomerDetails currentCustomer = storedCustomers.get(i);

            if (currentCustomer.getPhoneNumber().equals(phoneNumber))
                return currentCustomer;
        }
        throw new CustomerNotFoundException(phoneNumber);
    }

    /**
     * Adds fake data to the registry.
     */
    private void addFakeData() {
        Bike fakeBike = new Bike("BMX", "Crazy", "ABC123");
        
        storedCustomers.add(new CustomerDetails("Morgan Falk", "0790781234",
        "MF@gmail.com", fakeBike));
    }
}