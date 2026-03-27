package order;

import user.RegisteredClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Class name: OrderHistory
 * <p>
 * Description: It implements the order history of the registered client
 * @author Sofía C.L.
 * @version 1.0
 * @see Order
 */
public class OrderHistory {
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

}