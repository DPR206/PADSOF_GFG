/**
 *
 */
package product;

import java.time.LocalDate;
import java.util.*;
/**
 * Class name: Pack
 * <p>
 * Description: It implements the packs
 * @author Duna P.R.
 * @version 1.0
 * @see store.Store
 */
public class Pack {
	static public int totalId = 0;

	/** */
	private int id;
	private double price;
	private ArrayList<StoreProduct> products;
	private LocalDate dateAddCart = null;

	/*---------------------------------------------------Constructors---------------------------------------------------------------*/

	/**
	 * Creates a new pack
	 *
	 * @param id, the pack's id
	 * @param price, the pack's price
	 * @param products, the products the pack contains
	 */
	public Pack(int id, double price, ArrayList<StoreProduct> products, LocalDate date) {
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
	public Pack(double price, ArrayList<StoreProduct> products, LocalDate date) {
		this(totalId, price, products, date);
		totalId++;
	}

	/**
	 * Creates a new pack with no products
	 *
	 * @param price, price of the pack
	 * @param date, the products the pack contains
	 */
	public Pack(double price, LocalDate date) {
		this(totalId, price, new ArrayList<StoreProduct>(), date);
		totalId++;
	}

	/**
	 * Creates a new pack with no products
	 *
	 * @param price, price of the pack
	 * @param date, the products the pack contains
	 */
	public Pack(int id, double price, LocalDate date) {
		this(id, price, new ArrayList<StoreProduct>(), date);
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
		return (ArrayList<StoreProduct>) Collections.unmodifiableList(products);
	}

	/**
	 * Changes the array of products inside the pack
	 *
	 * @param products the products to set
	 */
	public void setProducts(ArrayList<StoreProduct> products) {
		this.products = products;
	}

	/**
	 * Obtains the date the pack was added to the cart
	 *
	 * @return the dateAddCart
	 */
	public LocalDate getDateAddCart() {
		return dateAddCart;
	}

	/**
	 * Sets the date the pack was added to the cart
	 *
	 * @param dateAddCart the dateAddCart to set
	 */
	public void setDateAddCart(LocalDate dateAddCart) {
		this.dateAddCart = dateAddCart;
	}

	/*-----------------------------------------------------Methods------------------------------------------------------------------*/

	/**
	 * Decreases the stock of the products included in the pack
	 */
	public void decreaseStock() {
		for(StoreProduct sp:products)
			sp.decreaseStock(1);
	}

	/**
	 * Increases the stock of the products included in the pack
	 */
	public void increaseStock() {
		for(StoreProduct sp: products)
			sp.increaseStock(1);
	}

	/**
	 * Adds a new product to the pack
	 *
	 * @param sp, store product to add
	 * @return true if the collection was changed successfully, false if not
	 */
	public boolean addProduct(StoreProduct sp) {
		return products.add(sp);
	}

	/**
	 * Eliminates a product from the pack
	 *
	 * @param sp, store product to eliminate
	 * @return true if the product was eliminated correctly, false if not
	 */
	public boolean eliminateProduct(StoreProduct sp) {
		return products.remove(sp);
	}

	/**
	 * Adds a new collection of products to the pack
	 *
	 * @param newProducts, the store products to add
	 * @return true if the products were added correctly, false if not
	 */
	public boolean addArrayProducts(ArrayList<StoreProduct> newProducts) {
		return products.addAll(newProducts);
	}

	/**
	 * Eliminates a collection of store products
	 *
	 * @param productsRemove, the store products to remove
	 * @return true if the products were removed correctly, false if not
	 */
	public boolean eliminateArrayProducts(ArrayList<StoreProduct> productsRemove) {
		return products.removeAll(productsRemove);
	}


	/**
	 * Obtains the raw price of all the products in the pack
	 *
	 * @return double, the raw price of the pack
	 */
	public double totalPrice() {
		double total = 0;
		for(StoreProduct sp: products)
			total += sp.getPrice();
		return total;
	}
}