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
     * @param id                the discount's id
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param deduction         the amount of money the discount deducts from the order's final price
     * @param categories        the discount's categories
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CategoryVolume(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                          double deduction, Category... categories) throws IllegalArgumentException {
        super(id, DiscountType.VOLUME, DiscountCoverage.CATEGORY, startDate, endDate);
        this.addCategories(categories);
        this.setSpendingThreshold(spendingThreshold);
        this.setDeduction(deduction);
    }

    /**
     * Instantiates a new Volume discount with categories
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param deduction         the amount of money the discount deducts from the order's final price
     * @param categories        the discount's categories
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CategoryVolume(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, double deduction,
                          Category... categories) throws IllegalArgumentException {
        super(DiscountType.VOLUME, DiscountCoverage.CATEGORY, startDate, endDate);
        this.addCategories(categories);
        this.setSpendingThreshold(spendingThreshold);
        this.setDeduction(deduction);
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
     * It sets the volume discount's spending threshold.
     * @return the volume discount's spending threshold
     */
    public double getSpendingThreshold() {
        return spendingThreshold;
    }

    /**
     * It allows the manager to change the volume discount's spending threshold
     * @param spendingThreshold the new spending threshold
     * @throws IllegalArgumentException the spending threshold must be greater than 0
     */
    public void setSpendingThreshold(double spendingThreshold) throws IllegalArgumentException {
        if (spendingThreshold < 0) {
            throw new IllegalArgumentException("The spending threshold must be greater than 0");
        }
        this.spendingThreshold = spendingThreshold;
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