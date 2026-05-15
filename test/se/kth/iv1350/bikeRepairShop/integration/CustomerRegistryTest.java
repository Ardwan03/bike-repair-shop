package se.kth.iv1350.bikeRepairShop.integration;

import se.kth.iv1350.bikeRepairShop.model.businessLogic.CustomerDetails;
import se.kth.iv1350.bikeRepairShop.exceptions.CustomerNotFoundException;
import se.kth.iv1350.bikeRepairShop.exceptions.DatabaseFailureException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerRegistryTest {
    private CustomerRegistry customerRegistry;

    @BeforeEach
    public void setUp(){
        customerRegistry = CustomerRegistry.getInstance();
    }

    @Test
    public void getCustomerDetailsNotNull() throws CustomerNotFoundException{
        String phoneNumber = "0790781234";

        CustomerDetails storeInHere = customerRegistry.getCustomerDetails(phoneNumber);

        assertNotNull(storeInHere, "Customer should exist.");
    }

    @Test
    public void throwsDatabaseFailureException(){
        String phoneNumber = "FAIL";

        assertThrows(DatabaseFailureException.class, () -> {
            customerRegistry.getCustomerDetails(phoneNumber);},
            "DatabaseFailureException should be thrown");
    }

    @Test
    public void throwsCustomerNotFoundException(){
        String phoneNumber = "0000000000";

        assertThrows(CustomerNotFoundException.class, () -> {
            customerRegistry.getCustomerDetails(phoneNumber);}, 
            "CustomerNotFoundException should be thrown.");
    }
}
