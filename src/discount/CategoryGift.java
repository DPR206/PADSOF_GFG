package discount;

import product.Category;
import product.StoreProduct;

import java.time.LocalDateTime;

/**
 * It implements the Category discount whose type is Gift
 * @author Ana O.R.
 * @version 1.0
 * @see StoreProduct
 * @see Category
 */
public class CategoryGift extends CategoryDiscount implements GiftDiscount {
    /** The product gifted to the client when a certain spending threshold is met */
    StoreProduct gift;
    /** The spending threshold that allows the discount to take place */
    private double spendingThreshold;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A gift discount's general constructor with categories
     * @param id                the discount's id
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param gift              the product gifted to the client when a certain spending threshold is met
     * @param categories        the discount's categories
     * @throws NullPointerException     the null pointer exception
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CategoryGift(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                        StoreProduct gift, Category... categories)
            throws NullPointerException, IllegalArgumentException {
        super(id, DiscountType.GIFT, DiscountCoverage.CATEGORY, startDate, endDate);
        this.addCategories(categories);
        this.setSpendingThreshold(spendingThreshold);
        this.setGift(gift);
    }

    /**
     * Instantiates a new gift discount with categories
     * @param startDate         the date when the discount starts
     * @param endDate           the date when the discount ends
     * @param spendingThreshold the spending threshold that allows the discount to take place
     * @param gift              the product gifted to the client when a certain spending threshold is met
     * @param categories        the discount's categories
     * @throws NullPointerException     the null pointer exception
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CategoryGift(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold, StoreProduct gift,
                        Category... categories) throws NullPointerException, IllegalArgumentException {
        super(DiscountType.GIFT, DiscountCoverage.CATEGORY, startDate, endDate);
        this.addCategories(categories);
        this.setSpendingThreshold(spendingThreshold);
        this.setGift(gift);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It returns the discount's basic info
     * @return the discount's basic info
     */
    public String getPrintInfo() {
        return "DUE"; // DUE
    }

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
     * @throws IllegalArgumentException the spending threshold must be greater than 0
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