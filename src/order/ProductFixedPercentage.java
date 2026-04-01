package order;

import product.StoreProduct;

import java.time.LocalDateTime;

public class ProductFixedPercentage extends ProductDiscount implements FixedPercentageDiscount {
    /** The percentage deducted from a product's price */
    private double percentage;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A fixed percentage discount's general constructor with products
     * @param id         the discount's id
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     * @param products   the discount's products
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public ProductFixedPercentage(String id, LocalDateTime startDate, LocalDateTime endDate, double percentage,
                                  StoreProduct... products) throws IllegalArgumentException {
        super(id, DiscountType.FIXED_PERCENTAGE, DiscountCoverage.PRODUCT, startDate, endDate);
        this.addProducts(products);
        this.setPercentage(percentage);
    }

    /**
     * A fixed percentage discount's general constructor over the whole store
     * @param id             the discount's id
     * @param startDate      the date when the discount starts
     * @param endDate        the date when the discount ends
     * @param percentage     the percentage deducted from a product's price
     * @param overWholeStore whether the discount is applied over the whole store or not (must be true)
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public ProductFixedPercentage(String id, LocalDateTime startDate, LocalDateTime endDate, double percentage,
                                  boolean overWholeStore) throws IllegalArgumentException {
        super(id, DiscountType.FIXED_PERCENTAGE, DiscountCoverage.PRODUCT, startDate, endDate);
        // DUE: this.addProducts(overWholeStore);
        this.setPercentage(percentage);
    }

    /**
     * Instantiates a new fixed percentage discount with products
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     * @param products   the discount's products
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public ProductFixedPercentage(LocalDateTime startDate, LocalDateTime endDate, double percentage,
                                  StoreProduct... products) throws IllegalArgumentException {
        super(DiscountType.FIXED_PERCENTAGE, DiscountCoverage.PRODUCT, startDate, endDate);
        this.addProducts(products);
        this.setPercentage(percentage);
    }

    /**
     * Instantiates a new fixed percentage discount over the whole store
     * @param startDate      the date when the discount starts
     * @param endDate        the date when the discount ends
     * @param percentage     the percentage deducted from a product's price
     * @param overWholeStore whether the discount is applied over the whole store or not (must be true)
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public ProductFixedPercentage(LocalDateTime startDate, LocalDateTime endDate, double percentage,
                                  boolean overWholeStore) throws IllegalArgumentException {
        super(DiscountType.FIXED_PERCENTAGE, DiscountCoverage.PRODUCT, startDate, endDate);
        //this.addProducts(overWholeStore);
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