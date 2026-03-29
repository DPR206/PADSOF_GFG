package search;

import product.*;
import exchange.*;

public class SearchExchange{
    private List<Exchange> exchanges;

    public SearchExchange(List<Exchange> ex){
        this.exchange = ex;
    }
     @Override
    public Exchange searchByID(int id){
        for(Exchange e: exchanges){
            if(e.getId() == id) return e;
        }
        return null;
    }
}