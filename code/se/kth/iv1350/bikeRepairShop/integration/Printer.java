package se.kth.iv1350.bikeRepairShop.integration;

import se.kth.iv1350.bikeRepairShop.model.dto.RepairOrderDTO;

/**
 * Prints repair order information for the customer.
 */
public class Printer{

    /**
     * Prints the provided repair order data.
     * @param repairOrderData the repair order information to print
     */
    public void printOrder(RepairOrderDTO repairOrderData){
        System.out.println("Date: " + repairOrderData.getDate());
        System.out.println("Description: " + repairOrderData.getDescription());
        System.out.println("Repair Tasks: " + repairOrderData.getRepairTasksDescription());
        System.out.println("Total Cost: " + repairOrderData.getTotalCost());
        System.out.println("State: " + repairOrderData.getState());
        System.out.println("Estimated Completion: " + repairOrderData.getEstimatedCompletion());
    }
}
