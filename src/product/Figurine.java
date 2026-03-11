package product;

/**
 * Class name: Figurine
 * <p>
 * Description: It implements the figurines
 * @author Ana O.R.
 * @version 1.0
 * @see StoreProduct
 */
public class Figurine extends StoreProduct {
    /** The figurine's height */
    private double height;
    /** The figurine's width */
    private double width;
    /** The figurine's depth */
    private double depth;
    /** The figurine's brand */
    private String brand;
    /** The figurine's material */
    private String material;

    /**
     * The figurine's constructor
     * @param id          the figurine's id
     * @param price       the figurine's price
     * @param name        the figurine's name
     * @param description the figurine's description
     * @param photo       the figurine's photo's path
     * @param stock       the figurine's stock
     * @param height      the figurine's height
     * @param width       the figurine's width
     * @param depth       the figurine's depth
     * @param brand       the figurine's brand
     * @param material    the figurine's material
     * @param categories  the figurine's categories
     */
    Figurine(int id, double price, String name, String description, String photo, int stock, double height,
             double width, double depth, String brand, String material, Category... categories) {
        super(id, price, name, description, photo, ProductType.FIGURINE, stock, categories);
        this.height = height;
        this.width = width;
        this.depth = depth;
        this.brand = brand;
        this.material = material;
    }

    /* ------------------------------------------------- LOS CHANGES ------------------------------------------------ */

    /**
     * It allows for an employee to change a figurine's price
     * @param price the figurine's new price
     */
    public void changePrice(double price) {
        super.changePrice(price);
    }

    /**
     * It allows for an employee to change a figurine's name
     * @param newName the figurine's new name
     */
    public void changeName(String newName) {
        super.changeName(newName);
    }

    /**
     * It allows for an employee to change a figurine's description
     * @param newDescription the figurine's new description
     */
    public void changeDescription(String newDescription) {
        super.changeDescription(newDescription);
    }

    /**
     * It allows for an employee to change a figurine's photo's path
     * @param newPhoto the figurine's new photo
     */
    public void changePhoto(String newPhoto) {
        super.changePhoto(newPhoto);
    }

    /**
     * It allows for an employee to change a figurine's figurine's type
     * @param newType the figurine's new product type
     */
    public void changeType(ProductType newType) {
        super.changeType(newType);
    }

    /**
     * It allows for an employee to change the figurine's stock as well as blocking or unblocking stock
     * @param newStock the figurine's new stock
     */
    public void changeStock(int newStock) {
        super.changeStock(newStock);
    }

    // DUE: Change -> categories

    // DUE: Change -> height
    // DUE: Change -> width
    // DUE: Change -> depth
    // DUE: Change -> brand
    // DUE: Change -> material

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */
    /**
     * It returns the figurine's id
     * @return the figurine's id
     */
    public int getId() {
        return super.getId();
    }

    /**
     * It returns the figurine's price
     * @return the figurine's price
     */
    public double getPrice() {
        return super.getPrice();
    }

    /**
     * It returns the figurine's description
     * @return the figurine's description
     */
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * It returns the figurine's photo's path
     * @return the figurine's photo
     */
    public String getPhoto() {
        return super.getPhoto();
    }

    /**
     * It returns the figurine's product type
     * @return the figurine's product type
     */
    public ProductType getType() {
        return super.getType();
    }

    /**
     * It returns the figurine's categories
     * @return the figurine's categories
     */
    public Category[] getCategories() {
        return super.getCategories();
    }

    // DUE: Change -> height
    // DUE: Change -> width
    // DUE: Change -> depth
    // DUE: Change -> brand
    // DUE: Change -> material
}