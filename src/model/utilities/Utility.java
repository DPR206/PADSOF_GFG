
package model.utilities;

import model.store.Store;
import model.user.RegisteredClient;
import model.user.User;

import java.util.*;

/**
 * It implements elements such a sign in method and login method which don't belong to either classes
 * @author Sofía C.L. and Duna P.R.
 * @version 1.1.
 */
public class Utility {

	/**
	 * Creates a new utility
	 */
    public Utility() {}

    /**
     * The sign in process
     *
     * @return a new user
     */
    public User signIn() {
        Scanner sc = new Scanner(System.in);
        String userName = null, pwd, dni;
        RegisteredClient rc;
        User u;
        boolean aux = false;

        Map<String, User> users = Store.getInstance().getUsers();
        System.out.print("Introduce tu usuario: ");

        try {

            while (!aux) {

                System.out.print("Introduce tu usuario: ");
                userName = sc.next();

                if (users.containsKey(userName)) {
                    System.out.print("Este nombre de usuario ya está cogido");
                } else {
                    aux = true;
                }
            }

            System.out.print("Introduce tu contraseña: ");
            pwd = sc.next();
            while(!this.securePassword(pwd)) {
        		System.out.println("Make sure your password has: \n "
        				+ "-At least 8 characters\n"
        				+ "-Upper case letters\n"
        				+ "-Lower case letters\n"
        				+ "-Numbers\n"
        				+ "-Special characters\n");
        		System.out.print("Introduce tu contraseña: ");
                pwd = sc.next();
            }
            System.out.print("Introduce tu DNI: ");
            dni = sc.next();

            rc = new RegisteredClient(userName, dni, pwd, true);

            u = rc;
            users.put(u.getUserName(), u);
            Store.getInstance().getRegisteredClients().put(userName, rc);
            return u;

        } catch (InputMismatchException e) {
            System.out.println("Error: El tipo de dato introducido no es válido.");
            return null;
        }
    }

    /**
     * The log in process
     *
     * @param userName the user's name
     * @param pwd the user's password
     * @return the associated user (if there is one)
     */
    public User logIn(String userName, String pwd) {
        User u;

        if (Store.getInstance().getUsers().containsKey(userName)) {
            u = Store.getInstance().getUsers().get(userName);
            if (Objects.equals(u.getPassword(), pwd)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Checks if a password is secure
     *
     * @param psswd the password to check
     * @return true if the password is safe, false if else
     */
    public boolean securePassword(String psswd) {
    	int upper = 0;
    	int lower = 0;
    	int numbers = 0;
    	@SuppressWarnings("unused")
		int spaces = 0;
    	int specialCharacters = 0;

    	for(char c : psswd.toCharArray()) {
    		if(Character.isUpperCase(c))
    			upper++;
    		else if(Character.isLowerCase(c))
    			lower++;
    		else if(Character.isDigit(c))
    			numbers++;
    		else if(Character.isWhitespace(c))
    			spaces++;
    		else
    			specialCharacters++;
    	}

    	if(psswd.length()<8)
    		return false;
    	else if(upper<=0)
    		return false;
    	else if(lower<=0)
    		return false;
    	else if(numbers<=0)
    		return false;
    	else if(specialCharacters<=0)
    		return false;
    	else
    		return true;
    }
}