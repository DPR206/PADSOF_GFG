package order;

import java.time.LocalDateTime;

/**
 * Class name: QuantityDisc
 * <p>
 * Description: It implements the quantity discount
 * @author Ana O.R.
 * @version 1.0
 * @see Discount
 */
public class QuantityDisc extends Discount {
    /** The amount of products in a cart from which the discount can take place */
    private int numProds;
    /** The amount of money the discount deducts from the order's final price */
    private double discount;

    /**
     * Instantiates a new quantity discount
     * @param startDate the date when the discount starts
     * @param endDate   the date when the discount ends
     * @param numProds  the amount of products in a cart from which the discount can take place
     * @param discount  the amount of money the discount deducts from the order's final price
     */
    QuantityDisc(LocalDateTime startDate, LocalDateTime endDate, int numProds, double discount) {
        super(startDate, endDate);
        this.numProds = numProds;
        this.discount = discount;
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
     * It allows the manager to change the quantity discount's amount of products in a cart from which the discount can
     * take place
     * @param numProds the new amount of products in a cart from which the discount can take place
     */
    public void changeNumProds(int numProds) {
        this.numProds = numProds;
    }

    /**
     * It allows the manager to change the quantity discount's discount
     * @param discount the new discount
     */
    public void changeDiscount(double discount) {
        this.discount = discount;
    }

    // DUE: public createNotification(){}

    // DUE: public obtainDisc();
}