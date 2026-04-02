package user;

import exchange.Exchange;
import order.Order;
import order.OrderState;
import product.*;
import search.*;

import java.time.Year;
import java.util.*;

/**
 * It implements the employee
 * @author Sofía C.L.
 * @version 1.0
 * @see Employee
 */

public class Employee extends User {

    private ExchangePermission ep;
    private OrderPermission op;
    private StorePermission sp;
    private Permission perm;

    /*------------------------------------------------------CONSTRUCTOR-----------------------------------------------------------------------*/

    /**
     * Creates a new employee
     * @param pwd
     * @param userName
     * @param p
     */
    public Employee(String pwd, String userName, Permission p, boolean asc) {
        super(UserType.EMPLOYEE, pwd, userName, asc);
        this.perm = p;

        if (p.getMeaning() == "store") {
            this.sp = new StorePermission();
            this.getSearcher().setTypes(SearchType.S_STORE);

        } else if (p.getMeaning() == "exchange") {
            this.ep = new ExchangePermission();
            if(this.sp != null) this.getSearcher().setTypes(SearchType.S_STORE, SearchType.S_EXCHANGE);
            else this.getSearcher().setTypes(SearchType.S_EXCHANGE);
        } else {
            this.op = new OrderPermission();
            if(this.sp != null && this.ep != null) this.getSearcher().setTypes(SearchType.S_STORE, SearchType.S_EXCHANGE, SearchType.S_ORDER);
            else if(this.sp != null && this.ep == null) this.getSearcher().setTypes(SearchType.S_STORE, SearchType.S_ORDER);
            else if(this.sp == null && this.ep != null) this.getSearcher().setTypes(SearchType.S_EXCHANGE, SearchType.S_ORDER);
            else this.getSearcher().setTypes(SearchType.S_ORDER);
        }
    }
    /*---------------------------------------------------METHODS--------------------------------------------------------------------------------*/

    /**
     * Adds and creates new comic
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
     * @param price
     * @param name
     * @param description
     * @param photo
     * @param stock
     * @param dimensions
     * @param brand
     * @param material
     * @param categories
     *
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
     * Manages exchange
     * @param exchange
     * @param e
     *
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
     * @param secondHandProduct
     * @param valuation
     *
     */
    public boolean valuate(SecondHandProduct secondHandProduct, double valuation) {
        if (this.ep != null) {
            ep.valuate(secondHandProduct, valuation);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }

    /**
     * Gives an order a status and manages it
     * @param o
     * @param status
     *
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
     *
     */
     public List<StoreProduct> searchStoreProduct(){
        return this.getSearcher().searchStoreProducts();
    }

    /**
     * Searches for the store products based on the filters and the categories
     *
     * @param c, the array of categories
     *
     */
    public List<StoreProduct> searchStoreProductByCategory(Category... c){
        return this.getSearcher().searchByCategory(c);
    }

    /**
     * Searches for the pack based on the id
     *
     * @param id, id of the pack
     *
     */
    public Pack searchPackByID(int id){
        return this.getSearcher().searchPackByID(id);
    }

    public Employee searchEmployeeByID(int id){
        return this.getSearcher().searchEmployeeByID(id);
    }

    /**
     * Searches for the employee based on the id
     *
     * @param id, id of the employee
     *
     */
    public Order searchOrderByID(int id){
        return this.getSearcher().searchOrderByID(id);
    }

    /**
     * Searches for the exchange based on the id
     *
     * @param id, id of the exchange
     *
     */
    public Exchange searchExchangeByID(int id){
        return this.getSearcher().searchExchangeByID(id);
    }
}