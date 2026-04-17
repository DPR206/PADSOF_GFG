
package model.exchange;

import model.user.RegisteredClient;

import java.util.*;

/**
 * It implements the exchange history
 * @author Duna P.R.
 * @version 1.0
 * @see Exchange
 */
public class ExchangeHistory {

	private Set<Exchange> exchanges;
	private RegisteredClient owner;

	/**
	 * Creates an exchange history
	 * @param assignedExchanges the exchanges
	 * @param assignedOwner     the owner
	 */
	public ExchangeHistory(Set<Exchange> assignedExchanges, RegisteredClient assignedOwner) {
		this.exchanges = new HashSet<Exchange>(assignedExchanges);
		this.owner = assignedOwner;
	}

	/**
	 * Creates an empty exchange history
	 * @param assignedOwner the owner
	 */
	public ExchangeHistory(RegisteredClient assignedOwner){
		this(new HashSet<>(), assignedOwner);
	}

/*------------------------------------------------------SETTERS AND GETTERS---------------------------------------------------------------*/

	/**
	 * Obtains the exchanges in the history
	 * @return the exchanges, the exchanges made
	 */
	public Set<Exchange> getExchanges() {
		return Collections.unmodifiableSet(exchanges);
	}

	/**
	 * Obtains the user associated to the research history
	 * @return the owner, the owner of the history
	 */
	public RegisteredClient getOwner() {
		return owner;
	}

/*---------------------------------------------------------METHODS------------------------------------------------------------------------*/

	/**
	 * Adds a new exchange to the history
	 * @param newExchange the new exchange
	 */
	public void addExchange(Exchange newExchange) {
		exchanges.add(newExchange);
	}

	/**
	 * Add exchanges.
	 * @param newExchanges the new exchanges
	 */
	public void addExchanges(Set<Exchange> newExchanges) {
		exchanges.addAll(newExchanges);
	}

	@Override
	public String toString() {
		return "ExchangeHistory [exchanges=" + exchanges + ", owner=" + owner + "]";
	}


}