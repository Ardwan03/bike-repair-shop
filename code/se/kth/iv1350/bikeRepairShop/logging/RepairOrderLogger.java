package se.kth.iv1350.bikeRepairShop.logging;

import se.kth.iv1350.bikeRepairShop.model.observer.RepairOrderObserver;
import se.kth.iv1350.bikeRepairShop.model.dto.RepairOrderDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Writes repair order updates to a file.
 */
public class RepairOrderLogger implements RepairOrderObserver {
    private PrintWriter logWriter;

    /**
     * Creates a new repair order logger.
     */
    public RepairOrderLogger() {
        try {
            logWriter = new PrintWriter(new FileWriter(
                "repair-order-log.txt"), true);
        } catch (IOException ioe) {
            System.out.println("Could not create repair order log file.");
            ioe.printStackTrace();
        }
    }

    /**
     * Writes updated repair order information to a file.
     * @param repairOrderDTO the updated repair order.
     */
    public void newRepairOrderUpdate(RepairOrderDTO repairOrderDTO) {
        logWriter.println();
        logWriter.println("REPAIR ORDER UPDATE");
        logWriter.println("Description: " + repairOrderDTO.getDescription());
        logWriter.println("Date: " + repairOrderDTO.getDate());
        logWriter.println("Repair Tasks: " + repairOrderDTO.getRepairTasksDescription());
        logWriter.println("Total Cost: " + repairOrderDTO.getTotalCost());
        logWriter.println("State: " + repairOrderDTO.getState());
        logWriter.println("Estimated Completion: " + repairOrderDTO.getEstimatedCompletion());
        logWriter.println();
    }
}
