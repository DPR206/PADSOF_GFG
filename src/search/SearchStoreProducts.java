package search;

import product.Category;
import product.StoreProduct;
import store.Store;

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
public class SearchStoreProducts{
    private boolean ascendant;
    private PriceFilter priceF;
    private PunctuationFilter punctuationF;
    private Store s;

    /**
     * Creates the searcher with no initial filters.
     *
     * @param asc determines if the searches are in ascending order ({@code true}) or descending order ({@code false})
     */
    public SearchStoreProducts(boolean asc){
        this.ascendant = asc;
        this.priceF = null;
        this.punctuationF = null;
        this.s = Store.getInstance();
    }

    /**
     * Adds and initializes a punctuation filter.
     *
     * @param min minimum punctuation (rating) wanted on the product
     * @param max maximum punctuation (rating) wanted on the product
     */
    public void addPunctuationFilter(int min, int max){
        this.punctuationF = new PunctuationFilter(min, max);
    }

    /**
     * Adds and initializes a price filter.
     *
     * @param min minimum price wanted on the product
     * @param max maximum price wanted on the product
     */
    public void addPriceFilter(double min, double max){
        this.priceF= new PriceFilter(min, max);
    }

    /**
     * Searches products filtered by price, punctuation, and optionally by categories.
     *
     * <p>Results are sorted according to the {@code ascendant} flag and active filters.</p>
     *
     * @param cs the categories to filter by (optional)
     * @return a list of {@link StoreProduct} matching the filters and categories
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
                pCs.sort(Comparator.comparing(StoreProduct::getAveragePunctuation)
                                   .thenComparing(StoreProduct::getPrice));
            }
            else if(this.priceF != null){
                pCs.sort(Comparator.comparing(StoreProduct::getPrice));
            }
            else if(this.punctuationF != null){
                pCs.sort(Comparator.comparing(StoreProduct::getAveragePunctuation));
            }
        }
        else{
             if(this.priceF != null && this.punctuationF != null){
                pCs.sort(Comparator.comparingDouble(StoreProduct::getAveragePunctuation)
                                   .reversed()
                                   .thenComparing(Comparator.comparingDouble(StoreProduct::getPrice).reversed()));
            }
            else if(this.priceF != null){
                pCs.sort(Comparator.comparingDouble(StoreProduct::getPrice).reversed());
            }
            else if(this.punctuationF != null){
                pCs.sort(Comparator.comparingDouble(StoreProduct::getAveragePunctuation).reversed());
            }
        }
        return pCs;
    }

    /**
     * Searches products filtered only by price and/or punctuation.
     *
     * @return a list of {@link StoreProduct} matching the active filters
     */
    public List<StoreProduct> searchStoreProducts(){
        List<StoreProduct> priced;
        List<StoreProduct> punctuation;

        if(this.punctuationF != null && this.priceF != null){
            priced = this.filterByPrice();
            punctuation = this.filterByPrice();
            if (priced != null && punctuation != null) {
                priced.retainAll(punctuation);
                return priced;
            }
        }
        else if(this.punctuationF == null && this.priceF != null){
            punctuation = this.filterByPrice();
            return punctuation;
        }
        else if(this.punctuationF != null){
            priced = this.filterByPrice();
            return priced;
        }
        return s.getStoreProductList();
    }

    /**
     * Filters products based on the price filter.
     *
     * @return a list of {@link StoreProduct} matching the price range, or {@code null} if no price filter is set
     */
    private List<StoreProduct> filterByPrice(){
        List<StoreProduct> aux = new ArrayList<>();
        List<StoreProduct> fromStore = (List<StoreProduct>) this.s.getStoreProducts().values();

        if(this.priceF != null){
            for(StoreProduct p: fromStore){
                if(p.getPrice() >= this.priceF.getMin() && p.getPrice() <= this.priceF.getMax()) aux.add(p);
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
        List<StoreProduct> fromStore = (List<StoreProduct>) this.s.getStoreProducts().values();

        if(this.punctuationF != null){
            for(StoreProduct p: fromStore){
                if(p.getAveragePunctuation() >= this.punctuationF.getMin() &&
                   p.getAveragePunctuation() <= this.punctuationF.getMax()){
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
    private List<StoreProduct> filterByCategory(Category... c){
        List<StoreProduct> aux = new ArrayList<>();
        List<StoreProduct> product = (List<StoreProduct>) this.s.getStoreProducts();
        Category[] caux;

        for(Category cat: c) {
            for(StoreProduct sp: product) {
                caux = sp.getCategories();
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
}