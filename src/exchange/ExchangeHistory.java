/**
 * 
 */
package exchange;

import user.*;

import java.util.*;

/**
 * Class name: ExchangeHistory
 * <p>
 * Description: It implements the exchange history
 * @author Duna P.R.
 * @version 1.0
 * @see Exchange
 */
public class ExchangeHistory {

	private Set<Exchange> exchanges;
	private RegisteredClient owner;
	
	/**
	 * Creates an exchange history
	 * 
	 * @param exchanges, the exchanges of the history
	 * @param owner, the user associated to the history
	 */
	public ExchangeHistory(Set<Exchange> exchanges, RegisteredClient owner) {
		this.exchanges = exchanges;
		this.owner = owner;
	}

	/**
	 * Creates an empty exchange history
	 * 
	 * @param owner, the user associated to the history
	 */
	public ExchangeHistory(RegisteredClient owner){
		this(Collections.emptySet(), owner);
	}
	
/*------------------------------------------------------SETTERS AND GETTERS---------------------------------------------------------------*/
	
//Creo que no tiene sentido hacer getters y setter the esta clase
	
/*---------------------------------------------------------METHODS------------------------------------------------------------------------*/
	
	public void addExchange(Exchange newExchange) {
		exchanges.add(newExchange);
	}
	
	
}
