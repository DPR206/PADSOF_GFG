package search;

import java.time.LocalDate;
import java.util.*;
import product.*;
import store.Store;
import product.StoreProduct;
/**
    * Class name: SearchStoreProducts
    * <p>
    * Description: It implements all the products filtering
    * @author Sofía C.L.
    * @version 1.3
    * @see Search
    */
public class SearchStoreProducts{
    private boolean ascendant;
    private PriceFilter priceF;
    private PunctuationFilter punctuationF;
    private Store s;

    /**
	 * Creates the class and initiates the filters as null in the beggining until the user changes them
	 *
	 * @param asc, determines if the searches are ascendant or descendant
     * @param type, determines the type of search we are doing
	 */
    public SearchStoreProducts(boolean asc){
        this.ascendant = asc;
        this.type = type;
        this.sp = sp;
        this.c = c;
        this.priceF = null;
        this.punctuationF = null;
        this.s = this.s.getInstance();
    }

    /**
	 * Creates and initializes the punctuation filter
	 *
	 * @param min, minimum punctuation wanted on the product
     * @param max, maximum punctuation wanted on the product
	 */
    public void addPunctuationFilter(int min, int max){
        this.punctuationF = new PunctuationFilter(min, max);
    }

    /**
	 * Creates and initializes the price filter
	 *
	 * @param min, minimum price wanted on the product
     * @param max, maximum price wanted on the product
	 */
    public void addPriceFilter(double min, double max){
        this.priceF= new PriceFilter(min, max);
    }

    /**
	 * Searches the products based on the filters and the categories
	 *
	 * @param cs, categories we want
	 */
    public List<StoreProduct> searchStoreProducts(Category... cs){
        List<StoreProduct> pCs = this.filterByCategory(cs);
        List<StoreProduct> filtered = this.searchStoreProducts();

        if(filtered == this.s.getStoreProducts()){
            return pCs;
        }
        
        pCs.retainAll(filtered);

        if(this.ascendant == true){
            if(this.priceF && this.punctuationF){
                pCs.sort(Comparator.comparing(StoreProduct::getAveragePunctuation).thenComparing(StoreProduct::getPrice));
            }
            else if(this.priceF && !this.punctuationF){
                pCs.sort(Comparator.comparing(StoreProduct::getPrice));
            }
            else if(!this.priceF && this.punctuation){
                pCs.sort(Comparator.comparing(StoreProduct::getAveragePunctuation));
            }
        }
        else{
             if(this.priceF && this.punctuationF){
                pCs.sort(Comparator.comparingDouble(StoreProduct::getAveragePunctuation).reversed().thenComparing(Comparator.comparingDouble(StoreProduct::getPrice).reversed()));
            }
            else if(this.priceF && !this.punctuationF){
                pCs.sort(Comparator.comparingDouble(StoreProduct::getPrice).reversed());
            }
            else if(!this.priceF && this.punctuation){
                pCs.sort(Comparator.comparingDouble(StoreProduct::getAveragePunctuation).reversed());
            }
        }
        return pCs;
    }

    /**
	 * Searches the products depending on the filter
	 *
	 */
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

     /**
	 * Searches the products depending on the parameters on the price filter
	 *
	 */
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
    /**
	 * Searches the products depending on the parameters on the punctuation filter
	 *
	 */
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

    /**
	 * Searches the products depending on the categories wanted
	 *
     * @param c, categories we want to search
	 */
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