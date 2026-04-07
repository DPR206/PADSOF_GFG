package search;

import product.SecondHandProduct;
import store.Store;

import java.util.*;

/**
 * It implements the second hand product search
 * @author Sofía C.L.
 * @version 1.3
 * @see SearchStoreProducts
  */
public class BrowseSecondHandProducts{

	final Store s = Store.getInstance();
    private List<SecondHandProduct> products;

    /**
	 * Creates the class and sends the store to get all the second hand products available
	 */
    public BrowseSecondHandProducts(){

        this.products = new ArrayList<>(s.getSecondHandProducts().values());

        this.products = new ArrayList<>(s.getSecondHandProducts().values());
    }

     /**
	 * Returns all the second hand products ordered in alphabetical order
	 * @return a list with the second-hand products found
	 */
    public List<SecondHandProduct> searchSecondHandProducts(){
        this.products.sort(Comparator.comparing(SecondHandProduct::getName));
        return this.products;
    }
}