package order;

import product.StoreProduct;

import java.time.LocalDateTime;

/**
 * Class name: QuantityDisc
 * <p>
 * Description: It implements the quantity discount
 * @author Ana O.R.
 * @version 1.1
 * @see Discount
 */
public class QuantityDisc extends Discount {
    /** The amount of products in a cart from which the discount can take place */
    private int numProds;
    /** The amount of money the discount deducts from the order's final price */
    private double deduction;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A quantity discount's general constructor
     * @param id        the discount's id
     * @param startDate the date when the discount starts
     * @param endDate   the date when the discount ends
     * @param numProds  the amount of products in a cart from which the discount can take place
     * @param deduction the amount of money the discount deducts from the order's final price
     * @param products  the discount's products
     */
    public QuantityDisc(String id, LocalDateTime startDate, LocalDateTime endDate, int numProds, double deduction,
                        StoreProduct... products) {
        super(id, startDate, endDate, DiscountType.QUANTITY, products);
        this.numProds = numProds;
        this.deduction = deduction;
    }

    /**
     * Instantiates a new quantity discount
     * @param startDate the date when the discount starts
     * @param endDate   the date when the discount ends
     * @param numProds  the amount of products in a cart from which the discount can take place
     * @param deduction the amount of money the discount deducts from the order's final price
     * @param products  the discount's products
     */
    public QuantityDisc(LocalDateTime startDate, LocalDateTime endDate, int numProds, double deduction,
                        StoreProduct... products) {
        super(startDate, endDate, DiscountType.QUANTITY, products);
        this.numProds = numProds;
        this.deduction = deduction;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    // DUE: public createNotification(){}

    // DUE: public obtainDisc();
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the quantity discount's discount
     * @return the quantity discount's discount
     */
    public double getDeduction() {
        return this.deduction;
    }

    /**
     * It allows the manager to change the quantity discount's discount
     * @param newDeduction the new discount
     */
    public void setDeduction(double newDeduction) {
        this.deduction = newDeduction;
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

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];PERCENTAGE;GIFT;SPENDING_THRESHOLD;<NUM_PRODS;DEDUCTION> */
        return super.toString() + ";"  /*percentage*/ + ";"  /*gift*/ + ";" /*spendingThreshold*/ + ";" +
               this.numProds + ";" + this.deduction;
    }
}