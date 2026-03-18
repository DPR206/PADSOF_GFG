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

    /**
     * Instantiates a new fixed percentage discount
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     */
    FixedPerDisc(LocalDateTime startDate, LocalDateTime endDate, double percentage) {
        super(startDate, endDate);
        this.percentage = percentage;
    }

    /**
     * It gets the discount's start date
     * @return the discount's start date
     */
    @Override
    public LocalDateTime getStartDate() {
        return super.getStartDate();
    }

    /**
     * It gets the discount's end date
     * @return the discount's end date
     */
    @Override
    public LocalDateTime getEndDate() {
        return super.getEndDate();
    }

    /**
     * It allows the manager to change a discount's start date
     * @param startDate the discount's new start date
     */
    @Override
    public void changeStartDate(LocalDateTime startDate) {
        super.changeStartDate(startDate);
    }

    /**
     * It allows the manager to change a discount's end date
     * @param endDate the discount's new end date
     */
    @Override
    public void changeEndDate(LocalDateTime endDate) {
        super.changeEndDate(endDate);
    }

    /**
     * It allows the manager to change the fixed percentage discount's percentage
     * @param percentage the new percentage
     */
    public void changePercentage(double percentage) {
        this.percentage = percentage;
    }

    // DUE: public createNotification(){}

    // DUE: public obtainDisc();
}