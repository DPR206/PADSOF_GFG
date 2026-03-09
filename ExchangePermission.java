// NOTA: Revisar esto
/**
 * Classname: ExchangePermission
 * Description: It implements the permission that allows an employee to manage Exchanges
 *
 * @author Ana O.R.
 * @version 1.0
 *
 * @see Exchange
 * @see SecondHandProduct
 *
 * Copyright??
 */
public class ExchangePermission {
    // NOTA: No tiene atributos propios
    /**
     * It allows an employee to change an exchange's status
     * @param e the desired exchange
     * @param exchanged whether the exchange will be set as exchanged or not
     */
    void manageExchange(Exchange e, boolean exchanged) {
        e.exchanged = exchanged;
    }

    /**
     * It allows an employee to set a price (valuation) to a second hand product
     * @param p the desired product
     * @param valuation the product's price
     */
    void valuate(SecondHandProduct p, double valuation) {
        p.changePrice(valuation);
    }
}