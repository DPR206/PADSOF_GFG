package search;

import order.Order;
import store.Store;

import java.util.List;

public class SearchOrder extends SearchID{
    private List<Order> orders;

    public SearchOrder(Store s){
        this.orders = s.getOrders();
    }
    @Override
    public Order searchByID(int id){
        for(Order o: this.orders){
            if(o.getId() == id) return o;
        }
        return null;
    }
}