package model.store;

import model.app.Pager;
import model.discount.Discount;
import model.exchange.Exchange;
import model.exchange.Offer;
import model.notification.Notification;
import model.order.Order;
import model.product.*;
import model.user.*;
import model.utilities.IdType;
import model.utilities.Utility;
import model.utilities.exceptions.*;

import java.io.*;
import java.util.*;

/**
 * It implements the store
 * @author Sofía C., Ana O.R. and Duna P.R.
 * @version 1.6
 * @see Parameter
 * @see Utility
 * @see Manager
 * @see Discount
 * @see Offer
 * @see Review
 * @see Exchange
 * @see Order
 * @see Pack
 * @see Category
 * @see StoreProduct
 * @see SecondHandProduct
 * @see User
 * @see RegisteredClient
 * @see Employee
 */
public class Store implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; /* Para el Save & Load */
    private static Store INSTANCE = null;
    /** The store's parameters */
    private final Parameter param;
    /** The class for the extra functions singIn and logIn */
    private final Utility utility;
    /** The manager of the store */
    private Manager manager;
    /** The list of discounts in the store */
    private List<Discount> discounts = new ArrayList<>();
    /** The list of all offers done in the store */
    private List<Offer> offers = new ArrayList<>();
    /** The list of all the store's reviews */
    private List<Review> reviews = new ArrayList<>();
    /** The list of all exchanges done in the store */
    private List<Exchange> exchanges = new ArrayList<>();
    /** The list of orders that have been completed */
    private List<Order> orders = new ArrayList<>();
    /** The list of packs available */
    private List<Pack> packs = new ArrayList<>();
    /** The list of categories available */
    private HashMap<String, Category> categories = new HashMap<>();
    /** The list of store products that belong to the store */
    private HashMap<String, StoreProduct> storeProducts = new HashMap<>();
    /** The list of second hand products that belong to the store */
    private HashMap<String, SecondHandProduct> secondHandProducts = new HashMap<>();
    /** The list users ordered by username */
    private Map<String, User> users = new HashMap<>();
    /** The list of registered clients ordered by username */
    private HashMap<String, RegisteredClient> registeredClients = new HashMap<>();
    /** The list of employees ordered by username */
    private HashMap<String, Employee> employees = new HashMap<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Store's constructor
     */
    private Store() {
        this.utility = new Utility();
        this.param = Parameter.getParam();
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * Gets the Instance of the Store
     * @return the store
     */
    public static Store getInstance() {
        if (Store.INSTANCE == null) {
            Store.INSTANCE = new Store();
        }
        return Store.INSTANCE;
    }

    public static void setInstance(Store instance) {
        Store.INSTANCE = instance;
    }

    /**
     * Logs in a user
     * @return the associated user
     */
    public User logIn(String userName, String pwd) {
        return utility.logIn(userName, pwd);
    }

    /**
     * Signs in a user
     * @return a new user
     */
    public User signIn(String username, String password, String dni, IdType idType)
            throws PasswordNotValid, UsernameTaken, InvalidDni {
        return utility.signIn(username, password, dni, idType);
    }

    /**
     * Adds a new store product to the store
     * @param product, the new product
     */
    public void addStoreProduct(StoreProduct product) {
        this.storeProducts.put(product.getId(), product);
    }

    /**
     * Adds a new second hand product to the store
     * @param product, the new product
     */
    public void addSecondHandProduct(SecondHandProduct product) {
        this.secondHandProducts.put(product.getId(), product);
    }

    /**
     * Deletes a new store product to the store
     * @param product, the product
     */
    public void deleteStoreProduct(StoreProduct product) {
        this.storeProducts.remove(product.getId(), product);
    }

    /**
     * Deletes a new second hand product to the store
     * @param product, the product
     */
    public void deleteSecondHandProduct(SecondHandProduct product) {
        this.secondHandProducts.remove(product.getId(), product);
    }

    /**
     * Adds a new pack
     * @param p the new pack
     */
    public void addPack(Pack p) {
        this.packs.add(p);
    }

    /**
     * Deletes a pack from the store
     * @param p the pack to remove
     */
    public void deletePack(Pack p) {
        this.packs.remove(p);
    }

    /**
     * Adds a new exchange to the store
     * @param e, the new exchange
     */
    public void addExchange(Exchange e) {
        this.exchanges.add(e);
    }

    /**
     * Adds a category to the store
     * @param c the category to add
     */
    public void addCategory(Category c) {
        this.categories.put(c.getName(), c);
    }

    /**
     * Deletes a category from the store
     * @param c the category to delete
     */
    public void deleteCategory(Category c) {
        this.categories.remove(c.name);
    }

    /**
     * Adds a discount to the store
     * @param d the discount to add
     */
    public void addDiscount(Discount d) {
        this.discounts.add(d);
    }

    /**
     * Deletes a discount from the store
     * @param d the discount to delete
     */
    public void deleteDiscount(Discount d) {
        this.discounts.remove(d);
    }

    /**
     * Adds an order to the store
     * @param o the order to add
     */
    public void addOrder(Order o) {
        this.orders.add(o);
    }

    /**
     * Adds an offer to the store
     * @param o the offer to add
     */
    public void addOffer(Offer o) {
        this.offers.add(o);
    }

    /**
     * Deletes an offer from the store
     * @param o the offer to delete
     */
    public void deleteOffer(Offer o) {
        this.offers.remove(o);
    }

    /**
     * Adds a review to the store
     * @param r the review to add
     */
    public void addReview(Review r) {
        this.reviews.add(r);
    }

    /**
     * Sends the notifications to the clients
     * @param n the notification to send (if interested)
     */
    public void sendNotificationClients(Notification n) {
        ArrayList<RegisteredClient> clients = new ArrayList<>(this.getRegisteredClients().values());
        for (RegisteredClient client : clients) {
            client.getNotificationHistory().addNotification(n);
        }
    }

    /**
     * Sends the notifications to the employees
     * @param n the notification to send (if interested)
     */
    public void sendNotificationEmployees(Notification n) {
        ArrayList<Employee> employees = new ArrayList<>(this.getEmployees().values());
        for (Employee employee : employees) {
            employee.getNotificationHistory().addNotification(n);
        }
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param pageNum the desired page's number
     */
    public void printRegisteredClientListPage(int pageNum) {
        Pager.getInstance().printRegisteredClientListPage(this.getRegisteredClientList(), pageNum);
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param pageNum the desired page's number
     */
    public void printEmployeeListPage(int pageNum) {
        Pager.getInstance().printEmployeeListPage(this.getEmployeeList(), pageNum);
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param pageNum the desired page's number
     */
    public void printPackListPage(int pageNum) {
        Pager.getInstance().printPackListPage(this.packs, pageNum);
    }

    /**
     * It prints a sub-list of another according to the desired page
     * @param pageNum the desired page's number
     */
    public void printDiscountListPage(int pageNum) {
        Pager.getInstance().printDiscountListPage(this.discounts, pageNum);
    }

    /**
     * It prints a sub-list of categories according to the desired page
     * @param pageNum the desired page's number
     */
    public void printCategoryListPage(int pageNum) {
        Pager.getInstance().printCategoryListPage(this.getCategoryList(), pageNum);
    }

    /**
     * It gets the employee listed as n.º clientNum in a certain page
     * @param pageNum   the desired page's number
     * @param clientNum the desired employee's n.º
     * @return the desired employee
     */
    public Employee selectEmployeeFromPage(int pageNum, int clientNum) {
        return Pager.getInstance().selectEmployeeFromPage(this.getEmployeeList(), pageNum, clientNum);
    }

    /**
     * It gets the discount listed as n.º discountNum in a certain page
     * @param pageNum     the desired page's number
     * @param discountNum the desired discount's n.º
     * @return the desired discount
     */
    public Discount selectDiscountFromPage(int pageNum, int discountNum) {
        return Pager.getInstance().selectDiscountFromPage(this.discounts, pageNum, discountNum);
    }

    /**
     * Adds a user to the storee
     * @param s the user to add
     */
    public void addUser(User s) {
        this.users.put(s.getUserName(), s);
    }

    /**
     * It allows for the store to be saved
     * @param dataFilename    the name of the file where most of the store's data will be saved
     * @param staticsFilename the name of the file where the static variables will be saved
     * @throws IOException something went wrong when reading or writing a file
     */
    public void saveStore(String dataFilename, String staticsFilename) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(".\\resources\\" + dataFilename + ".txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(Store.getInstance());

        objectOutputStream.flush();
        objectOutputStream.close();

        BufferedWriter buffer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(".\\resources\\" + staticsFilename + ".csv")));

        buffer.write(Discount.totalId + "\n");
        buffer.write(Exchange.totalId + "\n");
        buffer.write(Offer.totalId + "\n");
        buffer.write(Pack.totalId + "\n");
        buffer.write(Product.totalId + "\n");
        buffer.write(User.totalId + "\n");

        buffer.close();
    }

    /**
     * It allows for the store to be loaded
     * @param dataFilename    the name of the file where most of the store's data will be saved
     * @param staticsFilename the name of the file where the static variables will be saved
     * @throws IOException something went wrong when reading or writing a file
     */
    public void loadStore(String dataFilename, String staticsFilename) throws IOException {
        this.addUser(Manager.getInstance());
        try {
            if ((new File(".\\resources\\" + dataFilename + ".txt")).isFile()) {
                FileInputStream fileInputStream = new FileInputStream(".\\resources\\" + dataFilename + ".txt");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                Store.setInstance((Store) objectInputStream.readObject());

                objectInputStream.close();
            }

            if ((new File(".\\resources\\" + staticsFilename + ".txt")).isFile()) {
                BufferedReader buffer = new BufferedReader(
                        new InputStreamReader(new FileInputStream(".\\resources\\" + staticsFilename + ".csv")));

                Discount.setTotalId(Integer.parseInt(buffer.readLine()));
                Exchange.setTotalId(Integer.parseInt(buffer.readLine()));
                Offer.setTotalId(Integer.parseInt(buffer.readLine()));
                Pack.setTotalId(Integer.parseInt(buffer.readLine()));
                Product.setTotalId(Integer.parseInt(buffer.readLine()));
                User.setTotalId(Integer.parseInt(buffer.readLine()));

                buffer.close();
            }

        } catch (IOException | ClassNotFoundException exception) {
            throw new IOException(exception.getMessage());
        }
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Obtains whether a category exists in the store
     * @param name, the name of the category
     * @return true if the category exists, false if else
     */
    public boolean isCategoryInStore(String name) {
        return this.categories.containsKey(name);
    }

    /**
     * Gets the list of the categories of the store
     * @return a hash map of the categories and their names
     */
    public HashMap<String, Category> getCategories() {
        return this.categories;
    }

    /**
     * Gets the category by its name
     * @param name the category's name
     * @return the category with that name
     */
    public Category getCategoryFromName(String name) {
        if (this.categories.containsKey(name)) {
            return this.categories.get(name);
        }
        return null;
    }

    /**
     * It gets the store's categories in a list
     * @return the store's categories in a list
     */
    public List<Category> getCategoryList() {
        return new ArrayList<>(this.categories.values());
    }

    /**
     * It gets the maximum number of pages that can be obtained from the store's categories list
     * @return the maximum number of pages that can be obtained from the store's categories list
     */
    public int getCategoryMaxPageNum() {
        return Pager.getInstance().getCategoryMaxPageNum(this.getCategoryList());
    }

    /**
     * It gets the paged index belonging to a discount with a certain id
     * @param wantedID the id of the desired discount
     * @return the paged index belonging to an employee with a certain username
     */
    public int getDiscountIndex(String wantedID) {
        return Pager.getInstance().getDiscountIndex(this.discounts, wantedID);
    }

    /**
     * It gets the maximum number of pages that can be obtained from the store's discount list
     * @return the maximum number of pages that can be obtained from the store's discount list
     */
    public int getDiscountMaxPageNum() {
        return Pager.getInstance().getDiscountMaxPageNum(this.discounts);
    }

    /**
     * Gets the list of the discounts of the store
     * @return a list with the discounts
     *
     */
    public List<Discount> getDiscounts() {
        return this.discounts;
    }

    /**
     * It gets the paged index belonging to an employee with a certain username
     * @param wantedUsername the username of the desired employee
     * @return the paged index belonging to an employee with a certain username
     */
    public int getEmployeeIndex(String wantedUsername) {
        return Pager.getInstance().getEmployeeIndex(this.getEmployeeList(), wantedUsername);
    }

    /**
     * It gets the store's employees in a list
     * @return the store's employees in a list
     */
    public List<Employee> getEmployeeList() {
        return new ArrayList<>(this.employees.values());
    }

    /**
     * It gets the maximum number of pages that can be obtained from the store's employee  list
     * @return the maximum number of pages that can be obtained from the store's employee  list
     */
    public int getEmployeeMaxPageNum() {
        return Pager.getInstance().getEmployeeMaxPageNum(this.getEmployeeList());
    }

    /**
     * Obtains the employees of the store
     * @return a hash map with the employees
     */
    public HashMap<String, Employee> getEmployees() {
        return this.employees;
    }

    /**
     * Obtains the exchange associated to the id
     * @param id the id of the exchange we want to search
     * @return the exchange that has the id
     */
    public Exchange getExchangeById(int id) {

        for (Exchange e : this.exchanges) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    /**
     * It gets the paged index belonging to an exchange with a certain id in the store
     * @param exchangeId the id of the desired exchange
     * @return the index belonging to an exchange with a certain id in the store
     */
    public int getExchangeIndex(int exchangeId) {
        return Pager.getInstance().getExchangeIndex(this.exchanges, exchangeId);
    }

    /**
     * It gets the maximum number of pages that can be obtained from the store's exchange list
     * @return the maximum number of pages that can be obtained from the store's exchange list
     */
    public int getExchangeMaxPageNum() {
        return Pager.getInstance().getExchangeMaxPageNum(this.exchanges);
    }

    /**
     * Gets the list of the exchanges of the store
     * @return a list with the exchanges
     */
    public List<Exchange> getExchanges() {
        return this.exchanges;
    }

    /**
     * Gets the manager of the store
     * @return the manager of the store
     */
    public Manager getManager() {
        return this.manager;
    }

    /**
     * Obtains the offer associated to the id
     * @param id the id of the offer we want to search
     * @return the offer that has the id
     */
    public Offer getOfferById(int id) {
        for (Offer offer : this.offers) {
            if (offer.getId() == id) {
                return offer;
            }
        }
        return null;
    }

    /**
     * Gets the list of the offers of the store
     * @return a list with the offers
     */
    public List<Offer> getOffers() {
        return this.offers;
    }

    /**
     * Obtains the order associated to the id
     * @param id the id of the order we want to search
     * @return the order that has the id
     */
    public Order getOrderById(int id) {

        for (Order o : this.orders) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }

    /**
     * It gets the paged index belonging to an order with a certain id in the store
     * @param orderId the id of the desired order
     * @return the index belonging to an order with a certain id in the store
     */
    public int getOrderIndex(int orderId) {
        return Pager.getInstance().getOrderIndex(this.orders, orderId);
    }

    /**
     * It gets the maximum number of pages that can be obtained from the store's order list
     * @return the maximum number of pages that can be obtained from the store's order list
     */
    public int getOrderMaxPageNum() {
        return Pager.getInstance().getOrderMaxPageNum(this.orders);
    }

    /**
     * Gets the list of the orders done in the store
     * @return a list with the orders
     */
    public List<Order> getOrders() {
        return this.orders;
    }

    public Pack getPackById(int id) {
        for (Pack p : this.packs) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * It gets the paged index belonging to a pack with a certain id in the store
     * @param packID the id of the desired pack
     * @return the index belonging to a pack with a certain id in the store
     */
    public int getPackIndex(int packID) {
        return Pager.getInstance().getPackIndex(this.packs, packID);
    }

    /**
     * It gets the maximum number of pages that can be obtained from the store's pack list
     * @return the maximum number of pages that can be obtained from the store's pack list
     */
    public int getPackMaxPageNum() {
        return Pager.getInstance().getPackMaxPageNum(this.packs);
    }

    /**
     * Gets the list of the packs of the store
     * @return a list with the packs
     */
    public List<Pack> getPacks() {
        return this.packs;
    }

    /**
     * It gets the store's parameters
     * @return the store's parameters
     */
    public Parameter getParameters() {
        return this.param;
    }

    /**
     * It returns the id of the next product to be created and updates the counter
     * @return the new product's id
     */
    public int getProductId() {
        return Product.totalId;
    }

    /**
     * It gets the store's registered clients in a list
     * @return the store's registered clients in a list
     */
    public List<RegisteredClient> getRegisteredClientList() {
        return new ArrayList<>(this.registeredClients.values());
    }

    /**
     * It gets the maximum number of pages that can be obtained from the store's registered client list
     * @return the maximum number of pages that can be obtained from the store's registered client list
     */
    public int getRegisteredClientMaxPageNum() {
        return Pager.getInstance().getRegisteredClientMaxPageNum(this.getRegisteredClientList());
    }

    /**
     * Gets the list of the store's registered clients
     * @return a hash map of the registered clients and their ids
     */
    public HashMap<String, RegisteredClient> getRegisteredClients() {
        return this.registeredClients;
    }

    /**
     * Gets the list of the reviews of the store
     * @return a list of the reviews
     */
    public List<Review> getReviews() {
        return this.reviews;
    }

    /**
     * It gets a second-hand product from its id
     * @param productId the desired second-hand product's id
     * @return the desired product
     */
    public SecondHandProduct getSecondHandProductFromId(String productId) {
        if (this.secondHandProducts.containsKey(productId)) {
            return this.secondHandProducts.get(productId);
        }
        return null;
    }

    public List<SecondHandProduct> getSecondHandProductList() {
        return new ArrayList<>(this.secondHandProducts.values());
    }

    /**
     * Gets the list of the second hand products available in the store
     * @return a hash map of the second-hand products and their ids
     */
    public HashMap<String, SecondHandProduct> getSecondHandProducts() {
        return this.secondHandProducts;
    }

    /**
     * It gets a store product from its id
     * @param productId the desired store product's id
     * @return the desired product
     */
    public StoreProduct getStoreProductFromId(String productId) {
        if (this.storeProducts.containsKey(productId)) {
            return this.storeProducts.get(productId);
        }
        return null;
    }

    /**
     * It gets the store's store products in a list
     * @return the store's store products in a list
     */
    public List<StoreProduct> getStoreProductList() {
        return new ArrayList<>(this.storeProducts.values());
    }

    /**
     * Gets the list of the store products available in the store
     * @return a hash map of the store products and their ids
     */
    public HashMap<String, StoreProduct> getStoreProducts() {
        return this.storeProducts;
    }

    /**
     * Gets the list of the users of the store
     * @return a map of the users and their names
     */
    public Map<String, User> getUsers() {
        return this.users;
    }

    /**
     * It gets the store's utility
     * @return the store's utility
     */
    public Utility getUtility() {
        return utility;
    }
}