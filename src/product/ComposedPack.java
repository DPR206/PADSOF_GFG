/**
 * 
 */
package product;

import java.util.*;
/**
 * Class name: ComposedPack
 * <p>
 * Description: It implements the composed packs
 * @author Duna P.R.
 * @version 1.0
 * @see Pack
 */
public class ComposedPack extends Pack {

	private ArrayList<Pack> packs;
	
	/**
	 * 
	 */
	public ComposedPack(ArrayList<Pack> newPacks) {
		packs = newPacks;
	}


	/**
	 * Creates a new pack
	 * 
	 * @param id, the pack's id
	 * @param price, the pack's price
	 * @param products, the products the pack contains
	 */
	public Pack(int id, double price, ArrayList<StoreProduct> products, Date date) {
		this.id = id;
		this.price = price;
		this.products = products;
		this.dateAddCart = date;
	}
	
	/**
	 * Creates a new pack with default id and date
	 * 
	 * @param price, price of the pack
	 * @param products, the products the pack contains
	 */
	public Pack(double price, ArrayList<StoreProduct> products) {
		this(totalId, price, products, null);
		totalId++;
	}
	
	/**
	 * Creates a new pack with default id
	 * 
	 * @param price, price of the pack
	 * @param products, the products the pack contains
	 */
	public Pack(double price, ArrayList<StoreProduct> products, Date date) {
		this(totalId, price, products, date);
		totalId++;
	}
}
