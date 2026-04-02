package search;

import store.Store;
import product.*;
import user.*;
import order.*;
import exchange.*;

import java.util.*;

/**
 * Class name: Searcher
 * <p>
 * Description: It implements the searcher master class that contains all the searches in the package
 * @author Sofía C.L.
 * @version 1.3
 * @see SearchStoreProducts
  */
public class Searcher{
    private SearchType types[];
    private SearchStoreProducts searchStore;

    private Searcher<Pack> packSearcher = new Searcher<>(new SearchPack());
    private Searcher<Employee> employeeSearcher = new Searcher<>(new SearchEmployee());
    private Searcher<Order> orderSearcher = new Searcher<>(new SearchOrder());
    private Searcher<Exchange> exchangeSearcher = new Searcher<>(new SearchExchange());

    private BrowseSecondHandProducts secondHandProducts;

    /**
	 * Creates the master class searcher
	 *
	 * @param searchStore, the class to search products
     * @param types, array of the types of searching that will be done
	 */
    public Searcher(SearchStoreProducts searchStore, SearchType... types){
        this(searchStore);
        this.types = types;
    }

    /**
	 * Creates the master class searcher
	 *
	 * @param searchStore, the class to search products
	 */
    public Searcher(SearchStoreProducts searchStore){
        this.searchStore = searchStore;
    }

    public SearchStoreProducts getStoreSearcher(){
        return this.searchStore;
    }
    /**
	 * Sets the types
	 *
	 * @param st, the search types we want implemented in the class
	 */
    public void setTypes(SearchType... st){
        this.types = st;
    }

     /**
	 * Searches store products depending on the filters implemented in the SearchStoreProducts class
	 *
	 */
    public List<StoreProduct> searchStoreProducts(){
        return this.searchStore.searchStoreProducts();
    }

    /**
	 * Searches store products depending on the categories
     *
	 *@param c, the array of the categories of the products we want to search
	 */
    public List<StoreProduct> searchByCategory(Category... c){

        return this.searchStore.searchStoreProducts();
    }

    /**
	 * Searches the pack based on the id
	 *
     * @param id, searches the pack by the id
	 */
    public Pack searchPackByID(int id){
        if(this.linearSearch(SearchType.S_PACK)){
            return this.packSearcher.searchByID();
        }
        return null;
    }

    /**
	 * Searches the exchange based on the id
	 *
     * @param id, searches the exchange by the id
	 */
    public Exchange searchExchangeByID(int id){
        if(this.linearSearch(SearchType.S_EXCHANGE)){
            return this.exchangeSearcher.searchByID();
        }
        return null;
    }

    /**
	 * Searches the order based on the id
	 *
     * @param id, searches the order by the id
	 */
    public Order searchOrderByID(int id){
        if(this.linearSearch(SearchType.S_ORDER)){
            return this.orderSearcher.searchByID();
        }
        return null;
    }

    /**
	 * Searches the employee based on the id
	 *
     * @param id, searches the employee by the id
	 */
    public Order searchEmployeeByID(int id){
        if(this.linearSearch(SearchType.S_EMPLOYEE)){
            return this.employeeSearcher.searchByID();
        }
        return null;
    }

    /**
	 * Browses all the second hand products ordered in alphabetical order
	 *
	 */
    public List<SecondHandProduct> browseSecondHandProduct(){
        if(this.linearSearch(SearchType.S_SECOND_HAND)){
            return this.BrowseSecondHandProducts.searchSecondHandProducts();
        }
        return null;
    }

    /**
	 * Linear search implemented to search for the searchtype on the array of searchTypes
     *
	 *@param t, the searchType we want to check if it's there or not
	 */
    private boolean linearSearch(SearchType t){
        for(SearchType type: this.types){
            if(t.getType().equals(type.getType())) return true;
        }
        return false;
    }
}