package product;

import java.time.Year;

/**
 * Class name: Comic
 * <p>
 * Description: It implements the comics
 * @author Ana O.R.
 * @version 1.0
 * @see StoreProduct
 */
public class Comic extends StoreProduct {
    /** The comic's number of pages */
    private int numPages;
    /** The year in which the comic was published */
    private Year year;
    /** The comic's author */
    private String author;
    /** The comic's editorial */
    private String editorial;

    /**
     * The comic's constructor
     * @param id          the comic's id
     * @param price       the comic's price
     * @param name        the comic's name
     * @param description the comic's description
     * @param photo       the comic's photo's path
     * @param stock       the comic's stock
     * @param numPages    the comic's number of pages
     * @param year        the comic's publishing year
     * @param author      the comic's author
     * @param editorial   the comic's editorial
     * @param categories  the comic's categories
     */
    Comic(int id, double price, String name, String description, String photo, int stock, int numPages, Year year,
          String author, String editorial, Category... categories) {
        super(id, price, name, description, photo, ProductType.COMIC, stock, categories);
        this.numPages = numPages;
        this.year = year;
        this.author = author;
        this.editorial = editorial;
    }

    /* ------------------------------------------------- LOS CHANGES ------------------------------------------------ */
    /**
     * It allows for an employee to change a comic's price
     * @param price the comic's new price
     */
    public void changePrice(double price) {
        super.changePrice(price);
    }

    /**
     * It allows for an employee to change a comic's name
     * @param newName the comic's new name
     */
    public void changeName(String newName) {
        super.changeName(newName);
    }

    /**
     * It allows for an employee to change a comic's description
     * @param newDescription the comic's new description
     */
    public void changeDescription(String newDescription) {
        super.changeDescription(newDescription);
    }

    /**
     * It allows for an employee to change a comic's photo's path
     * @param newPhoto the comic's new photo
     */
    public void changePhoto(String newPhoto) {
        super.changePhoto(newPhoto);
    }

    /**
     * It allows for an employee to change a comic's comic's type
     * @param newType the comic's new product type
     */
    public void changeType(ProductType newType) {
        super.changeType(newType);
    }

    /**
     * It allows for an employee to change the comic's stock as well as blocking or unblocking stock
     * @param newStock the comic's new stock
     */
    public void changeStock(int newStock) {
        super.changeStock(newStock);
    }

    // DUE: Change -> categories

    // DUE: Change -> numPages
    // DUE: Change -> year
    // DUE: Change -> author
    // DUE: Change -> editorial

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */
    /**
     * It returns the comic's id
     * @return the comic's id
     */
    public int getId() {
        return super.getId();
    }

    /**
     * It returns the comic's price
     * @return the comic's price
     */
    public double getPrice() {
        return super.getPrice();
    }

    /**
     * It returns the comic's description
     * @return the comic's description
     */
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * It returns the comic's photo's path
     * @return the comic's photo
     */
    public String getPhoto() {
        return super.getPhoto();
    }

    /**
     * It returns the comic's product type
     * @return the comic's product type
     */
    public ProductType getType() {
        return super.getType();
    }

    /**
     * It returns the comic's categories
     * @return the comic's categories
     */
    public Category[] getCategories() {
        return super.getCategories();
    }

    public int getNumPages() {
        return this.numPages;
    }

    public Year getYear() {
        return this.year;
    }

    public String getAuthor() {
        return this.author;
    }
    // DUE: Change -> editorial
}