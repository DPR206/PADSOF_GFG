package search;

import store.Store;
import java.util.List;

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
    private Searcher<Exchange> orderSearcher = new Searcher<>(new SearchExchange());
    
}