package se.kth.iv1350.bikeRepairShop.integration;

import se.kth.iv1350.bikeRepairShop.model.businessLogic.RepairOrder;
import se.kth.iv1350.bikeRepairShop.model.businessLogic.CustomerDetails;
import se.kth.iv1350.bikeRepairShop.model.businessLogic.Bike;
import java.util.ArrayList;

/**
 * Stores and manages repair orders.
 */
public class RepairOrderRegistry{
    private ArrayList<RepairOrder> allOrders = new ArrayList<>();

    /**
     * Creates and stores a new repair order.
     * @param customerDetails the customer associated with the order
     * @param description the description of the reported problem
     * @param date the creation date of the order
     * @param bike the bike to be repaired
     * @return the created repair order
     */
    public RepairOrder createRepairOrder(CustomerDetails customerDetails, String description, 
        String date, Bike bike){
            RepairOrder newOrder = new RepairOrder(customerDetails, description, date, bike);
            allOrders.add(newOrder);
            return newOrder;
    }

    /**
     * Retrieves a repair order using a phone number.
     * @param phoneNumber the customer's phone number
     * @return the matching repair order, or <code>null</code> if no order is found
     */
    public RepairOrder getRepairOrder(String phoneNumber){
        for (int i = 0; i < allOrders.size(); i++) {
            RepairOrder currentOrder = allOrders.get(i);
            if (currentOrder.getCustomerDetails().getPhoneNumber().equals(phoneNumber))
                return currentOrder;
        }
        return null;
    }

    /**
     * Marks a repair order as rejected.
     * @param phoneNumber the customer's phone number
     */
    public void rejectRepairOrder(String phoneNumber){
        RepairOrder order = getRepairOrder(phoneNumber);
        if (order != null)
            order.markRejected();
    }
}