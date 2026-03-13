package product;

import order.Discount;
import store.Store;

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

    /**
     * It allows employees to add comics to the store
     * @param store       the store
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
     * @return the new comic
     */
    public Comic createComic(Store store, double price, String name, String description, String photo, int stock,
                          int numPages, Year year, String author, String editorial, Category... categories) {
        return new Comic(store.getProductId(), price, name, description, photo, stock, numPages, year, author,
                editorial, categories);
    }

    /**
     * Written information of a product
     * @return String, information of a product
     */
    @Override
    public String toString() {
        return super.toString() + ", " + this.numPages + ", " + this.year + ", " + this.author + ", " + this.editorial;
    }

    /* ------------------------------------------------- LOS CHANGES ------------------------------------------------ */

    /**
     * It allows for an employee to change a comic's price
     * @param price the comic's new price
     */
    @Override
    public void changePrice(double price) {
        super.changePrice(price);
    }

    /**
     * It allows for an employee to change a comic's name
     * @param newName the comic's new name
     */
    @Override
    public void changeName(String newName) {
        super.changeName(newName);
    }

    /**
     * It allows for an employee to change a comic's description
     * @param newDescription the comic's new description
     */
    @Override
    public void changeDescription(String newDescription) {
        super.changeDescription(newDescription);
    }

    /**
     * It allows for an employee to change a comic's photo's path
     * @param newPhoto the comic's new photo
     */
    @Override
    public void changePhoto(String newPhoto) {
        super.changePhoto(newPhoto);
    }

    /**
     * It allows for an employee to change a comic's comic's type
     * @param newType the comic's new product type
     */
    @Override
    public void changeType(ProductType newType) {
        super.changeType(newType);
    }

    /**
     * It allows for an employee to change the comic's stock as well as blocking or unblocking stock
     * @param newStock the comic's new stock
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
     * It allows for an employee to change the comic's number of pages
     * @param newNumPages the comic's number of pages
     */
    public void changeNumPages(int newNumPages) {
        this.numPages = newNumPages;
    }

    /**
     * It allows for an employee to change the comic's publishing year
     * @param newYear the comic's publishing year
     */
    public void changeYear(Year newYear) {
        this.year = newYear;
    }

    /**
     * It allows for an employee to change the comic's author
     * @param newAuthor the comic's author
     */
    public void changeAuthor(String newAuthor) {
        this.author = newAuthor;
    }

    /**
     * It allows for an employee to change the comic's editorial
     * @param newEditorial the comic's editorial
     */
    public void changeEditorial(String newEditorial) {
        this.editorial = newEditorial;
    }

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */

    /**
     * It returns the comic's id
     * @return the comic's id
     */
    @Override
    public int getId() {
        return super.getId();
    }

    /**
     * It returns the comic's price
     * @return the comic's price
     */
    @Override
    public double getPrice() {
        return super.getPrice();
    }

    /**
     * It returns the comic's description
     * @return the comic's description
     */
    @Override
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * It returns the comic's photo's path
     * @return the comic's photo
     */
    @Override
    public String getPhoto() {
        return super.getPhoto();
    }

    /**
     * It returns the comic's product type
     * @return the comic's product type
     */
    @Override
    public ProductType getType() {
        return super.getType();
    }

    /**
     * It returns the comic's categories
     * @return the comic's categories
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
     * It returns the comic's number of pages
     * @return the comic's number of pages
     */
    public int getNumPages() {
        return this.numPages;
    }

    /**
     * It returns the comic's publishing year
     * @return the comic's publishing year
     */
    public Year getYear() {
        return this.year;
    }

    /**
     * It returns the comic's author
     * @return the comic's author
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * It returns the comic's editorial
     * @return the comic's editorial
     */
    public String getEditorial() {
        return this.editorial;
    }
}