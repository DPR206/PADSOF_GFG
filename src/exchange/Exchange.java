/**
 * 
 */
package exchange;

import java.time.*;

/**
 * Class name: Exchange
 * <p>
 * Description: It implements the exchange
 * @author Duna P.R.
 * @version 1.0
 * @see Pack
 */
public class Exchange {
    
	static int totalId = 0;
	
	private int id;
	private LocalDateTime date;
	private boolean exchanged;
	
	

    /**
     * Creates a new exchange
     * 
	 * @param date, the date it was exchanged
	 * @param exchanged, whether the exchange was done
	 */
	public Exchange(LocalDateTime date, boolean exchanged) {
		this.date = date;
		this.exchanged = exchanged;
		this.id = totalId;
		totalId++;
	}
	
	/**
	 * Creates an exchange with the date
	 * 
	 * @param date, the date it will be exchanged
	 */
	public Exchange(LocalDateTime date) {
		this(date, false);
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
	
	//Falta createNotification
}
