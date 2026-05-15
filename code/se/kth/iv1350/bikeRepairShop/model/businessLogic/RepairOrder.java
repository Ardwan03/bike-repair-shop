package se.kth.iv1350.bikeRepairShop.model.businessLogic;

import se.kth.iv1350.bikeRepairShop.model.discount.Discount;
import se.kth.iv1350.bikeRepairShop.model.discount.NoDiscount;

/**
 * Represents a repair order for a customer's bike.
 * Contains information about the problem, status, and proposed repairs.
 */
public class RepairOrder {
    private final String NEWLY_CREATED = "NEWLY_CREATED";
    private final String READY_FOR_APPROVAL = "READY_FOR_APPROVAL";
    private final String ACCEPTED = "ACCEPTED";
    private final String REJECTED = "REJECTED";

    private CustomerDetails customerDetails;
    private Bike bikeDetails;
    private String description;
    private String date; 
    private String state;
    private RepairTasks repairTasks;
    private String estimatedCompletion;
    private String diagnostics;
    private Discount discount = new NoDiscount();

    /**
     * Creates a new repair order.
     * @param customerDetails the customer associated with the order.
     * @param description the description of the reported problem.
     * @param date the creation date of the order.
     * @param bikeDetails the bike to be repaired.
     */
    public RepairOrder(CustomerDetails customerDetails, String description, 
        String date, Bike bikeDetails){
            this.customerDetails = customerDetails;
            this.description = description;
            this.date = date;
            this.bikeDetails = bikeDetails;
            this.state = NEWLY_CREATED; 
            this.estimatedCompletion = "3h";
    }

    /**
     * Sets the discount strategy used for this repair order.
     * @param discount the discount strategy to use.
     */
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**
     * Updates the repair order with diagnostic information and repair tasks.
     * @param diagnostics the diagnostic report.
     * @param repairTasks the proposed repair tasks.
     */
    public void updateOrder(String diagnostics, RepairTasks repairTasks){
        this.diagnostics = diagnostics;
        this.repairTasks = repairTasks;
        markReadyForApproval();
    }

    /**
     * Calculates the total cost of the repair order.
     * @return the total cost, or <code>0.0</code> if no tasks are defined.
     */
    private double calculateTotalCost(){
        if (repairTasks != null) {
            return repairTasks.calculateTotalCost();
        }
        return 0.0;
    }

    /**
     * Retrieves the total cost of the repair order.
     * @return the total cost of the repair order.
     */
    public double getTotalCost(){
        return discount.applyDiscount(calculateTotalCost());
    }

    /**
     * Marks the repair order as ready for approval.
     */
    public void markReadyForApproval(){
        this.state = READY_FOR_APPROVAL;
    }

    /**
     * Marks the repair order as rejected.
     */
    public void markRejected(){
        this.state = REJECTED;
    }

    /**
     * Marks the repair order as accepted.
     */
    public void markAccepted(){
        this.state = ACCEPTED;
    }

    /**
     * Gets the description.
     * @return the description of the repair order.
     */
    public String getDescription(){ 
        return description;
    }

    /**
     * Gets the date.
     * @return the creation date of the repair order.
     */
    public String getDate(){ 
        return date;
    }

    /**
     * Gets the current state.
     * @return the current state of the repair order.
     */
    public String getState(){ 
        return state;
    }

    /**
     * Gets the estimated completion time.
     * @return the estimated completion time of the repair.
     */
    public String getEstimatedCompletion(){ 
        return estimatedCompletion;
    }

    /**
     * Gets the repair tasks.
     * @return the repair tasks associated with the order.
     */
    public RepairTasks getRepairTasks(){ 
        return repairTasks;
    }

    /**
     * Gets the diagnostics.
     * @return the diagnostic report of the repair order.
     */
    public String getDiagnostics() {
        return diagnostics;
    }

    /**
     * Gets the details of the bike.
     * @return the bike associated with the repair order.
     */
    public Bike getBikeDetails() {
        return bikeDetails;
    }

    /**
     * Gets the details of the customer.
     * @return the customer associated with the repair order.
     */
    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }
}