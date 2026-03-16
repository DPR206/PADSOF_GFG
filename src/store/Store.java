package store;

import java.util.*;
import product.Category;
import user.*;

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
    private Map<String, User> users = new HashMap<>();

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
    
    private boolean logIn(String userName, String pwd) {
    	User u;
    	
    	if(this.users.containsKey(userName)) {
    		u = this.users.get(userName);
    		if(u.getPassword() == pwd) {
    			return true;
    		}
    	}
    	return false;
    } 
    
    public boolean signIn() {
		Scanner sc = new Scanner(System.in);
		String userName, pwd;
		RegisteredClient rc;
		
		System.out.print("Introduce tu usuario: ");
		
		try {
			System.out.print("Introduce tu usuario: ");
			userName = sc.next();
			System.out.print("Introduce tu contraseña: ");
			pwd = sc.next();
		}catch (InputMismatchException e) {
            System.out.println("Error: El tipo de dato introducido no es válido.");
        } finally {
            sc.close();
        }
    }
}
