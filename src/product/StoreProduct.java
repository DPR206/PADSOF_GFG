package product;

import order.Order;

import java.util.*;

/**
 * Class name: StoreProduct
 * <p>
 * Description: It implements the store's products
 * @author Ana O.R.
 * @version 1.0
 * @see Product
 * @see Order
 * @see Review
 */
public abstract class StoreProduct extends Product {
    /** The number of available copies of this product */
    private int stock;
    private ArrayList<Review> reviews;

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

    /* ------------------------------------------------- LOS CHANGES ------------------------------------------------ */

    /**
     * It allows for an employee to change a product's price
     * @param price the product's new price
     */
    public void changePrice(double price) {
        super.changePrice(price);
    }

    /**
     * It allows for an employee to change a product's name
     * @param newName the product's new name
     */
    public void changeName(String newName) {
        super.changeName(newName);
    }

    /**
     * It allows for an employee to change a product's description
     * @param newDescription the product's new description
     */
    public void changeDescription(String newDescription) {
        super.changeDescription(newDescription);
    }

    /**
     * It allows for an employee to change a product's photo's path
     * @param newPhoto the product's new photo
     */
    public void changePhoto(String newPhoto) {
        super.changePhoto(newPhoto);
    }

    /**
     * It allows for an employee to change a product's product's type
     * @param newType the product's new product type
     */
    public void changeType(ProductType newType) {
        super.changeType(newType);
    }

    /**
     * It allows for an employee to change the product's stock as well as blocking or unblocking stock
     * @param newStock the product's new stock
     */
    public void changeStock(int newStock) {
        this.stock = newStock;
    }

    // DUE: Change -> categories

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */

    /**
     * It returns the product's id
     * @return the product's id
     */
    public int getId() {
        return super.getId();
    }

    /**
     * It returns the product's price
     * @return the product's price
     */
    public double getPrice() {
        return super.getPrice();
    }

    /**
     * It returns the product's description
     * @return the product's description
     */
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * It returns the product's photo's path
     * @return the product's photo
     */
    public String getPhoto() {
        return super.getPhoto();
    }

    /**
     * It returns the product's product type
     * @return the product's product type
     */
    public ProductType getType() {
        return super.getType();
    }

    /**
     * It returns the product's categories
     * @return the product's categories
     */
    public Category[] getCategories() {
        return super.getCategories();
    }

    public void addReview() {} //habrá que desarrollar
}