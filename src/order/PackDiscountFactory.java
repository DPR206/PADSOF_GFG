package order;

import product.Pack;
import product.StoreProduct;

import java.time.LocalDateTime;

public class PackDiscountFactory implements DiscountFactory {
    private Pack[] packs;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public PackDiscountFactory(Pack... packs) {
        this.packs = packs;
    }
    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    public PackFixedPercentage createFixedPercentageDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                                             double percentage) {
        return new PackFixedPercentage(id, startDate, endDate, percentage, this.packs);
    }

    public PackGift createGiftDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                       double spendingThreshold, StoreProduct gift) {
        return new PackGift(id, startDate, endDate, spendingThreshold, gift, this.packs);
    }

    public PackQuantity createQuantityDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                               int udsThreshold, double deduction) {
        return new PackQuantity(id, startDate, endDate, udsThreshold, deduction, this.packs);
    }

    public PackVolume createVolumeDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                           double spendingThreshold, double deduction) {
        return new PackVolume(id, startDate, endDate, spendingThreshold, deduction, this.packs);
    }
}