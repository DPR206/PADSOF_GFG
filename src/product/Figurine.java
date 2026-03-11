package product;

import store.Store;

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

    /**
     * It allows an employee to add a figurine to the store
     * @param store       the store
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
     * @return the new figurine
     */
    public Figurine addFigurine(Store store, double price, String name, String description, String photo, int stock,
                                double height,
                                double width, double depth, String brand, String material, Category... categories) {
        return new Figurine(store.getProductId(), price, name, description, photo, stock, height, width, depth, brand
                , material, categories);
    }

    /* ------------------------------------------------- LOS CHANGES ------------------------------------------------ */

    /**
     * It allows for an employee to change a figurine's price
     * @param price the figurine's new price
     */
    @Override
    public void changePrice(double price) {
        super.changePrice(price);
    }

    /**
     * It allows for an employee to change a figurine's name
     * @param newName the figurine's new name
     */
    @Override
    public void changeName(String newName) {
        super.changeName(newName);
    }

    /**
     * It allows for an employee to change a figurine's description
     * @param newDescription the figurine's new description
     */
    @Override
    public void changeDescription(String newDescription) {
        super.changeDescription(newDescription);
    }

    /**
     * It allows for an employee to change a figurine's photo's path
     * @param newPhoto the figurine's new photo
     */
    @Override
    public void changePhoto(String newPhoto) {
        super.changePhoto(newPhoto);
    }

    /**
     * It allows for an employee to change a figurine's figurine's type
     * @param newType the figurine's new product type
     */
    @Override
    public void changeType(ProductType newType) {
        super.changeType(newType);
    }

    /**
     * It allows for an employee to change the figurine's stock as well as blocking or unblocking stock
     * @param newStock the figurine's new stock
     */
    @Override
    public void changeStock(int newStock) {
        super.changeStock(newStock);
    }

    // DUE: Change -> categories

    /**
     * It allows for an employee to change the figurine's height
     * @param newHeight the figurine's height
     */
    public void changeHeight(double newHeight) {
        this.height = newHeight;
    }

    /**
     * It allows for an employee to change the figurine's width
     * @param newWidth the figurine's width
     */
    public void changeWidth(double newWidth) {
        this.width = newWidth;
    }

    /**
     * It allows for an employee to change the figurine's depth
     * @param newDepth the figurine's depth
     */
    public void changeDepth(double newDepth) {
        this.depth = newDepth;
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

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */

    /**
     * It returns the figurine's id
     * @return the figurine's id
     */
    @Override
    public int getId() {
        return super.getId();
    }

    /**
     * It returns the figurine's price
     * @return the figurine's price
     */
    @Override
    public double getPrice() {
        return super.getPrice();
    }

    /**
     * It returns the figurine's description
     * @return the figurine's description
     */
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * It returns the figurine's photo's path
     * @return the figurine's photo
     */
    @Override
    public String getPhoto() {
        return super.getPhoto();
    }

    /**
     * It returns the figurine's product type
     * @return the figurine's product type
     */
    @Override
    public ProductType getType() {
        return super.getType();
    }

    /**
     * It returns the figurine's categories
     * @return the figurine's categories
     */
    @Override
    public Category[] getCategories() {
        return super.getCategories();
    }

    /**
     * It returns's the product's stock
     * @return the product's store
     */
    @Override
    public int getStock() {
        return super.getStock();
    }

    /**
     * It returns's the product's height
     * @return the product's height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * It returns's the product's width
     * @return the product's width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * It returns's the product's depth
     * @return the product's depth
     */
    public double getDepth() {
        return this.depth;
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