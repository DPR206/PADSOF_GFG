/**
 * Class name: Store
 * <p>
 * Description: It implements elements such a sign in method and login method which don't belong to either classes
 * @author Sofía C.L.
 * @version 1.0
 */

package utilities;

import store.Store;
import user.*;

import java.util.*;

public class Utility {
    public Utility() {
    }

    public User signIn() {
        Scanner sc = new Scanner(System.in);
        String userName = null, pwd, dni;
        RegisteredClient rc;
        User u;
        boolean aux = false;

        Map<String, User> users = Store.getInstance().getUsers();
        System.out.print("Introduce tu usuario: ");

        try {

            while (aux == false) {

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
            System.out.print("Introduce tu DNI: ");
            dni = sc.next();

            rc = new RegisteredClient(userName, dni, pwd, true);

            u = rc;
            users.put(u.getUserName(), u);
            return u;

        } catch (InputMismatchException e) {
            System.out.println("Error: El tipo de dato introducido no es válido.");
            return null;
        } finally {
            sc.close();
        }
    }

    public User logIn(String userName, String pwd) {
        User u;

        if (Store.getInstance().getUsers().containsKey(userName)) {
            u = Store.getInstance().getUsers().get(userName);
            if (u.getPassword() == pwd) {
                return u;
            }
        }
        return null;
    }
}