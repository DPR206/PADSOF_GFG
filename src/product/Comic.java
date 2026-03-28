package product;

import java.time.Year;

/**
 * Class name: Comic
 * <p>
 * Description: It implements the comics
 * @author Ana O.R.
 * @version 1.5
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
        /* TYPE(C/G/F);ID;NAME;DESCRIPTION;PRICE;STOCK;CATEGORIES;PAGES;AUTHOR;EDITORIAL;YEAR;PLAYERS;AGE;
        STYLE(Cards/Dice/GameBoard/Miniature);BRAND;MATERIAL;DIMENSION */
        return ProductType.COMIC.getSymbol() + ";" + this.getId() + ";" + this.getName() + ";" + this.getDescription() +
               ";" + this.getPrice() + ";" + this.getStock() + ";" + this.getStock() + ";" + this.numPages + ";" +
               this.author + ";" + this.editorial + ";" + this.year + ";" /*jugadores*/ + ";" /*edad*/ + ";"
                /*estilo*/ + ";" /*marca*/ + ";" /*material*/ + ";" /*dimension*/;
    }
}