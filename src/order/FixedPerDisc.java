package order;

import product.StoreProduct;

import java.time.LocalDateTime;

/**
 * Class name: FixedPerDisc
 * <p>
 * Description: It implements the fixed percentage discount
 * @author Ana O.R.
 * @version 1.1
 * @see Discount
 */
public class FixedPerDisc extends Discount {
    /** The percentage deducted from a product's price */
    private double percentage;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A fixed percentage discount's general constructor
     * @param id         the discount's id
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     * @param products   the discount's products
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public FixedPerDisc(String id, LocalDateTime startDate, LocalDateTime endDate, double percentage,
                        StoreProduct... products) throws IllegalArgumentException {
        super(id, startDate, endDate, DiscountType.FIXED_PERCENTAGE, products);

        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }
        this.percentage = percentage;
    }

    /**
     * Instantiates a new fixed percentage discount
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     * @param products   the discount's products
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public FixedPerDisc(LocalDateTime startDate, LocalDateTime endDate, double percentage, StoreProduct... products)
            throws IllegalArgumentException {
        super(startDate, endDate, DiscountType.FIXED_PERCENTAGE, products);

        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }
        this.percentage = percentage;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    // DUE: createFixedPerDisc(LocalDateTime startDate, LocalDateTime endDate, double percentage) {}

    // DUE: public createNotification(){}

    // DUE: public obtainDisc();
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the fixed percentage discount's percentage.
     * @return the fixed percentage discount's percentage
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * It allows the manager to change the fixed percentage discount's percentage
     * @param newPercentage the new percentage
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public void setPercentage(double newPercentage) throws IllegalArgumentException {
        if (newPercentage < 0 || newPercentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }

        this.percentage = newPercentage;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];<PERCENTAGE>;GIFT;SPENDING_THRESHOLD;NUM_PRODS;DEDUCTION */
        return super.toString() + ";" + this.percentage + ";"  /*gift*/ + ";" /*spendingThreshold*/ + ";" /*numProds
         */ + ";" /*deduction*/;
    }
}