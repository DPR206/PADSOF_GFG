package search;

import java.util.*;
import product.*;

public class BrowseSecondHandProducts{

    private List<SecondHandProduct> products;

    public BrowseSecondHandProducts(Store s){
        this.products = s.getSecondHandProducts()
    }
    public List<SecondHandProduct> searchSecondHandProducts(){
        return this.products.sort(Comparator.comparing(SecondHandProduct::getName));
    }
}