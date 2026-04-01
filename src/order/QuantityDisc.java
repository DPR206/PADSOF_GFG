package order;

import product.*;

import java.time.LocalDateTime;

/**
 * Class name: QuantityDisc
 * <p>
 * Description: It implements the quantity discount
 * @author Ana O.R.
 * @version 1.2
 * @see Discount
 * @see StoreProduct
 * @see Pack
 * @see Category
 */
public class QuantityDisc extends Discount {
    /** The amount of products, or packs, in a cart from which the discount can take place */
    private int udsThreshold;
    /** The amount of money the discount deducts from the order's final price */
    private double deduction;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A quantity discount's general constructor with products
     * @param id           the discount's id
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param udsThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param products     the discount's products
     */
    public QuantityDisc(String id, LocalDateTime startDate, LocalDateTime endDate, int udsThreshold, double deduction,
                        StoreProduct... products) {
        super(id, startDate, endDate, DiscountType.QUANTITY, products);
        this.udsThreshold = udsThreshold;
        this.deduction = deduction;
    }

    /**
     * A quantity discount's general constructor with packs
     * @param id           the discount's id
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param udsThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param packs        the discount's packs
     */
    public QuantityDisc(String id, LocalDateTime startDate, LocalDateTime endDate, int udsThreshold, double deduction,
                        Pack... packs) {
        super(id, startDate, endDate, DiscountType.QUANTITY, packs);
        this.udsThreshold = udsThreshold;
        this.deduction = deduction;
    }

    /**
     * A quantity discount's general constructor with categories
     * @param id           the discount's id
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param udsThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param categories   the discount's categories
     */
    public QuantityDisc(String id, LocalDateTime startDate, LocalDateTime endDate, int udsThreshold, double deduction,
                        Category... categories) {
        super(id, startDate, endDate, DiscountType.QUANTITY, categories);
        this.udsThreshold = udsThreshold;
        this.deduction = deduction;
    }

    /**
     * A quantity discount's general constructor over the whole store
     * @param id             the discount's id
     * @param startDate      the date when the discount starts
     * @param endDate        the date when the discount ends
     * @param udsThreshold   the amount of products in a cart from which the discount can take place
     * @param deduction      the amount of money the discount deducts from the order's final price
     * @param overWholeStore whether the discount is applied over the whole store or not (must be true)
     */
    public QuantityDisc(String id, LocalDateTime startDate, LocalDateTime endDate, int udsThreshold, double deduction,
                        boolean overWholeStore) {
        super(id, startDate, endDate, DiscountType.QUANTITY, overWholeStore);
        this.udsThreshold = udsThreshold;
        this.deduction = deduction;
    }

    /**
     * Instantiates a new quantity discount with products
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param udsThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param products     the discount's products
     */
    public QuantityDisc(LocalDateTime startDate, LocalDateTime endDate, int udsThreshold, double deduction,
                        StoreProduct... products) {
        super(startDate, endDate, DiscountType.QUANTITY, products);
        this.udsThreshold = udsThreshold;
        this.deduction = deduction;
    }

    /**
     * Instantiates a new quantity discount with packs
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param udsThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param packs        the discount's packs
     */
    public QuantityDisc(LocalDateTime startDate, LocalDateTime endDate, int udsThreshold, double deduction,
                        Pack... packs) {
        super(startDate, endDate, DiscountType.QUANTITY, packs);
        this.udsThreshold = udsThreshold;
        this.deduction = deduction;
    }

    /**
     * Instantiates a new quantity discount with categories
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param udsThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param categories   the discount's categories
     */
    public QuantityDisc(LocalDateTime startDate, LocalDateTime endDate, int udsThreshold, double deduction,
                        Category... categories) {
        super(startDate, endDate, DiscountType.QUANTITY, categories);
        this.udsThreshold = udsThreshold;
        this.deduction = deduction;
    }

    /**
     * Instantiates a new quantity discount over the whole store
     * @param startDate      the date when the discount starts
     * @param endDate        the date when the discount ends
     * @param udsThreshold   the amount of products in a cart from which the discount can take place
     * @param deduction      the amount of money the discount deducts from the order's final price
     * @param overWholeStore whether the discount is applied over the whole store or not (must be true)
     */
    public QuantityDisc(LocalDateTime startDate, LocalDateTime endDate, int udsThreshold, double deduction,
                        boolean overWholeStore) {
        super(startDate, endDate, DiscountType.QUANTITY, overWholeStore);
        this.udsThreshold = udsThreshold;
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
    public int getUdsThreshold() {
        return this.udsThreshold;
    }

    /**
     * It allows the manager to change the quantity discount's amount of products in a cart from which the discount can
     * take place
     * @param udsThreshold the new amount of products in a cart from which the discount can take place
     */
    public void setUdsThreshold(int udsThreshold) {
        this.udsThreshold = udsThreshold;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];PERCENTAGE;GIFT;SPENDING_THRESHOLD;<NUM_PRODS;DEDUCTION> */
        return super.toString() + ";"  /*percentage*/ + ";"  /*gift*/ + ";" /*spendingThreshold*/ + ";" +
               this.udsThreshold + ";" + this.deduction;
    }
}