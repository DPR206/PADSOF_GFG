package discount;

import product.StoreProduct;
import store.Store;

import java.time.LocalDateTime;

/**
 * It implements the factory for those with store product coverage
 * @author Ana O.R.
 * @version 1.0
 * @see Store
 * @see StoreProduct
 */
public class ProductDiscountFactory implements DiscountFactory {
    /** The product discount's desired store products */
    private StoreProduct[] targetProducts;
    /** Whether the discount is applied over all the store products or not */
    private boolean overWholeStore;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Product discount factory with a product selection
     * @param targetProducts the desired targetProducts
     */
    public ProductDiscountFactory(StoreProduct[] targetProducts) {
        this.targetProducts = targetProducts;
        this.overWholeStore = false;
    }

    /**
     * Instantiates a new Product discount factory over the whole store
     * @param overWholeStore whether the discount is applied over all the store products or not
     */
    public ProductDiscountFactory(boolean overWholeStore) {
        this.overWholeStore = overWholeStore;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * The fixed percentage discount's general constructor whose coverage is Product
     * @param id         the discount's id
     * @param startDate  the discount's start date
     * @param endDate    the discount's end date
     * @param percentage the discount's percentage
     * @return the new ProductFixedPercentage discount
     */
    @Override
    public ProductFixedPercentage createFixedPercentageDiscount(String id, LocalDateTime startDate,
                                                                LocalDateTime endDate, double percentage) {
        if (this.overWholeStore) {
            return new ProductFixedPercentage(id, startDate, endDate, percentage, true);
        }

        return new ProductFixedPercentage(id, startDate, endDate, percentage, this.targetProducts);
    }

    /**
     * The fixed percentage discount's constructor with default id whose coverage is Product
     * @param startDate  the discount's start date
     * @param endDate    the discount's end date
     * @param percentage the discount's percentage
     * @return the new ProductFixedPercentage discount
     */
    @Override
    public ProductFixedPercentage createFixedPercentageDiscount(LocalDateTime startDate, LocalDateTime endDate,
                                                                double percentage) {
        if (this.overWholeStore) {
            return new ProductFixedPercentage(startDate, endDate, percentage, true);
        }

        return new ProductFixedPercentage(startDate, endDate, percentage, this.targetProducts);
    }

    /**
     * The gift discount's general constructor whose coverage is Product
     * @param id                the discount's id
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param gift              the discount's gift
     * @return the new ProductGift discount
     */
    @Override
    public ProductGift createGiftDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                          double spendingThreshold, StoreProduct gift) {
        if (this.overWholeStore) {
            return new ProductGift(id, startDate, endDate, spendingThreshold, gift, true);
        }

        return new ProductGift(id, startDate, endDate, spendingThreshold, gift, this.targetProducts);
    }

    /**
     * The gift discount's constructor with default id whose coverage is Product
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param gift              the discount's gift
     * @return the new ProductGift discount
     */
    @Override
    public ProductGift createGiftDiscount(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                          StoreProduct gift) {
        if (this.overWholeStore) {
            return new ProductGift(startDate, endDate, spendingThreshold, gift, true);
        }

        return new ProductGift(startDate, endDate, spendingThreshold, gift, this.targetProducts);
    }

    /**
     * The quantity discount's general constructor whose coverage is Product
     * @param id           the discount's id
     * @param startDate    the discount's start date
     * @param endDate      the discount's end date
     * @param numThreshold the discount's number of purchased targetProducts threshold
     * @param deduction    the discount's deduction
     * @return the new Product discount
     */
    @Override
    public ProductQuantity createQuantityDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                                  int numThreshold, double deduction) {
        if (this.overWholeStore) {
            return new ProductQuantity(id, startDate, endDate, numThreshold, deduction, true);
        }

        return new ProductQuantity(id, startDate, endDate, numThreshold, deduction, this.targetProducts);
    }

    /**
     * The quantity discount's constructor with default id whose coverage is Product
     * @param startDate    the discount's start date
     * @param endDate      the discount's end date
     * @param numThreshold the discount's number of purchased targetProducts threshold
     * @param deduction    the discount's deduction
     * @return the new ProductQuantity discount
     */
    @Override
    public ProductQuantity createQuantityDiscount(LocalDateTime startDate, LocalDateTime endDate, int numThreshold,
                                                  double deduction) {
        if (this.overWholeStore) {
            return new ProductQuantity(startDate, endDate, numThreshold, deduction, true);
        }

        return new ProductQuantity(startDate, endDate, numThreshold, deduction, this.targetProducts);
    }

    /**
     * The volume discount's general constructor whose coverage is Product
     * @param id                the discount's id
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param deduction         the discount's deduction
     * @return the new ProductVolume discount
     */
    @Override
    public ProductVolume createVolumeDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                              double spendingThreshold, double deduction) {
        if (this.overWholeStore) {
            return new ProductVolume(id, startDate, endDate, spendingThreshold, deduction, true);
        }

        return new ProductVolume(id, startDate, endDate, spendingThreshold, deduction, this.targetProducts);
    }

    /**
     * The volume discount's constructor with default id whose coverage is Product
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param deduction         the discount's deduction
     * @return the new ProductVolume discount
     */
    @Override
    public ProductVolume createVolumeDiscount(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                              double deduction) {
        if (this.overWholeStore) {
            return new ProductVolume(startDate, endDate, spendingThreshold, deduction, true);
        }

        return new ProductVolume(startDate, endDate, spendingThreshold, deduction, this.targetProducts);
    }
}