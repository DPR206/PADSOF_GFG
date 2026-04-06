package discount;

import product.Category;

import java.time.LocalDateTime;

/**
 * It implements the Category discount whose type is Volume
 * @author Ana O.R.
 * @version 1.0
 * @see Category
 */
public class CategoryVolume extends CategoryDiscount implements VolumeDiscount {
    /** The spending threshold that allows the discount to take place */
    private double spendingThreshold;
    /** The amount of money the discount deducts from the order's final price */
    private double deduction;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A volume discount's general constructor with categories
     * @param assignedId                the discount's id
     * @param assignedStartDate         the date when the discount starts
     * @param assignedEndDate           the date when the discount ends
     * @param assignedSpendingThreshold the spending threshold that allows the discount to take place
     * @param assignedDeduction         the amount of money the discount deducts from the order's final price
     * @param assignedCategories        the discount's categories
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CategoryVolume(String assignedId, LocalDateTime assignedStartDate, LocalDateTime assignedEndDate,
                          double assignedSpendingThreshold, double assignedDeduction, Category... assignedCategories)
            throws IllegalArgumentException {
        super(assignedId, DiscountType.VOLUME, DiscountCoverage.CATEGORY, assignedStartDate, assignedEndDate);
        this.addCategories(assignedCategories);
        this.setSpendingThreshold(assignedSpendingThreshold);
        this.setDeduction(assignedDeduction);
    }

    /**
     * Instantiates a new Volume discount with categories
     * @param assignedStartDate         the date when the discount starts
     * @param assignedEndDate           the date when the discount ends
     * @param assignedSpendingThreshold the spending threshold that allows the discount to take place
     * @param assignedDeduction         the amount of money the discount deducts from the order's final price
     * @param assignedCategories        the discount's categories
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CategoryVolume(LocalDateTime assignedStartDate, LocalDateTime assignedEndDate,
                          double assignedSpendingThreshold, double assignedDeduction, Category... assignedCategories)
            throws IllegalArgumentException {
        super(DiscountType.VOLUME, DiscountCoverage.CATEGORY, assignedStartDate, assignedEndDate);
        this.addCategories(assignedCategories);
        this.setSpendingThreshold(assignedSpendingThreshold);
        this.setDeduction(assignedDeduction);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the volume discount's deduction
     * @return the volume discount's deduction
     */
    public double getDeduction() {
        return this.deduction;
    }

    /**
     * It allows the manager to change the volume discount's discount
     * @param newDeduction the new deduction
     * @throws IllegalArgumentException the deduction must be greater than 0
     */
    public void setDeduction(double newDeduction) throws IllegalArgumentException {
        if (newDeduction <= 0) {
            throw new IllegalArgumentException("The deduction must be greater than 0");
        }
        this.deduction = newDeduction;
    }

    /**
     * It returns the discount's basic info
     * @return the discount's basic info
     */
    public String getPrintInfo() {
        return "DUE"; // DUE
    }

    /**
     * It sets the volume discount's spending threshold.
     * @return the volume discount's spending threshold
     */
    public double getSpendingThreshold() {
        return spendingThreshold;
    }

    /**
     * It allows the manager to change the volume discount's spending threshold
     * @param newSpendingThreshold the new spending threshold
     * @throws IllegalArgumentException the spending threshold must be greater than 0
     */
    public void setSpendingThreshold(double newSpendingThreshold) throws IllegalArgumentException {
        if (newSpendingThreshold < 0) {
            throw new IllegalArgumentException("The spending threshold must be greater than 0");
        }
        this.spendingThreshold = newSpendingThreshold;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Written information of a discount
     * @return the written information of a discount
     */
    @Override
    public String toString() { // DUE
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];PERCENTAGE;GIFT;<SPENDING_THRESHOLD>;NUM_PRODS;
        <DEDUCTION> */
        return super.toString() + ";"  /*percentage*/ + ";"  /*gift*/ + ";" + this.spendingThreshold +
               ";" /*numThreshold
         */ + ";" + this.deduction;
    }
}