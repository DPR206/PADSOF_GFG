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

    public List<StoreProduct> searchStoreProducts(Category... cs){
        List<StoreProduct> pCs = this.filterByCategory(cs);
        List<StoreProduct> filtered = this.searchStoreProducts();

        if(filtered == this.s.getStoreProducts()){
            return pCs;
        }
        
        pCs.retainAll(filtered);
        return pCs;
    }

    public List<StoreProduct> searchStoreProducts(){
        List<StoreProduct> priced = null;
        List<StoreProduct> punctuation = null;

        if(this.punctuationF && this.priceF){
            priced = this.filterByPrice();
            punctuation = this.filterByPrice();
            priced.retainAll(punctuation);
            return priced;
        }
        else if(this.punctuation && !this.priceF){
            punctuation = this.filterByPrice();
            return punctuation;
        }
        else if(this.priceF && !this.punctuation){
            priced = this.filterByPrice();
            return priced;
        }
        /*caso donde no hay ningún filtro */
        return this.s.getStoreProducts();
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

    private List<StoreProduct> filterByCategory(Category... c){ //para cada producto, buscar la categoría
        List<StoreProduct> aux = new ArrayList<>();
        List<StoreProduct> product = this.s.getStoreProducts();
        Categories productCategories[] = null
        
        for(Category category: c){
            for(StoreProduct sp: product){
                productCategories = sp.getCategories();
                for(Category productCat: productCategories){
                    if(c == productCat) aux.add(productCat);
                }
            }
        }

        return aux;
    }
}