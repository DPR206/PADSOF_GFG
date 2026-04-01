package order;

import product.*;

import java.time.LocalDateTime;

/**
 * Class name: VolumeDisc
 * <p>
 * Description: It implements the volume discount
 * @author Ana O.R.
 * @version 1.1
 * @see Discount
 * @see StoreProduct
 * @see product.Pack
 */
public class VolumeDisc extends Discount {
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
    public VolumeDisc(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                      double deduction, StoreProduct... products) {
        super(id, startDate, endDate, DiscountType.VOLUME, products);
        this.spendingThreshold = spendingThreshold;
        this.deduction = deduction;
    }

    /**
     * A volume discount's general constructor with packs
     * @param id                the discount's id
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param deduction         the amount of money the discount deducts from the order's final price
     * @param packs             the discount's packs
     */
    public VolumeDisc(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                      double deduction, Pack... packs) {
        super(id, startDate, endDate, DiscountType.VOLUME, packs);
        this.spendingThreshold = spendingThreshold;
        this.deduction = deduction;
    }

    /**
     * A volume discount's general constructor with categories
     * @param id                the discount's id
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param deduction         the amount of money the discount deducts from the order's final price
     * @param categories        the discount's categories
     */
    public VolumeDisc(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                      double deduction, Category... categories) {
        super(id, startDate, endDate, DiscountType.VOLUME, categories);
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
    public VolumeDisc(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                      double deduction, boolean overWholeStore) {
        super(id, startDate, endDate, DiscountType.VOLUME, overWholeStore);
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
    public VolumeDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, double deduction,
                      StoreProduct... products) {
        super(startDate, endDate, DiscountType.VOLUME, products);
        this.spendingThreshold = spendingThreshold;
        this.deduction = deduction;
    }

    /**
     * Instantiates a new Volume discount with packs
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param deduction         the amount of money the discount deducts from the order's final price
     * @param packs             the discount's packs
     */
    public VolumeDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, double deduction,
                      Pack... packs) {
        super(startDate, endDate, DiscountType.VOLUME, packs);
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
    public VolumeDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, double deduction,
                      Category... categories) {
        super(startDate, endDate, DiscountType.VOLUME, categories);
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
    public VolumeDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, double deduction,
                      boolean overWholeStore) {
        super(startDate, endDate, DiscountType.VOLUME, overWholeStore);
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
        return super.toString() + ";"  /*percentage*/ + ";"  /*gift*/ + ";" + this.spendingThreshold + ";" /*numProds
         */ + ";" + this.deduction;
    }
}