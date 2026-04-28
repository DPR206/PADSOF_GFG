/**
 *
 */
package model.user;

import model.utilities.IdType;
import model.utilities.exceptions.*;
import model.order.Cart;
import model.product.*;
import model.search.SearchType;
import model.store.Store;

import java.io.Serial;
import java.io.Serializable;
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

public class UnregisteredClient extends User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */
    private Cart c;
    private Store s;

   /**
    * Creates a new unregistered client
    * @param asc the products' order in the search
    */
    public UnregisteredClient(boolean asc) {
        super(UserType.UNREGISTERED_CLIENT, null, "Unregistered_Client", asc);
    	c = new Cart();
        s = Store.getInstance();

        this.getSearcher().setTypes(SearchType.S_STORE);
    }

    /**
     * Makes the buying process of the cart
     */
    public void buy(String username, String password, String dni) throws UsernameTaken, PasswordNotValid, InvalidId {
        this.s.signIn(username, password, dni, IdType.DNI); //DUE: Contemplar que se use otro
        try {
			this.c.payOrder();
		} catch (InvalidCardNumberException e) {
			System.out.println("Invalid card number");
		} catch (FailedInternetConnectionException e) {
			System.out.println("Failed Internet connection");
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
        return Store.getInstance().getStoreProductList();
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