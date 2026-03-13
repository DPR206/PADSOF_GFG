package store;

import product.Category;

// DUE: Falta el @see

/**
 * Class name: Store
 * <p>
 * Description: It implements the store
 * @author Sofía C. and Ana O.R.
 * @version 1.0
 */
public class Store {
    private static int productId = 0;
    // Está creada para que no se queje el compilador en sus referencias

    public Category getCategoryFromName(String name) {
        return null; // Sustituir y rellenar
    }

    /**
     * It returns the id of the next product to be created and updates the counter
     * @return the new product's id
     */
    public int getProductId() {
        int id = this.productId;
        return id;
    }
}
