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
    /** The global variable to determine which id should a new product have */
    static public int totalId = -1;
    /** The discount's id */
    private final int id;
    /** The date when the discount starts */
    private LocalDateTime startDate;
    /** The date when the discount ends */
    private LocalDateTime endDate;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A discount's general constructor
     * @param id        the discount's id
     * @param startDate the date when the discount starts
     * @param endDate   the date when the discount ends
     * @throws IllegalArgumentException the illegal argument exception
     */
    public Discount(int id, LocalDateTime startDate, LocalDateTime endDate) throws IllegalArgumentException {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date is after end date");
        }

        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * A discount's constructor
     * @param startDate the date when the discount starts
     * @param endDate   the date when the discount ends
     * @throws IllegalArgumentException the illegal argument exception
     */
    public Discount(LocalDateTime startDate, LocalDateTime endDate) throws IllegalArgumentException {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date is after end date");
        }

        this(++totalId, startDate, endDate);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    // DUE: public abstract createNotification(){}

    // DUE: public abstract obtainDisc(); <- Creo que no puedo pq no devuelven lo mismo

    /**
     * It gets the discount's end date
     * @return the discount's end date
     */
    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    /**
     * It allows the manager to change a discount's end date
     * @param newEndDate the discount's new end date
     * @throws IllegalArgumentException the start date is after the end date
     */
    public void setEndDate(LocalDateTime newEndDate) throws IllegalArgumentException {
        if (this.startDate.isAfter(newEndDate)) {
            throw new IllegalArgumentException("Start date is after the end date");
        }

        this.endDate = newEndDate;
    }

    /**
     * It gets the discount's id
     * @return the discount's id
     */
    public int getId() {
        return this.id;
    }

    /**
     * It gets the discount's start date
     * @return the discount's start date
     */
    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    /**
     * It allows the manager to change a discount's start date
     * @param newStartDate the discount's new start date
     * @throws IllegalArgumentException the start date is after the end date
     */
    public void setStartDate(LocalDateTime newStartDate) throws IllegalArgumentException {
        if (newStartDate.isAfter(this.endDate)) {
            throw new IllegalArgumentException("Start date is after the end date");
        }

        this.startDate = newStartDate;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        // DUE
        return this.startDate + "|" + this.endDate;
    }
}