package se.kth.iv1350.bikeRepairShop.model.businessLogic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepairTasksTest {
    private RepairTasks repairTasks;

    @BeforeEach
    public void setUp(){
        repairTasks = new RepairTasks("Fix brakes");
    }

    @Test
    public void addTaskChecksDescription(){
        repairTasks.addTask("Something", 100);

        String expResult = "Fix brakes, Something";
        String result = repairTasks.getDescription();

        assertEquals(expResult, result,"Description was not updated correctly.");
    }

    @Test
    public void calculateTotalCostReturnsCorrectValue(){
        repairTasks.addTask("someTask", 200);
        repairTasks.addTask("otherTask", 300);

        double expResult = 500;
        double result = repairTasks.calculateTotalCost();

        assertEquals(expResult, result,"Total cost was not calculated correctly.");
    }

    @Test
    public void calculateTotalCostReturnsZeroWhenNoTasks(){
        double expResult = 0;
        double result = repairTasks.calculateTotalCost();

        assertEquals(expResult, result,"Total cost should be 0 when no tasks exist.");
    }

    @Test
    public void getDescriptionReturnsCorrect(){
        String expResult = "Fix brakes";
        String result = repairTasks.getDescription();

        assertEquals(expResult, result,"Description was not returned correctly.");
    }
}
