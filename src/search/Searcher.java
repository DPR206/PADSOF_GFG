package search;

import store.Store;
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
    
    private Searcher(SearchStoreProducts searchStore, SearchType... types){
        this.searchStore = searchStore;
        this.types = types;
    }

    private List<StoreProduct> searchStoreProducts(){
        return this.searchStore.searchStoreProducts();
    }
    private List<StoreProduct> searchByCategory(Category... c){
        
        return this.searchStore.searchStoreProducts();
    }
    private Pack searchPackByID(int id){
        if(this.linearSearch(SearchType.S_PACK)){
            return this.packSearcher.searchByID();
        }
        return null;
    }
    private Exchange searchExchangeByID(int id){
        if(this.linearSearch(SearchType.S_EXCHANGE)){
            return this.exchangeSearcher.searchByID();
        }
        return null;
    }
    private Order searchOrderByID(int id){
        if(this.linearSearch(SearchType.S_ORDER)){
            return this.orderSearcher.searchByID();
        }
        return null;
    }
    private Order searchEmployeeByID(int id){
        if(this.linearSearch(SearchType.S_EMPLOYEE)){
            return this.employeeSearcher.searchByID();
        }
        return null;
    }

    private boolean linearSearch(SearchType t){
        for(SearchType type: this.types){
            if(t.getType().equals(type.getType())) return true
        }
        return false;
    }
}