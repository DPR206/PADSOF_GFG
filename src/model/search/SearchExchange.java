package model.search;

import model.exchange.Exchange;
import model.store.Store;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * This class implements a search mechanism to find exchanges by their ID.
 *
 * <p>It can either retrieve the list of exchanges from the {@link Store}
 * singleton or receive a custom list to perform the search on.</p>
 *
 * @author Sofía C.L.
 * @version 1.4
 * @see SearchStoreProducts
 */
public class SearchExchange implements SearchID, Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */
    private Store s = Store.getInstance();
    private List<Exchange> exchanges;

    /**
     * Creates the class and initializes the list of exchanges
     * obtained from the store.
     */
    public SearchExchange(){
        this.exchanges = s.getExchanges();
    }

    /**
     * Creates the class with a custom list of exchanges.
     *
     * @param ex the list of exchanges to search
     */
    public SearchExchange(List<Exchange> ex){
        this.exchanges = ex;
    }

    /**
     * Searches for an exchange by its ID.
     *
     * <p>Iterates through the list of exchanges and returns the one
     * that matches the given ID. If no exchange is found,
     * {@code null} is returned.</p>
     *
     * @param id the ID of the exchange to search for
     * @return the {@link Exchange} with the given ID, or {@code null} if it does not exist
     */
    public Exchange searchByID(int id){
        for(Exchange e: this.exchanges){
            if(e.getId() == id) return e;
        }
        return null;
    }
}