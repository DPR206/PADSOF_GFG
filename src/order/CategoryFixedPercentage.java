package order;

import product.Category;

import java.time.LocalDateTime;

public class CategoryFixedPercentage extends CategoryDiscount implements FixedPercentageDiscount {
    /** The percentage deducted from a product's price */
    double percentage = 0;
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A fixed percentage discount's general constructor with categories
     * @param id         the discount's id
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     * @param categories the discount's categories
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public CategoryFixedPercentage(String id, LocalDateTime startDate, LocalDateTime endDate, double percentage,
                                   Category... categories) throws IllegalArgumentException {
        super(id, DiscountType.FIXED_PERCENTAGE, DiscountCoverage.CATEGORY, startDate, endDate);
        this.addCategories(categories);
        this.setPercentage(percentage);
    }

    /**
     * Instantiates a new fixed percentage discount with categories
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     * @param categories the discount's categories
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public CategoryFixedPercentage(LocalDateTime startDate, LocalDateTime endDate, double percentage,
                                   Category... categories) throws IllegalArgumentException {
        super(DiscountType.FIXED_PERCENTAGE, DiscountCoverage.CATEGORY, startDate, endDate);
        this.addCategories(categories);
        this.setPercentage(percentage);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    // DUE: createFixedPerDisc(LocalDateTime startDate, LocalDateTime endDate, double percentage) {}

    // DUE: public createNotification(){}

    // DUE: public obtainDisc();
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
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public void setPercentage(double newPercentage) throws IllegalArgumentException {
        if (newPercentage < 0 || newPercentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }

        this.percentage = newPercentage;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];<PERCENTAGE>;GIFT;SPENDING_THRESHOLD;NUM_PRODS;DEDUCTION */
        return super.toString() + ";" + this.percentage + ";"  /*gift*/ + ";" /*spendingThreshold*/ + ";" /*udsThreshold
         */ + ";" /*deduction*/;
    }
}