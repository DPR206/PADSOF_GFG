package order;

import product.Category;

import java.time.LocalDateTime;

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
     */
    public CategoryVolume(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                          double deduction, Category... categories) {
        super(id, DiscountType.VOLUME, DiscountCoverage.CATEGORY, startDate, endDate);
        this.addCategories(categories);
        this.spendingThreshold = spendingThreshold;
        this.deduction = deduction;
    }

    /**
     * Instantiates a new Volume discount with categories
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param deduction         the amount of money the discount deducts from the order's final price
     * @param categories        the discount's categories
     */
    public CategoryVolume(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, double deduction,
                          Category... categories) {
        super(DiscountType.VOLUME, DiscountCoverage.CATEGORY, startDate, endDate);
        this.addCategories(categories);
        this.spendingThreshold = spendingThreshold;
        this.deduction = deduction;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    // DUE: public createNotification(){}

    // DUE: public obtainDisc();

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
     */
    public void setDeduction(double newDeduction) {
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
     */
    public void setSpendingThreshold(double spendingThreshold) {
        this.spendingThreshold = spendingThreshold;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];PERCENTAGE;GIFT;<SPENDING_THRESHOLD>;NUM_PRODS;
        <DEDUCTION> */
        return super.toString() + ";"  /*percentage*/ + ";"  /*gift*/ + ";" + this.spendingThreshold +
               ";" /*numThreshold
         */ + ";" + this.deduction;
    }
}