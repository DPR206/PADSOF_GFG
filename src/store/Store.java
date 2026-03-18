package store;

import java.util.*;
import user.*;
import product.*;
import exchange.Exchange;
import order.*;

// DUE: Falta el @see

/**
 * Class name: Store
 * <p>
 * Description: It implements the store
 * @author Sofía C. and Ana O.R.
 * @version 1.0
 */
public class Store {
	private static final Store INSTANCE;
    private static int productId = 0;
    // Está creada para que no se queje el compilador en sus referencias

    /**Falta parameter */

    /* Them manager of the store */
    private Manager manager;
    /* The list of discounts in the store */
    private List<Discount> discounts = new ArrayList()<>;
    /* The list of all exchanges done in the store */
    private List<Exchange> exchanges = new ArrayList()<>;
    /* The list of orders that have been completed */
    private List<Order> orders = new ArrayList()<>;
    /* The list of packs available */
    private List<Pack> packs = new ArrayList()<>;
    /* The list of categories available */
    private List<Category> categories = new ArrayList()<>;
    /* The list of products that belong to the store */
    private List<Product> products = new ArrayList()<>;
    /* The list users ordered by username */
    private Map<String, User> users = new HashMap<>();
    /* The class for the extra functions singIn and logIn */
    private utility extras;
    private Store(){
        this.extras = new utilities();
    }

    public Store getInstance(){
        if(Store.INSTANCE == null){
            Store.INSTANCE = new Store();
        }
        return Store.INSTANCE;
    }

    public Category getCategoryFromName(String name) {
        return null; // Sustituir y rellenar
    }

    /**
     * It returns the id of the next product to be created and updates the counter
     * @return the new product's id
     */
    public int getProductId() {
        int id = this.productId;
        return id;
    }
    
    private boolean logIn() {
    	User u;
    	
    	try {
			System.out.print("Introduce tu usuario: ");
			userName = sc.next();
			System.out.print("Introduce tu contraseña: ");
			pwd = sc.next();
		}catch (InputMismatchException e) {
            System.out.println("Error: El tipo de dato introducido no es válido.");
            return false;
        }

        if(utilities.logIn(userName, pwd, this) == false) return false;

    	return true;
    } 
    
    public boolean signIn() {
		utilities.signIn(this);
    }
}
