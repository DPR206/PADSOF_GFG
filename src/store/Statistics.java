/**
 *
 */
package store;

import product.Category;
import product.StoreProduct;
import user.RegisteredClient;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * It implements the statistics
 *
 * @author Duna P.R.
 * @version 1.4.
 */
public class Statistics {
	private static Statistics INSTANCE;
	private static double total_revenue;

	private HashMap<Month, Double> revenueByMonth;
	private List<RegisteredClient> clients;
	private HashMap<String, Category> categories;
	private List<StoreProduct> storeProducts;
	private double revenue_valuation;

	/**
	 * Statistics' constructor
	 */
	private Statistics() {
		this.revenueByMonth = new HashMap<Month, Double>();
		for (Month month : Month.values()) {
            this.revenueByMonth.put(month, 0.0);
        }
		this.clients = new ArrayList<>(Store.getInstance().getRegisteredClients().values());
		this.categories = Store.getInstance().getCategories();
		this.storeProducts = new ArrayList<>(Store.getInstance().getStoreProducts().values());
		this.revenue_valuation = 0.0;
		Statistics.total_revenue = 0.0;
	}

	/**
	 * Obtains the statistics of the store
	 *
	 * @return the INSTANCE
	 */
	public static Statistics getINSTANCE() {
		if(Statistics.INSTANCE == null)
			Statistics.INSTANCE = new Statistics();
		return INSTANCE;
	}

	/**
	 * Obtains the revenue by months
	 *
	 * @return the revenueByMonth a HashMap of the revenues
	 */
	public HashMap<Month, Double> getRevenueByMonth() {
		return revenueByMonth;
	}

	/**
	 * Sets the revenue by month
	 *
	 * @param revenueByMonth the revenueByMonth to set
	 */
	public void setRevenueByMonth(HashMap<Month, Double> revenueByMonth) {
		this.revenueByMonth = revenueByMonth;
	}

	/**
	 * Obtains the registered clients
	 *
	 * @return the clients in the statistics
	 */
	public List<RegisteredClient> getClients() {
		return Collections.unmodifiableList(this.clients);
	}

	/**
	 * Sets the list of clients in the statistics
	 *
	 * @param clients the clients to set
	 */
	public void setClients(List<RegisteredClient> clients) {
		this.clients = clients;
	}

	/**
	 * The categories in the statistics
	 *
	 * @return the categories in the statistics
	 */
	public HashMap<String, Category> getCategories() {
		return categories;
	}

	/**
	 * Sets the categories in the statistics
	 *
	 * @param categories the categories to set
	 */
	public void setCategories(HashMap<String, Category> categories) {
		this.categories = categories;
	}

	/**
	 * Obtains the revenues from valuations
	 *
	 * @return the revenue_valuation the total amount in valuation revenues
	 */
	public double getRevenue_valuation() {
		return revenue_valuation;
	}

	/**
	 * Sets the valuation revenues
	 *
	 * @param revenue_valuation the revenue_valuation to set
	 */
	public void setRevenue_valuation(double revenue_valuation) {
		this.revenue_valuation = revenue_valuation;
	}

	/**
	 * Obtains the total revenue of the store
	 *
	 * @return the total_revenue
	 */
	public static double getTotal_revenue() {
		return total_revenue;
	}


/*-----------------------------------------------------------METHODS--------------------------------------------------------------*/

	/**
	 * Increases the revenue in a certain month as well as the revenues in valuations/products
	 *
	 * @param quantity the increase in the revenue
	 * @param type the type of revenue
	 * @param date the date the revenue took place
	 * @param products the products that were affected in the revenue (if there were any)
	 */
	public void addRevenue(Double quantity, RevenueType type, LocalDate date, StoreProduct...products) {
		this.revenueByMonth.computeIfPresent(date.getMonth(), (month, currentValue) -> currentValue + quantity);
		Statistics.total_revenue += quantity;
		if(type == RevenueType.VALUATION)
			this.revenue_valuation += quantity;
		else if(type == RevenueType.PRODUCTS)
			for(StoreProduct p:products) {
				p.increaseSales(date);
				for(Category c : p.getCategories())
					c.increaseRevenue(quantity);
			}
	}

	/**
	 * Obtains the revenue of a category
	 *
	 * @param name the name of the category
	 * @return the revenue made by that category
	 */
	public double getRevenueByCategory(String name)
	{
		return this.categories.get(name).getRevenue();
	}

	/**
	 * Obtains the revenues by category
	 *
	 * @return a HashMap of each category and its revenue
	 */
	public HashMap<Category, Double> getRevenueAllCategories(){
		HashMap<Category, Double> revenueCategories = new HashMap<>();
		for(Category c : this.getCategories().values())
			revenueCategories.put(c, c.getRevenue());
		return revenueCategories;
	}


	/**
	 * Obtains a list in descending order of store products based on their sales
	 *
	 * @return a list of store products
	 */
	public List<StoreProduct> getProductsBySales() {
		return this.storeProducts.stream().sorted(Comparator.comparingInt(StoreProduct::getSales).reversed())
                                 .collect(Collectors.toUnmodifiableList());
	}

	/**
	 * Obtains the sales of each store product
	 *
	 * @return a HashMap of each store product and its sales
	 */
	public HashMap<StoreProduct, Integer> getProductsSales(){
		HashMap<StoreProduct, Integer> productsAndSales = new HashMap<>();
		for(StoreProduct sp : this.getProductsBySales())
			productsAndSales.put(sp, sp.getSales());
		return productsAndSales;
	}

	/**
	 * Obtains the sales of each product and its percentage regarding total revenues
	 *
	 * @return a HashMap of each store product and its sales and percentage
	 */
	public HashMap<StoreProduct, String> getProductsTotalPercentage(){
		HashMap<StoreProduct, String> productsPercentage = new HashMap<>();
		for(StoreProduct p : this.getProductsBySales()) {
			String data = "Sales: " + p.getSales() + " Percentage: " + this.TotalPercentage(p) + "% \n";
			productsPercentage.put(p, data);
		}
		return productsPercentage;
	}

	/**
	 * Obtains the sales of each product and its percentage regarding month revenues
	 *
	 * @param m the month to calculate
	 * @return a HashMap of each store product and its sales and percentage
	 */
	public HashMap<StoreProduct, String> getProductsTotalPercentage(Month m){
		HashMap<StoreProduct, String> productsPercentage = new HashMap<>();
		for(StoreProduct p : this.getProductsBySales()) {
			String data = "Sales: " + p.getSales() + " Percentage: " + this.MonthPercentage(p, m) + "% \n";
			productsPercentage.put(p, data);
		}
		return productsPercentage;
	}

	/**
	 * Calculates the percentage of a product regarding total revenue
	 *
	 * @param p the store product
	 * @return the percentage regarding total revenue
	 */
	private double TotalPercentage(StoreProduct p) {
		return (p.getPrice()*p.getSales())/Statistics.total_revenue*100;
	}

	/**
	 * Calculates the percentage of a product regarding monthly revenue
	 *
	 * @param p the store product
	 * @param m the month to calculate of
	 * @return the percentage regarding monthly revenue
	 */
	private double MonthPercentage(StoreProduct p, Month m) {
		return (p.getPrice() * p.getSalesByMonth().get(m))/this.revenueByMonth.get(m)*100;
	}

	/**
	 * Makes a descending list of the number of orders of each client
	 *
	 * @return a descending list of registered clients
	 */
	public List<RegisteredClient> getUsersMostOrders()
	{
        //Descending order
        /*Make the result a list*/
        return this.clients.stream() //Obtain data
                           .sorted(Comparator.comparingInt(
                                                     RegisteredClient::getNumOrders) //Compare based on the number of orders (type int)
                                             .reversed()).collect(Collectors.toUnmodifiableList());
	}

	/**
	 * Obtains the number of orders of each client
	 *
	 * @return a HashMap of each client and its numbers of orders
	 */
	public HashMap<RegisteredClient, Integer> getUserAndOrders(){
		HashMap<RegisteredClient, Integer> userOrders = new HashMap<>();
		for(RegisteredClient rc : this.getUsersMostOrders())
			userOrders.put(rc, rc.getNumOrders());
		return userOrders;
	}

	/**
	 * Makes a descending list of the number of exchanges of each client
	 *
	 * @return  a descending list of registered clients
	 */
	public List<RegisteredClient> getUsersMostExchanges(){
		return this.clients.stream().sorted(Comparator.comparingInt(RegisteredClient::getNumExchanges).reversed())
                           .collect(Collectors.toUnmodifiableList());
	}

	/**
	 * Obtains the number of exchanges of each client
	 *
	 * @return a HashMap of each client and its numbers of exchanges
	 */
	public HashMap<RegisteredClient, Integer> getUserAndExchanges(){
		HashMap<RegisteredClient, Integer> userExchanges = new HashMap<>();
		for(RegisteredClient rc : this.getUsersMostExchanges())
			userExchanges.put(rc, rc.getNumExchanges());
		return userExchanges;
	}


}