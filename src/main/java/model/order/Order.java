
package model.order;

import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.RegisteredClient;

import java.time.LocalDateTime;
import java.util.List;

/**
 * It implements the order paid by the client
 * @author Sofía C.L.
 * @version 1.0
 */
public class Order {
	/** ID counter for the orders */
    static public int totalId = 0;

    private int id;
    private double price;
    private LocalDateTime pickedUpDate;
	private RegisteredClient owner;

    private List<StoreProduct> sp;
    private List<Pack> p;
    private OrderState state;

    /**
     * Creates an order for when it hasn't been picked up
     * @param id  the unique number of the order
     * @param price total price paid of the order
     * @param state the order's state
     * @param sp the store products in the order
     * @param p the packs in the order
     * @param owner the owner of the order
     */
    public Order(int id, double price, OrderState state, List<StoreProduct> sp, List<Pack> p, RegisteredClient owner) {
        this.id = id;
        this.price = price;
        this.pickedUpDate = null;
        this.state = state;
        this.sp = sp;
        this.p = p;
		this.owner = owner;

		Store.getInstance().addOrder(this);
    }

    /**
     * Creates an order for when it hasn't been picked up
     * @param price total price paid of the order
     * @param state the order's state
     * @param sp the store products in the order
     * @param p the packs in the order
     * @param owner the owner of the order
     */
    public Order(double price, OrderState state, List<StoreProduct> sp, List<Pack> p, RegisteredClient owner){
        this(Order.totalId, price, state, sp, p, owner);
        Order.totalId++;
    }

	/**
	 * It prints the order's info when seen individually
	 */
	public void bigPrintInfo() {
		System.out.println("State: " + state);
	}

	/**
     * Changes the state of the Order
     * @param state, the state to which we have to change it
     */
    public void setStatus(OrderState state) {
        this.state = state;
    }

    /**
     * Sets the date of the order once it has been picked up
     * @param pickedUp, the date when the Order has been picked up
     */
    public void setPickedUpDate(LocalDateTime pickedUp) {
        this.pickedUpDate = pickedUp;
    }

	/**
	 * Obtains the id of the order
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the order
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Obtains the price of the order
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price of the order
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Obtains the store products in the order
	 * @return the list of store products
	 */
	public List<StoreProduct> getSp() {
		return sp;
	}

	/**
	 * Sets the store products in the order
	 * @param sp the list of store products to set
	 */
	public void setSp(List<StoreProduct> sp) {
		this.sp = sp;
	}

	/**
	 * Obtains the packs in the order
	 * @return the list of packs
	 */
	public List<Pack> getP() {
		return p;
	}

	/**
	 * Obtains the owner of the order
	 * @return the registered client that owns the order
	 */
	public RegisteredClient getOwner(){
		return this.owner;
	}

	/**
	 * Sets the packs in the order
	 * @param p the list of packs to set
	 */
	public void setP(List<Pack> p) {
		this.p = p;
	}

	/**
	 * Obtains the state of the order
	 * @return the state the order's state
	 */
	public OrderState getState() {
		return state;
	}

	/**
	 * Sets the order's state
	 * @param state the state to set
	 */
	public void setState(OrderState state) {
		this.state = state;
	}

	/**
	 * Obtains the date the order was picked up
	 * @return the pickedUpDate
	 */
	public LocalDateTime getPickedUpDate() {
		return pickedUpDate;
	}


}