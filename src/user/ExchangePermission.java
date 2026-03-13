package user;
import exchange.Exchange;
import product.SecondHandProduct;

/**
 * Class name: ExchangePermission
 * <p>
 * Description: It implements the permission that allows an employee to manage Exchanges
 * @author Ana O.R.
 * @version 1.0
 * @see Exchange
 * @see SecondHandProduct
 */
public class ExchangePermission {
    // NOTE: No tiene atributos propios
	//nota prueba

    /**
     * It allows an employee to change an exchange's status
     * @param exchange  the desired exchange
     * @param exchanged whether the exchange will be set as exchanged or not
     */
    public void manageExchange(Exchange exchange, boolean exchanged) {
        exchange.changeExchanged(exchanged);
    }

    /**
     * It allows an employee to set a price (valuation) to a second hand product
     * @param secondHandProduct the desired product
     * @param valuation         the product's price
     */
    public void valuate(SecondHandProduct secondHandProduct, double valuation) {
        secondHandProduct.changePrice(valuation);
    }
}