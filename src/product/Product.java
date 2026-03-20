package product;

/**
 * Class name: Product
 * <p>
 * Description: It implements the abstract Product class
 * @author Ana O.R.
 * @version 1.7
 */
public abstract class Product {
    /** The global variable to determine which id should a new product have */
    static int productId = -1; // NOTE: Así el primer ID es 0000 (ver línea 38)
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

    /*------------------------------------------------- CONSTRUCTORS -------------------------------------------------*/

    /**
     * General product constructor
     * @param price       the product's price
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     * @param type        the type
     */
    public Product(double price, String name, String description, String photo, ProductType type)
            throws IllegalArgumentException, NullPointerException {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (name == null || description == null || photo == null) {
            throw new NullPointerException();
        }
        // NOTE: Según StackOverflow: String.format("%04d", your integer));
        this.id = type.getSymbol() + String.format("%04d", ++productId);
        this.price = price;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.type = type;
    }

    /**
     * SecondHandProduct's Product constructor
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     * @param type        the type
     */
    Product(String name, String description, String photo, ProductType type) {
        // NOTE: Revisar qué precio inicial poner
        this(-1, name, description, photo, type);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

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
}