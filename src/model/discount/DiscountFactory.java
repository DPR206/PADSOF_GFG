package model.discount;

import model.product.StoreProduct;

import java.time.LocalDateTime;

/**
 * It defines the base discount constructors (without specific coverage)
 * @author Ana O.R.
 * @version 1.0
 * @see StoreProduct
 */
public interface DiscountFactory {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * The fixed percentage discount's general constructor
     * @param id         the discount's id
     * @param startDate  the discount's start date
     * @param endDate    the discount's end date
     * @param percentage the discount's percentage
     * @return the new FixedPercentage discount
     */
    FixedPercentageDiscount createFixedPercentageDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                                          double percentage);

    /**
     * The fixed percentage discount's constructor with default id
     * @param startDate  the discount's start date
     * @param endDate    the discount's end date
     * @param percentage the discount's percentage
     * @return the new FixedPercentage discount
     */
    FixedPercentageDiscount createFixedPercentageDiscount(LocalDateTime startDate, LocalDateTime endDate,
                                                          double percentage);

    /**
     * The gift discount's general constructor
     * @param id                the discount's id
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param gift              the discount's gift
     * @return the new Gift discount
     */
    GiftDiscount createGiftDiscount(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                    StoreProduct gift);

    /**
     * The gift discount's constructor with default id
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param gift              the discount's gift
     * @return the new Gift discount
     */
    GiftDiscount createGiftDiscount(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                    StoreProduct gift);

    /**
     * The quantity discount's general constructor
     * @param id           the discount's id
     * @param startDate    the discount's start date
     * @param endDate      the discount's end date
     * @param numThreshold the discount's number of purchased products threshold
     * @param deduction    the discount's deduction
     * @return the new Quantity discount
     */
    QuantityDiscount createQuantityDiscount(String id, LocalDateTime startDate, LocalDateTime endDate, int numThreshold,
                                            double deduction);

    /**
     * The quantity discount's constructor with default id
     * @param startDate    the discount's start date
     * @param endDate      the discount's end date
     * @param numThreshold the discount's number of purchased products threshold
     * @param deduction    the discount's deduction
     * @return the new Quantity discount
     */
    QuantityDiscount createQuantityDiscount(LocalDateTime startDate, LocalDateTime endDate, int numThreshold,
                                            double deduction);

    /**
     * The volume discount's general constructor
     * @param id                the discount's id
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param deduction         the discount's deduction
     * @return the new Volume discount
     */
    VolumeDiscount createVolumeDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                        double spendingThreshold, double deduction);

    /**
     * The volume discount's constructor with default id
     * @param startDate         the discount's start date
     * @param endDate           the discount's end date
     * @param spendingThreshold the discount's spending threshold
     * @param deduction         the discount's deduction
     * @return the new Volume discount
     */
    VolumeDiscount createVolumeDiscount(LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                        double deduction);
}