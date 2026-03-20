package product;

/**
 * Class name: Figurine
 * <p>
 * Description: It implements the figurines
 * @author Ana O.R.
 * @version 1.4
 * @see StoreProduct
 */
public class Figurine extends StoreProduct {
    /** The figurine's dimension */
    private String dimension;
    /** The figurine's brand */
    private String brand;
    /** The figurine's material */
    private String material;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The figurine's constructor
     * @param price       the figurine's price
     * @param name        the figurine's name
     * @param description the figurine's description
     * @param photo       the figurine's photo's path
     * @param stock       the figurine's stock
     * @param dimension   the figurine's dimension
     * @param brand       the figurine's brand
     * @param material    the figurine's material
     * @param categories  the figurine's categories
     */
    public Figurine(double price, String name, String description, String photo, int stock, String dimension,
                    String brand, String material, Category... categories) {
        super(price, name, description, photo, ProductType.FIGURINE, stock, categories);
        this.dimension = dimension;
        this.brand = brand;
        this.material = material;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * Written information of a product
     * @return String, information of a product
     */
    @Override
    public String toString() {
        /* TYPE(C/G/F);ID;NAME;DESCRIPTION;PRICE;STOCK;CATEGORIES;PAGES;AUTHOR;EDITORIAL;YEAR;PLAYERS;AGE;
        STYLE(Cards/Dice/GameBoard/Miniature);BRAND;MATERIAL;DIMENSION */
        return "C" + ";" + this.getId() + ";" + this.getName() + ";" + this.getDescription() + ";" + this.getPrice() +
                ";" + this.getStock() + ";" + this.getStock() + ";" + /*paginas*/ ";" + /*autor*/ ";" + /*editorial
                */ ";" + /*año*/ ";" /*jugadores*/ + ";" /*edad*/ + ";" /*estilo*/ + ";" + this.brand + ";"
                + this.material + ";" + this.dimension;
    }

    /*--------------------------------------------------- CHANGERS ---------------------------------------------------*/

    /**
     * It allows for an employee to change the figurine's dimension
     * @param newDimension the figurine's dimension
     */
    public void changeDimension(String newDimension) {
        this.dimension = newDimension;
    }

    /**
     * It allows for an employee to change the figurine's brand
     * @param newBrand the figurine's brand
     */
    public void changeBrand(String newBrand) {
        this.brand = newBrand;
    }

    /**
     * It allows for an employee to change the figurine's material
     * @param newMaterial the figurine's material
     */
    public void changeMaterial(String newMaterial) {
        this.material = newMaterial;
    }

    /*---------------------------------------------------- GETTERS ---------------------------------------------------*/

    /**
     * It returns's the product's dimension
     * @return the product's dimension
     */
    public String getDimension() {
        return this.dimension;
    }

    /**
     * It returns's the product's brand
     * @return the product's brand
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * It returns's the product's material
     * @return the product's material
     */
    public String getMaterial() {
        return this.material;
    }
}