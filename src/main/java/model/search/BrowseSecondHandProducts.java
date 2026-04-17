package model.search;

import model.product.SecondHandProduct;
import model.store.Store;

import java.util.*;

/**
 * This class provides functionality to browse and retrieve
 * second-hand products available in the store.
 * <p>
 * It obtains the products from the {@link Store} singleton instance
 * and allows sorting them alphabetically by name.
 * </p>
 *
 * @author Sofía C.L.
 * @version 1.4
 * @see SearchStoreProducts
 */
public class BrowseSecondHandProducts {

    /**
     * Singleton instance of the store.
     */
    private final Store s = Store.getInstance();

    /**
     * List of second-hand products available in the store.
     */
    private List<SecondHandProduct> products;

    /**
     * Constructs a BrowseSecondHandProducts object and initializes
     * the list of second-hand products from the store.
     */
    public BrowseSecondHandProducts() {
        this.products = new ArrayList<>(s.getSecondHandProducts().values());
    }

    /**
     * Retrieves all second-hand products sorted in alphabetical order by name.
     *
     * @return a {@link List} of {@link SecondHandProduct} sorted alphabetically
     */
    public List<SecondHandProduct> searchSecondHandProducts() {
        this.products.sort(Comparator.comparing(SecondHandProduct::getName));
        return this.products;
    }
}