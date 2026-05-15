package se.kth.iv1350.bikeRepairShop.model.businessLogic;

import java.util.ArrayList;

/**
 * Represents a collection of repair tasks and their associated costs.
 */
public class RepairTasks {
    private String description;
    private ArrayList<Double> costs = new ArrayList<>();

    /**
     * Creates a new set of repair tasks.
     * @param description a description of the repair tasks
     */
    public RepairTasks(String description){
        this.description = description;
    }

    /**
     * Adds a task with an associated cost.
     * @param taskDescription the task to be added
     * @param cost the cost of the task
     */
    public void addTask(String taskDescription, double cost){
        if (this.description == null)
            this.description = taskDescription;
        else
            this.description += ", " + taskDescription;
        costs.add(cost);
    }

    /**
     * Calculates the total cost of all tasks.
     * @return the sum of all task costs
     */
    public double calculateTotalCost(){
        double sumAtMoment = 0.0;

        for (int i = 0; i < costs.size(); i++){
            double costForTask = costs.get(i);
            sumAtMoment += costForTask;
        }
        return sumAtMoment;
    }

    /**
     * @return the description of the repair tasks
     */
    public String getDescription(){
        return description;
    }
}