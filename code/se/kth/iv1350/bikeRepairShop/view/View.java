package se.kth.iv1350.bikeRepairShop.view;

import se.kth.iv1350.bikeRepairShop.controller.Controller;
import se.kth.iv1350.bikeRepairShop.exceptions.CustomerNotFoundException;
import se.kth.iv1350.bikeRepairShop.exceptions.DatabaseFailureException;
import se.kth.iv1350.bikeRepairShop.logging.Logger;
import se.kth.iv1350.bikeRepairShop.model.dto.CustomerDetailsDTO;
import se.kth.iv1350.bikeRepairShop.model.dto.RepairOrderDTO;
import se.kth.iv1350.bikeRepairShop.model.businessLogic.RepairTasks;

/**
 * Represents the view of the application.
 * Handles presentation of information to the user.
 */
public class View {
    private Controller controller;
    private Logger<String> logger;
    
    /**
     * Creates a new view.
     * @param controller the controller used to perform system operations.
     * @param logger the logger used to write error messages.
     */
    public View(Controller controller, Logger<String> logger) {
        this.controller = controller;
        this.logger = logger;
    }

    /**
     * Displays customer details.
     * @param customerDetails the customer details to display
     */
    public void showCustomerDetails(CustomerDetailsDTO customerDetails){
        System.out.println();
        System.out.println("Name: " + customerDetails.getName());
        System.out.println("Phone Number: " + customerDetails.getPhoneNumber());
        System.out.println("Email: " + customerDetails.getEmail());
        System.out.println("Bike Brand: " + customerDetails.getBrand());
        System.out.println("Bike Model: " + customerDetails.getModel());
        System.out.println("Bike Serial Number: " + customerDetails.getSerialNumber());
        System.out.println();
    }

    /**
     * Displays repair order information.
     * @param repairOrder the repair order information to display
     */
    public void showRepairOrder(RepairOrderDTO repairOrder){
        System.out.println();
        System.out.println("Description: " + repairOrder.getDescription());
        System.out.println("Date: " + repairOrder.getDate());
        System.out.println("Repair Tasks: " + repairOrder.getRepairTasksDescription());
        System.out.println("Total Cost: " + repairOrder.getTotalCost());
        System.out.println("State: " + repairOrder.getState());
        System.out.println("Estimated Completion: " + repairOrder.getEstimatedCompletion());
        System.out.println();
    }

    /**
     * Starts the flow with hard-coded input values.
     */
    public void startFlow() {
        String phoneNumber = "0790781234";

        try {
            CustomerDetailsDTO customerDetails = controller.search(phoneNumber);
            showCustomerDetails(customerDetails);

            RepairOrderDTO repairOrder = controller.reportProblem(
                "The brakes do not work.", phoneNumber);
            showRepairOrder(repairOrder);

            RepairOrderDTO theOrder = controller.getRepairOrder(phoneNumber);
            showRepairOrder(theOrder);

            RepairTasks repairTasks = new RepairTasks(null);
            repairTasks.addTask("Search for the problem", 100);
            repairTasks.addTask("Add brake wires", 500);

            RepairOrderDTO updatedOrder = controller.updateOrder(
                phoneNumber,
                "The brake system needs new wires.",
                repairTasks
            );
            showRepairOrder(updatedOrder);

            controller.repairAccepted(phoneNumber);

        } catch (CustomerNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
            logger.logMessage(cnfe.getMessage());

        } catch(DatabaseFailureException dbfe){
            System.out.println("Could not access customer registry.");
            logger.logMessage(dbfe.getMessage());
        }
    }
}