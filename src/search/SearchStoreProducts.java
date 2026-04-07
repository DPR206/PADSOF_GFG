package search;

import product.Category;
import product.StoreProduct;
import store.Store;

import java.util.*;

/**
    * It implements all the products filtering
    * @author Sofía C.L.
    * @version 1.3
    * @see Searcher
    */
public class SearchStoreProducts{
    private boolean ascendant;
    private PriceFilter priceF;
    private PunctuationFilter punctuationF;
    private Store s;


    /**
	 * Creates the class and initiates the filters as null in the beginning until the user changes them
	 *
	 * @param asc, determines if the searches are ascendant or descendant
     *  type, determines the type of search we are doing
	 */
    public SearchStoreProducts(boolean asc){
        this.ascendant = asc;
        this.priceF = null;
        this.punctuationF = null;
        this.s = Store.getInstance();
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

        if(this.ascendant){
            if(this.priceF != null && this.punctuationF != null){
                pCs.sort(Comparator.comparing(StoreProduct::getAveragePunctuation).thenComparing(StoreProduct::getPrice));
            }
            else if(this.priceF != null /*&& this.punctuationF == null*/){
                pCs.sort(Comparator.comparing(StoreProduct::getPrice));
            }
            else if(/*this.priceF == null &&*/ this.punctuationF != null){
                pCs.sort(Comparator.comparing(StoreProduct::getAveragePunctuation));
            }
        }
        else{
             if(this.priceF != null && this.punctuationF != null){
                pCs.sort(Comparator.comparingDouble(StoreProduct::getAveragePunctuation).reversed().thenComparing(Comparator.comparingDouble(StoreProduct::getPrice).reversed()));
            }
            else if(this.priceF != null /*&& this.punctuationF == null*/){
                pCs.sort(Comparator.comparingDouble(StoreProduct::getPrice).reversed());
            }
            else if(/*this.priceF == null &&*/ this.punctuationF != null){
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
        List<StoreProduct> priced;
        List<StoreProduct> punctuation;

        if(this.punctuationF != null && this.priceF != null){
            priced = this.filterByPrice();
            punctuation = this.filterByPrice();
            if (priced != null && punctuation != null) { // Me saltaba warning po rno hacer null check
                priced.retainAll(punctuation);
                return priced;
            }
        }
        else if(this.punctuationF == null && this.priceF != null){
            punctuation = this.filterByPrice();
            return punctuation;
        }
        else if(/*this.priceF == null &&*/ this.punctuationF != null){
            priced = this.filterByPrice();
            return priced;
        }
        /*caso donde no hay ningún filtro */
        return s.getStoreProductList();
    }

     /**
	 * Searches the products depending on the parameters on the price filter
	 *
	 */
    private List<StoreProduct> filterByPrice(){

        List<StoreProduct> aux = new ArrayList<>();
        List<StoreProduct> fromStore = (List<StoreProduct>) this.s.getStoreProducts().values();

        if(this.priceF != null){
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

        if(this.punctuationF != null){
            for(StoreProduct p: fromStore){
                if(p.getAveragePunctuation() >= this.punctuationF.getMin() && p.getAveragePunctuation() <= this.punctuationF.getMax()){
                    aux.add(p);
                }
            }
        }
        return aux;
    }

    /**
	 * Searches the products depending on the categories wanted
	 *
     * @param c, categories we want to search
	 */
    private List<StoreProduct> filterByCategory(Category... c){ //para cada producto, buscar la categoría
        List<StoreProduct> aux = new ArrayList<>();
        List<StoreProduct> product = (List<StoreProduct>) this.s.getStoreProducts();
        Category[] caux;

        for(Category cat: c) {
        	/** Para cada categoría, busco productos que tengan ESA categoría*/
        	for(StoreProduct sp: product) {
        		/*Para cada producto, pillamos su categoría*/
        		caux = sp.getCategories();
        		/**Para cada categoría del producto, miramos si alguna coincide con cat*/
        		for(Category cc: caux) {
        			if(cc == cat) {
        				aux.add(sp);
        				break;
        			}
        		}
        	}
        }

        return new ArrayList<>(new LinkedHashSet<>(aux));
    }

    public void setAsc(boolean bool) {
    	this.ascendant = bool;
    }
}