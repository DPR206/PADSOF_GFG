package search;

import java.util.*;
import product.*;
import store.Store;

/**
 * Class name: BrowseSecondHandProducts
 * <p>
 * Description: It implements the second hand product search
 * @author Sofía C.L.
 * @version 1.3
 * @see SearchStoreProducts
  */
public class BrowseSecondHandProducts{
	
	Store s = Store.getInstance();
    private List<SecondHandProduct> products;

    /**
	 * Creates the class
	 *
	 * @param s, sends the store to get all the second hand products available
	 */
    public BrowseSecondHandProducts(Store s){
        this.products = new ArrayList<>(s.getSecondHandProducts().values());
    }

     /**
	 * Returns all the second hand products ordered in alphabetical order
	 *
	 */
    public List<SecondHandProduct> searchSecondHandProducts(){
        this.products.sort(Comparator.comparing(SecondHandProduct::getName));
        return this.products;
    }
}