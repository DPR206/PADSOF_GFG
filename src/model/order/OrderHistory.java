package model.order;

import model.user.RegisteredClient;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * It implements the order history of the registered client
 * @author Sofía C.L.
 * @version 1.0
 * @see Order
 */
public class OrderHistory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */
    private List<Order> orders = new ArrayList<>();
    private RegisteredClient owner;

    /**
     * Creates an order history
     * @param owner, the user associated to the history
     */
    public OrderHistory(RegisteredClient owner) {
        this.owner = owner;
    }

    /**
     * Adds a completed order to the history
     * @param o, the owner to add to the history
     */
    public void addOrder(Order o) {
        this.orders.add(o);
    }

    /**
     * It gets the order history's orders
     * @return the order history's orders
     */
    public List<Order> getOrders() {
        return this.orders;
    }

	/**
	 * Obtains the owner of the order history
	 *
	 * @return the owner of the order history
	 */
	public RegisteredClient getOwner() {
		return owner;
	}


}