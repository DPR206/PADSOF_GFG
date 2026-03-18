package order;

import java.time.LocalDateTime;

/**
 * Class name: Discount
 * <p>
 * Description: It implements the general discount
 * @author Ana O.R.
 * @version 1.0
 */
public abstract class Discount {
    /** The date when the discount starts */
    private LocalDateTime startDate;
    /** The date when the discount ends */
    private LocalDateTime endDate;

    /**
     * General constructor for a discount
     * @param startDate the date when the discount starts
     * @param endDate   the date when the discount ends
     */
    Discount(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * It gets the discount's start date
     * @return the discount's start date
     */
    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    /**
     * It gets the discount's end date
     * @return the discount's end date
     */
    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    /**
     * It allows the manager to change a discount's start date
     * @param startDate the discount's new start date
     */
    public void changeStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * It allows the manager to change a discount's end date
     * @param endDate the discount's new end date
     */
    public void changeEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    // DUE: public abstract createNotification(){}

    // DUE: public abstract obtainDisc();
}