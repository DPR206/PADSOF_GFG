package order;

import product.Pack;
import product.StoreProduct;

import java.time.LocalDateTime;

/**
 * It implements the Pack discount whose type is Gift
 * @author Ana O.R.
 * @version 1.0
 * @see Pack
 * @see StoreProduct
 */
public class PackGift extends PackDiscount implements GiftDiscount {
    /** The product gifted to the client when a certain spending threshold is met */
    StoreProduct gift;
    /** The spending threshold that allows the discount to take place */
    private double spendingThreshold;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A gift discount's general constructor with packs
     * @param id                the discount's id
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param gift              the product gifted to the client when a certain spending threshold is met
     * @param packs             the discount's packs
     * @throws IllegalArgumentException the illegal argument exception
     */
    public PackGift(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                    StoreProduct gift, Pack... packs) throws IllegalArgumentException {
        super(id, DiscountType.GIFT, DiscountCoverage.PACK, startDate, endDate);
        this.addPacks(packs);
        this.setSpendingThreshold(spendingThreshold);
        this.setGift(gift);
    }

    /**
     * Instantiates a new gift discount with packs
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param gift              the product gifted to the client when a certain spending threshold is met
     * @param packs             the discount's products
     * @throws IllegalArgumentException the illegal argument exception
     */
    public PackGift(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct gift,
                    Pack... packs) throws IllegalArgumentException {
        super(DiscountType.GIFT, DiscountCoverage.PACK, startDate, endDate);
        this.addPacks(packs);
        this.setSpendingThreshold(spendingThreshold);
        this.setGift(gift);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    

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
     * @throws NullPointerException the gift wasn't provided
     */
    public void setGift(StoreProduct newGift) throws NullPointerException {
        if (newGift == null) {
            throw new NullPointerException("The gift wasn't provided");
        }
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

    /**
     * Written information of a discount
     * @return the written information of a discount
     */
    @Override
    public String toString() { // DUE
        /* [TYPE;ID;START_DATE;END_DATE;PRODUCTS;OVER_WHOLE];PERCENTAGE;<GIFT;SPENDING_THRESHOLD>;NUM_PRODS;DEDUCTION */
        return super.toString() + ";"  /*percentage*/ + ";" + this.gift.getId() + ";" + this.spendingThreshold + ";"
                /*numThreshold*/ + ";" /*deduction*/;
    }
}