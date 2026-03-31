package store;

import exchange.Exchange;
import exchange.Offer;
import order.Discount;
import order.Order;
import product.*;
import user.*;
import utilities.Utility;

import java.util.*;

// DUE: Falta el @see

/**
 * Class name: Store
 * <p>
 * Description: It implements the store
 * @author Sofía C. and Ana O.R.
 * @version 1.0
 */
public class Store {
    private static Store INSTANCE = null;

    private Parameter param;

    /* The manager of the store */
    private Manager manager;
    /* The list of discounts in the store */
    private List<Discount> discounts = new ArrayList<>();
    /* The list of all offers done in the store */
    private List<Offer> offers = new ArrayList<>();
    /* The list of all the store's reviews */
    private List<Review> reviews = new ArrayList<>();
    /* The list of all exchanges done in the store */
    private List<Exchange> exchanges = new ArrayList<>();
    /* The list of orders that have been completed */
    private List<Order> orders = new ArrayList<>();
    /* The list of packs available */
    private List<Pack> packs = new ArrayList<>();
    /* The list of categories available */
    private HashMap<String, Category> categories = new HashMap<>();
    /* The list of store products that belong to the store */
    private HashMap<String, StoreProduct> storeProducts = new HashMap<>();
    /* The list of second hand products that belong to the store */
    private HashMap<String, SecondHandProduct> secondHandProducts = new HashMap<>();
    /* The list users ordered by username */
    private Map<String, User> users = new HashMap<>();
    /* The list of registered clients ordered by username */
    private HashMap<String, RegisteredClient> registeredClients = new HashMap<>();
    /* The class for the extra functions singIn and logIn */
    private Utility utility;

    /**
     * Store's constructor
     */
    private Store() {
        this.utility = new Utility();
        this.param = Parameter.getParam();
    }

    /**
     * Gets the Instance of the Store
     *
     */
    public static Store getInstance() {
        if (Store.INSTANCE == null) {
            Store.INSTANCE = new Store();
        }
        return Store.INSTANCE;
    }

    /**
     * It gets the store's utility
     * @return the store's utility
     */
    public Utility getUtility() {
        return utility;
    }

    /**
     * Gets the category by its name
     * @param name the category's name
     */
    public Category getCategoryFromName(String name) {
        if (this.categories.containsKey(name)) {
            return this.categories.get(name);
        }
        return null;
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

    /**
     * It returns the id of the next product to be created and updates the counter
     * @return the new product's id
     */
    public int getProductId() {
        return Product.totalId;
    }

    /**
     * It gets the store's parameters
     * @return the store's parameters
     */
    public Parameter getParameters() {
        return this.param;
    }

    /**
     * Logs in a user
     *
     */
    public User logIn() {
        User u;
        Scanner sc = new Scanner(System.in);
        String userName, pwd;

        try {
            System.out.print("Introduce tu usuario: ");
            userName = sc.next();
            System.out.print("Introduce tu contraseña: ");
            pwd = sc.next();
        } catch (InputMismatchException e) {
            System.out.println("Error: El tipo de dato introducido no es válido.");
            return null;
        }

        return utility.logIn(userName, pwd);
    }

    /**
     * Signs in a user
     *
     */
    public User signIn() {
        return utility.signIn();
    }

    /**
     * Gets the manager of the store
     *
     */
    public Manager getManager() {
        return this.manager;
    }

    /**
     * Gets the list of the discounts of the store
     *
     */
    public List<Discount> getDiscounts() {
        return this.discounts;
    }

    /**
     * Gets the list of the reviews of the store
     *
     */
    public List<Review> getReviews() {
        return this.reviews;
    }

    /**
     * Gets the list of the offers of the store
     *
     */
    public List<Offer> getOffers() {
        return this.offers;
    }

    /**
     * Gets the list of the exchanges of the store
     *
     */
    public List<Exchange> getExchanges() {
        return this.exchanges;
    }

    /**
     * Gets the list of the orders done in the store
     *
     */
    public List<Order> getOrders() {
        return this.orders;
    }

    /**
     * Gets the list of the packs of the store
     *
     */
    public List<Pack> getPacks() {
        return this.packs;
    }

    /**
     * Gets the list of the categories of the store
     *
     */
    public HashMap<String, Category> getCategories() {
        return this.categories;
    }

    /**
     * Gets the list of the store products available in the store
     *
     */
    public HashMap<String, StoreProduct> getStoreProducts() {
        return this.storeProducts;
    }

    /**
     * Gets the list of the second hand products available in the store
     *
     */
    public HashMap<String, SecondHandProduct> getSecondHandProducts() {
        return this.secondHandProducts;
    }

    /**
     * Gets the list of the users of the store
     *
     */
    public Map<String, User> getUsers() {
        return this.users;
    }

    /**
     * Gets the list of the store's registered clients
     *
     */
    public HashMap<String, RegisteredClient> getRegisteredClients() {
        return this.registeredClients;
    }

    /**
     * Obtains whether a category exists in the store
     * @param name, the name of the category
     * @return true if the category exists, false if else
     */
    public boolean isCategoryInStore(String name) {
        return this.categories.containsKey(name);
    }

    /**
     * Adds a new store product to the store
     * @param product, the new product
     */
    public void addStoreProduct(StoreProduct product) {
        this.storeProducts.put(product.getName(), product);
    }

    /**
     * Adds a new second hand product to the store
     * @param product, the new product
     */
    public void addSecondHandProduct(SecondHandProduct product) {
        this.secondHandProducts.put(product.getName(), product);
    }

    public void addPack(Pack p) {
        this.packs.add(p);
    }

    /**
     * Adds a new exchange to the store
     * @param e, the new exchange
     */
    public void addExchange(Exchange e) {
        this.exchanges.add(e);
    }
}