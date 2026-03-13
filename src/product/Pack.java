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
}
