package user;

import exchange.Exchange;
import product.SecondHandProduct;

/**
 * It implements the permission that allows an employee to manage Exchanges
 * @author Ana O.R.
 * @version 1.2
 * @see Exchange
 * @see SecondHandProduct
 */
public class ExchangePermission {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor for an exchange permission
     */
    public ExchangePermission() {
        // NOTE: Very complex constructor I know
    }

    /*--------------------------------------------------- MISC ----------------------------------------------------*/

    /**
     * It allows an employee to change an exchange's status
     * @param exchange  the desired exchange
     * @param exchanged whether the exchange will be set as exchanged or not
     * @throws NullPointerException exchange was null
     */
    public void manageExchange(Exchange exchange, boolean exchanged) throws NullPointerException {
        if (exchange == null) {
            throw new IllegalArgumentException("Exchange is null");
        }

        exchange.setExchanged(exchanged);
    }

    /**
     * It allows an employee to set a price (valuation) to a second hand product
     * @param secondHandProduct the desired product
     * @param valuation         the product's price
     * @throws IllegalArgumentException valuation was negative
     * @throws NullPointerException     second hand product was null
     */
    public void valuate(SecondHandProduct secondHandProduct, double valuation)
            throws IllegalArgumentException, NullPointerException {
        if (secondHandProduct == null) {
            throw new NullPointerException("Second Hand Product is null");
        }
        if (valuation < 0) {
            throw new NullPointerException("Value cannot be negative");
        }

        secondHandProduct.setEstimatedPrice(valuation);
    }

    // DUE: Realizar búsquedas
}