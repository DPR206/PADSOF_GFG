package user;

import discount.Discount;
import exchange.Exchange;
import notification.Notification;
import notification.NotificationHistory;
import order.Order;
import order.OrderState;
import product.*;

import java.io.IOException;
import java.time.*;
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
    private Permission[] perm;
    private NotificationHistory notificationHistory;

    /*------------------------------------------------------CONSTRUCTOR-----------------------------------------------------------------------*/

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Creates a new employee
     * @param pwd      the password of the employee
     * @param userName the employee's username
     * @param p        the employee's permission
     * @param asc      the products' order in the search
     */
    public Employee(String pwd, String userName, boolean asc, Permission... p) {
        super(UserType.EMPLOYEE, pwd, userName, asc);
        this.perm = p;
        for (Permission permission : p) {
            switch (permission) {
                case STORE:
                    this.sp = new StorePermission();
                    break;
                case EXCHANGE:
                    this.ep = new ExchangePermission();
                    break;
                case ORDER:
                    this.op = new OrderPermission(true);
                    break;
                default:
                    break;
            }
        }

        this.notificationHistory = new NotificationHistory(this);
    }

    /*---------------------------------------------------GETTERS AND SETTERS-------------------------------------------------------------*/

    /**
     * Adds and creates new comic
     * @param price       the price of the comic
     * @param name        the name of the comic
     * @param description the description of the comic
     * @param photo       the photo of the comic
     * @param stock       the comic's stock
     * @param numPages    the comic's number of pages
     * @param year        the year of publication
     * @param author      the comic's author
     * @param editorial   the comic's editorial
     * @param categories  the comic's categories
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
     * @param price       the price of the game
     * @param name        the name of the game
     * @param description the description of the game
     * @param photo       the photo of the comic
     * @param stock       the game's stock
     * @param numPlayers  the game's number of players
     * @param ageRange    the game's age range
     * @param gameStyle   the style of the game
     * @param categories  the game's categories
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
     * @param price       the price of the figurine
     * @param name        the name of the figurine
     * @param description the description of the figurine
     * @param photo       the photo of the figurine
     * @param stock       the figurine's stock
     * @param dimensions  the figurine's dimensions
     * @param brand       the figurine's brand
     * @param material    the figurine's material
     * @param categories  the figurine's categories
     * @return true if the figurine was added, false if else
     */
    public boolean addFigurine(double price, String name, String description, String photo, int stock,
                               String dimensions, String brand, String material, Category... categories) {
        if (this.sp != null) {
            this.sp.addFigurine(price, name, description, photo, stock, dimensions, brand, material, categories);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }

    /**
     * Adds and creates new products from a file
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
     * @param e        whether the exchange has been done or not
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
     * @param valuation         the valuation(price)
     * @param status            the conservation status
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
     * @param valuation         the valuation(price)
     * @param status            the conservation status
     * @param valuationDate     the date of valuation
     * @return true if the product could be evaluated, false if else
     */
    public boolean valuate(SecondHandProduct secondHandProduct, double valuation, ConservationStatus status,
                           LocalDate valuationDate) {
        if (this.ep != null) {
            ep.valuate(secondHandProduct, valuation, status, valuationDate);
            return true;
        }
        System.err.println("You have no permission to do that...");
        return false;
    }

    /**
     * Gives an order a status and manages it
     * @param o      the order to manage
     * @param status the order's status
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
    public List<StoreProduct> searchStoreProduct() {
        return this.getSearcher().searchStoreProducts();
    }

    /*---------------------------------------------------METHODS--------------------------------------------------------------------------------*/

    /**
     * Searches for the store products based on the filters and the categories
     * @param c, the array of categories
     * @return the results in a list of store products
     */
    public List<StoreProduct> searchStoreProductByCategory(Category... c) {
        return this.getSearcher().searchByCategory(c);
    }

    /**
     * Searches for the pack based on the id
     * @param id, id of the pack
     * @return the pack with said id
     */
    public Pack searchPackByID(int id) {
        return this.getSearcher().searchPackByID(id);
    }

    /**
     * Searches for the employee based on the id
     * @param id, id of the employee
     * @return the employee with said id
     */
    public Employee searchEmployeeByID(int id) {
        return this.getSearcher().searchEmployeeByID(id);
    }

    /**
     * Searches for the order based on the id
     * @param id, id of the order
     * @return the order with said id
     */
    public Order searchOrderByID(int id) {
        return this.getSearcher().searchOrderByID(id);
    }

    /**
     * Searches for the exchange based on the id
     * @param id, id of the exchange
     * @return the exchange with said id
     */
    public Exchange searchExchangeByID(int id) {
        return this.getSearcher().searchExchangeByID(id);
    }

    /**
     * The notifications of the employee
     * @return a list with all the notifications ordered by most recent
     */
    public List<Notification> browseNotifications() {
        List<Notification> notifications = new ArrayList<>();
        for (Notification n : this.notificationHistory.getNotificationsSorted()) {
            notifications.add(n);
        }
        return notifications;
    }

    /**
     * Adds a product to the pack
     * @param p,  the pack to search
     * @param sp, the store product to add
     *
     */
    public void PackAddProduct(Pack p, StoreProduct sp) {
        if (this.sp != null) {
            this.sp.PackAddProduct(p, sp);
        }
    }

    /**
     * Adds a list of products to the pack
     * @param p,   the pack to search
     * @param lsp, the list of store product to add
     *
     */
    public void PackAddListProducts(Pack p, ArrayList<StoreProduct> lsp) {
        if (this.sp != null) {
            this.sp.PackAddListProducts(p, lsp);
        }
    }

    /**
     * Deletes a product from a pack
     * @param p  the pack to delete from
     * @param sp the store product to delete
     */
    public void DeleteProductFromPack(Pack p, StoreProduct sp) {
        if (this.sp != null) {
            this.sp.DeleteProductFromPack(p, sp);
        }
    }

    /**
     * Deletes a list of store products from the pack
     * @param p,  the pack to search
     * @param sp, the list of store products to delete
     *
     */
    public void DeleteListOfProductFromPack(Pack p, ArrayList<StoreProduct> sp) {
        if (this.sp != null) {
            this.sp.DeleteListOfProductFromPack(p, sp);
        }
    }

    /**
     * Adds categories to a product.
     * @param sp         the product to update
     * @param categories the categories to add
     */
    public void addCategories(StoreProduct sp, Category... categories) {
        if (this.sp != null) {
            this.sp.addCategories(sp, categories);
        }
    }

    /**
     * Removes categories from a product.
     * @param sp         the product to update
     * @param categories the categories to remove
     */
    public void removeCategories(StoreProduct sp, Category... categories) {
        if (this.sp != null) {
            this.sp.removeCategories(sp, categories);
        }
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

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
     * Obtains the notification history
     * @return the notificationHistory
     */
    public NotificationHistory getNotificationHistory() {
        return notificationHistory;
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
     * Obtains the employee's permission
     * @return the permission
     */
    public Permission[] getPerm() {
        return perm;
    }

    /**
     * Sets the employee's permission
     * @param newPerm the permission to set
     */
    public void setPerm(Permission... newPerm) {
        this.perm = newPerm;
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
     * Sets the categories of a product.
     * @param sp         the product to update
     * @param categories the categories to assign
     */
    public void setCategories(StoreProduct sp, HashMap<String, Category> categories) {
        this.sp.setCategories(sp, categories);
    }

    /**
     * Sets the description of a product.
     * @param sp   the product to update
     * @param desc the new description
     */
    public void setDescription(StoreProduct sp, String desc) {
        if (this.sp != null) {
            this.sp.setDescription(sp, desc);
        }
    }

    /**
     * Sets the discount of a product.
     * @param sp   the product to update
     * @param disc the discount to apply
     */
    public void setDiscount(StoreProduct sp, Discount disc) {
        this.sp.setDiscount(sp, disc);
    }

    /**
     * Sets the name of a product.
     * @param sp   the product to update
     * @param name the new name
     */
    public void setName(StoreProduct sp, String name) {
        if (this.sp != null) {
            this.sp.setName(sp, name);
        }
    }

    /**
     * Changes the discount of a pack
     * @param p,        the pack to search
     * @param discount, new discount of the pack
     *
     */
    public void setPackDiscount(Pack p, Discount discount) {
        if (this.sp != null) {
            this.sp.setPackDiscount(p, discount);
        }
    }

    /**
     * Changes the price of a pack
     * @param p,     the pack to search
     * @param price, new price of the pack
     *
     */
    public void setPackPrice(Pack p, double price) {
        if (this.sp != null) {
            this.sp.setPackPrice(p, price);
        }
    }

    /**
     * Sets the list of products of the pack
     * @param p,  the pack to search
     * @param sp, the list of store products to add
     *
     */
    public void setPackProducts(Pack p, ArrayList<StoreProduct> sp) {
        if (this.sp != null) {
            this.sp.setPackProducts(p, sp);
        }
    }

    /**
     * Sets the photo of a product.
     * @param sp    the product to update
     * @param photo the photo reference
     */
    public void setPhoto(StoreProduct sp, String photo) {
        if (this.sp != null) {
            this.sp.setPhoto(sp, photo);
        }
    }

    /**
     * Sets the price of a product.
     * @param sp    the product to update
     * @param price the new price
     */
    public void setPrice(StoreProduct sp, double price) {
        if (this.sp != null) {
            this.sp.setPrice(sp, price);
        }
    }

    /**
     * Sets the total sales of a product.
     * @param sp    the product to update
     * @param sales the number of sales
     */
    public void setSales(StoreProduct sp, int sales) {
        if (this.sp != null) {
            this.sp.setSales(sp, sales);
        }
    }

    /**
     * Sets the monthly sales of a product.
     * @param sp    the product to update
     * @param sales the sales by month
     */
    public void setSalesByMonth(StoreProduct sp, HashMap<Month, Integer> sales) {
        if (this.sp != null) {
            this.sp.setSalesByMonth(sp, sales);
        }
    }

    /**
     * Sets the stock of a product.
     * @param sp    the product to update
     * @param stock the available stock
     */
    public void setStock(StoreProduct sp, int stock) {
        if (this.sp != null) {
            this.sp.setStock(sp, stock);
        }
    }

    /**
     * Sets the type of a product.
     * @param sp the product to update
     * @param pt the product type
     */
    public void setType(StoreProduct sp, ProductType pt) {
        if (this.sp != null) {
            this.sp.setType(sp, pt);
        }
    }

}