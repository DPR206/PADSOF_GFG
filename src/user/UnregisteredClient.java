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
	private LocalDate registerDate;
	private String dni;
	private Carrito c;
	//searcher
	
	/**
	 * Creates a new unregistered client
	 */
	public UnregisteredClient() {
	}
	
	public void buy() {
		this.c.payOrder() //inicia sesión
	}

	public void removeFromCart(StoreProduct sp){
		c.cancelProduct(sp);
	}

	public void addProduct(StoreProduct wanted){
		c.addProduct(wanted);
	}
}