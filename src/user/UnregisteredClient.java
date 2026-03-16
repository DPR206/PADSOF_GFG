/**
 * 
 */
package user;

import order.Cart;

/**
 * Class name: UnregisteredClient
 * <p>
 * Description: It implements the unregistered client
 * @author Duna P.R.
 * @version 1.0
 * @see User
 */

public class UnregisteredClient extends User {
	private Carrito c;
	private Store s;
	//searcher
	
	/**
	 * Creates a new unregistered client
	 */
	public UnregisteredClient() {
		c = new Carrito();
	}
	
	public void buy() {
		if(s.)
		this.c.payOrder() //inicia sesión
	}

	public void removeFromCart(StoreProduct sp){
		c.cancelProduct(sp);
	}

	public void addProduct(StoreProduct wanted){
		c.addProduct(wanted);
	}
}