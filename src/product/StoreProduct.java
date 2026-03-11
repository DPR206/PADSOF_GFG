package product;
import order.Order;

/**
 * Class name: StoreProduct
 * <p>
 * Description: It implements the store's products
 * @author Ana O.R.
 * @version 1.0
 * @see Product
 * @see Order
 */
public abstract class StoreProduct extends Product {
    /** The number of available copies of this product */
    private int stock;

    /**
     * Store product's constructor
     * @param id          the product's id
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     * @param price       the product's price
     * @param type        the product's product type
     * @param stock       the product's stock
     * @param categories  the product's categories
     */
    StoreProduct(int id, double price, String name, String description, String photo, ProductType type, int stock,
                 Category... categories) {
        super(id, price, name, description, photo, type, categories);
        this.stock = stock;
    }

    // DUE: Change -> name
    // DUE: Change -> description
    // DUE: Change -> photo
    // DUE: Change -> price
    // DUE: Change -> type
    // DUE: Change -> stock
    // DUE: Change -> categories

    /**
     * It allows for an employee to change the product's stock as well as blocking or unblocking stock
     * @param stock the product's new stock
     */
    public void changeStock(int stock) {
        this.stock = stock;
    }
}
