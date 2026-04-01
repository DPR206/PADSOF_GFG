package order;

import product.*;

import java.time.LocalDateTime;

/**
 * Class name: FixedPerDisc
 * <p>
 * Description: It implements the fixed percentage discount
 * @author Ana O.R.
 * @version 1.2
 * @see Discount
 * @see StoreProduct
 * @see Pack
 * @see Category
 */
public class FixedPerDisc extends Discount {
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
    public FixedPerDisc(String id, LocalDateTime startDate, LocalDateTime endDate, double percentage,
                        StoreProduct... products) throws IllegalArgumentException {
        super(id, startDate, endDate, DiscountType.FIXED_PERCENTAGE, products);

        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }
        this.percentage = percentage;
    }

    /**
     * A fixed percentage discount's general constructor with packs
     * @param id         the discount's id
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     * @param packs      the discount's pack
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public FixedPerDisc(String id, LocalDateTime startDate, LocalDateTime endDate, double percentage, Pack... packs)
            throws IllegalArgumentException {
        super(id, startDate, endDate, DiscountType.FIXED_PERCENTAGE, packs);

        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }
        this.percentage = percentage;
    }

    /**
     * A fixed percentage discount's general constructor with categories
     * @param id         the discount's id
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     * @param categories the discount's categories
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public FixedPerDisc(String id, LocalDateTime startDate, LocalDateTime endDate, double percentage,
                        Category... categories) throws IllegalArgumentException {
        super(id, startDate, endDate, DiscountType.FIXED_PERCENTAGE, categories);

        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }
        this.percentage = percentage;
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
    public FixedPerDisc(String id, LocalDateTime startDate, LocalDateTime endDate, double percentage,
                        boolean overWholeStore) throws IllegalArgumentException {
        super(id, startDate, endDate, DiscountType.FIXED_PERCENTAGE, overWholeStore);

        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }
        this.percentage = percentage;
    }

    /**
     * Instantiates a new fixed percentage discount with products
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     * @param products   the discount's products
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public FixedPerDisc(LocalDateTime startDate, LocalDateTime endDate, double percentage, StoreProduct... products)
            throws IllegalArgumentException {
        super(startDate, endDate, DiscountType.FIXED_PERCENTAGE, products);

        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }
        this.percentage = percentage;
    }

    /**
     * Instantiates a new fixed percentage discount with packs
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     * @param packs      the discount's packs
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public FixedPerDisc(LocalDateTime startDate, LocalDateTime endDate, double percentage, Pack... packs)
            throws IllegalArgumentException {
        super(startDate, endDate, DiscountType.FIXED_PERCENTAGE, packs);

        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }
        this.percentage = percentage;
    }

    /**
     * Instantiates a new fixed percentage discount with categories
     * @param startDate  the date when the discount starts
     * @param endDate    the date when the discount ends
     * @param percentage the percentage deducted from a product's price
     * @param categories the discount's categories
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public FixedPerDisc(LocalDateTime startDate, LocalDateTime endDate, double percentage, Category... categories)
            throws IllegalArgumentException {
        super(startDate, endDate, DiscountType.FIXED_PERCENTAGE, categories);

        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }
        this.percentage = percentage;
    }

    /**
     * Instantiates a new fixed percentage discount over the whole store
     * @param startDate      the date when the discount starts
     * @param endDate        the date when the discount ends
     * @param percentage     the percentage deducted from a product's price
     * @param overWholeStore whether the discount is applied over the whole store or not (must be true)
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    public FixedPerDisc(LocalDateTime startDate, LocalDateTime endDate, double percentage, boolean overWholeStore)
            throws IllegalArgumentException {
        super(startDate, endDate, DiscountType.FIXED_PERCENTAGE, overWholeStore);

        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("The percentage must be between 0% and 100%");
        }
        this.percentage = percentage;
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