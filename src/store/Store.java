package store;

import exchange.Exchange;
import order.Discount;
import order.Order;
import product.Category;
import product.Game;
import product.Pack;
import product.Product;
import user.Manager;
import user.User;
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
    private static int productId = 0;
    // Está creada para que no se queje el compilador en sus referencias

    /** Falta parameter */

    /* The manager of the store */
    private Manager manager;
    /* The list of discounts in the store */
    private List<Discount> discounts = new ArrayList<>();
    /* The list of all exchanges done in the store */
    private List<Exchange> exchanges = new ArrayList<>();
    /* The list of orders that have been completed */
    private List<Order> orders = new ArrayList<>();
    /* The list of packs available */
    private List<Pack> packs = new ArrayList<>();
    /* The list of categories available */
    private HashMap<String, Category> categories = new HashMap<>();
    /* The list of products that belong to the store */
    private List<Product> products = new ArrayList<>();
    /* The list users ordered by username */
    private Map<String, User> users = new HashMap<>();
    /* The class for the extra functions singIn and logIn */
    private Utility extras;

    /**
     * Store's constructor
     */
    private Store() {
        this.extras = new Utility();
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
     * Gets the category by its name
     * @param name the category's name
     */
    public Category getCategoryFromName(String name) {
        if(this.categories.containsKey(name)){
            return this.categories.get(name);
        }
        return null;
    }

    /**
     * It returns the id of the next product to be created and updates the counter
     * @return the new product's id
     */
    public int getProductId() {
        int id = this.productId;
        return id;
    }

    /**
     * Logs in a user
     *
     */
    private boolean logIn() {
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
            return false;
        }

        if (extras.logIn(userName, pwd, this) == false) return false;

        return true;
    }

    /**
     * Signs in a user
     *
     */
    public boolean signIn() {
        return extras.signIn(this);
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
    public List getDiscounts() {
        return this.discounts;
    }

    /**
     * Gets the list of the exchanges of the store
     *
     */
    public List getExchanges() {
        return this.exchanges;
    }

    /**
     * Gets the list of the orders done in the store
     *
     */
    public List getOrders() {
        return this.orders;
    }

    /**
     * Gets the list of the packs of the store
     *
     */
    public List getPacks() {
        return this.packs;
    }

    /**
     * Gets the list of the categories of the store
     *
     */
    public Map getCategories() {
        return this.categories;
    }

    /**
     * Gets the list of the products available in the store
     *
     */
    public List getProducts() {
        return this.products;
    }

    /**
     * Gets the list of the users of the store
     *
     */
    public Map getUsers() {
        return this.users;
    }

    /**
     * Obtains whether a category exists in the store
     * 
     * @param name, the name of the category
     * @return true if the category exists, false if else
     */
	public boolean isCategoryInStore(String name) {
		return this.categories.containsKey(name);
	}

	/**
	 * Adds a new product to the store
	 * @param g, the new game to add
	 */
	public void addProduct(Game g) {
		
		
	}
}