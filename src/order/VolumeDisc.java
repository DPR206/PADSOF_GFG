package order;

import product.StoreProduct;

import java.time.LocalDateTime;

/**
 * Class name: VolumeDisc
 * <p>
 * Description: It implements the volume discount
 * @author Ana O.R.
 * @version 1.0
 * @see Discount
 */
public class VolumeDisc extends Discount {
    /** The spending threshold that allows the discount to take place */
    private double spendingThreshold;
    /** The amount of money the discount deducts from the order's final price */
    private double discount;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Volume discount
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param discount          the amount of money the discount deducts from the order's final price
     */
    public VolumeDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, double discount,
                      StoreProduct... products) {
        super(startDate, endDate, DiscountType.VOLUME, products);
        this.spendingThreshold = spendingThreshold;
        this.discount = discount;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    // DUE: public createNotification(){}

    // DUE: public obtainDisc();
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the volume discount's discount
     * @return the volume discount's discount
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * It allows the manager to change the volume discount's discount
     * @param discount the new discount
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * It sets the volume discount's spending threshold.
     * @return the volume discount's spending threshold
     */
    public double getSpendingThreshold() {
        return spendingThreshold;
    }

    /**
     * It allows the manager to change the volume discount's spending threshold
     * @param spendingThreshold the new spending threshold
     */
    public void setSpendingThreshold(double spendingThreshold) {
        this.spendingThreshold = spendingThreshold;
    }

}