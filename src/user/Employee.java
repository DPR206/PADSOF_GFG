package user;

/**
 * Class name: Employee
 * <p>
 * Description: It implements the employee
 * @author Sofía C.L.
 * @version 1.0
 * @see Employee
 */

public class Employee extends User{

    private ExchangePermission ep;
    private OrderPermission op;
    private StorePermission sp;
    private Permission perm;
    
/*------------------------------------------------------CONSTRUCTOR-----------------------------------------------------------------------*/

    /**
	 * Creates a new employee
	 * 
	 * @param psw
	 * @param userName
	 * @param actualID
	 * @param p
	 */
    public Employee(String pwd, String userName, int actualID, Permission p){
        super(pwd, userName, actualID);
        this.perm = p;

        if(p.getMeaning() == "store"){sp = new StorePermission();}
        else if(p.getMeaning() == "exchange") {sp = new ExchangePermission();}
        else{this.sp = new OrderPermission();}
    }

/*---------------------------------------------------METHODS--------------------------------------------------------------------------------*/
    
    /**
	 * Adds and creates new comic
	 * 
	 * @param price
	 * @param name
	 * @param description
	 * @param photo
     * @param stock
     * @param numPages
     * @param year
     * @param author
     * @param categories
     * @param editorial
     * 
	 */
    public boolean addComic(double price, String name, String description, String photo, int stock, int numPages, int year, String author, Category... categories, String editorial) {
        if(this.sp != null){
            this.sp.addComic(price, name, description, photo, stock, numPages, year, author, categories, editorial);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    } 

    /**
	 * Adds and creates new game
	 * 
	 * @param price
	 * @param name
	 * @param description
	 * @param photo
     * @param stock
     * @param numPlayers
     * @param ageRange
     * @param categories
     * 
	 */
    public boolean addGame(double price, String name, String description, String photo, int stock, int numPlayers, String ageRange, Category... categories) {
        if(this.sp != null){
            this.sp.addGame(price, name, description, photo, stock, numPlayers, ageRange, categories);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }
    
    /**
	 * Adds and creates new figurine
	 * 
	 * @param price
	 * @param name
	 * @param description
	 * @param photo
     * @param stock
     * @param height
     * @param width
     * @param depth
     * @param brand
     * @param material
     * @param categories
     * 
	 */
    public boolean addFigurine(double price, String name, String description, String photo, int stock, double height, double width, double depth, String brand, String material, Category... categories) {
        if(this.sp != null){
            this.sp.addFigurine(price, name, description, photo, stock, height, width, depth, brand, material, categories);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }
    
    /**
	 * Manages exchange
	 * 
	 * @param exchanged
	 * @param e
     * 
	 */
    public void manageExchange(Exchange exchange, boolean e){
        if(this.ep != null){
            ep.manageExchange(exchange, e);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }

    /**
	 * Gives a secondhand product a value
	 * 
	 * @param secondHandProduct
	 * @param valuation
     * 
	 */
    public void valuate(SecondHandProduct secondHandProduct, double valuation) {
        if(this.ep != null){
            ep.valuate(exchange, exchanged);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }

    /**
	 * Gives an order a status and manages it
	 * 
	 * @param exchanged
	 * @param e
     * 
	 */
    public void manageOrder(Order o, OrderState status) {
        if(this.op != null){
            op.valuate(o, status);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false
    }
}