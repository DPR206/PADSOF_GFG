package product;

import order.Discount;
import order.Order;
import user.RegisteredClient;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class name: StoreProduct
 * <p>
 * Description: It implements the store's products
 * @author Ana O.R. and Duna P.R.
 * @version 1.3
 * @see Product
 * @see Order
 * @see Review
 */
public abstract class StoreProduct extends Product {
    /** The number of available copies of this product */
    private int stock;
    /** The product's reviews */
    private final ArrayList<Review> reviews;
    /** The product's discount, if it has one */
    private Discount discount;
    /** The product's categories */
    private HashMap<String, Category> categories;

    /**
     * Store product's constructor
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     * @param price       the product's price
     * @param type        the product's product type
     * @param stock       the product's stock
     * @param categories  the product's categories
     */
    StoreProduct(double price, String name, String description, String photo, ProductType type, int stock,
                 Category... categories) {
        super(price, name, description, photo, type);
        this.stock = stock;
        this.reviews = new ArrayList<>();
        this.discount = null;
        this.categories = new HashMap<>();
        for (Category category : categories) {
            this.addCategory(category);
        }
    }

    /* ------------------------------------------------- LOS CHANGES ------------------------------------------------ */

    /**
     * It allows for an employee to change a product's price
     * @param price the product's new price
     */
    @Override
    public void changePrice(double price) {
        super.changePrice(price);
    }

    /**
     * It allows for an employee to change a product's name
     * @param newName the product's new name
     */
    @Override
    public void changeName(String newName) {
        super.changeName(newName);
    }

    /**
     * It allows for an employee to change a product's description
     * @param newDescription the product's new description
     */
    @Override
    public void changeDescription(String newDescription) {
        super.changeDescription(newDescription);
    }

    /**
     * It allows for an employee to change a product's photo's path
     * @param newPhoto the product's new photo
     */
    @Override
    public void changePhoto(String newPhoto) {
        super.changePhoto(newPhoto);
    }

    /**
     * It allows for an employee to change a product's product's type
     * @param newType the product's new product type
     */
    @Override
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

    /**
     * It decreases the stock a certain value
     * @param value, the amount it decreases
     */
    public void decreaseStock(int value) {

        if ((stock = stock - value) < 0)
            stock = 0;
    }

    /**
     * It increases the stock a certain value
     * @param value, the amount it increases
     */
    public void increaseStock(int value) {
        stock = stock + value;
    }

    /**
     * It allows the system or an employee to add categories to a product
     * @param newCategories the categories to be added
     */
    public void addCategory(Category... newCategories) {
        for (Category newCategory : newCategories) {
            if (!this.categories.containsKey(newCategory.getName())) {
                this.categories.put(newCategory.getName(), newCategory);
            }
        }
    }

    /**
     * It allows an employee to remove categories from a product
     * @param categories the categories to be deleted
     */
    public void removeCategory(Category... categories) {
        for (Category category : categories) {
            this.categories.remove(category.getName());
        }
    }

    /**
     * It allows an employee to add discounts to products or categories (Discounts is in charge of making sure they
     * don't overlap)
     * @param newDiscount the new discount to be applied
     */
    public void changeDiscount(Discount newDiscount) {
        this.discount = newDiscount;
    }

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */

    /**
     * It returns the product's id
     * @return the product's id
     */
    @Override
    public String getId() {
        return super.getId();
    }

    /**
     * It returns the product's price
     * @return the product's price
     */
    @Override
    public double getPrice() {
        return super.getPrice();
    }

    /**
     * It returns the product's name
     * @return the product's name
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * It returns the product's description
     * @return the product's description
     */
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * It returns the product's photo's path
     * @return the product's photo
     */
    @Override
    public String getPhoto() {
        return super.getPhoto();
    }

    /**
     * It returns the product's product type
     * @return the product's product type
     */
    @Override
    public ProductType getType() {
        return super.getType();
    }

    /**
     * It returns the product's categories
     * @return the product's categories
     */
    public Category[] getCategories() {
        return this.categories.values().toArray(new Category[0]);
    }

    /**
     * It returns's the product's stock
     * @return the product's store
     */
    public int getStock() {
        return this.stock;
    }

    /**
     * It allows a registered client to review a product
     * @param scoring the review's score
     * @param comment the review's comment
     * @param author  the review's author
     */
    public void addReview(int scoring, String comment, RegisteredClient author) {
        reviews.add(new Review(scoring, comment, author));
    }
}