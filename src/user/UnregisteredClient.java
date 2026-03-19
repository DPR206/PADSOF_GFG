/**
 *
 */
package user;

import order.Cart;
import product.StoreProduct;
import store.Store;

/**
 * Class name: UnregisteredClient
 * <p>
 * Description: It implements the unregistered client
 * @author Duna P.R.
 * @version 1.0
 * @see User
 */

public class UnregisteredClient extends User {
    private Cart c;
    private Store s;
    //searcher

    /**
     * Creates a new unregistered client
     */
    public UnregisteredClient() {
        c = new Cart();
        s = new Store();
    }

    public void buy() {

        this.c.payOrder(); //inicia sesión
        this.s.signIn();
    }

    public void removeFromCart(StoreProduct sp) {
        c.cancelProduct(sp);
    }

    public void addProduct(StoreProduct wanted) {
        c.addProduct(wanted);
    }
}