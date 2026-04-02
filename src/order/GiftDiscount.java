package order;

import product.StoreProduct;
import store.Store;

/**
 * Interface name: GiftDiscount
 * <p>
 * Description: It defines the base Gift discount's methods
 * @author Ana O.R.
 * @version 1.0
 * @see Store
 * @see StoreProduct
 */
public interface GiftDiscount {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the gift discount's gift
     * @return the gift discount's gift
     */
    StoreProduct getGift();

    /**
     * It allows the manager to change a gift discount's gift
     * @param newGift the new gift
     */
    void setGift(StoreProduct newGift);

    /**
     * It gets the gift discount's gift spending threshold.
     * @return the gift discount's gift spending threshold
     */
    double getSpendingThreshold();

    /**
     * It allows the manager to change the gift discount's spending threshold
     * @param spendingThreshold the new spending threshold
     * @throws IllegalArgumentException the illegal argument exception
     */
    void setSpendingThreshold(double spendingThreshold);
}