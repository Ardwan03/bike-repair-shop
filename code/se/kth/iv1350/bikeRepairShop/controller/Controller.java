package se.kth.iv1350.bikeRepairShop.controller;

import se.kth.iv1350.bikeRepairShop.model.dto.CustomerDetailsDTO;
import se.kth.iv1350.bikeRepairShop.model.dto.RepairOrderDTO;
import se.kth.iv1350.bikeRepairShop.model.businessLogic.CustomerDetails;
import se.kth.iv1350.bikeRepairShop.model.businessLogic.RepairOrder;
import se.kth.iv1350.bikeRepairShop.model.businessLogic.RepairTasks;
import se.kth.iv1350.bikeRepairShop.model.discount.LoyaltyDiscount;
import se.kth.iv1350.bikeRepairShop.model.observer.RepairOrderObserver;
import se.kth.iv1350.bikeRepairShop.integration.Printer;
import se.kth.iv1350.bikeRepairShop.exceptions.CustomerNotFoundException;
import se.kth.iv1350.bikeRepairShop.integration.CustomerRegistry;
import se.kth.iv1350.bikeRepairShop.integration.RepairOrderRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the <code>Controller</code> class that delegates tasks between packages.
 */
public class Controller {
    private RepairOrderRegistry repairOrderRegistry;
    private CustomerRegistry customerRegistry;
    private Printer printer;
    private List<RepairOrderObserver> repairOrderObservers = new ArrayList<>();

    /**
     * Creates a new controller.
     * @param repairOrderRegistry the registry used for repair orders.
     * @param customerRegistry the registry used for customer details.
     * @param printer the printer used for repair orders.
     */
    public Controller(RepairOrderRegistry repairOrderRegistry, CustomerRegistry customerRegistry, 
                      Printer printer){
        this.repairOrderRegistry = repairOrderRegistry;
        this.customerRegistry = customerRegistry;
        this.printer = printer;
    }

    /**
     * Searches for customer details using a phone number.
     * @param phoneNumber the customer's phone number.
     * @return the customer details found for the specified <code>phoneNumber</code>.
     * @throws CustomerNotFoundException if no customer is found for the specified phone number.
     */
    public CustomerDetailsDTO search(String phoneNumber) 
    throws CustomerNotFoundException{
        CustomerDetails customerDetails = customerRegistry.getCustomerDetails(phoneNumber);

        return new CustomerDetailsDTO(
            customerDetails.getName(), 
            customerDetails.getPhoneNumber(),
            customerDetails.getEmail(),    
            customerDetails.getBike().getBrand(),
            customerDetails.getBike().getModel(), 
            customerDetails.getBike().getSerialNumber()
        );
    }

    /**
     * Creates a data transfer object for a repair order.
     * @param repairOrder the repair order to create.
     * @return the repair order data transfer object.
     */
    private RepairOrderDTO createRepairOrderDTO(RepairOrder repairOrder) {
        String repairTasksDescription = "No repair tasks added yet.";

        if (repairOrder.getRepairTasks() != null) {
            repairTasksDescription = repairOrder.getRepairTasks().getDescription();
        }

        return new RepairOrderDTO(
            repairOrder.getDescription(),
            repairOrder.getDate(),
            repairOrder.getTotalCost(),
            repairOrder.getState(),
            repairOrder.getEstimatedCompletion(),
            repairTasksDescription
        );
    }

    /**
     * Creates a repair order for a reported bike problem.
     * @param description the customer's description of the bike problem.
     * @param phoneNumber the customer's phone number.
     * @return the created repair order information.
     * @throws CustomerNotFoundException if no customer is found for the specified phone number.
     */
    public RepairOrderDTO reportProblem(String description, String phoneNumber) 
    throws CustomerNotFoundException{
        String date = java.time.LocalDate.now().toString();

        if(description == null || description.isEmpty())
            return null;

        CustomerDetails customerDetails = customerRegistry.getCustomerDetails(phoneNumber);
        RepairOrder repairOrder = repairOrderRegistry.createRepairOrder(
            customerDetails,
            description,
            date,
            customerDetails.getBike()
        );

        repairOrder.setDiscount(new LoyaltyDiscount());

        RepairOrderDTO repairOrderDTO = createRepairOrderDTO(repairOrder);

        notifyRepairOrderObservers(repairOrderDTO);

        return repairOrderDTO;
    }

    /**
     * Gets repair order information for a customer.
     * @param phoneNumber the customer's phone number.
     * @return the repair order information for the specified <code>phoneNumber</code>.
     */
    public RepairOrderDTO getRepairOrder(String phoneNumber){
        if(phoneNumber == null || phoneNumber.isEmpty())
            return null;

        RepairOrder repairOrder = repairOrderRegistry.getRepairOrder(phoneNumber);

        return createRepairOrderDTO(repairOrder);
    }

    /**
     * Updates a repair order with a diagnostic report and proposed repair tasks.
     * @param phoneNumber the customer's phone number.
     * @param diagnostics the diagnostic report.
     * @param tasks the proposed repair tasks.
     * @return the updated repair order information.
     */
    public RepairOrderDTO updateOrder(String phoneNumber, String diagnostics, RepairTasks tasks){
        RepairOrder repairOrder = repairOrderRegistry.getRepairOrder(phoneNumber);

        repairOrder.updateOrder(diagnostics, tasks);

        RepairOrderDTO repairOrderDTO = createRepairOrderDTO(repairOrder);

        notifyRepairOrderObservers(repairOrderDTO);

        return repairOrderDTO;
    }

    /**
     * Registers that the customer accepted the repair order.
     * @param phoneNumber the customer's phone number.
     */
    public void repairAccepted(String phoneNumber){
        RepairOrder repairOrder = repairOrderRegistry.getRepairOrder(phoneNumber);

        repairOrder.markAccepted();

        RepairOrderDTO repairOrderDTO = createRepairOrderDTO(repairOrder);

        notifyRepairOrderObservers(repairOrderDTO);

        printer.printOrder(repairOrderDTO);
    }

    /**
     * Registers that the customer rejected the repair order.
     * @param phoneNumber the customer's phone number.
     */
    public void rejectRepairOrder(String phoneNumber) {
        repairOrderRegistry.rejectRepairOrder(phoneNumber);

        RepairOrder repairOrder = repairOrderRegistry.getRepairOrder(phoneNumber);

        RepairOrderDTO repairOrderDTO = createRepairOrderDTO(repairOrder);

        notifyRepairOrderObservers(repairOrderDTO);
    }

    /**
     * Adds an observer that will be notified when repair orders are updated.
     * @param observer the observer to add.
     */
    public void addRepairOrderObserver(RepairOrderObserver observer){
        repairOrderObservers.add(observer);
    }

    /**
     * Notifies all observers about a repair order update.
     * @param repairOrderDTO the updated repair order.
     */
    private void notifyRepairOrderObservers(RepairOrderDTO repairOrderDTO){
        for(RepairOrderObserver observer : repairOrderObservers){
            observer.newRepairOrderUpdate(repairOrderDTO);
        }
    }
}
