/**
 * Class name: Store
 * <p>
 * Description: It implements elements such a sign in method and login method which don't belong to either classes
 * @author Sofía C
 * @version 1.0
 */

package utilities;

import store.Store;
import user.RegisteredClient;
import user.User;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utility {

    public Utility() {
    }

    public boolean signIn(Store s) {
        Scanner sc = new Scanner(System.in);
        String userName, pwd, dni;
        RegisteredClient rc;
        User u;
        boolean aux = false;

        HashMap<String, User> users = s.getUsers();
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
        } catch (InputMismatchException e) {
            System.out.println("Error: El tipo de dato introducido no es válido.");
            return false;
        } finally {
            sc.close();
        }

        rc = new RegisteredClient(userName, dni, pwd);

        u = rc;
        users.put(u, u.getUserName());
        return true;
    }

    public boolean logIn(String userName, String pwd, Store s) {
        User u;

        if (s.getUsers().containsKey(userName)) {
            u = this.users.get(userName);
            if (u.getPassword() == pwd) {
                return true;
            }
        }
        return false;
    }
}