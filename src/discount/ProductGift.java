package discount;

import product.StoreProduct;

import java.time.LocalDateTime;

/**
 * It implements the Product discount whose type is Gift
 * @author Ana O.R.
 * @version 1.0
 * @see StoreProduct
 */
public class ProductGift extends ProductDiscount implements GiftDiscount {
    /** The product gifted to the client when a certain spending threshold is met */
    StoreProduct gift;
    /** The spending threshold that allows the discount to take place */
    private double spendingThreshold;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A gift discount's general constructor with products
     * @param id                the discount's id
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param gift              the product gifted to the client when a certain spending threshold is met
     * @param products          the discount's products
     */
    public ProductGift(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                       StoreProduct gift, StoreProduct... products) {
        super(id, DiscountType.GIFT, DiscountCoverage.PRODUCT, startDate, endDate);
        this.addProducts(products);
        this.spendingThreshold = spendingThreshold;
        this.gift = gift;
    }

    /**
     * A gift discount's general constructor over the whole store
     * @param id                the discount's id
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param gift              the product gifted to the client when a certain spending threshold is met
     * @param overWholeStore    whether the discount is applied over the whole store or not (must be true)
     */
    public ProductGift(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                       StoreProduct gift, boolean overWholeStore) {
        super(id, DiscountType.GIFT, DiscountCoverage.PRODUCT, startDate, endDate);
        this.addWholeStore(overWholeStore);
        this.spendingThreshold = spendingThreshold;
        this.gift = gift;
    }

    /**
     * Instantiates a new gift discount with products
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param gift              the product gifted to the client when a certain spending threshold is met
     * @param products          the discount's products
     */
    public ProductGift(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct gift,
                       StoreProduct... products) {
        super(DiscountType.GIFT, DiscountCoverage.PRODUCT, startDate, endDate);
        this.addProducts(products);
        this.spendingThreshold = spendingThreshold;
        this.gift = gift;
    }

    /**
     * Instantiates a new gift discount over the whole store
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param gift              the product gifted to the client when a certain spending threshold is met
     * @param overWholeStore    whether the discount is applied over the whole store or not (must be true)
     */
    public ProductGift(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct gift,
                       boolean overWholeStore) {
        super(DiscountType.GIFT, DiscountCoverage.PRODUCT, startDate, endDate);
        this.addWholeStore(overWholeStore);
        this.spendingThreshold = spendingThreshold;
        this.gift = gift;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    // DUE: createGiftDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct
    // gift) {}

    

    // DUE: public obtainDisc();
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the gift discount's gift
     * @return the gift discount's gift
     */
    public StoreProduct getGift() {
        return this.gift;
    }

    /**
     * It allows the manager to change a gift discount's gift
     * @param newGift the new gift
     */
    public void setGift(StoreProduct newGift) {
        this.gift = newGift;
    }

    /**
     * It gets the gift discount's gift spending threshold.
     * @return the gift discount's gift spending threshold
     */
    public double getSpendingThreshold() {
        return this.spendingThreshold;
    }

    /**
     * It allows the manager to change the gift discount's spending threshold
     * @param spendingThreshold the new spending threshold
     * @throws IllegalArgumentException the illegal argument exception
     */
    public void setSpendingThreshold(double spendingThreshold) throws IllegalArgumentException {
        if (spendingThreshold < 0) {
            throw new IllegalArgumentException("The spending threshold must be greater than 0");
        }
        this.spendingThreshold = spendingThreshold;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];PERCENTAGE;<GIFT;SPENDING_THRESHOLD>;NUM_PRODS;DEDUCTION */
        return super.toString() + ";"  /*percentage*/ + ";" + this.gift.getId() + ";" + this.spendingThreshold + ";"
                /*numThreshold*/ + ";" /*deduction*/;
    }
}