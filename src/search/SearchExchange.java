package search;

import exchange.Exchange;
import store.Store;

import java.util.List;

public class SearchExchange extends SearchID{
    private List<Exchange> exchanges;

    public SearchExchange(Store s){
        this.exchanges = s.getExchanges();
    }

    public SearchExchange(List<Exchange> ex){
        this.exchange = ex;
    }
    @Override
    public Exchange searchByID(int id){
        for(Exchange e: this.exchanges){
            if(e.getId() == id) return e;
        }
        return null;
    }
}