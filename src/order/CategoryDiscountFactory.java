package order;

import product.Category;
import product.StoreProduct;
import store.Store;

import java.time.LocalDateTime;

/**
 * Class name: CategoryDiscountFactory
 * <p>
 * Description: It implements the factory for those with category coverage
 * @author Ana O.R.
 * @version 1.0
 * @see Store
 * @see StoreProduct
 * @see Category
 */
public class CategoryDiscountFactory implements DiscountFactory {
    /** The category discount's desired categories */
    private Category[] targetCategories;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Category discount factory
     * @param targetCategories the desired categories
     */
    public CategoryDiscountFactory(Category... targetCategories) {
        this.targetCategories = targetCategories;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * The fixed percentage discount's general constructor whose coverage is Category
     * @param id         the discount's id
     * @param startDate  the discount's start date
     * @param endDate    the discount's end date
     * @param percentage the discount's percentage
     * @return the new CategoryFixedPercentage discount
     */
    @Override
    public FixedPercentageDiscount createFixedPercentageDiscount(String id, LocalDateTime startDate,
                                                                 LocalDateTime endDate, double percentage) {
        return new CategoryFixedPercentage(id, startDate, endDate, percentage, this.targetCategories);
    }

    /**
     * The fixed percentage discount's constructor with default id whose coverage is Category
     * @param startDate  the discount's start date
     * @param endDate    the discount's end date
     * @param percentage the discount's percentage
     * @return the new CategoryFixedPercentage discount
     */
    @Override
    public FixedPercentageDiscount createFixedPercentageDiscount(LocalDateTime startDate, LocalDateTime endDate,
                                                                 double percentage) {
        return new CategoryFixedPercentage(startDate, endDate, percentage, this.targetCategories);
    }

    /**
     * The gift discount's general constructor whose coverage is Category
     * @param id                the discount's id
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param gift              the discount's gift
     * @return the new CategoryGift discount
     */
    @Override
    public GiftDiscount createGiftDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                           double spendingThreshold, StoreProduct gift) {
        return new CategoryGift(id, startDate, endDate, spendingThreshold, gift, this.targetCategories);
    }

    /**
     * The gift discount's constructor with default id whose coverage is Category
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param gift              the discount's gift
     * @return the new CategoryGift discount
     */
    @Override
    public GiftDiscount createGiftDiscount(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                           StoreProduct gift) {
        return new CategoryGift(startDate, endDate, spendingThreshold, gift, this.targetCategories);
    }

    /**
     * The quantity discount's general constructor whose coverage is Category
     * @param id           the discount's id
     * @param startDate    the discount's start date
     * @param endDate      the discount's end date
     * @param numThreshold the discount's number of purchased products threshold
     * @param deduction    the discount's deduction
     * @return the new CategoryQuantity discount
     */
    @Override
    public QuantityDiscount createQuantityDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                                   int numThreshold, double deduction) {
        return new CategoryQuantity(id, startDate, endDate, numThreshold, deduction, this.targetCategories);
    }

    /**
     * The quantity discount's constructor with default id whose coverage is Category
     * @param startDate    the discount's start date
     * @param endDate      the discount's end date
     * @param numThreshold the discount's number of purchased products threshold
     * @param deduction    the discount's deduction
     * @return the new CategoryQuantity discount
     */
    @Override
    public QuantityDiscount createQuantityDiscount(LocalDateTime startDate, LocalDateTime endDate, int numThreshold,
                                                   double deduction) {
        return new CategoryQuantity(startDate, endDate, numThreshold, deduction, this.targetCategories);
    }

    /**
     * The volume discount's general constructor whose coverage is Category
     * @param id                the discount's id
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param deduction         the discount's deduction
     * @return the new CategoryVolume discount
     */
    @Override
    public VolumeDiscount createVolumeDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                               double spendingThreshold, double deduction) {
        return new CategoryVolume(id, startDate, endDate, spendingThreshold, deduction, this.targetCategories);
    }

    /**
     * The volume discount's constructor with default id whose coverage is Category
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param deduction         the discount's deduction
     * @return the new CategoryVolume discount
     */
    @Override
    public VolumeDiscount createVolumeDiscount(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                               double deduction) {
        return new CategoryVolume(startDate, endDate, spendingThreshold, deduction, this.targetCategories);
    }
}