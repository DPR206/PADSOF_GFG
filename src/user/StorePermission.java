package user;
import product.*;

public class StorePermission{

    /**
 * Class name: StorePermission
 * <p>
 * Description: It implements the store permission of the store
 * @author Sofía C.L.
 * @version 1.3
 * @see Employee
 */
    private Store s; //creo que debería tener acceso al store si la va a modificar
    /**
     * Constructor of the class
     * @param o      the desired order
     * @param status the order's status
     */
    public StorePermission(Store s){
        this.s = s;
    }

    public void addComic(double price, String name, String description, String photo, int stock, int numPages, int year, String author, Category... categories, String editorial){
        Comic c = new Comic(price, name, description, photo, stock, numPages, year, author, categories, editorial);
        this.s.addProduct(c);
    }
}