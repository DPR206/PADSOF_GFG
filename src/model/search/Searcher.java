package model.search;

import model.exchange.Exchange;
import model.order.Order;
import model.product.Category;
import model.product.Pack;
import model.product.SecondHandProduct;
import model.product.StoreProduct;
import model.user.Employee;

import java.util.List;

/**
 * This class acts as the main search manager, grouping together
 * all search functionalities available in the package.
 *
 * <p>It delegates specific searches (products, employees, orders,
 * exchanges, packs, etc.) to their corresponding search classes,
 * depending on the enabled {@link SearchType}.</p>
 *
 * @author Sofía C.L.
 * @version 1.4
 * @see SearchStoreProducts
 */
public class Searcher{
    private SearchType[] types;
    private SearchStoreProducts searchStore;

    private SearchPack packSearcher = new SearchPack();
    private SearchEmployee employeeSearcher = new SearchEmployee();
    private SearchOrder orderSearcher = new SearchOrder();
    private SearchExchange exchangeSearcher = new SearchExchange();

    private BrowseSecondHandProducts secondHandProducts;

    /**
     * Creates the master searcher with specific search types enabled.
     *
     * @param searchStore the class used to search store products
     * @param types the types of searches that will be available
     */
    public Searcher(SearchStoreProducts searchStore, SearchType... types){
        this(searchStore);
        this.types = types;
    }

    /**
     * Creates the master searcher.
     *
     * @param searchStore the class used to search store products
     */
    public Searcher(SearchStoreProducts searchStore){
        this.searchStore = searchStore;
    }

    /**
     * Returns the store product searcher.
     *
     * @return the {@link SearchStoreProducts} instance
     */
    public SearchStoreProducts getStoreSearcher(){
        return this.searchStore;
    }

    /**
     * Sets the enabled search types.
     *
     * @param st the search types to be used
     */
    public void setTypes(SearchType... st){
        this.types = st;
    }

    /**
     * Searches store products using the filters defined
     * in {@link SearchStoreProducts}.
     *
     * @return a list of matching {@link StoreProduct}
     */
    public List<StoreProduct> searchStoreProducts(){
        if(this.searchStore.getCategoryFilter() == null) {
        	return this.searchStore.searchStoreProductsWithoutCategories();
        }
        return this.searchStore.searchStoreProducts();
    }

    /**
     * Searches store products filtered by category.
     *
     * @param c the categories of the products to search
     * @return a list of matching {@link StoreProduct}
     */
    public List<StoreProduct> searchByCategory(Category... c){
        return this.searchStore.searchStoreProducts();
    }

    /**
     * Searches for a pack by its ID.
     *
     * @param id the ID of the pack
     * @return the {@link Pack} if found, or {@code null} if not found
     */
    public Pack searchPackByID(int id){
        if(this.linearSearch(SearchType.S_PACK)){
            return this.packSearcher.searchByID(id);
        }
        return null;
    }

    /**
     * Searches for an exchange by its ID.
     *
     * @param id the ID of the exchange
     * @return the {@link Exchange} if found, or {@code null} if not found
     */
    public Exchange searchExchangeByID(int id){
        if(this.linearSearch(SearchType.S_EXCHANGE)){
            return this.exchangeSearcher.searchByID(id);
        }
        return null;
    }

    /**
     * Searches for an order by its ID.
     *
     * @param id the ID of the order
     * @return the {@link Order} if found, or {@code null} if not found
     */
    public Order searchOrderByID(int id){
        if(this.linearSearch(SearchType.S_ORDER)){
            return this.orderSearcher.searchByID(id);
        }
        return null;
    }

    /**
     * Searches for an employee by their ID.
     *
     * @param id the ID of the employee
     * @return the {@link Employee} if found, or {@code null} if not found
     */
    public Employee searchEmployeeByID(int id){
        if(this.linearSearch(SearchType.S_EMPLOYEE)){
            return this.employeeSearcher.searchByID(id);
        }
        return null;
    }

    /**
     * Retrieves all second-hand products sorted alphabetically.
     *
     * @return a list of {@link SecondHandProduct}, or {@code null} if the search type is not enabled
     */
    public List<SecondHandProduct> browseSecondHandProduct(){
        if(this.linearSearch(SearchType.S_SECOND_HAND)){
            return this.secondHandProducts.searchSecondHandProducts();
        }
        return null;
    }

    /**
     * Performs a linear search to check whether a given search type
     * is enabled in the current configuration.
     *
     * @param t the {@link SearchType} to check
     * @return {@code true} if the type is enabled, {@code false} otherwise
     */
    private boolean linearSearch(SearchType t){
        for(SearchType type: this.types){
            if(t.getType().equals(type.getType())) return true;
        }
        return false;
    }

    /**
     * Changes the ordering of the product search results.
     *
     * @param bool {@code true} for ascending order, {@code false} for descending
     */
    public void changeProductOrder(boolean bool) {
        this.searchStore.setAsc(bool);
    }

    /**
     * It toggles the searcher's order between ascending and descending
     */
    public void toggleProductOrder() {
        this.searchStore.toggleOrder();
    }


    /**
     * It returns the boolean that determines wether or not the search is ascendant in values
     *
     * @return boolean
     */
    public boolean getBoolean() {
    	return this.searchStore.getBoolean();
    }
}