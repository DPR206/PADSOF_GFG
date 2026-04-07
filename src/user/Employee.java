package user;

import exchange.Exchange;
import order.Order;
import order.OrderState;
import product.*;
import search.*;
import notification.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

/**
 * It implements the employee
 * @author Sofía C.L. and Duna P.R.
 * @version 1.0
 * @see Employee
 */

public class Employee extends User {

    private ExchangePermission ep;
    private OrderPermission op;
    private StorePermission sp;
    private Permission perm;
    private NotificationHistory notificationHistory;

    /*------------------------------------------------------CONSTRUCTOR-----------------------------------------------------------------------*/

    /**
     * Creates a new employee
     * @param pwd the password of the employee
     * @param userName the employee's username
     * @param p the employee's permission
     * @param asc the products' order in the search
     */
    public Employee(String pwd, String userName, Permission p, boolean asc) {
        super(UserType.EMPLOYEE, pwd, userName, asc);
        this.perm = p;

        if (p.getMeaning().contentEquals("store")) {
            this.sp = new StorePermission();
            this.getSearcher().setTypes(SearchType.S_STORE);

        } else if (p.getMeaning().contentEquals("exchange")) {
            this.ep = new ExchangePermission();
            if(this.sp != null) this.getSearcher().setTypes(SearchType.S_STORE, SearchType.S_EXCHANGE);
            else this.getSearcher().setTypes(SearchType.S_EXCHANGE);
        } else {
            this.op = new OrderPermission(true);
            if(this.sp != null && this.ep != null) this.getSearcher().setTypes(SearchType.S_STORE, SearchType.S_EXCHANGE, SearchType.S_ORDER);
            else if(this.sp != null /*&& this.ep == null*/) this.getSearcher().setTypes(SearchType.S_STORE,
                    SearchType.S_ORDER);
            else if(/*this.sp == null &&*/ this.ep != null) this.getSearcher().setTypes(SearchType.S_EXCHANGE,
                    SearchType.S_ORDER);
            else this.getSearcher().setTypes(SearchType.S_ORDER);
        }

        this.notificationHistory = new NotificationHistory(this);
    }

/*---------------------------------------------------GETTERS AND SETTERS-------------------------------------------------------------*/

    /**
     * Obtains the employee's exchange permission
   	 * @return the exchange permission
   	 */
   	public ExchangePermission getEp() {
   		return ep;
   	}

   	/**
   	 * Sets the exchange permission
   	 * @param newEp the exchange permission to set
   	 */
   	public void setEp(ExchangePermission newEp) {
   		this.ep = newEp;
   	}

   	/**
   	 * Obtains the employee's order permission
   	 * @return the order permission
   	 */
   	public OrderPermission getOp() {
   		return op;
   	}

   	/**
   	 * Sets the order permission
   	 * @param newOp the order permission to set
   	 */
   	public void setOp(OrderPermission newOp) {
   		this.op = newOp;
   	}

   	/**
   	 * Obtains the employee's store permission
   	 * @return the store permission
   	 */
   	public StorePermission getSp() {
   		return sp;
   	}

   	/**
   	 * Sets the order permission
   	 * @param newSp the store permission to set
   	 */
   	public void setSp(StorePermission newSp) {
   		this.sp = newSp;
   	}

   	/**
   	 * Obtains the employee's permission
   	 * @return the permission
   	 */
   	public Permission getPerm() {
   		return perm;
   	}

   	/**
   	 * Sets the employee's permission
   	 * @param newPerm the permission to set
   	 */
   	public void setPerm(Permission newPerm) {
   		this.perm = newPerm;
   	}

   	/**
   	 * Obtains the notification history
   	 * @return the notificationHistory
   	 */
   	public NotificationHistory getNotificationHistory() {
   		return notificationHistory;
   	}

/*---------------------------------------------------METHODS--------------------------------------------------------------------------------*/

    /**
     * Adds and creates new comic
     * @param price the price of the comic
     * @param name the name of the comic
     * @param description the description of the comic
     * @param photo the photo of the comic
     * @param stock the comic's stock
     * @param numPages the comic's number of pages
     * @param year the year of publication
     * @param author the comic's author
     * @param editorial the comic's editorial
     * @param categories the comic's categories
     *
     * @return true if the comic was added, false if else
     */
    public boolean addComic(double price, String name, String description, String photo, int stock, int numPages,
                            Year year, String author, String editorial, Category... categories) {
        if (this.sp != null) {
            this.sp.addComic(price, name, description, photo, stock, numPages, year, author, editorial, categories);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }

	/**
     * Adds and creates new game
     * @param price the price of the game
     * @param name the name of the game
     * @param description the description of the game
     * @param photo the photo of the comic
     * @param stock the game's stock
     * @param numPlayers the game's number of players
     * @param ageRange the game's age range
     * @param gameStyle the style of the game
     * @param categories the game's categories
     *
     * @return true if the game was added, false if else
     */
    public boolean addGame(double price, String name, String description, String photo, int stock, int numPlayers,
                           String ageRange, GameStyle gameStyle, Category... categories) {
        if (this.sp != null) {
            this.sp.addGame(price, name, description, photo, stock, numPlayers, ageRange, gameStyle, categories);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }

    /**
     * Adds and creates new figurine
     * @param price the price of the figurine
     * @param name the name of the figurine
     * @param description  the description of the figurine
     * @param photo the photo of the figurine
     * @param stock the figurine's stock
     * @param dimensions the figurine's dimensions
     * @param brand the figurine's brand
     * @param material the figurine's material
     * @param categories the figurine's categories
     *
     * @return true if the figurine was added, false if else
     */
    public boolean addFigurine(double price, String name, String description, String photo, int stock, String dimensions, String brand, String material, Category... categories) {
        if (this.sp != null) {
            this.sp.addFigurine(price, name, description, photo, stock, dimensions, brand, material, categories);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }
    /**
     * Adds and creates new products from a file
     *
     * @param filename the name of the file
     * @return true if the product's were added successfully, false if else
     */
    public boolean addProductFromFile(String filename) {
    	try {
			return this.sp.addProductByFile(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return false;
    }

    /**
     * Manages exchange
     * @param exchange the exchange to manage
     * @param e whether the exchange has been done or not
     *
     * @return true if the order could be managed, false if else
     */
    public boolean manageExchange(Exchange exchange, boolean e) {
        if (this.ep != null) {
            ep.manageExchange(exchange, e);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }

    /**
     * Gives a secondhand product a value
     * @param secondHandProduct the second-hand product to value
     * @param valuation the valuation(price)
     * @param status the conservation status
     *
     * @return true if the product could be evaluated, false if else
     */
    public boolean valuate(SecondHandProduct secondHandProduct, double valuation, ConservationStatus status) {
        if (this.ep != null) {
            ep.valuate(secondHandProduct, valuation, status);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }

    /**
     * Gives a secondhand product a value
     * @param secondHandProduct the second-hand product to value
     * @param valuation the valuation(price)
     * @param status the conservation status
     * @param valuationDate the date of valuation
     * 
     * @return true if the product could be evaluated, false if else
     */
    public boolean valuate(SecondHandProduct secondHandProduct, double valuation, ConservationStatus status, LocalDate valuationDate) {
        if (this.ep != null) {
            ep.valuate(secondHandProduct, valuation, status, valuationDate);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }

    /**
     * Gives an order a status and manages it
     * @param o the order to manage
     * @param status the order's status
     *
     * @return true if the order could be managed, false if else
     */
    public boolean manageOrder(Order o, OrderState status) {
        if (this.op != null) {
            op.manageOrder(o, status);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }

    /**
     * Searches for the store products based on the filters
     * @return the results in a list of store products
     */
     public List<StoreProduct> searchStoreProduct(){
        return this.getSearcher().searchStoreProducts();
    }

    /**
     * Searches for the store products based on the filters and the categories
     *
     * @param c, the array of categories
     * @return the results in a list of store products
     */
    public List<StoreProduct> searchStoreProductByCategory(Category... c){
        return this.getSearcher().searchByCategory(c);
    }

    /**
     * Searches for the pack based on the id
     *
     * @param id, id of the pack
     * @return the pack with said id
     */
    public Pack searchPackByID(int id){
        return this.getSearcher().searchPackByID(id);
    }


    /**
     * Searches for the employee based on the id
     *
     * @param id, id of the employee
     * @return the employee with said id
     */
    public Employee searchEmployeeByID(int id){
        return this.getSearcher().searchEmployeeByID(id);
    }

    /**
     * Searches for the order based on the id
     *
     * @param id, id of the order
     * @return the order with said id
     */
    public Order searchOrderByID(int id){
        return this.getSearcher().searchOrderByID(id);
    }

    /**
     * Searches for the exchange based on the id
     *
     * @param id, id of the exchange
     * @return the exchange with said id
     */
    public Exchange searchExchangeByID(int id){
        return this.getSearcher().searchExchangeByID(id);
    }
    
    /**
     * The notifications of the employee
     *
     * @return a list with all the notifications ordered by most recent
     */
    public List<Notification> browseNotifications(){
    	List<Notification> notifications = new ArrayList<>();
    	for(Notification n : this.notificationHistory.getNotificationsSorted())
    		notifications.add(n);
    	return notifications;
    }
}