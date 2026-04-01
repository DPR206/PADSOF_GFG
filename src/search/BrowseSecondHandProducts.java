package search;

import java.util.*;
import product.*;

package search;
/**
 * Class name: BrowseSecondHandProducts
 * <p>
 * Description: It implements the second hand product search
 * @author Sofía C.L.
 * @version 1.3
 * @see SearchStoreProducts
  */
public class BrowseSecondHandProducts{

    private List<SecondHandProduct> products;

    /**
	 * Creates the class
	 *
	 * @param s, sends the store to get all the second hand products available
	 */
    public BrowseSecondHandProducts(Store s){
        this.products = s.getSecondHandProducts()
    }

     /**
	 * Returns all the second hand products ordered in alphabetical order
	 *
	 */
    public List<SecondHandProduct> searchSecondHandProducts(){
        return this.products.sort(Comparator.comparing(SecondHandProduct::getName));
    }
}