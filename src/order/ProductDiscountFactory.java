package order;

import product.StoreProduct;

import java.time.LocalDateTime;

public class ProductDiscountFactory implements DiscountFactory {
    private StoreProduct[] products;
    private boolean overWholeStore;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    ProductDiscountFactory(StoreProduct[] products) {
        this.products = products;
        this.overWholeStore = false;
    }

    ProductDiscountFactory(boolean overWholeStore) {
        this.overWholeStore = overWholeStore;
    }
    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    public ProductFixedPercentage createFixedPercentageDiscount(String id, LocalDateTime startDate,
                                                                LocalDateTime endDate, double percentage) {
        if (this.overWholeStore) {
            return new ProductFixedPercentage(id, startDate, endDate, percentage, true);
        }

        return new ProductFixedPercentage(id, startDate, endDate, percentage, this.products);
    }

    public ProductGift createGiftDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                          double spendingThreshold, StoreProduct gift) {
        if (this.overWholeStore) {
            return new ProductGift(id, startDate, endDate, spendingThreshold, gift, true);
        }

        return new ProductGift(id, startDate, endDate, spendingThreshold, gift, this.products);
    }

    public ProductQuantity createQuantityDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                                  int udsThreshold, double deduction) {
        if (this.overWholeStore) {
            return new ProductQuantity(id, startDate, endDate, udsThreshold, deduction, true);
        }

        return new ProductQuantity(id, startDate, endDate, udsThreshold, deduction, this.products);
    }

    public ProductVolume createVolumeDiscount(String id, LocalDateTime startDate, LocalDateTime endDate,
                                              double spendingThreshold, double deduction) {
        if (this.overWholeStore) {
            return new ProductVolume(id, startDate, endDate, spendingThreshold, deduction, true);
        }

        return new ProductVolume(id, startDate, endDate, spendingThreshold, deduction, this.products);
    }
}