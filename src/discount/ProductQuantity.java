package discount;

import product.StoreProduct;

import java.time.LocalDateTime;

/**
 * It implements the Product discount whose type is Quantity
 * @author Ana O.R.
 * @version 1.0
 * @see StoreProduct
 */
public class ProductQuantity extends ProductDiscount implements QuantityDiscount {
    /** The amount of products, or packs, in a cart from which the discount can take place */
    private int numThreshold;
    /** The amount of money the discount deducts from the order's final price */
    private double deduction;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A quantity discount's general constructor with products
     * @param id           the discount's id
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param numThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param products     the discount's products
     */
    public ProductQuantity(String id, LocalDateTime startDate, LocalDateTime endDate, int numThreshold,
                           double deduction, StoreProduct... products) {
        super(id, DiscountType.QUANTITY, DiscountCoverage.PRODUCT, startDate, endDate);
        this.addProducts(products);
        this.numThreshold = numThreshold;
        this.deduction = deduction;
    }

    /**
     * A quantity discount's general constructor over the whole store
     * @param id             the discount's id
     * @param startDate      the date when the discount starts
     * @param endDate        the date when the discount ends
     * @param numThreshold   the amount of products in a cart from which the discount can take place
     * @param deduction      the amount of money the discount deducts from the order's final price
     * @param overWholeStore whether the discount is applied over the whole store or not (must be true)
     */
    public ProductQuantity(String id, LocalDateTime startDate, LocalDateTime endDate, int numThreshold,
                           double deduction, boolean overWholeStore) {
        super(id, DiscountType.QUANTITY, DiscountCoverage.PRODUCT, startDate, endDate);
        this.addWholeStore(overWholeStore);
        this.setNumThreshold(numThreshold);
        this.setDeduction(deduction);
    }

    /**
     * Instantiates a new quantity discount with products
     * @param startDate    the date when the discount starts
     * @param endDate      the date when the discount ends
     * @param numThreshold the amount of products in a cart from which the discount can take place
     * @param deduction    the amount of money the discount deducts from the order's final price
     * @param products     the discount's products
     */
    public ProductQuantity(LocalDateTime startDate, LocalDateTime endDate, int numThreshold, double deduction,
                           StoreProduct... products) throws IllegalArgumentException {
        super(DiscountType.QUANTITY, DiscountCoverage.PRODUCT, startDate, endDate);
        this.addProducts(products);
        this.setNumThreshold(numThreshold);
        this.setDeduction(deduction);
    }

    /**
     * Instantiates a new quantity discount over the whole store
     * @param startDate      the date when the discount starts
     * @param endDate        the date when the discount ends
     * @param numThreshold   the amount of products in a cart from which the discount can take place
     * @param deduction      the amount of money the discount deducts from the order's final price
     * @param overWholeStore whether the discount is applied over the whole store or not (must be true)
     */
    public ProductQuantity(LocalDateTime startDate, LocalDateTime endDate, int numThreshold, double deduction,
                           boolean overWholeStore) throws IllegalArgumentException {
        super(DiscountType.QUANTITY, DiscountCoverage.PRODUCT, startDate, endDate);
        this.addWholeStore(overWholeStore);
        this.setNumThreshold(numThreshold);
        this.setDeduction(deduction);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
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
    public int getNumThreshold() {
        return this.numThreshold;
    }

    /**
     * It allows the manager to change the quantity discount's amount of products in a cart from which the discount can
     * take place
     * @param numThreshold the new amount of products in a cart from which the discount can take place
     */
    public void setNumThreshold(int numThreshold) {
        this.numThreshold = numThreshold;
    }

    /**
     * It returns the discount's basic info
     * @return the discount's basic info
     */
    public String getPrintInfo() {
        return "-" + this.deduction + "€ if you buy more than " + this.numThreshold + " units of the products: " +
               this.getPrintNameStoreProducts();
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];PERCENTAGE;GIFT;SPENDING_THRESHOLD;<NUM_PRODS;DEDUCTION> */
        return super.toString() + ";"  /*percentage*/ + ";"  /*gift*/ + ";" /*spendingThreshold*/ + ";" +
               this.numThreshold + ";" + this.deduction;
    }
}