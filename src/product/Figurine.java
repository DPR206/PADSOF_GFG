package product;

import java.time.LocalDate;

/**
 * It implements the figurines
 * @author Ana O.R.
 * @version 1.6
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
     * A figurine's general constructor
     * @param id                 the figurine's id
     * @param price              the figurine's price
     * @param name               the figurine's name
     * @param description        the figurine's description
     * @param photo              the figurine's photo's path
     * @param averagePunctuation the figurine's average punctuation
     * @param addedDate          the figurine's added date
     * @param stock              the figurine's stock
     * @param assignedDimension  the figurine's dimension
     * @param assignedBrand      the figurine's brand
     * @param assignedMaterial   the figurine's material
     * @param assignedCategories the figurine's categories
     */
    public Figurine(String id, double price, String name, String description, String photo, double averagePunctuation,
                    LocalDate addedDate, int stock, String assignedDimension, String assignedBrand,
                    String assignedMaterial, Category... assignedCategories) {
        super(id, price, name, description, photo, averagePunctuation, addedDate, ProductType.FIGURINE, stock,
                assignedCategories);
        this.dimension = assignedDimension;
        this.brand = assignedBrand;
        this.material = assignedMaterial;
    }

    /**
     * The figurine's constructor
     * @param price             the figurine's price
     * @param name              the figurine's name
     * @param description       the figurine's description
     * @param photo             the figurine's photo
     * @param stock             the figurine's stock
     * @param assignedDimension the figurine's dimension
     * @param assignedBrand     the figurine's brand
     * @param assignedMaterial  the figurine's material
     * @param categories        the figurine's categories
     */
    public Figurine(double price, String name, String description, String photo, int stock, String assignedDimension,
                    String assignedBrand, String assignedMaterial, Category... categories) {
        super(price, name, description, photo, ProductType.FIGURINE, stock, categories);
        this.dimension = assignedDimension;
        this.brand = assignedBrand;
        this.material = assignedMaterial;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It prints the product's info when seen individually
     */
    @Override
    public void bigPrintInfo() {
        /* super;NUM_PAGES;AUTHOR;EDITORIAL;YEAR;NUM_PLAYERS;AGE_RANGE;GAME_STYLE;<BRAND;MATERIAL;DIMENSION> */
        super.bigPrintInfo();
        System.out.println("Brand: " + this.brand);
        System.out.println("Material: " + this.material);
        System.out.println("Dimensions: " + this.dimension + " cm\n");
    }

    /**
     * It prints the product's info when managed
     */
    @Override
    public void printAllInfo() {
        super.printAllInfo();
        System.out.println("Brand: " + this.brand);
        System.out.println("Material: " + this.material);
        System.out.println("Dimensions: " + this.dimension + " cm\n");
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It returns's the product's brand
     * @return the product's brand
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * It allows for an employee to change the figurine's brand
     * @param newBrand the figurine's brand
     */
    public void setBrand(String newBrand) {
        this.brand = newBrand;
    }

    /**
     * It returns's the product's dimension
     * @return the product's dimension
     */
    public String getDimension() {
        return this.dimension;
    }

    /**
     * It allows for an employee to change the figurine's dimension
     * @param newDimension the figurine's dimension
     */
    public void setDimension(String newDimension) {
        this.dimension = newDimension;
    }

    /**
     * It returns's the product's material
     * @return the product's material
     */
    public String getMaterial() {
        return this.material;
    }

    /**
     * It allows for an employee to change the figurine's material
     * @param newMaterial the figurine's material
     */
    public void setMaterial(String newMaterial) {
        this.material = newMaterial;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Written information of a product
     * @return String, information of a product
     */
    @Override
    public String toString() {
        /* super;NUM_PAGES;AUTHOR;EDITORIAL;YEAR;NUM_PLAYERS;AGE_RANGE;GAME_STYLE;<BRAND;MATERIAL;DIMENSION> */
        return super.toString() + ";" + /*num_pages*/ ";" + /*author*/ ";" + /*editorial*/ ";" + /*year*/
               ";" /*num_players*/ + ";" /*ager_range*/ + ";" /*game_style*/ + ";" + this.brand + ";" + this.material +
               ";" + this.dimension;
    }
}