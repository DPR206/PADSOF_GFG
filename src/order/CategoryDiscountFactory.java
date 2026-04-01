package order;

import product.Category;
import product.StoreProduct;

import java.time.LocalDateTime;

public class CategoryDiscountFactory implements DiscountFactory {
    private Category[] targetCategories;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public CategoryDiscountFactory(Category... categories) {
        this.targetCategories = categories;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    @Override
    public FixedPercentageDiscount createFixedPercentageDiscount(String id, LocalDateTime startDate,
                                                                 LocalDateTime endDate, double percentage) {
        return new CategoryFixedPercentage(id, startDate, endDate, percentage, this.targetCategories);
    }

    @Override
    public GiftDiscount createGiftDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                           double spendingThreshold, StoreProduct gift) {
        return new CategoryGift(id, startDate, endDate, spendingThreshold, gift, this.targetCategories);
    }

    @Override
    public QuantityDiscount createQuantityDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                                   int udsThreshold, double deduction) {
        return new CategoryQuantity(id, startDate, endDate, udsThreshold, deduction, this.targetCategories);
    }

    @Override
    public VolumeDiscount createVolumeDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                               double spendingThreshold, double deduction) {
        return new CategoryVolume(id, startDate, endDate, spendingThreshold, deduction, this.targetCategories);
    }
}