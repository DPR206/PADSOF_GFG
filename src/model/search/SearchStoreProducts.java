package model.search;

import model.product.Category;
import model.product.StoreProduct;
import model.store.Store;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * This class implements product filtering based on price, rating, and category.
 *
 * <p>It allows adding {@link PriceFilter} and {@link PunctuationFilter}, and
 * can perform searches on store products using these filters along with
 * optional category constraints. Results can be sorted in ascending or descending order.</p>
 *
 * <p>Typically used within {@link Searcher} for store product searches.</p>
 *
 * @author Sofía C.L.
 * @version 1.4
 * @see Searcher
 */
public class SearchStoreProducts implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */
    private boolean ascendant;
    private List<PriceFilter> priceF = new ArrayList<>();;
    private List<PunctuationFilter> punctuationF = new ArrayList<>();
    private CategoryFilter categoryF = null;
    private Store s;

    /**
     * Creates the searcher with no initial filters.
     *
     * @param asc determines if the searches are in ascending order ({@code true}) or descending order ({@code false})
     */
    public SearchStoreProducts(boolean asc){
        this.ascendant = asc;
        this.s = Store.getInstance();
        List<Category> c = new ArrayList<>();
        this.categoryF = new CategoryFilter(c);
    }

    /**
     * Adds and initializes a punctuation filter.
     *
     * @param min minimum punctuation (rating) wanted on the product
     * @param max maximum punctuation (rating) wanted on the product
     */
    public void addPunctuationFilter(int min, int max){
        this.punctuationF.add(new PunctuationFilter(min, max));
    }

    /**
     * Adds and initializes a price filter.
     *
     * @param min minimum price wanted on the product
     * @param max maximum price wanted on the product
     */
    public void addPriceFilter(double min, double max){
        this.priceF.add(new PriceFilter(min, max));
    }

    public void addCategoryFilter(CategoryFilter c) {
    	this.categoryF = c;
    }

    /**
     * Searches products filtered by price, punctuation, and optionally by categories.
     *
     * <p>Results are sorted according to the {@code ascendant} flag and active filters.</p>
     *
     * @param cs the categories to filter by (optional)
     * @return a list of {@link StoreProduct} matching the filters and categories
     */
    public List<StoreProduct> searchStoreProducts(){
       List<StoreProduct> pCs;

       List<StoreProduct> catFiltered = this.filterByCategory();

       if (!this.categoryF.getCategories().isEmpty()) {
           pCs = catFiltered;
       } else {
           pCs = new ArrayList<>(this.s.getStoreProducts().values());
       }

       if(!this.priceF.isEmpty()) pCs.retainAll(this.filterByPrice());
       if(!this.punctuationF.isEmpty()) pCs.retainAll(this.filterByPunctuation());
        
       if(this.ascendant == true && (!this.priceF.isEmpty() && !this.punctuationF.isEmpty())) {
        	pCs.sort(Comparator.comparing(StoreProduct::getPrice).thenComparing(StoreProduct::getAveragePunctuation));
        }
        
       else if(this.ascendant == true && (!this.priceF.isEmpty())) {
    	   pCs.sort(Comparator.comparing(StoreProduct::getPrice));
       }
       
       else if(this.ascendant == true && (!this.punctuationF.isEmpty())) {
    	   pCs.sort(Comparator.comparing(StoreProduct::getAveragePunctuation));
       }
       
       else if(this.ascendant == false && (!this.priceF.isEmpty() && !this.punctuationF.isEmpty())) {
       	pCs.sort(Comparator.comparing(StoreProduct::getPrice).thenComparing(StoreProduct::getAveragePunctuation).reversed());
       }
       
      else if(this.ascendant == false && (!this.priceF.isEmpty())) {
   	   pCs.sort(Comparator.comparing(StoreProduct::getPrice).reversed());
      }
      
      else if(this.ascendant == false && (!this.punctuationF.isEmpty())) {
   	   pCs.sort(Comparator.comparing(StoreProduct::getAveragePunctuation).reversed());
      }
       return pCs;
    }
  

    /**
     * Filters products based on the price filter.
     *
     * @return a list of {@link StoreProduct} matching the price range, or {@code null} if no price filter is set
     */
    private List<StoreProduct> filterByPrice(){
        List<StoreProduct> aux = new ArrayList<>();
        List<StoreProduct> fromStore = new ArrayList<>(this.s.getStoreProducts().values());

        if(this.priceF != null){
            for(StoreProduct p: fromStore){
            	for(PriceFilter priceFf: this.priceF)
                	if(p.getPrice() >= priceFf.getMin() && p.getPrice() <= priceFf.getMax()) aux.add(p);
            }
            return aux;
        }
        return null;
    }

    /**
     * Filters products based on the punctuation (rating) filter.
     *
     * @return a list of {@link StoreProduct} matching the punctuation range
     */
    private List<StoreProduct> filterByPunctuation(){
        List<StoreProduct> aux = new ArrayList<>();
        List<StoreProduct> fromStore = new ArrayList<>(this.s.getStoreProducts().values());

        if(this.punctuationF != null){
            for(StoreProduct p: fromStore){
            	for(PunctuationFilter f: this.punctuationF)
                	if(p.getAveragePunctuation() >= f.getMin() &&
                		p.getAveragePunctuation() <= f.getMax()){
                		aux.add(p);
                }
            }
        }
        return aux;
    }

    /**
     * Filters products based on the specified categories.
     *
     * @param c the categories to filter by
     * @return a list of {@link StoreProduct} matching the categories
     */
    private List<StoreProduct> filterByCategory(){
        List<StoreProduct> aux = new ArrayList<>();
        Store s = Store.getInstance();
        List<StoreProduct> product = new ArrayList<> (this.s.getStoreProducts().values());
        List<Category> c = this.categoryF.getCategories();
        Category[] caux;

        for(Category ca: c){
        	aux.addAll(ca.getProducts());
        }

        return new ArrayList<>(new LinkedHashSet<>(aux));
    }

    /**
     * Sets the sorting order of search results.
     *
     * @param bool {@code true} for ascending order, {@code false} for descending order
     */
    public void setAsc(boolean bool) {
        this.ascendant = bool;
    }

    /**
     * It toggles the searcher's order between ascending and descending
     */
    public void toggleOrder(){
        this.ascendant = !this.ascendant;
    }

    /**
     * Returns the boolean that determines wether or not the search is ascendant
     *
     * @return boolean
     */
    public boolean getBoolean() {
    	return this.ascendant;
    }

    public CategoryFilter getCategoryFilter() {
    	return this.categoryF;
    }
    
    public void clearFilters() {
        this.priceF.clear();
        this.punctuationF.clear();
        this.categoryF = new CategoryFilter(new ArrayList<>());
    }

}