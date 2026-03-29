package search;

import product.*;
import exchange.*;

public class SearchExchange extends SearchID{
    private List<Exchange> exchanges;

    

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