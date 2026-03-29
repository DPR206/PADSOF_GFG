/**
 * 
 */
package exchange;

import java.time.*;
import java.util.*;
import product.*;
import store.Store;
import user.*;

/**
 * It implements the exchange
 * 
 * @author Duna P.R.
 * @version 1.0
 */
public class Exchange {
    
	static int totalId = 0;
	
	private int id;
	private LocalDateTime date;
	private boolean exchanged;
	private HashMap<RegisteredClient, ArrayList<SecondHandProduct>> productos_propietario = new HashMap<>();
	
	
	

    /**
     * Creates a new exchange
     * 
	 * @param date, the date it was exchanged
	 * @param exchanged, whether the exchange was done
	 * @param user1, the registered client that owns the products "products1"
	 * @param products1, the products of user1
	 * @param user2, the registered client that owns the products "products2"
	 * @param products2, the products of user2
	 */
	public Exchange(LocalDateTime date, boolean exchanged, RegisteredClient user1, ArrayList<SecondHandProduct> products1,
			RegisteredClient user2, ArrayList<SecondHandProduct> products2) {
		this.date = date;
		this.exchanged = exchanged;
		this.productos_propietario.put(user1, products1);
		this.productos_propietario.put(user2, products2);
		this.id = totalId;
		totalId++;
		Store.getInstance().addExchange(this);
	}
	
	/**
	 * Creates an exchange that hasn't been exchanged
	 * 
	 * @param date, the date it will be exchanged
	 * @param user1, the registered client that owns the products "products1"
	 * @param products1, the products of user1
	 * @param user2, the registered client that owns the products "products2"
	 * @param products2, the products of user2
	 */
	public Exchange(LocalDateTime date, RegisteredClient user1, ArrayList<SecondHandProduct> products1,
			RegisteredClient user2, ArrayList<SecondHandProduct> products2) {
		this(date, false, user1, products1, user2, products2);
	}
	

/*-------------------------------------------------------------SETTERS AND GETTERS----------------------------------------------------------*/

	/**
	 * Obtains the id of a product
	 * 
	 * @return the id, the id of the product
	 */
	public int getId() {
		return id;
	}

	/**
	 * The time when the exchange took place
	 * 
	 * @return the date, the time of the exchange
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * Sets the date of the exchange
	 * 
	 * @param date the date to set
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	/**
	 * Obtains if the exchange was done
	 * 
	 * @return true if the exchange was done, false if not
	 */
	public boolean isExchanged() {
		return exchanged;
	}

	/**
	 * Sets the state of the exchange
	 * 
	 * @param exchanged the exchanged to set
	 */
	public void setExchanged(boolean exchanged) {
		this.exchanged = exchanged;
	}

	/**
	 * Obtains the total id of the exchanges
	 * @return the totalId
	 */
	public static int getTotalId() {
		return totalId;
	}
	
	/**
	 * Obtains the clients and products involved in the exchange
	 * 
	 * @return the productos_propietario, the HashMap of the clients and their products
	 */
	public HashMap<RegisteredClient, ArrayList<SecondHandProduct>> getProductos_propietario() {
		return productos_propietario;
	}	
	
/*---------------------------------------------------------------METHODS--------------------------------------------------------------------*/
	

	/**
	 * Changes if the exchange was done
	 * 
	 * @param exchanged, the state of exchange
	 */
	public void changeExchanged(boolean exchanged) {
        this.setExchanged(exchanged);
    }

	/**
	 * Changes the date of the exchange
	 * 
	 * @param date, the date of the exchange
	 */
	public void changeDate(LocalDateTime date) {
		this.setDate(date);
	}

	@Override
	public String toString() {
		return "Exchange [id=" + id + ", date=" + date + ", exchanged=" + exchanged + "]";
	}
	
	//Falta createNotification
	
	
}
