import order.Cart;
import product.StoreProduct;
import store.SaverLoader;
import store.Store;
import user.*;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class MainApp {
    private final Scanner scanner = new Scanner(System.in);
    /** The last chosen option when prompted */
    int chosenOption;
    /** The apps current user */
    User currentUser;

    /**
     * The apps main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void main() throws IOException, IllegalArgumentException, NullPointerException {

        SaverLoader.getInstance()
                   .loadStore("parameter", "categories", "reviews", "storeProducts", "secondHandProducts", "packs",
                           "discounts", "offers", "exchanges", "orders", "users");

        // NOTE: Me hacía ilu :7
        if (LocalTime.now().isBefore(LocalTime.of(17, 0))) {
            System.out.println("Good morning!");
        } else {
            System.out.println("Good evening!");
        }

        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Browse as unregistered client");
        System.out.println("\t[2] Log in");
        System.out.println("\t[3] Sign up");
        System.out.println("\t[4] Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                unregisteredClientLoop();
                break;
            case 2:
                logger();
                break;
            case 3:
                signer();
                break;
            case 4:
                exit();
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                main();
        }

    }

    /**
     * It chooses which loop will be triggered according to the current user
     * @param userType the user type
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void loopSelector(UserType userType) throws IOException, IllegalArgumentException, NullPointerException {
        switch (userType) {
            case REGISTERED_CLIENT -> registeredClientLoop();
            case EMPLOYEE -> employeeLoop();
            case MANAGER -> managerLoop();
            case UNKNOWN -> unregisteredClientLoop();
        }
    }

    /**
     * The unregistered client's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void unregisteredClientLoop() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.println("---- Unregistered Client ----");
        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Browse products");
        System.out.println("\t[2] Log in");
        System.out.println("\t[3] Sign up");
        System.out.println("\t[4] Go to main page");
        System.out.println("\t[5] Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                unregisteredClientOrderLoop();
                break;
            case 2:
                logger();
                break;
            case 3:
                signer();
                break;
            case 4:
                main();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                unregisteredClientLoop();
        }
    }

    /**
     * It allows an unregistered client to browse products and add them to their cart
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void unregisteredClientOrderLoop() throws IOException, IllegalArgumentException, NullPointerException {
        // browseStoreProducts(); <- DUE
        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] See a product");
        System.out.println("\t[2] See my cart");
        System.out.println("\t[3] Go to main page");
        System.out.println("\t[4] Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                seeProduct();
                unregisteredClientLoop();
                break;
            case 2:
                seeCart();
                System.out.println("What do you wish to do? (enter the nº)");
                System.out.println("\t[1] Place order");
                System.out.println("\t[2] Go back");
                int chosenOption2 = scanner.nextInt();

                if (chosenOption2 == 1) {
                    unregisteredClientPlaceOrder();
                } else {
                    unregisteredClientOrderLoop();
                }
                break;
            case 3:
                signer();
                break;
            case 4:
                main();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                unregisteredClientLoop();
        }
    }

    /**
     * It allows an unregistered client to place an order via log in or sign up
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void unregisteredClientPlaceOrder() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.println("You must log in or sign up to proceed");
        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Log in");
        System.out.println("\t[2] Sign up");
        System.out.println("\t[3] Go back");
        int chosenOption3 = scanner.nextInt();

        switch (chosenOption3) {
            case 1:
                cartLogger();
                break;
            case 2:
                cartSigner();
                break;
            case 3:
                unregisteredClientOrderLoop();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                unregisteredClientLoop();
        }
    }

    /**
     * It prints a certain product's info
     */
    public void seeProduct() {
        // DUE
    }

    /**
     * It prints the current user's cart's products
     */
    public void seeCart() {
        // DUE
    }

    /**
     * Cart logger.
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void cartLogger() throws IOException, IllegalArgumentException, NullPointerException {
        UnregisteredClient unregisteredClient = (UnregisteredClient) currentUser;
        Cart currrentCart = unregisteredClient.getCart();
        logger();
        for (StoreProduct product : currrentCart.getProducts()) {
            currrentCart.addProduct(product);
        }
    }

    /**
     * It handles the sign-up whilst preserving the unregistered client's cart
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void cartSigner() throws IOException, IllegalArgumentException, NullPointerException {
        UnregisteredClient unregisteredClient = (UnregisteredClient) currentUser;
        Cart currrentCart = unregisteredClient.getCart();
        signer();
        for (StoreProduct product : currrentCart.getProducts()) {
            currrentCart.addProduct(product);
        }
    }

    /**
     * It handles the log-in and updates the current user accordingly
     * @throws IOException the io exception
     */
    public void logger() throws IOException {
        currentUser = Store.getInstance().getUtility().signIn();
        if (currentUser != null) {
            loopSelector(currentUser.getType());
        }
    }

    /**
     * It handles the sign-up and updates the current user accordingly
     * @throws IOException the io exception
     */
    public void signer() throws IOException {
        currentUser = Store.getInstance().logIn();
        if (currentUser != null) {
            loopSelector(currentUser.getType());
        }
    }

    /**
     * The registered client's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void registeredClientLoop() throws IOException, IllegalArgumentException, NullPointerException {
        // DUE
    }

    /**
     * The employee's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void employeeLoop() throws IOException, IllegalArgumentException, NullPointerException {
        // DUE
    }

    /**
     * The manager's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void managerLoop() throws IOException, IllegalArgumentException, NullPointerException {
        // DUE
    }

    /**
     * It exists the app and saves the store
     * @throws IOException the io exception
     */
    public void exit() throws IOException {
        System.out.println("See you soon!");

        SaverLoader.getInstance()
                   .saveStore("parameter", "categories", "reviews", "storeProducts", "secondHandProducts", "packs",
                           "discounts", "offers", "exchanges", "orders", "users");

    }

}