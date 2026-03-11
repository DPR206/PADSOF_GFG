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

    // DUE: Change -> name
    // DUE: Change -> description
    // DUE: Change -> photo
    // DUE: Change -> price
    // DUE: Change -> type
    // DUE: Change -> stock
    // DUE: Change -> categories
    // DUE: Change -> stock
    // DUE: Change -> numPages
    // DUE: Change -> year
    // DUE: Change -> author
    // DUE: Change -> editorial
}
