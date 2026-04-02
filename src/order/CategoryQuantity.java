package order;

import product.Category;

import java.time.LocalDateTime;

/**
 * Class name: CategoryQuantity
 * <p>
 * Description: It implements the Category discount whose type is Quantity
 * @author Ana O.R.
 * @version 1.0
 * @see Category
 */
public class CategoryQuantity extends CategoryDiscount implements QuantityDiscount {
    /** The amount of products, or packs, in a cart from which the discount can take place */
    private int numThreshold;
    /** The amount of money the discount deducts from the order's final price */
    private double deduction;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A quantity discount's general constructor with categories
     * @param id           the discount's id
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param numThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param categories   the discount's categories
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CategoryQuantity(String id, LocalDateTime startDate, LocalDateTime endDate, int numThreshold,
                            double deduction, Category... categories) throws IllegalArgumentException {
        super(id, DiscountType.QUANTITY, DiscountCoverage.CATEGORY, startDate, endDate);
        this.addCategories(categories);
        this.setnumThreshold(numThreshold);
        this.setDeduction(deduction);
    }

    /**
     * Instantiates a new quantity discount with categories
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param numThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param categories   the discount's categories
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CategoryQuantity(LocalDateTime startDate, LocalDateTime endDate, int numThreshold, double deduction,
                            Category... categories) throws IllegalArgumentException {
        super(DiscountType.QUANTITY, DiscountCoverage.CATEGORY, startDate, endDate);
        this.addCategories(categories);
        this.setnumThreshold(numThreshold);
        this.setDeduction(deduction);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    // DUE: public createNotification(){}

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
     * @throws IllegalArgumentException the deduction must be greater than 0
     */
    public void setDeduction(double newDeduction) throws IllegalArgumentException {
        if (newDeduction <= 0) {
            throw new IllegalArgumentException("The deduction must be greater than 0");
        }
        this.deduction = newDeduction;
    }

    /**
     * It gets the quantity discount's amount of products in a cart from which the discount can take place
     * @return the amount of products in a cart from which the discount can take place
     */
    public int getnumThreshold() {
        return this.numThreshold;
    }

    /**
     * It allows the manager to change the quantity discount's amount of products in a cart from which the discount can
     * take place
     * @param numThreshold the new amount of products in a cart from which the discount can take place
     */
    public void setnumThreshold(int numThreshold) throws IllegalArgumentException {
        if (numThreshold <= 0) {
            throw new IllegalArgumentException("The number of units must be greater than 0");
        }
        this.numThreshold = numThreshold;
    }

    /**
     * It gets the discount's amount of products, or packs, in a cart from which the discount can take place
     * @return the discount's amount of products, or packs, in a cart from which the discount can take place
     */
    public int getNumThreshold() {
        return numThreshold;
    }

    /**
     * It sets the discount's amount of products, or packs, in a cart from which the discount can take place
     * @param newNumThreshold the discount's new amount of products, or packs, in a cart from which the discount can
     *                        take place
     */
    public void setNumThreshold(int newNumThreshold) {
        this.numThreshold = newNumThreshold;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Written information of a discount
     * @return the written information of a discount
     */
    @Override
    public String toString() { // DUE
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];PERCENTAGE;GIFT;SPENDING_THRESHOLD;<NUM_PRODS;DEDUCTION> */
        return super.toString() + ";"  /*percentage*/ + ";"  /*gift*/ + ";" /*spendingThreshold*/ + ";" +
               this.numThreshold + ";" + this.deduction;
    }
}