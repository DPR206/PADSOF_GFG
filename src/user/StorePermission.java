package user;

import product.Category;
import product.Comic;
import store.Store;

import java.time.Year;

/**
 * Class name: StorePermission
 * <p>
 * Description: It implements the store permission of the store
 * @author Sofía C.L.
 * @version 1.3
 * @see Employee
 */
public class StorePermission {
    private Store s; //creo que debería tener acceso al store si la va a modificar

    /**
     * Constructor of the class
     * @param s the order's status
     */
    public StorePermission(Store s) {
        this.s = s;
    }

    public void addComic(double price, String name, String description, String photo, int stock, int numPages, Year year,
                         String author, String editorial, Category... categories) {
        Comic c = new Comic(price, name, description, photo, stock, numPages, year, author, editorial, categories);
        this.s.addStoreProduct(c); // DUE
    }
}