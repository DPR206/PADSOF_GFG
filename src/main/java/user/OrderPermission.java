package user;

import order.Order;
import order.OrderState;
import search.SearchStoreProducts;
import search.Searcher;

/**
 * It implements the permission that allows an employee to manage Orders
 * @author Ana O.R.
 * @version 1.3
 * @see Order
 */
public class OrderPermission {
    /** The user's searcher */
    private Searcher searching;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor for an order permission
     * @param asc whether the searcher will search in ascending or descending order
     */
    public OrderPermission(boolean asc) {
        this.searching = new Searcher(new SearchStoreProducts(asc));
    }

    /*--------------------------------------------------- MISC ----------------------------------------------------*/

    /**
     * It allows an employee to change an order's status
     * @param order  the desired order
     * @param status the order's status
     * @throws IllegalArgumentException order was null
     */
    public void manageOrder(Order order, OrderState status) throws IllegalArgumentException {
        if (order == null) {
            throw new IllegalArgumentException("Order is null");
        }

        order.setStatus(status);
    }

    /**
     * Searches for the order based on the id
     * @param id the id
     * @return the order
     */
    public Order searchOrderByID(int id) {
        return this.getSearcher().searchOrderByID(id);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Obtains the order permission's searcher
     * @return the order permission's searcher
     */
    public Searcher getSearcher() {
        return this.searching;
    }
}