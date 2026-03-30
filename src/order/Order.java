
package order;

import product.Pack;
import product.StoreProduct;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class name: Order
 * <p>
 * Description: It implements the order paid by the client
 * @author Sofía C.L.
 * @version 1.0
 */
public class Order {
    static public int totalId = 0;

    private int id;
    private double price;
    private LocalDateTime pickedUpDate;

    private List<StoreProduct> sp = new ArrayList<>();
    private List<Pack> p = new ArrayList<>();
    private OrderState state;

    /**
     * Creates an order for when it hasn't been picked up
     * @param id,    the unique number of the order
     * @param price, total price paid of the order
     */
    public Order(int id, double price, OrderState state, List<StoreProduct> sp, List<Pack> p) {
        this.id = id;
        this.price = price;
        this.pickedUpDate = null;
        this.state = state;
        this.sp = sp;
        this.p = p;
    }

    /**
     * Creates an order for when it hasn't been picked up
     * @param price, total price paid of the order
	 */
    public Order(double price, OrderState state, List<StoreProduct> sp, List<Pack> p){
        this(Order.totalId, price, state, sp, p);
        Order.totalId++;
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the sp
	 */
	public List<StoreProduct> getSp() {
		return sp;
	}

	/**
	 * @param sp the sp to set
	 */
	public void setSp(List<StoreProduct> sp) {
		this.sp = sp;
	}

	/**
	 * @return the p
	 */
	public List<Pack> getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(List<Pack> p) {
		this.p = p;
	}

	/**
	 * @return the state
	 */
	public OrderState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(OrderState state) {
		this.state = state;
	}

	/**
	 * @return the pickedUpDate
	 */
	public LocalDateTime getPickedUpDate() {
		return pickedUpDate;
	}
    
    
}