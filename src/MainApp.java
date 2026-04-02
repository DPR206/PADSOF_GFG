import order.Cart;
import product.StoreProduct;
import store.Pager;
import store.Store;
import user.*;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

/**
 * The type Main app.
 */
public class MainApp {
    private final Scanner scanner = new Scanner(System.in);
    /** The last chosen option when prompted */
    int chosenOption;
    /** The apps current user */
    User currentUser;
    /** The current page's number, if a list is being browsed */
    int currentScreenPageNum = 0;
    int previousScreenPageNum = 0;

    /**
     * The apps main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void main() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- main ---- \n"); // Es para debug, borrar

        /*SaverLoader.getInstance()
                   .loadStore("parameter", "categories", "reviews", "storeProducts", "secondHandProducts", "packs",
                           "discounts", "offers", "exchanges", "orders", "users");*/ // DUE

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
                loopSelector(UserType.UNREGISTERED_CLIENT);
                break;
            case 2:
                logger();
                break;
            case 3:
                signer();
                break;
            case 4:
                exit();
                break;
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
        System.out.print("\n ---- loopSelector ---- \n"); // Es para debug, borrar
        if (currentUser == null) {
            currentUser = new UnregisteredClient(true); // Podría ser un default de la tienda tmb aunque habría que
            // limpiar el carro cada vez que se invoque, no sé qué es asc
        }

        switch (userType) {
            case UNREGISTERED_CLIENT -> unregisteredClientLoop();
            case REGISTERED_CLIENT -> registeredClientLoop();
            case EMPLOYEE -> employeeLoop();
            case MANAGER -> managerLoop();
            default -> System.out.println("You shouldn't be able to see this º-º"); // Este NO debería saltar nunca, lo
            // pongo por si acaso
        }
    }

    /**
     * The unregistered client's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void unregisteredClientLoop() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.println("\n ---- unregisteredClientLoop ---- \n");
        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Browse products");
        System.out.println("\t[2] Log in");
        System.out.println("\t[3] Sign up");
        System.out.println("\t[4] <- Go back");
        System.out.println("\t[5] <<- Go to main page");
        System.out.println("\t[6] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                this.currentScreenPageNum = 1;
                unregisteredClientOrderLoop();
                break;
            case 2:
                logger();
                break;
            case 3:
                signer();
                break;
            case 4, 5:
                main();
                break;
            case 6:
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
        System.out.print("\n ---- unregisteredClientOrderLoop ---- \n"); // Es para debug, borrar
        System.out.println("Page: " + this.currentScreenPageNum);

        List<StoreProduct> products = ((UnregisteredClient) currentUser).searchStoreProduct(); // DUE: Añadir filtrado
        Pager.getInstance().printStoreProductListPage(products, this.currentScreenPageNum);

        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] See a product");
        System.out.println("\t[2] See my cart");
        System.out.println("\t[3] Sign up");
        if ((this.currentScreenPageNum - 1) > 0) {
            System.out.println("\t[4] < Previous page");
        } else {
            System.out.println("\t[4] Reload page");
        }
        if ((this.currentScreenPageNum + 1) < Pager.getInstance().getProductMaxPageNum(products)) {
            System.out.println("\t[5] Next page >");
        } else {
            System.out.println("\t[5] Reload page");
        }
        System.out.println("\t[6] <- Go back");
        System.out.println("\t[7] <<- Go to main page");
        System.out.println("\t[8] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                System.out.print("\n ---- unregisteredSeeProduct ---- \n"); // Es para debug, borrar
                System.out.println("Enter the number of the desired product:");
                int productNum = scanner.nextInt();
                this.previousScreenPageNum = this.currentScreenPageNum;
                this.currentScreenPageNum = 1;
                unregisteredSeeProduct(productNum, this.currentScreenPageNum);
                break;
            case 2:
                unregisteredSeeCart();
                break;
            case 3:
                signer();
                break;
            case 4:
                this.currentScreenPageNum = (this.currentScreenPageNum - 1) > 0 ? this.currentScreenPageNum - 1 : 1;
                unregisteredClientOrderLoop();
                break;
            case 5:
                this.currentScreenPageNum =
                        (this.currentScreenPageNum + 1) < Pager.getInstance().getProductMaxPageNum(products) ?
                        this.currentScreenPageNum + 1 : this.currentScreenPageNum;
                unregisteredClientOrderLoop();
                break;
            case 6:
                unregisteredClientOrderLoop();
                break;
            case 7:
                main();
                break;
            case 8:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                unregisteredClientOrderLoop();
        }
    }

    /**
     * It allows an unregistered client to place an order via log in or sign up
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void unregisteredClientPlaceOrder() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- unregisteredClientPlaceOrder ---- \n"); // Es para debug, borrar
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
     * @throws IOException the io exception
     */
    public void unregisteredSeeProduct(int productNum, int pageNum) throws IOException {
        StoreProduct product = null;
        this.currentScreenPageNum = pageNum;
        /*StoreProduct product = getProductFromPageNumber(productNum);
        product.printInfo();*/ // DUE

        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] See reviews");
        System.out.println("\t[2] See my cart");
        System.out.println("\t[3] Sign up");
        System.out.println("\t[4] Add to cart");
        System.out.println("\t[5] <- Go back");
        System.out.println("\t[6] <<- Go to main page");
        System.out.println("\t[7] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                this.previousScreenPageNum = this.currentScreenPageNum;
                this.currentScreenPageNum = 1;
                unregisteredSeeReviews(product, productNum);
                break;
            case 2:
                unregisteredSeeCart();
                break;
            case 3:
                signer();
                break;
            case 4:
                System.out.println("Enter the number of copies you desire");
                int numProds = scanner.nextInt();
                for (int i = 0; i < numProds; i++) {
                    ((UnregisteredClient) currentUser).addCart(product);
                }
                System.out.println("Added " + numProds + " copies to your cart");
                unregisteredClientOrderLoop(); // go back?
                break;
            case 5:
                unregisteredClientOrderLoop();
                break;
            case 6:
                main();
                break;
            case 7:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                unregisteredSeeProduct(productNum, this.currentScreenPageNum);
        }

    }

    /**
     * See reviews.
     * @param product the product
     */
    /*
     * It prints a certain product's reviews
     */
    public void unregisteredSeeReviews(StoreProduct product, int productNum) throws IOException {
        System.out.print("\n ---- unregisteredSeeReviews ---- \n"); // Es para debug, borrar
        System.out.println("Page: " + this.currentScreenPageNum);

        product.printReviews(this.currentScreenPageNum);

        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Sign up");
        if ((this.currentScreenPageNum - 1) > 0) {
            System.out.println("\t[2] < Previous page");
        } else {
            System.out.println("\t[2] Reload page");
        }
        if ((this.currentScreenPageNum + 1) < Pager.getInstance().getReviewtMaxPageNum(product)) {
            System.out.println("\t[3] Next page >");
        } else {
            System.out.println("\t[3] Reload page");
        }
        System.out.println("\t[4] <- Go back");
        System.out.println("\t[5] <<- Go to main page");
        System.out.println("\t[6] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                signer();
                break;
            case 2:
                this.currentScreenPageNum = (this.currentScreenPageNum - 1) > 0 ? this.currentScreenPageNum - 1 : 1;
                unregisteredSeeReviews(product, this.currentScreenPageNum);
                break;
            case 3:
                this.currentScreenPageNum =
                        (this.currentScreenPageNum + 1) < Pager.getInstance().getReviewtMaxPageNum(product) ?
                        this.currentScreenPageNum + 1 : this.currentScreenPageNum;
                unregisteredSeeReviews(product, this.currentScreenPageNum);
                break;
            case 4:
                unregisteredSeeProduct(productNum, this.previousScreenPageNum);
                break;
            case 5:
                main();
                break;
            case 6:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                unregisteredSeeReviews(product, productNum);
        }
    }

    /**
     * It prints the current user's cart's products
     * @throws IOException the io exception
     */
    public void unregisteredSeeCart() throws IOException {
        System.out.print("\n ---- unregisteredSeeCart ---- \n"); // Es para debug, borrar
        ((UnregisteredClient) this.currentUser).getCart().printInfo();
        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Place order");
        System.out.println("\t[2] Go back"); // DUE: Cancelar productos por n.º de impresión (ver printInfo)
        int chosenOption2 = scanner.nextInt();

        if (chosenOption2 == 1) {
            unregisteredClientPlaceOrder();
        } else {
            unregisteredClientOrderLoop();
        }
    }

    /**
     * It handles the log-in whilst preserving the unregistered client's cart
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void cartLogger() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- cartLogger ---- \n"); // Es para debug, borrar
        UnregisteredClient unregisteredClient = (UnregisteredClient) currentUser;
        Cart currrentCart = unregisteredClient.getCart();
        /* Logger */
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
                cartLogger();
            } else {
                unregisteredClientPlaceOrder();
            }
        }
        /* Logger */
        for (StoreProduct product : currrentCart.getProducts()) {
            currrentCart.addProduct(product);
        }
        /* Logger */
        loopSelector(currentUser.getType());
        /* Logger */
    }

    /**
     * It handles the sign-up whilst preserving the unregistered client's cart
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void cartSigner() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- cartSigner ---- \n"); // Es para debug, borrar
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
        System.out.print("\n ---- logger ---- \n"); // Es para debug, borrar
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
                logger();
            } else {
                main();
            }
        }
        loopSelector(currentUser.getType());
    }

    /**
     * It handles the sign-up and updates the current user accordingly
     * @throws IOException the io exception
     */
    public void signer() throws IOException {
        System.out.print("\n ---- signer ---- \n"); // Es para debug, borrar
        currentUser = Store.getInstance().signIn();
        loopSelector(currentUser.getType());
    }

    /**
     * The registered client's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void registeredClientLoop() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- registeredClientLoop ---- \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * The employee's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void employeeLoop() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- employeeLoop ---- \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * The manager's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    public void managerLoop() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- managerLoop ---- \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It exists the app and saves the store
     * @throws IOException the io exception
     */
    public void exit() throws IOException {
        System.out.print("\n ---- exit ---- \n"); // Es para debug, borrar
        System.out.println("See you soon!");

        /*SaverLoader.getInstance()
                   .saveStore("parameter", "categories", "reviews", "storeProducts", "secondHandProducts", "packs",
                           "discounts", "offers", "exchanges", "orders", "users");*/ // DUE

    }

}