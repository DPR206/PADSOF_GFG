package main;

import store.Store;
import user.*;

import java.io.IOException;
import java.util.Scanner;

public abstract class Loop {
    /** The app's scanner tool */
    protected final Scanner scanner = new Scanner(System.in);
    /** The last chosen option when prompted */
    protected int chosenOption;
    /** The current screen's page number, if a list is being browsed */
    protected int currentScreenPageNum = 0;
    /** The previous screen's page number, if a list is being browsed */
    protected int previousScreenPageNum = 0;
    /** The apps current user */
    protected User currentUser;

    private MainLoop mainLoop = MainLoop.getInstance();
    private UnregisteredClientLoop unregisteredClientLoop = UnregisteredClientLoop.getInstance();
    private RegisteredClientLoop registeredClientLoop = RegisteredClientLoop.getInstance();
    private ManagerLoop managerLoop = ManagerLoop.getInstance();

    protected void pageNumGoForward() { // Se ejecuta siempre que se salga de un paged loop
        this.previousScreenPageNum = this.currentScreenPageNum;
        this.currentScreenPageNum = 1;
    }

    protected void pageNumGoBack() { // Se ejecuta siempre que se vuelva a un paged loop
        int aux = previousScreenPageNum;
        this.previousScreenPageNum = this.currentScreenPageNum;
        this.currentScreenPageNum = aux;
    }

    /**
     * It handles the log-in and updates the current user accordingly
     * @throws IOException the io exception
     */
    protected void loggerLoop() throws IOException {
        System.out.print("\n ---- loggerLoop ---- \n"); // Es para debug, borrar
        System.out.print("Enter your username: ");
        String userName = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        currentUser = Store.getInstance().getUtility().logIn(userName, password);
        if (currentUser == null) {
            System.out.println("Invalid username or password :[");
            System.out.println("What do you wish to do? (enter the nº)");
            System.out.println("\t[1] Try again");
            System.out.println("\t[2] Go back");
            int chosenOption2 = scanner.nextInt();

            if (chosenOption2 == 1) {
                loggerLoop();
            } else {
                main();
            }
        }
        loopSelector();
    }

    /**
     * It handles the sign-up and updates the current user accordingly
     * @throws IOException the io exception
     */
    protected void signer() throws IOException {
        System.out.print("\n ---- signer ---- \n"); // Es para debug, borrar
        currentUser = Store.getInstance().signIn();
        mainLoop.loopSelector();
    }

    protected void main() throws IOException {
        mainLoop.main();
    }

    /**
     * It chooses which loop will be triggered according to the current user
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    protected void loopSelector() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- loopSelector ---- \n"); // Es para debug, borrar
        UserType userType = currentUser.getType();
        if (currentUser == null) {
            currentUser = new UnregisteredClient(true); // Podría ser un default de la tienda tmb aunque habría que
            // limpiar el carro cada vez que se invoque, no sé qué es asc
        }

        switch (userType) {
            case UNREGISTERED_CLIENT:
                unregisteredClientLoop.unregisteredClientLoop();
                break;
            case REGISTERED_CLIENT:
                registeredClientLoop.registeredClientLoop();
                break;
            case EMPLOYEE:
                // DUE employeeLoop();
                break;
            case MANAGER:
                managerLoop.managerLoop();
                break;
            default: // Este NO debería saltar nunca, lo pongo por si acaso
                System.out.println("You shouldn't be able to see this :(");
                main();
                break;
        }
    }

    /**
     * It exists the app and saves the store
     * @throws IOException the io exception
     */
    protected void exit() throws IOException {
        System.out.print("\n ---- exit ---- \n"); // Es para debug, borrar
        System.out.println("See you soon!");

	        /*SaverLoader.getInstance()
	                   .saveStore("parameter", "categories", "reviews", "storeProducts", "secondHandProducts", "packs",
	                           "discounts", "offers", "exchanges", "orders", "users");*/ // DUE

    }
}