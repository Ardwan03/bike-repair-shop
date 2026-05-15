package se.kth.iv1350.bikeRepairShop.model.dto;

/**
 * Represents a data transfer object (<code>DTO</code>) containing repair order information.
 * This object is used to transfer read-only data to the view layer.
 */
public class RepairOrderDTO{
    private final String description;
    private final String date;
    private final double totalCost;
    private final String state;
    private final String estimatedCompletion;
    private final String repairTasksDescription;

    /**
     * Creates a new repair order data transfer object.
     * @param description the description of the problem
     * @param date the creation date of the repair order
     * @param totalCost the total cost of the repair
     * @param state the current state of the repair order
     * @param estimatedCompletion the estimated completion time
     * @param repairTasksDescription the repair tasks associated with the order
     */
    public RepairOrderDTO(String description, String date, double totalCost, 
                      String state, String estimatedCompletion, String repairTasksDescription){
        this.description = description;
        this.date = date;
        this.totalCost = totalCost;
        this.state = state;
        this.estimatedCompletion = estimatedCompletion;
        this.repairTasksDescription = repairTasksDescription;
    }

    /**
     * @return the description of the problem
     */
    public String getDescription(){
        return description;
    }

    /**
     * @return the creation date of the repair order
     */
    public String getDate(){
        return date;
    }

    /**
     * @return the total cost of the repair
     */
    public double getTotalCost(){
        return totalCost;
    }

    /**
     * @return the current state of the repair order
     */
    public String getState(){
        return state;
    }

    /**
     * @return the estimated completion time
     */
    public String getEstimatedCompletion(){
        return estimatedCompletion;
    }

    /**
     * @return the repair tasks associated with the order
     */
    public String getRepairTasksDescription(){
        return repairTasksDescription;
    }
}
