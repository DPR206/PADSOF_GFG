package order;

import product.StoreProduct;

import java.time.LocalDateTime;

/**
 * Class name: GiftDisc
 * <p>
 * Description: It implements the gift discount
 * @author Ana O.R.
 * @version 1.0
 * @see Discount
 * @see StoreProduct
 */
public class GiftDisc extends Discount {
    /** The product gifted to the client when a certain spending threshold is met */
    StoreProduct gift;
    /** The spending threshold that allows the discount to take place */
    private double spendingThreshold;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new gift discount
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param gift              the product gifted to the client when a certain spending threshold is met
     */
    public GiftDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct gift) {
        super(startDate, endDate);
        this.spendingThreshold = spendingThreshold;
        this.gift = gift;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    // DUE: createGiftDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct
    // gift) {}

    // DUE: public createNotification(){}

    // DUE: public obtainDisc();
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the gift discount's gift
     * @return the gift discount's gift
     */
    public StoreProduct getGift() {
        return this.gift;
    }

    /**
     * It allows the manager to change a gift discount's gift
     * @param newGift the new gift
     */
    public void setGift(StoreProduct newGift) {
        this.gift = newGift;
    }

    /**
     * It gets the gift discount's gift spending threshold.
     * @return the gift discount's gift spending threshold
     */
    public double getSpendingThreshold() {
        return this.spendingThreshold;
    }

    /**
     * It allows the manager to change the gift discount's spending threshold
     * @param spendingThreshold the new spending threshold
     * @throws IllegalArgumentException the illegal argument exception
     */
    public void setSpendingThreshold(double spendingThreshold) throws IllegalArgumentException {
        if (spendingThreshold < 0) {
            throw new IllegalArgumentException("The spending threshold must be greater than 0");
        }
        this.spendingThreshold = spendingThreshold;
    }


}