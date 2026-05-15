package se.kth.iv1350.bikeRepairShop.startup;

import se.kth.iv1350.bikeRepairShop.controller.Controller;
import se.kth.iv1350.bikeRepairShop.integration.CustomerRegistry;
import se.kth.iv1350.bikeRepairShop.integration.RepairOrderRegistry;
import se.kth.iv1350.bikeRepairShop.logging.FileLogger;
import se.kth.iv1350.bikeRepairShop.logging.RepairOrderLogger;
import se.kth.iv1350.bikeRepairShop.integration.Printer;
import se.kth.iv1350.bikeRepairShop.view.View;
import se.kth.iv1350.bikeRepairShop.view.RepairOrderView;

/**
 * Starts the program.
 */
public class Main {

    /**
     * The main method of the class.
     * @param args not used
     */
    public static void main(String[] args) {

        CustomerRegistry customerRegistry = CustomerRegistry.getInstance();
        RepairOrderRegistry repairOrderRegistry = new RepairOrderRegistry();
        Printer printer = new Printer();

        Controller controller = new Controller(
            repairOrderRegistry,
            customerRegistry,
            printer
        );

        controller.addRepairOrderObserver(new RepairOrderView());
        controller.addRepairOrderObserver(new RepairOrderLogger());

        View view = new View(controller, new FileLogger());
        
        view.startFlow();
    }
}