package product;

import order.Discount;

/**
 * Class name: Figurine
 * <p>
 * Description: It implements the figurines
 * @author Ana O.R.
 * @version 1.3
 * @see StoreProduct
 */
public class Figurine extends StoreProduct {
    /** The figurine's dimension */
    private String dimension;
    /** The figurine's brand */
    private String brand;
    /** The figurine's material */
    private String material;

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
    Figurine(double price, String name, String description, String photo, int stock, String dimension, String brand,
             String material, Category... categories) {
        super(price, name, description, photo, ProductType.FIGURINE, stock, categories);
        this.dimension = dimension;
        this.brand = brand;
        this.material = material;
    }

    /**
     * It allows an employee to add a figurine to the store
     * @param price       the figurine's price
     * @param name        the figurine's name
     * @param description the figurine's description
     * @param photo       the figurine's photo's path
     * @param stock       the figurine's stock
     * @param dimension   the figurine's dimension
     * @param brand       the figurine's brand
     * @param material    the figurine's material
     * @param categories  the figurine's categories
     * @return the new figurine
     */
    public Figurine createFigurine(double price, String name, String description, String photo, int stock,
                                   String dimension, String brand, String material, Category... categories) {
        return new Figurine(price, name, description, photo, stock, dimension, brand
                , material, categories);
    }

    /**
     * Written information of a product
     * @return String, information of a product
     */
    @Override
    public String toString() {
        // TIPO(C/J/F);ID;NOMBRE;DESCRIPCIÓN;PRECIO;UNIDADES;CATEGORÍAS;PAGINAS;AUTOR;EDITORIAL;AÑO;JUGADORES;EDAD
        // ;ESTILO(Cartas/Dados/Tablero/Miniatura);MARCA;MATERIAL;DIMENSION
        return "C" + ";" + this.getId() + ";" + this.getName() + ";" + this.getDescription() + ";" + this.getPrice() +
                ";" + this.getStock() + ";" + /*paginas*/ ";" + /*autor*/ ";" + /*editorial*/ ";" +
                /*año*/ ";" /*jugadores*/ + ";" /*edad*/ + ";" /*estilo*/ + ";" + this.brand + ";"
                + this.material + ";" + this.dimension;
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

    /**
     * It allows the system or an employee to add categories to a product
     * @param newCategories the categories to be added
     */
    @Override
    public void addCategory(Category... newCategories) {
        super.addCategory(newCategories);
    }

    /**
     * It allows an employee to remove categories from a product
     * @param categories the categories to be deleted
     */
    @Override
    public void removeCategory(Category... categories) {
        super.removeCategory(categories);
    }

    /**
     * It allows an employee to add discounts to products or categories (Discounts is in charge of making sure they
     * don't overlap)
     * @param newDiscount the new discount to be applied
     */
    @Override
    public void changeDiscount(Discount newDiscount) {
        super.changeDiscount(newDiscount);
    }

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

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */

    /**
     * It returns the figurine's id
     * @return the figurine's id
     */
    @Override
    public String getId() {
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
     * It returns the product's name
     * @return the product's name
     */
    @Override
    public String getName() {
        return super.getName();
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