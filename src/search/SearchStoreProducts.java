package search;

import java.time.LocalDate;
import java.util.*;
import product.*;
import store.Store;
import product.StoreProduct;

public class SearchStoreProducts{
    private boolean ascendant;
    private SearchType type;
    private PriceFilter priceF;
    private PunctuationFilter punctuationF;
    private Store s;

    public SearchStoreProducts(boolean asc, SearchType type){
        this.ascendant = asc;
        this.type = type;
        this.sp = sp;
        this.c = c;
        this.priceF = null;
        this.punctuationF = null;
        this.s = this.s.getInstance();
    }

    public void addPunctuationFilter(int min, int max){
        this.punctuationF = new PunctuationFilter(min, max);
    }
    public void addPriceFilter(double min, double max){
        this.priceF= new PriceFilter(min, max);
    }
    public List<StoreProduct> searchStoreProducts(Store s){
        return (List<StoreProduct>) this.s.getStoreProducts().values();
    }

    private List<StoreProduct> filterByPrice(){

        List<StoreProduct> aux = new ArrayList<>();
        List<StoreProduct> fromStore = (List<StoreProduct>) this.s.getStoreProducts().values();

        if(this.priceF){
            for(StoreProduct p: fromStore){
                if(p.getPrice() >= this.priceF.getMin() && p.getPrice()<= this.priceF.getMax()) aux.add(p);
            }
            return aux;
        }
        return null;
    }

    private List<StoreProduct> filterByPunctuation(){
        List<StoreProduct> aux = new ArrayList<>();
        List<StoreProduct> fromStore = (List<StoreProduct>) this.s.getStoreProducts().values();

        if(this.punctuationF){
            for(StoreProduct p: fromStore){
                if(p.getAveragePunctuation() >= this.punctuationF.getMin() && p.getAveragePunctuation() <= this.punctuationF.getMax()){
                    aux.add(p);
                }
            }
        }
    }

    private List<StoreProduct> filterByCategory(){ //para cada producto, buscar la categoría

    }
}