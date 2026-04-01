package order;

import product.*;

import java.time.LocalDateTime;

/**
 * Class name: GiftDisc
 * <p>
 * Description: It implements the gift discount
 * @author Ana O.R.
 * @version 1.2
 * @see Discount
 * @see StoreProduct
 * @see Pack
 * @see Category
 */
public class GiftDisc extends Discount {
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
    public GiftDisc(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                    StoreProduct gift, StoreProduct... products) {
        super(id, startDate, endDate, DiscountType.GIFT, products);
        this.spendingThreshold = spendingThreshold;
        this.gift = gift;
    }

    /**
     * A gift discount's general constructor with packs
     * @param id                the discount's id
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param gift              the product gifted to the client when a certain spending threshold is met
     * @param packs             the discount's packs
     */
    public GiftDisc(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                    StoreProduct gift, Pack... packs) {
        super(id, startDate, endDate, DiscountType.GIFT, packs);
        this.spendingThreshold = spendingThreshold;
        this.gift = gift;
    }

    /**
     * A gift discount's general constructor with categories
     * @param id                the discount's id
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param gift              the product gifted to the client when a certain spending threshold is met
     * @param categories        the discount's categories
     */
    public GiftDisc(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                    StoreProduct gift, Category... categories) {
        super(id, startDate, endDate, DiscountType.GIFT, categories);
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
    public GiftDisc(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                    StoreProduct gift, boolean overWholeStore) {
        super(id, startDate, endDate, DiscountType.GIFT, overWholeStore);
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
    public GiftDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct gift,
                    StoreProduct... products) {
        super(startDate, endDate, DiscountType.GIFT, products);
        this.spendingThreshold = spendingThreshold;
        this.gift = gift;
    }

    /**
     * Instantiates a new gift discount with packs
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param gift              the product gifted to the client when a certain spending threshold is met
     * @param packs             the discount's products
     */
    public GiftDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct gift,
                    Pack... packs) {
        super(startDate, endDate, DiscountType.GIFT, packs);
        this.spendingThreshold = spendingThreshold;
        this.gift = gift;
    }

    /**
     * Instantiates a new gift discount with categories
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param gift              the product gifted to the client when a certain spending threshold is met
     * @param categories        the discount's categories
     */
    public GiftDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct gift,
                    Category... categories) {
        super(startDate, endDate, DiscountType.GIFT, categories);
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
    public GiftDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct gift,
                    boolean overWholeStore) {
        super(startDate, endDate, DiscountType.GIFT, overWholeStore);
        this.spendingThreshold = spendingThreshold;
        this.gift = gift;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    // DUE: createGiftDisc(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct
    // gift) {}

    // DUE: public createNotification(){}

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
                /*numProds*/ + ";" /*deduction*/;
    }
}