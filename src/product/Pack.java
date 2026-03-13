/**
 * 
 */
package product;

import java.util.*;
/**
 * Class name: Pack
 * <p>
 * Description: It implements the packs
 * @author Duna P.R.
 * @version 1.0
 * @see Store
 */
public class Pack {
	static int totalId = 0;
	
	private int id;
	private double price;
	private ArrayList<StoreProduct> products;
	
	/*---------------------------------------------------Constructors---------------------------------------------------------------*/
	
	/**
	 * Creates a new pack
	 * 
	 * @param id, the pack's id
	 * @param price, the pack's price
	 * @param products, the products the pack contains
	 */
	public Pack(int id, double price, ArrayList<StoreProduct> products) {
		this.id = id;
		this.price = price;
		this.products = products;
	}
	
	/**
	 * Creates a new pack with default id
	 * 
	 * @param price, price of the pack
	 * @param products, the products the pack contains
	 */
	public Pack(double price, ArrayList<StoreProduct> products) {
		this(totalId, price, products);
		totalId++;
	}

	/*----------------------------------------------------Getters and Setters---------------------------------------------------------------*/
	
	/**
	 * Obtains the pack's id
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Obtains the packs price
	 * 
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the packs price
	 * 
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Obtains the products inside the pack
	 * 
	 * @return the products, the products included
	 */
	public ArrayList<StoreProduct> getProducts() {
		return products;
	}

	/**
	 * Changes the array of products inside the pack 
	 * 
	 * @param products the products to set
	 */
	public void setProducts(ArrayList<StoreProduct> products) {
		this.products = products;
	}
	
	/*-----------------------------------------------------Methods------------------------------------------------------------------*/

	/**
	 * Changes the stock of the products included in the pack
	 */
	public void changeStock() {
		for(StoreProduct sp:products)
			sp.drecreaseStock(1);
	}
	
	public void addProduct(StoreProduct sp) {
		products.add(sp);
	}
	
	public void eliminateProduct(StoreProduct sp) {
		products.remove(sp);
	}
	
	public void addArrayProducts(ArrayList<StoreProduct> newProducts) {
		
	}
}
