package user;

/**
 * Class name: Employee
 * <p>
 * Description: It implements the registered client
 * @author Sofía C.L.
 * @version 1.0
 * @see Employee
 */

public class Employee extends User{

    private ExchangePermission ep;
    private OrderPermission op;
    private StorePermission sp;
    private Permission perm;
    
    public Employee(String pwd, String userName, int actualID, Permission p){
        super(pwd, userName, actualID);
        this.perm = p;

        if(p.getMeaning() == "store"){sp = new StorePermission();}
        else if(p.getMeaning() == "exchange") {sp = new ExchangePermission();}
        else{this.sp = new OrderPermission();}
    }

    public boolean addComic(double price, String name, String description, String photo, int stock, int numPages, int year, String author, Category... categories, String editorial) {
        if(this.sp != null){
            this.sp.addComic(price, name, description, photo, stock, numPages, year, author, categories, editorial);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    } 

    public boolean addGame(double price, String name, String description, String photo, int stock, int numPlayers, String ageRange, Category... categories) {
        if(this.sp != null){
            this.sp.addGame(price, name, description, photo, stock, numPlayers, ageRange, categories);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }
    
    public boolean addFigurine(double price, String name, String description, String photo, int stock, double height, double width, double depth, String brand, String material, Category... categories) {
        if(this.sp != null){
            this.sp.addFigurine(price, name, description, photo, stock, height, width, depth, brand, material, categories);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }
    
    public void manageExchange(Exchange exchange, boolean exchanged){
        if(this.ep != null){
            ep.manageExchange(exchange, exchanged);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }

    public void valuate(SecondHandProduct secondHandProduct, double valuation) {
        if(this.ep != null){
            ep.valuate(exchange, exchanged);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }

    public void manageOrder(Order o, OrderState status) {
        if(this.op != null){
            op.valuate(o, status);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false
    }
}