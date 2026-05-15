package se.kth.iv1350.bikeRepairShop.model.discount;

/**
 * Applies a loyalty discount to the total cost.
 */
public class LoyaltyDiscount implements Discount{

    /**
     * Applies a 20 percent loyalty discount.
     * @param totalCost the original total cost.
     * @return the discounted total cost.
     */
    public double applyDiscount(double totalCost){
        return totalCost * 0.8;
    }
}
