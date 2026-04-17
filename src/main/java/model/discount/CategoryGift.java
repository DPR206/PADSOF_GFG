package model.discount;

import model.product.Category;
import model.product.StoreProduct;

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
     * @param assignedId                the discount's id
     * @param assignedStartDate         the date when the discount starts
     * @param assignedEndDate           the date when the discount ends
     * @param assignedSpendingThreshold the spending threshold that allows the discount to take place
     * @param assignedGift              the product gifted to the client when a certain spending threshold is met
     * @param assignedCategories        the discount's categories
     * @throws NullPointerException     the null pointer exception
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CategoryGift(String assignedId, LocalDateTime assignedStartDate, LocalDateTime assignedEndDate,
                        double assignedSpendingThreshold, StoreProduct assignedGift, Category... assignedCategories)
            throws NullPointerException, IllegalArgumentException {
        super(assignedId, DiscountType.GIFT, DiscountCoverage.CATEGORY, assignedStartDate, assignedEndDate);
        this.addCategories(assignedCategories);
        this.setSpendingThreshold(assignedSpendingThreshold);
        this.setGift(assignedGift);
    }

    /**
     * Instantiates a new gift discount with categories
     * @param assignedStartDate         the date when the discount starts
     * @param assignedEndDate           the date when the discount ends
     * @param assignedSpendingThreshold the spending threshold that allows the discount to take place
     * @param assignedGift              the product gifted to the client when a certain spending threshold is met
     * @param assignedCategories        the discount's categories
     * @throws NullPointerException     the null pointer exception
     * @throws IllegalArgumentException the illegal argument exception
     */
    public CategoryGift(LocalDateTime assignedStartDate, LocalDateTime assignedEndDate,
                        double assignedSpendingThreshold, StoreProduct assignedGift, Category... assignedCategories)
            throws NullPointerException, IllegalArgumentException {
        super(DiscountType.GIFT, DiscountCoverage.CATEGORY, assignedStartDate, assignedEndDate);
        this.addCategories(assignedCategories);
        this.setSpendingThreshold(assignedSpendingThreshold);
        this.setGift(assignedGift);
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
     * It returns the discount's basic info
     * @return the discount's basic info
     */
    public String getPrintInfo() {
        return "Free " + this.gift.getName() + " if you spend over " + this.spendingThreshold + "€ on the " +
               "categories:" + this.getPrintCategories();
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
     * @param assignedSpendingThreshold the new spending threshold
     * @throws IllegalArgumentException the spending threshold must be greater than 0
     */
    public void setSpendingThreshold(double assignedSpendingThreshold) throws IllegalArgumentException {
        if (assignedSpendingThreshold < 0) {
            throw new IllegalArgumentException("The spending threshold must be greater than 0");
        }
        this.spendingThreshold = assignedSpendingThreshold;
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