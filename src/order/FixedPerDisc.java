package order;

import product.StoreProduct;

import java.time.LocalDateTime;

/**
 * Class name: FixedPerDisc
 * <p>
 * Description: It implements the fixed percentage discount
 * @author Ana O.R.
 * @version 1.0
 * @see Discount
 */
public class FixedPerDisc extends Discount {
    /** The percentage deducted from a product's price */
    private double percentage;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new fixed percentage discount
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public FixedPerDisc(LocalDateTime startDate, LocalDateTime endDate, double percentage, StoreProduct... products)
            throws IllegalArgumentException {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }

        super(startDate, endDate, DiscountType.FIXED_PERCENTAGE, products);
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

}