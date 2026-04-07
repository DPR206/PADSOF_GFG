package discount;

import product.StoreProduct;

import java.time.LocalDateTime;

/**
 * It implements the Product discount whose type is Volume
 * @author Ana O.R.
 * @version 1.0
 * @see StoreProduct
 */
public class ProductVolume extends ProductDiscount implements VolumeDiscount {
    /** The spending threshold that allows the discount to take place */
    private double spendingThreshold;
    /** The amount of money the discount deducts from the order's final price */
    private double deduction;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A volume discount's general constructor with products
     * @param id                the discount's id
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param deduction         the amount of money the discount deducts from the order's final price
     * @param products          the discount's products
     */
    public ProductVolume(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                         double deduction, StoreProduct... products) {
        super(id, DiscountType.VOLUME, DiscountCoverage.PRODUCT, startDate, endDate);
        this.addProducts(products);
        this.spendingThreshold = spendingThreshold;
        this.deduction = deduction;
    }

    /**
     * A volume discount's general constructor over the whole store
     * @param id                the discount's id
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param deduction         the amount of money the discount deducts from the order's final price
     * @param overWholeStore    whether the discount is applied over the whole store or not (must be true)
     */
    public ProductVolume(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                         double deduction, boolean overWholeStore) {
        super(id, DiscountType.VOLUME, DiscountCoverage.PRODUCT, startDate, endDate);
        this.addWholeStore(overWholeStore);
        this.spendingThreshold = spendingThreshold;
        this.deduction = deduction;
    }

    /**
     * Instantiates a new Volume discount with products
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param deduction         the amount of money the discount deducts from the order's final price
     * @param products          the discount's products
     */
    public ProductVolume(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, double deduction,
                         StoreProduct... products) {
        super(DiscountType.VOLUME, DiscountCoverage.PRODUCT, startDate, endDate);
        this.addProducts(products);
        this.spendingThreshold = spendingThreshold;
        this.deduction = deduction;
    }

    /**
     * Instantiates a new Volume discount over the whole store
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param deduction         the amount of money the discount deducts from the order's final price
     * @param overWholeStore    whether the discount is applied over the whole store or not (must be true)
     */
    public ProductVolume(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, double deduction,
                         boolean overWholeStore) {
        super(DiscountType.VOLUME, DiscountCoverage.PRODUCT, startDate, endDate);
        this.addWholeStore(overWholeStore);
        this.spendingThreshold = spendingThreshold;
        this.deduction = deduction;
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
     */
    public void setDeduction(double newDeduction) {
        this.deduction = newDeduction;
    }

    /**
     * It returns the discount's basic info
     * @return the discount's basic info
     */
    public String getPrintInfo() {
        return "-" + this.deduction + "€ if you spend over " + this.spendingThreshold + "€ on the products: " +
               this.getPrintNameStoreProducts();
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
    public String toString() { // DUE
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];PERCENTAGE;GIFT;<SPENDING_THRESHOLD>;NUM_PRODS;
        <DEDUCTION> */
        return super.toString() + ";"  /*percentage*/ + ";"  /*gift*/ + ";" + this.spendingThreshold +
               ";" /*numThreshold
         */ + ";" + this.deduction;
    }
}