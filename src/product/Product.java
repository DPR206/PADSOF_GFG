package product;

/**
 * Class name: Product
 * <p>
 * Description: It implements the abstract Product class
 * @author Ana O.R.
 * @version 1.1
 */
public abstract class Product {
    /** The product's id */
    private int id;
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
    private Category[] categories;

    /**
     * General product constructor
     * @param id          the product's id
     * @param price       the product's price
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     * @param type        the type
     * @param categories  the product's categories
     */
    Product(int id, double price, String name, String description, String photo, ProductType type,
            Category... categories) {
        // NOTE: Este constructor existe porque tengo miedo de Java y facilita los constructores, NO debería llamarse
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.type = type;
        this.categories = categories;
    }

    /**
     * SecondHandProduct's Product constructor
     * @param id          the product's id
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     * @param type        the type
     * @param categories  the product's categories
     */
    Product(int id, String name, String description, String photo, ProductType type, Category... categories) {
        // NOTE: Revisar qué precio inicial poner
        // NOTE: Técnicamente da igual el numSales de los productos de segunda mano
        this(id, -1, name, description, photo, type, categories);
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

    // DUE: Change -> categories

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */

    /**
     * It returns the product's id
     * @return the product's id
     */
    public int getId() {
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
        return this.categories;
    }
}