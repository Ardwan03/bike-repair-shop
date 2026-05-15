package se.kth.iv1350.bikeRepairShop.model.discount;

/**
 * Applies no discount.
 */
public class NoDiscount implements Discount{

    /**
     * Returns the original total cost.
     * @param totalCost the original total cost.
     * @return the original total cost.
     */
    public double applyDiscount(double totalCost){
        return totalCost;
    }
}
