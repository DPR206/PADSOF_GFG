/**
 *
 */
package product;

import discount.*;
import store.Store;

import java.time.LocalDate;
import java.util.*;

/**
 * It implements the packs
 * @author Duna P.R.
 * @version 1.1
 * @see store.Store
 */
public class Pack {
	static public int totalId = 0;

	/** */
	private int id;
	private double price;
	private ArrayList<StoreProduct> products;
	private LocalDate dateAddCart;
	/** The pack's discount, if it has one */
	private Discount discount;

	/*---------------------------------------------------Constructors---------------------------------------------------------------*/

	/**
	 * Creates a new pack
	 *
	 * @param id, the pack's id
	 * @param price, the pack's price
	 * @param products, the products the pack contains
	 * @param date the date it was added to the cart
	 */
	public Pack(int id, double price, ArrayList<StoreProduct> products, LocalDate date) {
		this.id = id;
		this.price = price;
		this.products = products;
		this.dateAddCart = date;

		Store.getInstance().getPacks().add(this);
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
	 * @param date the date it was added to the cart
	 */
	public Pack(double price, ArrayList<StoreProduct> products, LocalDate date) {
		this(totalId, price, products, date);
		totalId++;
	}

	/**
	 * Creates a new pack with no products
	 *
	 * @param price, price of the pack
	 * @param date, the date it was added to the cart
	 */
	public Pack(double price, LocalDate date) {
		this(totalId, price, new ArrayList<StoreProduct>(), date);
		totalId++;
	}

	/**
	 * Creates a new pack with no products
	 *
	 * @param id, the pack's id
	 * @param price, price of the pack
	 * @param date, the products the pack contains
	 */
	public Pack(int id, double price, LocalDate date) {
		this(id, price, new ArrayList<StoreProduct>(), date);
	}

	/*----------------------------------------------------Getters and Setters---------------------------------------------------------------*/

	/**
	 * It gets the pack's discount
	 * @return the pack's discount
	 */
	public Discount getDiscount() {
		return this.discount;
	}

	/**
	 * It allows an employee to add discounts to packs (Discounts is in charge of making sure they don't overlap)
	 * @param newDiscount the new discount to be applied
	 * @throws NullPointerException discount was null
	 */
	public void setDiscount(Discount newDiscount) throws NullPointerException {
		if (newDiscount == null) {
			throw new NullPointerException("Discount cannot be null");
		}

		this.discount = newDiscount;
	}

	/**
	 * Obtains the pack's id
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * It returns the pack's basic info
     * @return the pack's basic info
	 */
	public String getPrintInfo(){
		return "DUE";// DUE
	}

/*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Applies the pack's discounts (except quantity) and obtains the packs price
     * @return the pack's price
     */
    public double getPrice() {
		if (this.discount == null) {
			return this.price; // TEST_FIX
		}

        switch (this.discount.getType()) {
            case FIXED_PERCENTAGE:
                FixedPercentageDiscount fixedPerDisc = (FixedPercentageDiscount) this.discount;
                return this.price - (this.price * fixedPerDisc.getPercentage());
            case GIFT:
                GiftDiscount giftDisc = (GiftDiscount) this.discount;
                if (this.price > giftDisc.getSpendingThreshold()) {
                    this.addProduct(giftDisc.getGift());
                }
                return this.price;
            case VOLUME:
                VolumeDiscount volumeDisc = (VolumeDiscount) this.discount;
                if (this.price > volumeDisc.getSpendingThreshold()) {
                    return this.price - volumeDisc.getDeduction();
                }
                return this.price;
		default:
			break;
        }
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
		// Prueba: return (ArrayList<StoreProduct>) Collections.unmodifiableList(products);
		return this.products; // TEST_FIX
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

	/**
	 * It returns the pack's products in a save-file-friendly manner
	 * @return a string containing the pack's products
	 */
	public String getPrintProducts() {
		StringBuilder sb = new StringBuilder();

		for (StoreProduct product : products) {
			sb.append(product.getId()).append(",");
		}

		return sb.toString();
	}

	/**
	 * Written information of a pack
	 * @return String, information of a pack
	 */
	@Override
	public String toString() {
		/*ID;PRICE;PRODUCT_IDS;DATE_ADD_CART*/
		return this.id + ";" + this.price + ";" + this.getPrintProducts() + ";" + this.dateAddCart;
	}

	public double getOriginalPrice() {
		return this.price;
	}
}