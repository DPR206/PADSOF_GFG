package order;

import java.time.LocalDateTime;

/**
 * Class name: Discount
 * <p>
 * Description: It implements the general discount
 * @author Ana O.R.
 * @version 1.1
 */
public abstract class Discount {
    /** The date when the discount starts */
    private LocalDateTime startDate;
    /** The date when the discount ends */
    private LocalDateTime endDate;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * General constructor for a discount
     * @param startDate the date when the discount starts
     * @param endDate   the date when the discount ends
     * @throws IllegalArgumentException the illegal argument exception
     */
    public Discount(LocalDateTime startDate, LocalDateTime endDate) throws IllegalArgumentException {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date is after end date");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    // DUE: public abstract createNotification(){}

    // DUE: public abstract obtainDisc();

    /*--------------------------------------------------- CHANGERS ---------------------------------------------------*/

    /**
     * It allows the manager to change a discount's start date
     * @param startDate the discount's new start date
     * @throws IllegalArgumentException the illegal argument exception
     */
    public void changeStartDate(LocalDateTime startDate) throws IllegalArgumentException {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date is after end date");
        }
        this.startDate = startDate;
    }

    /**
     * It allows the manager to change a discount's end date
     * @param endDate the discount's new end date
     * @throws IllegalArgumentException the illegal argument exception
     */
    public void changeEndDate(LocalDateTime endDate) throws IllegalArgumentException {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date is after end date");
        }
        this.endDate = endDate;
    }

    /*---------------------------------------------------- GETTERS ---------------------------------------------------*/

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
}