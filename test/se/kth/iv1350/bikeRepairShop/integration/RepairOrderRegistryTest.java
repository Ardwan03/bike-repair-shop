package se.kth.iv1350.bikeRepairShop.integration;

import se.kth.iv1350.bikeRepairShop.model.businessLogic.RepairOrder;
import se.kth.iv1350.bikeRepairShop.model.businessLogic.CustomerDetails;
import se.kth.iv1350.bikeRepairShop.model.businessLogic.Bike;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepairOrderRegistryTest{
    private RepairOrderRegistry repairOrderRegistry;

    @BeforeEach
    public void setUp(){
        repairOrderRegistry = new RepairOrderRegistry();
    }

    @Test
    public void createRepairOrderReturnsNotNull(){
        Bike bike = new Bike("BMX", "Crazy", "ABC123");
        CustomerDetails customerDetails = new CustomerDetails(
            "Morgan Falk", "0790781234", "MF@gmail.com", bike);
        
        String description = "The brakes do not work";
        String date = java.time.LocalDate.now().toString();

        RepairOrder repairOrder = repairOrderRegistry.createRepairOrder(
            customerDetails, description, date, bike);
        
        assertNotNull(repairOrder, "createRepairOrder returned null when creating repair order.");
    }

    @Test
    public void createRepairOrderReturnsCorrectBikeDetails(){
        Bike bike = new Bike("BMX", "Crazy", "ABC123");
        CustomerDetails customerDetails = new CustomerDetails(
            "Morgan Falk", "0790781234", "MF@gmail.com", bike);
        
        String description = "The brakes do not work";
        String date = java.time.LocalDate.now().toString();

        RepairOrder repairOrder = repairOrderRegistry.createRepairOrder(
            customerDetails, description, date, bike);
        
        assertEquals(bike, repairOrder.getBikeDetails(), 
            "Wrong bikeDetails returned when creating repair order.");
    }

    @Test
    public void createRepairOrderReturnsCorrectEstimation(){
        Bike bike = new Bike("BMX", "Crazy", "ABC123");
        CustomerDetails customerDetails = new CustomerDetails(
            "Morgan Falk", "0790781234", "MF@gmail.com", bike);
        
        String description = "The brakes do not work";
        String date = java.time.LocalDate.now().toString();

        RepairOrder repairOrder = repairOrderRegistry.createRepairOrder(
            customerDetails, description, date, bike);
        
        String expResult = "3h";
        String result = repairOrder.getEstimatedCompletion();

        assertEquals(expResult, result, "Estimation should be 3h.");
    }

    @Test
    public void getRepairOrderReturnsNull(){
        RepairOrder storeInHere = repairOrderRegistry.getRepairOrder("123456789");

        assertNull(storeInHere, 
            "Should return null when no repair order exists for given phone number.");
    }

    @Test
    public void getRepairOrderReturnsCorrectDescription(){
        Bike bike = new Bike("BMX", "Crazy", "ABC123");
        CustomerDetails customerDetails = new CustomerDetails("Morgan Falk", 
        "0790781234", "MF@gmail.com", bike);

        String description = "The brakes do not work.";
        String date = java.time.LocalDate.now().toString();

        repairOrderRegistry.createRepairOrder(customerDetails, description, date, bike);
        RepairOrder storeInHere = repairOrderRegistry.getRepairOrder("0790781234");

        assertEquals(description, storeInHere.getDescription(), 
            "Wrong description returned from getRepairOrder.");
    }

    @Test
    public void getRepairOrderReturnsCorrectBikeDetails(){
        Bike bike = new Bike("BMX", "Crazy", "ABC123");
        CustomerDetails customerDetails = new CustomerDetails("Morgan Falk", 
        "0790781234", "MF@gmail.com", bike);

        String description = "The brakes do not work.";
        String date = java.time.LocalDate.now().toString();

        repairOrderRegistry.createRepairOrder(customerDetails, description, date, bike);
        RepairOrder storeInHere = repairOrderRegistry.getRepairOrder("0790781234");

        assertEquals(bike, storeInHere.getBikeDetails(), 
            "Wrong bike details returned from getRepairOrder.");
    }

    @Test
    public void getRepairOrderReturnsCorrectCustomerDetails(){
        Bike bike = new Bike("BMX", "Crazy", "ABC123");
        CustomerDetails customerDetails = new CustomerDetails("Morgan Falk", 
        "0790781234", "MF@gmail.com", bike);

        String description = "The brakes do not work.";
        String date = java.time.LocalDate.now().toString();

        repairOrderRegistry.createRepairOrder(customerDetails, description, date, bike);
        RepairOrder storeInHere = repairOrderRegistry.getRepairOrder("0790781234");

        assertEquals(customerDetails, storeInHere.getCustomerDetails(), 
            "Wrong customer details returned from getRepairOrder.");
    }

    @Test
    public void getRepairOrderHasNoRepairTasksAtStart(){
        Bike bike = new Bike("BMX", "Crazy", "ABC123");
        CustomerDetails customerDetails = new CustomerDetails("Morgan Falk", 
        "0790781234", "MF@gmail.com", bike);

        String description = "The brakes do not work.";
        String date = java.time.LocalDate.now().toString();

        repairOrderRegistry.createRepairOrder(customerDetails, description, date, bike);
        RepairOrder storeInHere = repairOrderRegistry.getRepairOrder("0790781234");

        assertNull(storeInHere.getRepairTasks(), "RepairTasks should be null initially.");
    }

    @Test
    public void rejectRepairOrderCheckState(){
        Bike bike = new Bike("BMX", "Crazy", "ABC123");
        CustomerDetails customerDetails = new CustomerDetails("Morgan Falk", 
        "0790781234", "MF@gmail.com", bike);

        String phoneNumber = "0790781234";
        String description = "The brakes do not work.";
        String date = java.time.LocalDate.now().toString();

        repairOrderRegistry.createRepairOrder(customerDetails, description, date, bike);
        repairOrderRegistry.rejectRepairOrder(phoneNumber);
        RepairOrder storeInHere = repairOrderRegistry.getRepairOrder(phoneNumber);

        String expResult = "REJECTED";
        String result = storeInHere.getState();

        assertEquals(expResult, result, "Repair order was not rejected.");
    }
}
