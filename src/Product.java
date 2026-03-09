/**
 * Classname: Product Description: It implements the abstract Product class
 * @author Ana O.R.
 * @version 1.0
 * <p>
 * Copyright??
 */
public abstract class Product {
    private int id; /* The product's id */
    private double price; /* The product's price */
    private String name; /* The product's name */
    private String description; /* The product's description */
    private String photo; /* The product's photo's path */
    private int numSales; /* The product's total sales */
    private ProductType type;
    // TODO: Array de Category

    // NOTE: Este constructor existe porque tengo miedo de Java y facilita los otros constructores, NO debería llamarse

    /**
     * General product's constructor
     * @param id          the product's id
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     * @param price       the product's price
     * @param numSales    the product's total sales
     */
    public Product(int id, String name, String description, String photo, double price, int numSales, ProductType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.price = price;
        this.numSales = numSales;
        this.type = type;
    }

    /**
     * StoreProduct's Product constructor
     * @param id          the product's id
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     * @param price       the product's price
     */
    public Product(int id, String name, String description, String photo, double price, ProductType type) {
        Product(id, name, description, photo, price, 0, type);
    }

    /**
     * SecondHandProduct's Product constructor
     * @param id          the product's id
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     */
    public Product(int id, String name, String description, String photo, ProductType type) {
        // NOTE: Revisar qué precio inicial poner
        // NOTE: Tecnicamente da igual el numSales de los productos de segunda mano
        Product(id, name, description, photo, -1, -1, type);
    }

    public int buy(int uds) {
        // ! Terminar
    }
}