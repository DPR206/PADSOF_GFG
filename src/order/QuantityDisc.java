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

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new quantity discount
     * @param startDate the date when the discount starts
     * @param endDate   the date when the discount ends
     * @param numProds  the amount of products in a cart from which the discount can take place
     * @param discount  the amount of money the discount deducts from the order's final price
     */
    public QuantityDisc(LocalDateTime startDate, LocalDateTime endDate, int numProds, double discount) {
        super(startDate, endDate);
        this.numProds = numProds;
        this.discount = discount;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    // DUE: public createNotification(){}

    // DUE: public obtainDisc();
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the quantity discount's discount
     * @return the quantity discount's discount
     */
    public double getDiscount() {
        return this.discount;
    }

    /**
     * It allows the manager to change the quantity discount's discount
     * @param discount the new discount
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * It gets the quantity discount's amount of products in a cart from which the discount can take place
     * @return the amount of products in a cart from which the discount can take place
     */
    public int getNumProds() {
        return this.numProds;
    }

    /**
     * It allows the manager to change the quantity discount's amount of products in a cart from which the discount can
     * take place
     * @param numProds the new amount of products in a cart from which the discount can take place
     */
    public void setNumProds(int numProds) {
        this.numProds = numProds;
    }
}