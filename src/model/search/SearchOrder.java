package model.search;

import model.order.Order;
import model.store.Store;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * This class implements a search mechanism to find orders by their ID.
 *
 * <p>It retrieves all available orders from the {@link Store} and
 * performs a search to locate a specific order using its identifier.</p>
 *
 * @author Sofía C.L.
 * @version 1.4
 * @see SearchStoreProducts
 */
public class SearchOrder implements SearchID, Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */

    private Store s = Store.getInstance();
    private List<Order> orders;

    /**
     * Creates the class and initializes the list of orders
     * obtained from the store.
     */
    public SearchOrder(){
        this.orders = s.getOrders();
    }

    /**
     * Searches for an order by its ID.
     *
     * <p>Iterates through the list of orders and returns the one
     * whose ID matches the given parameter. If no order is found,
     * {@code null} is returned.</p>
     *
     * @param id the ID of the order to search for
     * @return the {@link Order} with the given ID, or {@code null} if it does not exist
     */
    public Order searchByID(int id){
        for(Order o: this.orders){
            if(o.getId() == id) return o;
        }
        return null;
    }
}