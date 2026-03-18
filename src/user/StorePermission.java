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

    public void addGame(double price, String name, String description, String photo, int stock, int numPlayers, String ageRange, Category... categories){
        Game g = new Game(price, name, description, photo, stock, numPlayers, ageRange, categories);
        s.addProduct(g);
    }
    public void addFigurine(double price, String name, String description, String photo, int stock, double height, double width, double depth, String brand, String material, Category... category){
        Figurine f = createFigurine(price, name, description, photo, stock, height, width, depth, brand, material, category);
        s.addProduct(f);
    }
    public bool addProductByFile(String fileName){
        //rellenar xd
    }
    public void addPack(double price, ArrayList<StoreProduct> products, LocalDate date) {
        Pack p = new Pack(price, products, date);
        s.addProduct(p);
    }
}