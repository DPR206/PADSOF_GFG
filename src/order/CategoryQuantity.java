package order;

import product.Category;

import java.time.LocalDateTime;

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
     */
    public CategoryQuantity(String id, LocalDateTime startDate, LocalDateTime endDate, int numThreshold,
                            double deduction, Category... categories) {
        super(id, DiscountType.QUANTITY, DiscountCoverage.CATEGORY, startDate, endDate);
        this.addCategories(categories);
        this.numThreshold = numThreshold;
        this.deduction = deduction;
    }

    /**
     * Instantiates a new quantity discount with categories
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param numThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param categories   the discount's categories
     */
    public CategoryQuantity(LocalDateTime startDate, LocalDateTime endDate, int numThreshold, double deduction,
                            Category... categories) {
        super(DiscountType.QUANTITY, DiscountCoverage.CATEGORY, startDate, endDate);
        this.addCategories(categories);
        this.numThreshold = numThreshold;
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
    public int getnumThreshold() {
        return this.numThreshold;
    }

    /**
     * It allows the manager to change the quantity discount's amount of products in a cart from which the discount can
     * take place
     * @param numThreshold the new amount of products in a cart from which the discount can take place
     */
    public void setnumThreshold(int numThreshold) {
        this.numThreshold = numThreshold;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];PERCENTAGE;GIFT;SPENDING_THRESHOLD;<NUM_PRODS;DEDUCTION> */
        return super.toString() + ";"  /*percentage*/ + ";"  /*gift*/ + ";" /*spendingThreshold*/ + ";" +
               this.numThreshold + ";" + this.deduction;
    }
}