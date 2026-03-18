package product;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Class name: Product
 * <p>
 * Description: It implements the abstract Product class
 * @author Ana O.R.
 * @version 1.4
 */
public abstract class Product {
    /** The global variable to determine which id should a new product have */
    public static int productId;
    /** The product's id */
    private final String id;
    /** The product's price */
    private double price;
    /** The product's name */
    private String name;
    /** The product's description */
    private String description;
    /** The product's photo's path */
    private String photo;
    /** The product's product type */
    private ProductType type;
    /** The product's categories */
    private HashMap<String, Category> categories;

    static {
        productId = 0;
    }

    /**
     * General product constructor
     * @param price       the product's price
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     * @param type        the type
     * @param categories  the product's categories
     */
    Product(double price, String name, String description, String photo, ProductType type,
            Category... categories) {
        this.id = type.getSymbol() + productId;
        productId++;
        this.price = price;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.type = type;
        this.categories = new HashMap<>();
        for (Category category : categories) {
            this.addCategory(category);
        }
    }

    /**
     * SecondHandProduct's Product constructor
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     * @param type        the type
     * @param categories  the product's categories
     */
    Product(String name, String description, String photo, ProductType type, Category... categories) {
        // NOTE: Revisar qué precio inicial poner
        this(-1, name, description, photo, type, categories);
    }

    /**
     * Written information of a product
     * @return String, information of a product
     */
    @Override
    public String toString() {
        return "Product #" + this.id + ", " + this.name + ", " + this.description + ", " + this.photo + "[" + this.type
                + "]" + "{" + Arrays.toString(this.categories.values().toArray(new Category[0])) + "}";
    }

    /* ------------------------------------------------- LOS CHANGES ------------------------------------------------ */

    /**
     * It allows for an employee to change a product's price
     * @param price the product's new price
     */
    public void changePrice(double price) {
        this.price = price;
    }

    /**
     * It allows for an employee to change a product's name
     * @param newName the product's new name
     */
    public void changeName(String newName) {
        this.name = newName;
    }

    /**
     * It allows for an employee to change a product's description
     * @param newDescription the product's new description
     */
    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * It allows for an employee to change a product's photo's path
     * @param newPhoto the product's new photo
     */
    public void changePhoto(String newPhoto) {
        this.photo = newPhoto;
    }

    /**
     * It allows for an employee to change a product's product's type
     * @param newType the product's new product type
     */
    public void changeType(ProductType newType) {
        this.type = newType;
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

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */

    /**
     * It returns the product's id
     * @return the product's id
     */
    public String getId() {
        return this.id;
    }

    /**
     * It returns the product's price
     * @return the product's price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * It returns the product's name
     * @return the product's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * It returns the product's description
     * @return the product's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * It returns the product's photo's path
     * @return the product's photo
     */
    public String getPhoto() {
        return this.photo;
    }

    /**
     * It returns the product's product type
     * @return the product's product type
     */
    public ProductType getType() {
        return this.type;
    }

    /**
     * It returns the product's categories
     * @return the product's categories
     */
    public Category[] getCategories() {
        return this.categories.values().toArray(new Category[0]);
    }
}