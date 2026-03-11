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

    // DUE: Change -> name
    // DUE: Change -> description
    // DUE: Change -> photo
    // DUE: Change -> price
    // DUE: Change -> type
    // DUE: Change -> stock
    // DUE: Change -> categories
    // DUE: Change -> stock
    // DUE: Change -> height
    // DUE: Change -> width
    // DUE: Change -> depth
    // DUE: Change -> brand
    // DUE: Change -> material
}
