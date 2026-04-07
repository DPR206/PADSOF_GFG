package discount;

import product.Category;

import java.time.LocalDateTime;

/**
 * It implements the Category discount whose type is Quantity
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
     * @param assignedId           the discount's id
     * @param assignedStartDate    the date when the discount starts
     * @param assignedEndDate      the date when the discount ends
     * @param assignedNumThreshold the amount of products in a cart from which the discount can take place
     * @param assignedDeduction    the amount of money the discount deducts from the order's final price
     * @param assignedCategories   the discount's categories
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CategoryQuantity(String assignedId, LocalDateTime assignedStartDate, LocalDateTime assignedEndDate,
                            int assignedNumThreshold, double assignedDeduction, Category... assignedCategories)
            throws IllegalArgumentException {
        super(assignedId, DiscountType.QUANTITY, DiscountCoverage.CATEGORY, assignedStartDate, assignedEndDate);
        this.addCategories(assignedCategories);
        this.setNumThreshold(assignedNumThreshold);
        this.setDeduction(assignedDeduction);
    }

    /**
     * Instantiates a new quantity discount with categories
     * @param assignedStartDate    the date when the discount starts
     * @param assignedEndDate      the date when the discount ends
     * @param assignedNumThreshold the amount of products in a cart from which the discount can take place
     * @param assignedDeduction    the amount of money the discount deducts from the order's final price
     * @param assignedCategories   the discount's categories
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CategoryQuantity(LocalDateTime assignedStartDate, LocalDateTime assignedEndDate, int assignedNumThreshold,
                            double assignedDeduction, Category... assignedCategories) throws IllegalArgumentException {
        super(DiscountType.QUANTITY, DiscountCoverage.CATEGORY, assignedStartDate, assignedEndDate);
        this.addCategories(assignedCategories);
        this.setNumThreshold(assignedNumThreshold);
        this.setDeduction(assignedDeduction);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
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
     * It gets the discount's amount of products, or packs, in a cart from which the discount can take place
     * @return the discount's amount of products, or packs, in a cart from which the discount can take place
     */
    public int getNumThreshold() {
        return numThreshold;
    }

    /**
     * It allows the manager to change the quantity discount's amount of products in a cart from which the discount can
     * take place
     * @param newNumThreshold the new amount of products in a cart from which the discount can take place
     */
    public void setNumThreshold(int newNumThreshold) throws IllegalArgumentException {
        if (newNumThreshold <= 0) {
            throw new IllegalArgumentException("The number of units must be greater than 0");
        }
        this.numThreshold = newNumThreshold;
    }

    /**
     * It returns the discount's basic info
     * @return the discount's basic info
     */
    public String getPrintInfo() {
        return "-" + this.deduction + "€ if you buy more than " + this.numThreshold + " units of any products " +
               "belonging to the categories: " + this.getPrintCategories();
    }

    /**
     * It gets the quantity discount's amount of products in a cart from which the discount can take place
     * @return the amount of products in a cart from which the discount can take place
     */
    public int getnumThreshold() {
        return this.numThreshold;
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