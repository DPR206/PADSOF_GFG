/**
 * 
 */
package store;

import java.util.*;
import java.time.*;

import user.RegisteredClient;
import product.*;

/**
 * It implements the statistics
 * 
 * @author Duna P.R.
 * @version 1.1
 */
public class Statistics {
	private static Statistics INSTANCE;
	
	private HashMap<Month, Double> revenueByMonth;
	private HashMap<String, RegisteredClient> clients;
	private HashMap<String, Category> categories;
	private HashMap<Month, Double> revenue_valuation;
	
	/**
	 * Statistics' constructor
	 */
	private Statistics() {
		this.revenueByMonth = new HashMap<Month, Double>();
		for (Month month : Month.values()) {
            this.revenueByMonth.put(month, 0.0);
        }
		this.clients = Store.getInstance().getRegisteredClients();
		this.categories = Store.getInstance().getCategories();
		this.revenue_valuation = new HashMap<Month, Double>();
		for (Month month : Month.values()) {
            this.revenue_valuation.put(month, 0.0);
        }
	}

	/**
	 * @return the INSTANCE
	 */
	public static Statistics getINSTANCE() {
		if(Statistics.INSTANCE == null)
			Statistics.INSTANCE = new Statistics();
		return INSTANCE;
	}

	/**
	 * @return the revenueByMonth
	 */
	public HashMap<Month, Double> getRevenueByMonth() {
		return revenueByMonth;
	}

	/**
	 * @param revenueByMonth the revenueByMonth to set
	 */
	public void setRevenueByMonth(HashMap<Month, Double> revenueByMonth) {
		this.revenueByMonth = revenueByMonth;
	}

	/**
	 * @return the clients
	 */
	public HashMap<String, RegisteredClient> getClients() {
		return clients;
	}

	/**
	 * @param clients the clients to set
	 */
	public void setClients(HashMap<String, RegisteredClient> clients) {
		this.clients = clients;
	}

	/**
	 * @return the categories
	 */
	public HashMap<String, Category> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(HashMap<String, Category> categories) {
		this.categories = categories;
	}
	
	/**
	 * @return the revenue_valuation
	 */
	public HashMap<Month, Double> getRevenue_valuation() {
		return revenue_valuation;
	}

	/**
	 * @param revenue_valuation the revenue_valuation to set
	 */
	public void setRevenue_valuation(HashMap<Month, Double> revenue_valuation) {
		this.revenue_valuation = revenue_valuation;
	}
	

/*-----------------------------------------------------------METHODS--------------------------------------------------------------*/
	
	

	public void addRevenue(Double quantity, RevenueType type, LocalDate date, StoreProduct...products) {
		this.revenueByMonth.computeIfPresent(date.getMonth(), (month, currentValue) -> currentValue + quantity);
		if(type == RevenueType.VALUATION)
			this.revenue_valuation.computeIfPresent(date.getMonth(), (month, currentValue) -> currentValue + quantity);
		else
			;
		
	}

	
	
}
