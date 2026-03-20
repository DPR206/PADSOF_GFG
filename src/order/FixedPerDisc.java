package order;

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
     */
    public FixedPerDisc(LocalDateTime startDate, LocalDateTime endDate, double percentage) throws IllegalArgumentException {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }
        super(startDate, endDate);
        this.percentage = percentage;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    // DUE: createFixedPerDisc(LocalDateTime startDate, LocalDateTime endDate, double percentage) {}

    // DUE: public createNotification(){}

    // DUE: public obtainDisc();

    /*--------------------------------------------------- CHANGERS ---------------------------------------------------*/

    /**
     * It allows the manager to change the fixed percentage discount's percentage
     * @param percentage the new percentage
     */
    public void changePercentage(double percentage) throws IllegalArgumentException {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }
        this.percentage = percentage;
    }
    /*---------------------------------------------------- GETTERS ---------------------------------------------------*/

}