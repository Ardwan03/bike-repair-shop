package se.kth.iv1350.bikeRepairShop.view;

import se.kth.iv1350.bikeRepairShop.model.dto.RepairOrderDTO;
import se.kth.iv1350.bikeRepairShop.model.observer.RepairOrderObserver;

/**
 * Prints repair order updates to System.out.
 */
public class RepairOrderView implements RepairOrderObserver{

    /**
     * Prints updated repair order information.
     * @param repairOrderDTO the updated repair order.
     */
    public void newRepairOrderUpdate(RepairOrderDTO repairOrderDTO) {
        System.out.println();
        System.out.println("REPAIR ORDER UPDATE");
        System.out.println("Description: " + repairOrderDTO.getDescription());
        System.out.println("Date: " + repairOrderDTO.getDate());
        System.out.println("Repair Tasks: " + repairOrderDTO.getRepairTasksDescription());
        System.out.println("Total Cost: " + repairOrderDTO.getTotalCost());
        System.out.println("State: " + repairOrderDTO.getState());
        System.out.println("Estimated Completion: " + repairOrderDTO.getEstimatedCompletion());
        System.out.println();
    }
}
