package se.kth.iv1350.bikeRepairShop.controller;

import se.kth.iv1350.bikeRepairShop.model.businessLogic.RepairTasks;
import se.kth.iv1350.bikeRepairShop.model.dto.CustomerDetailsDTO;
import se.kth.iv1350.bikeRepairShop.model.dto.RepairOrderDTO;
import se.kth.iv1350.bikeRepairShop.exceptions.CustomerNotFoundException;
import se.kth.iv1350.bikeRepairShop.exceptions.DatabaseFailureException;
import se.kth.iv1350.bikeRepairShop.integration.Printer;
import se.kth.iv1350.bikeRepairShop.integration.CustomerRegistry;
import se.kth.iv1350.bikeRepairShop.integration.RepairOrderRegistry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerTest{

    private Controller controller;

    @BeforeEach
    public void setUp(){
        CustomerRegistry customerRegistry = CustomerRegistry.getInstance();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();

        controller = new Controller(repairOrderRegistry, customerRegistry, printer);
    }

    @Test
    public void searchForCustomerNotNull() throws CustomerNotFoundException{
        String phoneNumber = "0790781234";

        CustomerDetailsDTO storeInHere = controller.search(phoneNumber);

        assertNotNull(storeInHere, "search returned null for existing customer.");
    }

    @Test
    public void searchThrowsCustomerNotFoundException(){
        String phoneNumber = "0000000000";

        assertThrows(CustomerNotFoundException.class, () -> {
            controller.search(phoneNumber);}, 
            "CustomerNotFoundException should be thrown.");
    }

    @Test
    public void searchThrowsDatabaseFailureException(){
        String phoneNumber = "FAIL";

        assertThrows(DatabaseFailureException.class, () -> {
            controller.search(phoneNumber);}, 
            "DatabaseFailureException should be thrown.");
    }

    @Test
    public void searchForCustomerWithEmptyString(){
        String phoneNumber = "";

        assertThrows(CustomerNotFoundException.class, () -> {
            controller.search(phoneNumber);}, 
            "CustomerNotFoundException should be thrown.");
    }

    @Test
    public void reportProblemNotNull() throws CustomerNotFoundException{
        String description = "The brakes do not work.";
        String phoneNumber = "0790781234";

        RepairOrderDTO storeInHere = controller.reportProblem(description, phoneNumber);

        assertNotNull(storeInHere, "reportProblem returned null for existing customer");
    }

    @Test
    public void reportProblemWithEmptyDescriptionAndNumber() throws CustomerNotFoundException{
        String description = "";
        String phoneNumber = "";

        RepairOrderDTO storeInHere = controller.reportProblem(description, phoneNumber);

        assertNull(storeInHere, 
            "reportProblem should return null for empty description and phone number.");
    }

    @Test
    public void getRepairOrderReturnsNotNull() throws CustomerNotFoundException{
        String phoneNumber = "0790781234";
        String description = "The brakes do not work.";

        controller.reportProblem(description, phoneNumber);

        RepairOrderDTO storeInHere = controller.getRepairOrder(phoneNumber);

        assertNotNull(storeInHere, "getRepairOrder returned null for existing order.");
    }

    @Test
    public void getRepairOrderReturnsNull(){
        String phoneNumber = "";

        RepairOrderDTO storeInHere = controller.getRepairOrder(phoneNumber);

        assertNull(storeInHere, "getRepairOrder should return null when no order exists.");
    }

    @Test
    public void getRepairOrderReturnsCorrectDescription() throws CustomerNotFoundException{
        String phoneNumber = "0790781234";
        String description = "The brakes do not work.";

        controller.reportProblem(description, phoneNumber);

        RepairOrderDTO storeInHere = controller.getRepairOrder(phoneNumber);

        String expResult = description;
        String result = storeInHere.getDescription();

        assertEquals(expResult, result, "Wrong description returned from getRepairOrder.");
    }

    @Test
    public void rejectRepairOrderCheckState() throws CustomerNotFoundException{
        String phoneNumber = "0790781234";
        String description = "The brakes do not work";

        controller.reportProblem(description, phoneNumber);
        controller.rejectRepairOrder(phoneNumber);

        RepairOrderDTO storeInHere = controller.getRepairOrder(phoneNumber);

        String expResult = "REJECTED";
        String result = storeInHere.getState();

        assertEquals(expResult, result, "The order was not rejected.");
    }

    @Test
    public void repairAcceptedCheckState() throws CustomerNotFoundException{
        String phoneNumber = "0790781234";
        String description = "The brakes do not work";

        controller.reportProblem(description, phoneNumber);
        controller.repairAccepted(phoneNumber);

        RepairOrderDTO storeInHere = controller.getRepairOrder(phoneNumber);        

        String expResult = "ACCEPTED";
        String result = storeInHere.getState();

        assertEquals(expResult, result, "The order was not accepted.");
    }

    @Test
    public void updateOrderNotNull() throws CustomerNotFoundException{
        String phoneNumber = "0790781234";
        String description = "The brakes do not work.";
        String diagnostics = "Brake wires broken.";
        
        RepairTasks tasks = new RepairTasks("Brake repair");
        tasks.addTask("Add brake wires", 500);

        controller.reportProblem(description, phoneNumber);

        RepairOrderDTO storeInHere = controller.updateOrder(phoneNumber, diagnostics, tasks);

        assertNotNull(storeInHere, "updateOrder returned null for existing order.");
    }

    @Test
    public void updateOrderWithEmptyTasks() throws CustomerNotFoundException{
        String phoneNumber = "0790781234";
        String description = "The brakes do not work.";
        String diagnostics = "Brake wires broken.";
        
        RepairTasks tasks = new RepairTasks("");

        controller.reportProblem(description, phoneNumber);

        RepairOrderDTO storeInHere = controller.updateOrder(phoneNumber, diagnostics, tasks);

        assertNotNull(storeInHere, "updateOrder failed with empty tasks.");
    }
}
