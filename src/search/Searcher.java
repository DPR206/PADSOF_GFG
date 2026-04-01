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

    private BrowseSecondHandProducts secondHandProducts;
    
    public Searcher(SearchStoreProducts searchStore, SearchType... types){
        this.searchStore = searchStore;
        this.types = types;
    }

    public List<StoreProduct> searchStoreProducts(){
        return this.searchStore.searchStoreProducts();
    }
    public List<StoreProduct> searchByCategory(Category... c){
        
        return this.searchStore.searchStoreProducts();
    }
    public Pack searchPackByID(int id){
        if(this.linearSearch(SearchType.S_PACK)){
            return this.packSearcher.searchByID();
        }
        return null;
    }
    public Exchange searchExchangeByID(int id){
        if(this.linearSearch(SearchType.S_EXCHANGE)){
            return this.exchangeSearcher.searchByID();
        }
        return null;
    }
    public Order searchOrderByID(int id){
        if(this.linearSearch(SearchType.S_ORDER)){
            return this.orderSearcher.searchByID();
        }
        return null;
    }
    public Order searchEmployeeByID(int id){
        if(this.linearSearch(SearchType.S_EMPLOYEE)){
            return this.employeeSearcher.searchByID();
        }
        return null;
    }
    public List<SecondHandProduct> browseSecondHandProduct(){
        if(this.linearSearch(SearchType.S_SECOND_HAND)){
            return this.BrowseSecondHandProducts.searchSecondHandProducts();
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