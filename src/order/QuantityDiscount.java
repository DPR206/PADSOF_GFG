package order;

public interface QuantityDiscount {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    /**
     * It gets the quantity discount's discount
     * @return the quantity discount's discount
     */
    double getDeduction();

    /**
     * It allows the manager to change the quantity discount's discount
     * @param newDeduction the new discount
     */
    void setDeduction(double newDeduction);

    /**
     * It gets the quantity discount's amount of products in a cart from which the discount can take place
     * @return the amount of products in a cart from which the discount can take place
     */
    int getUdsThreshold();

    /**
     * It allows the manager to change the quantity discount's amount of products in a cart from which the discount can
     * take place
     * @param udsThreshold the new amount of products in a cart from which the discount can take place
     */
    void setUdsThreshold(int udsThreshold);
}