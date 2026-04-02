package order;

import product.StoreProduct;

/**
 * It defines the base Volume discount's methods
 * @author Ana O.R.
 * @version 1.0
 * @see StoreProduct
 */
public interface VolumeDiscount {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the volume discount's deduction
     * @return the volume discount's deduction
     */
    double getDeduction();

    /**
     * It allows the manager to change the volume discount's discount
     * @param newDeduction the new deduction
     */
    void setDeduction(double newDeduction);

    /**
     * It sets the volume discount's spending threshold.
     * @return the volume discount's spending threshold
     */
    double getSpendingThreshold();

    /**
     * It allows the manager to change the volume discount's spending threshold
     * @param spendingThreshold the new spending threshold
     */
    void setSpendingThreshold(double spendingThreshold);
}