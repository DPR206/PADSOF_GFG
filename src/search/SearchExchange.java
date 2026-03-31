package search;

import exchange.Exchange;
import store.Store;

import java.util.List;

/**
 * Class name: SearchExchange
 * <p>
 * Description: It implements the class to search for an exchange
 * @author Sofía C.L.
 * @version 1.3
 * @see SearchStoreProducts
  */
public class SearchExchange implements SearchID{
    private List<Exchange> exchanges;

     /**
	 * Creates the class
	 *
	 * @param s, the store to create the list of exchanges
	 */
    public SearchExchange(Store s){
        this.exchanges = s.getExchanges();
    }

     /**
	 * Creates the class
	 *
	 * @param ex, sends a list of exchanges to search them
	 */
    public SearchExchange(List<Exchange> ex){
        this.exchange = ex;
    }

     /**
	 * Searches the exchange based on a sent id
	 *
	 * @param id, the id of the exchange that the user wants to search
	 */
    public Exchange searchByID(int id){
        for(Exchange e: this.exchanges){
            if(e.getId() == id) return e;
        }
        return null;
    }
}