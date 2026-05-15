package se.kth.iv1350.bikeRepairShop.model.observer;

import se.kth.iv1350.bikeRepairShop.model.dto.RepairOrderDTO;

/**
 * Receives updates when a repair order changes.
 */
public interface RepairOrderObserver {
    
    /**
     * Called when a repair order has been updated.
     * @param repairOrderDTO the updated repair order information.
     */
    void newRepairOrderUpdate(RepairOrderDTO repairOrderDTO);
}
