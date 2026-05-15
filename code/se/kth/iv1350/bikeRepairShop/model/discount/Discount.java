package se.kth.iv1350.bikeRepairShop.model.discount;

/**
 * Represents a discount strategy.
 */
public interface Discount {

    /**
     * Applies a discount to the total cost.
     * @param totalCost the original total cost.
     * @return the discounted total cost.
     */
    double applyDiscount(double totalCost);
}
