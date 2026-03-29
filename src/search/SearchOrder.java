package search;

import order.*;

public class SearchOrder extends SearchID{
    private List<Order> orders;

    public SearchOrder(List<Order> orders){
        this.orders = orders;
    }
    @Override
    public Order searchByID(int id){
        for(Order o: this.orders){
            if(o.getId() == id) return o;
        }
        return null;
    }
}