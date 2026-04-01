/**
 *
 */
package user;

import order.*;
import product.Pack;
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
        super();
    	c = new Cart();
        s = Store.getInstance();
    }

    public void buy() {

        this.c.payOrder(); //inicia sesión
        this.s.signIn();
    }

    /**
     * Removes a product from the cart
     *
     * @param product, the product to remove
     */
    public void deleteCart(StoreProduct product){
    	this.c.cancelProduct(product);
    }

    /**
     * Removes a pack from the cart
     *
     * @param pack, the pack to remove
     */
    public void deleteCart(Pack pack) {
    	this.c.cancelPack(pack);
    }

    /**
     * Adds a product to the cart
     * @param product, the product to add
     */
    public void addCart(StoreProduct product) {
    	this.c.addProduct(product);
    }

    /**
     * Adds a pack to the cart
     *
     * @param pack, the pack to add
     */
    public void addCart(Pack pack) {
    	this.c.addPack(pack);
    }

    /**
     * It gets the unregistered client's cart
     * @return the unregistered client's cart
     */
    public Cart getCart(){
        return this.c;
    }


}