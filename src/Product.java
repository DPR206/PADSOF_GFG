/**
 * Classname: Product
 * <p>
 * Description: It implements the abstract Product class
 * @author Ana O.R.
 * @version 1.0  <p> Copyright??
 */
public abstract class Product {
    private int id; /* The product's id */
    private double price; /* The product's price */
    private String name; /* The product's name */
    private String description; /* The product's description */
    private String photo; /* The product's photo's path */
    private ProductType type; /* The product's product type */
    private Category[] categories; /* The product's categories */

    /**
     * General product constructor
     * @param id          the product's id
     * @param name        the product's name
     * @param description the product's description
     * @param photo       the product's photo's path
     * @param price       the product's price
     * @param type        the type
     * @param categories  the product's categories
     */
    Product(int id, String name, String description, String photo, double price, ProductType type,
            Category... categories) {
        // NOTE: Este constructor existe porque tengo miedo de Java y facilita los constructores, NO debería llamarse
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.price = price;
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
        this(id, name, description, photo, -1, type, categories);
    }

    /**
     * It allows for an employee to change a product's price
     * @param price the product's price
     */
    public void changePrice(double price) {
        this.price = price;
    }
}