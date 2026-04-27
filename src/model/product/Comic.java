package model.product;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;

/**
 * It implements the comic store product type
 * @author Ana O.R.
 * @version 1.7
 * @see StoreProduct
 */
public class Comic extends StoreProduct implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */
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
     * @param assignedId                 the comic's id
     * @param assignedPrice              the comic's price
     * @param assignedName               the comic's name
     * @param assignedDescription        the comic's description
     * @param assignedPhoto              the comic's photo's path
     * @param assignedAveragePunctuation the comic's average punctuation
     * @param assignedAddedDate          the comic's added date
     * @param assignedStock              the comic's stock
     * @param assignedNumPages           the comic's number of pages
     * @param assignedYear               the comic's publishing year
     * @param assignedAuthor             the comic's author
     * @param assignedEditorial          the comic's editorial
     * @param assignedCategories         the comic's categories
     */
    public Comic(String assignedId, double assignedPrice, String assignedName, String assignedDescription,
                 String assignedPhoto, double assignedAveragePunctuation, LocalDate assignedAddedDate,
                 int assignedStock, int assignedNumPages, Year assignedYear, String assignedAuthor,
                 String assignedEditorial, Category... assignedCategories) {
        super(assignedId, assignedPrice, assignedName, assignedDescription, assignedPhoto, assignedAveragePunctuation,
                assignedAddedDate, ProductType.COMIC, assignedStock, assignedCategories);
        this.numPages = assignedNumPages;
        this.year = assignedYear;
        this.author = assignedAuthor;
        this.editorial = assignedEditorial;
    }

    /**
     * The comic's constructor
     * @param assignedPrice       the comic's price
     * @param assignedName        the comic's name
     * @param assignedDescription the comic's description
     * @param assignedPhoto       the comic's photo's path
     * @param assignedStock       the comic's stock
     * @param assignedNumPages    the comic's number of pages
     * @param assignedYear        the comic's publishing year
     * @param assignedAuthor      the comic's author
     * @param assignedEditorial   the comic's editorial
     * @param assignedCategories  the comic's categories
     */
    public Comic(double assignedPrice, String assignedName, String assignedDescription, String assignedPhoto,
                 int assignedStock, int assignedNumPages, Year assignedYear, String assignedAuthor,
                 String assignedEditorial, Category... assignedCategories) {
        super(assignedPrice, assignedName, assignedDescription, assignedPhoto, ProductType.COMIC, assignedStock,
                assignedCategories);
        this.numPages = assignedNumPages;
        this.year = assignedYear;
        this.author = assignedAuthor;
        this.editorial = assignedEditorial;
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
        System.out.println("Publishing year: " + this.year + "\n");
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
        System.out.println("Publishing year: " + this.year + "\n");
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