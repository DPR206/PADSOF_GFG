package model.discount;

import model.product.Category;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * It implements the Category discount whose type is Fixed Percentage
 * @author Ana O.R.
 * @version 1.0
 * @see Category
 */
public class CategoryFixedPercentage extends CategoryDiscount implements FixedPercentageDiscount, Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */
    /** The percentage deducted from a product's price */
    double percentage = 0;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A fixed percentage discount's general constructor with categories
     * @param assignedId         the discount's id
     * @param assignedStartDate  the date when the discount starts
     * @param assignedEndDate    the date when the discount ends
     * @param assignedPercentage the percentage deducted from a product's price
     * @param assignedCategories the discount's categories
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100, 0 not included
     */
    public CategoryFixedPercentage(String assignedId, LocalDateTime assignedStartDate, LocalDateTime assignedEndDate,
                                   double assignedPercentage, Category... assignedCategories)
            throws IllegalArgumentException {
        super(assignedId, DiscountType.FIXED_PERCENTAGE, DiscountCoverage.CATEGORY, assignedStartDate, assignedEndDate);
        this.addCategories(assignedCategories);
        this.setPercentage(assignedPercentage);
    }

    /**
     * Instantiates a new fixed percentage discount with categories
     * @param assignedStartDate  the date when the discount starts
     * @param assignedEndDate    the date when the discount ends
     * @param assignedPercentage the percentage deducted from a product's price
     * @param assignedCategories the discount's categories
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100, 0 not included
     */
    public CategoryFixedPercentage(LocalDateTime assignedStartDate, LocalDateTime assignedEndDate,
                                   double assignedPercentage, Category... assignedCategories)
            throws IllegalArgumentException {
        super(DiscountType.FIXED_PERCENTAGE, DiscountCoverage.CATEGORY, assignedStartDate, assignedEndDate);
        this.addCategories(assignedCategories);
        this.setPercentage(assignedPercentage);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the fixed percentage discount's percentage.
     * @return the fixed percentage discount's percentage
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * It allows the manager to change the fixed percentage discount's percentage
     * @param newPercentage the new percentage
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100, 0 not included
     */
    public void setPercentage(double newPercentage) throws IllegalArgumentException {
        if (newPercentage <= 0 || newPercentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%, 0% not included");
        }

        this.percentage = newPercentage;
    }

    /**
     * It returns the discount's basic info
     * @return the discount's basic info
     */
    public String getPrintInfo() {
        return "-" + this.percentage + "% discount applied over the categories:" + this.getPrintCategories();
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Written information of a discount
     * @return the written information of a discount
     */
    @Override
    public String toString() { // DUE
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];<PERCENTAGE>;GIFT;SPENDING_THRESHOLD;NUM_PRODS;DEDUCTION */
        return super.toString() + ";" + this.percentage + ";"  /*gift*/ + ";" /*spendingThreshold*/ + ";" /*numThreshold
         */ + ";" /*deduction*/;
    }
}