package order;

/**
 * Interface name: FixedPercentageDiscount
 * <p>
 * Description: It defines the base Fixed Percentage discount's methods
 * @author Ana O.R.
 * @version 1.0
 */
public interface FixedPercentageDiscount {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the fixed percentage discount's percentage.
     * @return the fixed percentage discount's percentage
     */
    double getPercentage();

    /**
     * It allows the manager to change the fixed percentage discount's percentage
     * @param newPercentage the new percentage
     * @throws IllegalArgumentException the percentage wasn't between 0 and 100
     */
    void setPercentage(double newPercentage) throws IllegalArgumentException;
}