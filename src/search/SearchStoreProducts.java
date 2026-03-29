package search;

import java.time.LocalDate;
import java.util.*;
import product.*;

public class SearchStoreProducts{
    private boolean ascendant;
    private SearchType type;
    private List<StoreProduct> sp = new ArrayList<>();
    private List<Category> c = new ArrayList<>();
    private PriceFilter priceF;
    private PunctuationFilter punctuationF;

    public SearchStoreProducts(boolean asc, SearchType type, List<StoreProduct> sp, List<Category> c){
        this.ascendant = asc;
        this.type = type;
        this.sp = sp;
        this.c = c;
        this.priceF = null;
        this.punctuationF = null;
    }

    public void addPunctuationFilter(int min, int max){
        this.punctuationF = new PunctuationFilter(min, max);
    }
    public void addPriceFilter(double min, double max){
        this.priceFn= new PriceFilter(min, max);
    }
}