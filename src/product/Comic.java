package product;

import java.time.LocalDate;
import java.time.Year;

/**
 * It implements the comic store product type
 * @author Ana O.R.
 * @version 1.7
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

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * A comic's general constructor
     * @param id                 the comic's id
     * @param price              the comic's price
     * @param name               the comic's name
     * @param description        the comic's description
     * @param photo              the comic's photo's path
     * @param averagePunctuation the comic's average punctuation
     * @param addedDate          the comic's added date
     * @param stock              the comic's stock
     * @param numPages           the comic's number of pages
     * @param year               the comic's publishing year
     * @param author             the comic's author
     * @param editorial          the comic's editorial
     * @param categories         the comic's categories
     */
    public Comic(String id, double price, String name, String description, String photo, double averagePunctuation,
                 LocalDate addedDate, int stock, int numPages, Year year, String author, String editorial,
                 Category... categories) {
        super(id, price, name, description, photo, averagePunctuation, addedDate, ProductType.COMIC, stock, categories);
        this.numPages = numPages;
        this.year = year;
        this.author = author;
        this.editorial = editorial;
    }

    /**
     * The comic's constructor
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
    public Comic(double price, String name, String description, String photo, int stock, int numPages, Year year,
                 String author, String editorial, Category... categories) {
        super(price, name, description, photo, ProductType.COMIC, stock, categories);
        this.numPages = numPages;
        this.year = year;
        this.author = author;
        this.editorial = editorial;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It prints the product's info when seen individually
     */
    @Override
    public void bigPrintInfo() {
        /* super;<NUM_PAGES;AUTHOR;EDITORIAL;YEAR>;NUM_PLAYERS;AGE_RANGE;GAME_STYLE;BRAND;MATERIAL;DIMENSION */
        super.bigPrintInfo();
        System.out.println("Number of pages: " + this.numPages);
        System.out.println("Author: " + this.author);
        System.out.println("Editorial: " + this.editorial);
        System.out.println("Publishing year: " + this.year);
    }

    /**
     * It prints the product's info when managed
     */
    @Override
    public void printAllInfo() {
        super.printAllInfo();
        System.out.println("Number of pages: " + this.numPages);
        System.out.println("Author: " + this.author);
        System.out.println("Editorial: " + this.editorial);
        System.out.println("Publishing year: " + this.year);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It returns the comic's author
     * @return the comic's author
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * It allows for an employee to change the comic's author
     * @param newAuthor the comic's author
     */
    public void setAuthor(String newAuthor) {
        this.author = newAuthor;
    }

    /**
     * It returns the comic's editorial
     * @return the comic's editorial
     */
    public String getEditorial() {
        return this.editorial;
    }

    /**
     * It allows for an employee to change the comic's editorial
     * @param newEditorial the comic's editorial
     */
    public void setEditorial(String newEditorial) {
        this.editorial = newEditorial;
    }

    /**
     * It returns the comic's number of pages
     * @return the comic's number of pages
     */
    public int getNumPages() {
        return this.numPages;
    }

    /**
     * It allows for an employee to change the comic's number of pages
     * @param newNumPages the comic's number of pages
     */
    public void setNumPages(int newNumPages) {
        this.numPages = newNumPages;
    }

    /**
     * It returns the comic's publishing year
     * @return the comic's publishing year
     */
    public Year getYear() {
        return this.year;
    }

    /**
     * It allows for an employee to change the comic's publishing year
     * @param newYear the comic's publishing year
     */
    public void setYear(Year newYear) {
        this.year = newYear;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Written information of a product
     * @return String, information of a product
     */
    @Override
    public String toString() {
        /* super;<NUM_PAGES;AUTHOR;EDITORIAL;YEAR>;NUM_PLAYERS;AGE_RANGE;GAME_STYLE;BRAND;MATERIAL;DIMENSION */
        return super.toString() + ";" + this.numPages + ";" + this.author + ";" + this.editorial + ";" + this.year +
               ";" /*num_players*/ + ";" /*ager_range*/ + ";" /*game_style*/ + ";" /*brand*/ + ";" /*material*/ +
               ";" /*dimension*/;
    }
}