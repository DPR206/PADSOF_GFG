package order;

import product.Pack;
import product.StoreProduct;

import java.time.LocalDateTime;

/**
 * Class name: PackDiscountFactory
 * <p>
 * Description: It implements the factory for those with pack coverage
 * @author Ana O.R.
 * @version 1.0
 * @see Pack
 * @see StoreProduct
 */
public class PackDiscountFactory implements DiscountFactory {
    /** The pack discount's desired packs */
    private final Pack[] targetPacks;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Pack discount factory
     * @param targetPacks the target packs
     */
    public PackDiscountFactory(Pack... targetPacks) {
        this.targetPacks = targetPacks;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * The fixed percentage discount's general constructor whose coverage is Pack
     * @param id         the discount's id
     * @param startDate  the discount's start date
     * @param endDate    the discount's end date
     * @param percentage the discount's percentage
     * @return the new PackFixedPercentage discount
     */
    @Override
    public FixedPercentageDiscount createFixedPercentageDiscount(String id, LocalDateTime startDate,
                                                                 LocalDateTime endDate, double percentage) {
        return new PackFixedPercentage(id, startDate, endDate, percentage, this.targetPacks);
    }

    /**
     * The fixed percentage discount's constructor with default id whose coverage is Pack
     * @param startDate  the discount's start date
     * @param endDate    the discount's end date
     * @param percentage the discount's percentage
     * @return the new PackFixedPercentage discount
     */
    @Override
    public FixedPercentageDiscount createFixedPercentageDiscount(LocalDateTime startDate, LocalDateTime endDate,
                                                                 double percentage) {
        return new PackFixedPercentage(startDate, endDate, percentage, this.targetPacks);
    }

    /**
     * The gift discount's general constructor whose coverage is Pack
     * @param id                the discount's id
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param gift              the discount's gift
     * @return the new PackGift discount
     */
    @Override
    public GiftDiscount createGiftDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                           double spendingThreshold, StoreProduct gift) {
        return new PackGift(id, startDate, endDate, spendingThreshold, gift, this.targetPacks);
    }

    /**
     * The gift discount's constructor with default id whose coverage is Pack
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param gift              the discount's gift
     * @return the new PackGift discount
     */
    @Override
    public GiftDiscount createGiftDiscount(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                           StoreProduct gift) {
        return new PackGift(startDate, endDate, spendingThreshold, gift, this.targetPacks);
    }

    /**
     * The quantity discount's general constructor whose coverage is Pack
     * @param id           the discount's id
     * @param startDate    the discount's start date
     * @param endDate      the discount's end date
     * @param numThreshold the discount's number of purchased products threshold
     * @param deduction    the discount's deduction
     * @return the new PackQuantity discount
     */
    @Override
    public QuantityDiscount createQuantityDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                                   int numThreshold, double deduction) {
        return new PackQuantity(id, startDate, endDate, numThreshold, deduction, this.targetPacks);
    }

    /**
     * The quantity discount's constructor with default id whose coverage is Pack
     * @param startDate    the discount's start date
     * @param endDate      the discount's end date
     * @param numThreshold the discount's number of purchased products threshold
     * @param deduction    the discount's deduction
     * @return the new PackQuantity discount
     */
    @Override
    public QuantityDiscount createQuantityDiscount(LocalDateTime startDate, LocalDateTime endDate, int numThreshold,
                                                   double deduction) {
        return new PackQuantity(startDate, endDate, numThreshold, deduction, this.targetPacks);
    }

    /**
     * The volume discount's general constructor whose coverage is Pack
     * @param id                the discount's id
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param deduction         the discount's deduction
     * @return the new PackVolume discount
     */
    @Override
    public VolumeDiscount createVolumeDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                               double spendingThreshold, double deduction) {
        return new PackVolume(id, startDate, endDate, spendingThreshold, deduction, this.targetPacks);
    }

    /**
     * The volume discount's constructor with default id whose coverage is Pack
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param deduction         the discount's deduction
     * @return the new PackVolume discount
     */
    @Override
    public VolumeDiscount createVolumeDiscount(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                               double deduction) {
        return new PackVolume(startDate, endDate, spendingThreshold, deduction, this.targetPacks);
    }
}