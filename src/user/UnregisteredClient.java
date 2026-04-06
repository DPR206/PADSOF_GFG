/**
 *
 */
package user;

import order.Cart;
import product.*;
import search.SearchType;
import store.Store;

import java.util.List;

import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;

/**
 * It implements the unregistered client
 * @author Duna P.R.
 * @version 1.0
 * @see User
 */

public class UnregisteredClient extends User {
    private Cart c;
    private Store s;

    /**
     * Creates a new unregistered client
     */
    public UnregisteredClient(boolean asc) {
        super(UserType.UNREGISTERED_CLIENT, null, null, asc);
    	c = new Cart();
        s = Store.getInstance();

        this.getSearcher().setTypes(SearchType.S_STORE);
    }

    public void buy() {

    	this.s.signIn();
        try {
			this.c.payOrder(); //inicia sesión
		} catch (InvalidCardNumberException e) {
			System.out.println("Invalid card number");
		} catch (FailedInternetConnectionException e) {
			System.out.println("Failed internet connection");
		} catch (OrderRejectedException e) {
			System.out.println("Order rejected");
		}
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

    /**
     * Searches for the store products
     * @return the store product based on the filters
     */
    public List<StoreProduct> searchStoreProduct(){
        return this.getSearcher().searchStoreProducts();
    }

    /**
     * Searches for the store products based on the category
     *
     * @param c, the categories we want our searched products to belong to
     *
     * @return the store product based on the filters
     */
    public List<StoreProduct> searchStoreProductByCategory(Category... c){
        return this.getSearcher().searchByCategory(c);
    }

}