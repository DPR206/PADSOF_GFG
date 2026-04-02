package search;

import order.Order;
import store.Store;

import java.util.List;

/**
    * Class name: SearchOrder
    * <p>
    * Description: It implements the order filter through ID
    * @author Sofía C.L.
    * @version 1.3
    * @see SearchStoreProducts
    */
public class SearchOrder implements SearchID{
    
    private Store s = Store.getInstance();
    private List<Order> orders;

    /**
	 * Creates the class
	 *
	 * @param s, sends the store to get all the orders available
	 */
    public SearchOrder(){
        this.orders = s.getOrders();
    }

    /**
	 * Searches the order that has the id sent as a parameter
	 *
	 * @param id, id of the order the user wants to find
	 */
    public Order searchByID(int id){
        for(Order o: this.orders){
            if(o.getId() == id) return o;
        }
        return null;
    }
}