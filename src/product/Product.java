package product;

/**
 * It implements the abstract Product class
 * @author Ana O.R.
 * @version 1.9
 */
public abstract class Product {
    /** The global variable to determine which id should a new product have */
    static public int totalId = -1; // NOTE: Así el primer ID es 0000 (ver línea 38)
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

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A product's general constructor
     * @param id          the product's id
     * @param price       the product's price
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     * @param type        the product's type
     * @throws IllegalArgumentException price was negative
     * @throws NullPointerException     name, description or photo's path were null
     */
    public Product(String id, double price, String name, String description, String photo, ProductType type)
            throws IllegalArgumentException, NullPointerException {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (name == null || description == null || photo == null) {
            throw new NullPointerException("Expect non-null parameters but received one");
        }

        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.type = type;
    }

    /**
     * A product's constructor
     * @param price       the product's price
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     * @param type        the product's type
     * @throws IllegalArgumentException price was negative
     * @throws NullPointerException     name, description or photo's path were null
     */
    public Product(double price, String name, String description, String photo, ProductType type)
            throws IllegalArgumentException, NullPointerException {
        this(type.getSymbol() + String.format("%06d", ++totalId), price, name, description, photo, type);
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It returns the product's description
     * @return the product's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * It allows for an employee to change a product's description
     * @param newDescription the product's new description
     * @throws NullPointerException description was null
     */
    public void setDescription(String newDescription) throws NullPointerException {
        if (newDescription == null) {
            throw new IllegalArgumentException("The description cannot be null");
        }

        this.description = newDescription;
    }

    /**
     * It returns the product's id
     * @return the product's id
     */
    public String getId() {
        return this.id;
    }

    /**
     * It returns the product's name
     * @return the product's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * It allows for an employee to change a product's name
     * @param newName the product's new name
     * @throws NullPointerException name was null
     */
    public void setName(String newName) throws NullPointerException {
        if (newName == null) {
            throw new IllegalArgumentException("The name cannot be null");
        }

        this.name = newName;
    }

    /**
     * It returns the product's photo's path
     * @return the product's photo
     */
    public String getPhoto() {
        return this.photo;
    }

    /**
     * It allows for an employee to change a product's photo's path
     * @param newPhoto the product's new photo
     * @throws NullPointerException photo's path was null
     */
    public void setPhoto(String newPhoto) throws NullPointerException {
        if (newPhoto == null) {
            throw new IllegalArgumentException("The photo's path cannot be null");
        }

        this.photo = newPhoto;
    }

    /**
     * It returns the product's price
     * @return the product's price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * It allows for an employee to change a product's price
     * @param newPrice the product's new price
     * @throws IllegalArgumentException price was negative
     */
    public void setPrice(double newPrice) throws IllegalArgumentException {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        this.price = newPrice;
    }

    /**
     * It returns the product's product type
     * @return the product's product type
     */
    public ProductType getType() {
        return this.type;
    }

    /**
     * It allows for an employee to change a product's product's type
     * @param newType the product's new product type
     */
    public void setType(ProductType newType) {
        this.type = newType;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Written information of a product
     * @return String, information of a product
     */
    @Override
    public String toString() {
        /* TYPE;ID;PRICE;NAME;DESC;PHOTO */
        return this.type.getSymbol() + ";" + this.id + ";" + this.price + ";" + this.name + ";" + this.description +
               ";" + this.price;
    }
}