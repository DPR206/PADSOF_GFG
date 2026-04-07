package product;

import java.time.*;
import java.util.*;

/**
 * It implements the simple packs
 * @author Duna P.R.
 * @version 1.0
 * @see Pack
 */
public class SimplePack extends Pack{


	/**
	 * Creates a new simple pack
	 *
	 * @param id, the pack's id
	 * @param price, the pack's price
	 * @param products, the products the pack contains
	 * @param date, the date the pack was added to the cart
	 */
	public SimplePack(int id, double price, ArrayList<StoreProduct> products, LocalDate date) {
		super(id, price, products, date);
	}

	/**
	 * Creates a new simple pack with default id and date
	 *
	 * @param price, price of the pack
	 * @param products, the products the pack contains
	 */
	public SimplePack(double price, ArrayList<StoreProduct> products) {
		super(price, products);
	}

	/**
	 * Creates a new simple pack with default id
	 *
	 * @param price, price of the pack
	 * @param products, the products the pack contains
	 * @param date, the date the pack was added to the cart
	 */
	public SimplePack(double price, ArrayList<StoreProduct> products, LocalDate date) {
		super(price, products, date);
	}

	/**
	 * Creates a new simple pack with no date
	 * @param id the pack's id
	 * @param price the pack's price
	 * @param products the products the pack contains
	 */
	public SimplePack(int id, double price, ArrayList<StoreProduct> products) {
		this(id, price, products, null);
	}

}