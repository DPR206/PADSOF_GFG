package order;

import product.StoreProduct;

import java.time.LocalDateTime;

public interface DiscountFactory {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    FixedPercentageDiscount createFixedPercentageDiscount(String id, LocalDateTime start, LocalDateTime end,
                                                          double percentage);

    GiftDiscount createGiftDiscount(String id, LocalDateTime startDate, LocalDateTime endDate, double spendingThreshold,
                                    StoreProduct gift);

    QuantityDiscount createQuantityDiscount(String id, LocalDateTime startDate, LocalDateTime endDate, int udsThreshold,
                                            double deduction);

    VolumeDiscount createVolumeDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                        double spendingThreshold, double deduction);
}