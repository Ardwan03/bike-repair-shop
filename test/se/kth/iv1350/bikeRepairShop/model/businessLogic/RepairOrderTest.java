package se.kth.iv1350.bikeRepairShop.model.businessLogic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepairOrderTest {
    private RepairOrder repairOrder;

    @BeforeEach
    public void setUp(){
        Bike bike = new Bike("BMX", "Crazy", "ABC123");
        CustomerDetails customerDetails = new CustomerDetails(
            "Morgan Falk", "0790781234", "MF@gmail.com", bike);

        String description = "The brakes do not work.";
        String date = java.time.LocalDate.now().toString();

        repairOrder = new RepairOrder(customerDetails, description, date, bike);
    }

    @Test
    public void updateOrderCheckTasks(){
        RepairTasks tasks = new RepairTasks("Fix brakes");
        tasks.addTask("Add wires", 500);
        String diagnostics = "Brakes not working";

        repairOrder.updateOrder(diagnostics, tasks);

        assertEquals(tasks, repairOrder.getRepairTasks(),"RepairTasks were not set correctly.");
    }

    @Test
    public void updateOrderCheckState(){
        RepairTasks tasks = new RepairTasks("Fix brakes");
        tasks.addTask("Add wires", 500);
        String diagnostics = "Brakes not working";

        repairOrder.updateOrder(diagnostics, tasks);

        assertEquals("READY_FOR_APPROVAL", repairOrder.getState(),
            "State was not updated");
    }

    @Test
    public void updateOrderCheckDiagnostics(){
        RepairTasks tasks = new RepairTasks("Fix brakes");
        tasks.addTask("Add wires", 500);
        String diagnostics = "Brakes not working";

        repairOrder.updateOrder(diagnostics, tasks);

        assertEquals(diagnostics, repairOrder.getDiagnostics(),
            "Diagnostics were not set correctly.");
    }

    @Test
    public void getTotalCostFromTasks(){
        RepairTasks tasks = new RepairTasks("Fix brakes");
        tasks.addTask("Add wires", 500);
        tasks.addTask("Add new brakes", 500);
        String diagnostics = "Brakes not working";

        repairOrder.updateOrder(diagnostics, tasks);

        double expResult = 1000;
        double result = repairOrder.getTotalCost();

        assertEquals(expResult, result,"Total cost was not calculated correctly.");
    }

    @Test
    public void markReadyForApprovalMarksCorrectly(){
        repairOrder.markReadyForApproval();

        String expResult = "READY_FOR_APPROVAL";
        String result = repairOrder.getState();

        assertEquals(expResult, result,"State was not updated to READY_FOR_APPROVAL.");
    }

    @Test
    public void markRejectedMarksCorrectly(){
        repairOrder.markRejected();

        String expResult = "REJECTED";
        String result = repairOrder.getState();

        assertEquals(expResult, result,"State was not updated to REJECTED.");
    }

    @Test
    public void markAcceptedState(){
        repairOrder.markAccepted();

        String expResult = "ACCEPTED";
        String result = repairOrder.getState();

        assertEquals(expResult, result,"State was not updated to ACCEPTED.");
    }

    @Test
    public void getDescriptionMatches(){
        repairOrder.getDescription();

        String expResult = "The brakes do not work.";
        String result = repairOrder.getDescription();

        assertEquals(expResult, result,"Description does not match.");
    }

    @Test
    public void getDateCorrect(){
        String expResult = java.time.LocalDate.now().toString();
        String result = repairOrder.getDate();

        assertEquals(expResult, result,"The dates are incorrect.");
    }

    @Test
    public void getStateChangeStates(){
        String expResult = "NEWLY_CREATED";
        String result = repairOrder.getState();

        assertEquals(expResult, result, "States are incorrect.");
    }

    @Test
    public void getDiagnosticsNull(){
        String result = repairOrder.getDiagnostics();

        assertNull(result,"Diagnostics should be null initially.");
    }

    @Test
    public void getDiagnosticsNotNull(){
        String diagnostics = "Some diagnostics";
        RepairTasks tasks = new RepairTasks("The brakes do not work.");
        tasks.addTask("SomeOtherDescription", 100);

        repairOrder.updateOrder(diagnostics, tasks);

        String result = repairOrder.getDiagnostics();

        assertEquals(diagnostics, result,"Diagnostics were not set correctly.");
    }

    @Test
    public void getEstimatedCompletionNotNull(){
        String expResult = "3h";
        String result = repairOrder.getEstimatedCompletion();

        assertEquals(expResult, result,
            "Estimated completion is set to 3h when repair order is created.");
    }

    @Test
    public void getRepairTasksCorrectly(){
        RepairTasks tasks = new RepairTasks("The brakes do not work");
        tasks.addTask("SomeOtherDescription", 100);
        
        String expResult = "The brakes do not work, SomeOtherDescription";
        String result = tasks.getDescription();

        assertEquals(expResult, result,"RepairTasks description was not built correctly.");
    }

    @Test
    public void getBikeDetailsReturnsNotNull(){
        Bike bike = repairOrder.getBikeDetails();

        assertNotNull(bike, "Bike details should not be null.");
    }

    @Test
    public void getCustomerDetailsNotNull(){
        CustomerDetails customerDetails = new CustomerDetails(
            "Morgan Falk", null, null, null);
        
        String expResult = "Morgan Falk";
        String result = customerDetails.getName();

        assertEquals(expResult, result,"Details do not match.");
    }
}
