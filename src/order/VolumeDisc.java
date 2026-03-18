package order;

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

    /*------------------------------------------------- CONSTRUCTORS -------------------------------------------------*/

    /**
     * Instantiates a new Volume discount
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param discount          the amount of money the discount deducts from the order's final price
     */
    public VolumeDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, double discount) {
        super(startDate, endDate);
        this.spendingThreshold = spendingThreshold;
        this.discount = discount;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    // DUE: public createNotification(){}

    // DUE: public obtainDisc();

    /* ------------------------------------------------- LOS CHANGES ------------------------------------------------ */

    /**
     * It allows the manager to change the volume discount's spending threshold
     * @param spendingThreshold the new spending threshold
     */
    public void changeSpendingThreshold(double spendingThreshold) {
        this.spendingThreshold = spendingThreshold;
    }

    /**
     * It allows the manager to change the volume discount's discount
     * @param discount the new discount
     */
    public void changeDiscount(double discount) {
        this.discount = discount;
    }
    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */

}