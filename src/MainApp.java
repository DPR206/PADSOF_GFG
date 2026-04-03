import order.Cart;
import product.ProductType;
import product.StoreProduct;
import store.*;
import user.*;

import java.io.IOException;
import java.time.LocalTime;
import java.time.Period;
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
    /** The current screen's page number, if a list is being browsed */
    int currentScreenPageNum = 0;
    /** The previous screen's page number, if a list is being browsed */
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
                loggerLoop();
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
    private void loopSelector(UserType userType) throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- loopSelector ---- \n"); // Es para debug, borrar
        if (currentUser == null) {
            currentUser = new UnregisteredClient(true); // Podría ser un default de la tienda tmb aunque habría que
            // limpiar el carro cada vez que se invoque, no sé qué es asc
        }

        switch (userType) {
            case UNREGISTERED_CLIENT:
                unregisteredClientLoop();
            case REGISTERED_CLIENT:
                registeredClientLoop();
            case EMPLOYEE:
                employeeLoop();
            case MANAGER:
                managerLoop();
            default: // Este NO debería saltar nunca, lo pongo por si acaso
                System.out.println("You shouldn't be able to see this :(");
                main();
        }
    }

    /**
     * The unregistered client's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    private void unregisteredClientLoop() throws IOException, IllegalArgumentException, NullPointerException {
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
                resetCurrentPageNum();
                unregisteredClientOrderLoop();
                break;
            case 2:
                loggerLoop();
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

    private void resetCurrentPageNum() {
        this.previousScreenPageNum = this.currentScreenPageNum;
        this.currentScreenPageNum = 1;
    }

    /**
     * It allows an unregistered client to browse products and add them to their cart
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    private void unregisteredClientOrderLoop() throws IOException, IllegalArgumentException, NullPointerException {
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
                resetCurrentPageNum();
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
    private void unregisteredClientPlaceOrder() throws IOException, IllegalArgumentException, NullPointerException {
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
    private void unregisteredSeeProduct(int productNum, int pageNum) throws IOException {
        System.out.print("\n ---- unregisteredSeeProduct 2 ---- \n"); // Es para debug, borrar
        this.currentScreenPageNum = pageNum;
        List<StoreProduct> products = ((UnregisteredClient) currentUser).searchStoreProduct();
        StoreProduct product = Pager.getInstance().getProductFromPage(products, this.currentScreenPageNum, productNum);
        product.bigPrintInfo();

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
                resetCurrentPageNum();
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
                unregisteredClientOrderLoop();
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
    private void unregisteredSeeReviews(StoreProduct product, int productNum) throws IOException {
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
        if ((this.currentScreenPageNum + 1) < Pager.getInstance().getReviewMaxPageNum(product)) {
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
                        (this.currentScreenPageNum + 1) < Pager.getInstance().getReviewMaxPageNum(product) ?
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
    private void unregisteredSeeCart() throws IOException {
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
    private void cartLogger() throws IOException, IllegalArgumentException, NullPointerException {
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
    private void cartSigner() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- cartSigner ---- \n"); // Es para debug, borrar
        UnregisteredClient unregisteredClient = (UnregisteredClient) currentUser;
        Cart currrentCart = unregisteredClient.getCart();
        /* Signer */
        currentUser = Store.getInstance().signIn();
        /* Signer */
        for (StoreProduct product : currrentCart.getProducts()) {
            currrentCart.addProduct(product);
        }
        loopSelector(currentUser.getType());
    }

    /**
     * It handles the log-in and updates the current user accordingly
     * @throws IOException the io exception
     */
    private void loggerLoop() throws IOException {
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
        loopSelector(currentUser.getType());
    }

    /**
     * It handles the sign-up and updates the current user accordingly
     * @throws IOException the io exception
     */
    private void signer() throws IOException {
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
    private void registeredClientLoop() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- registeredClientLoop ---- \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * The employee's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    private void employeeLoop() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- employeeLoop ---- \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * The manager's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    private void managerLoop() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- managerLoop ---- \n"); // Es para debug, borrar
        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Manage packs");
        System.out.println("\t[2] Manage store products");
        System.out.println("\t[3] Add store products");
        System.out.println("\t[4] Manage employees");
        System.out.println("\t[5] Generate statistics");
        System.out.println("\t[6] Manage discounts");
        System.out.println("\t[7] Manage parameters");
        System.out.println("\t[8] See profile");
        System.out.println("\t[9] <- Go back");
        System.out.println("\t[10] <<- Go to main page");
        System.out.println("\t[11] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                managePacks();
                break;
            case 2:
                manageStoreProducts();
                break;
            case 3:
                managerAddStoreProduct();
                break;
            case 4:
                manageEmployees();
                break;
            case 5:
                generateStatistics();
                break;
            case 6:
                manageDiscounts();
                break;
            case 7:
                manageParameters();
                break;
            case 8:
                managerSeeProfile();
                break;
            case 9, 10:
                main();
                break;
            case 11:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                managerLoop();
        }
    }

    private void managePacks() {
        System.out.print("\n ---- managePacks ---- \n"); // Es para debug, borrar
        // DUE
    }

    private void manageStoreProducts() throws IOException {
        System.out.print("\n ---- manageStoreProducts ---- \n"); // Es para debug, borrar
        System.out.println("Page: " + this.currentScreenPageNum);

        List<StoreProduct> products = ((Manager) currentUser).searchStoreProduct(); // DUE: Añadir filtrado
        Pager.getInstance().printStoreProductListPage(products, this.currentScreenPageNum);

        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Manage a product");
        if ((this.currentScreenPageNum - 1) > 0) {
            System.out.println("\t[2] < Previous page");
        } else {
            System.out.println("\t[2] Reload page");
        }
        if ((this.currentScreenPageNum + 1) < Pager.getInstance().getProductMaxPageNum(products)) {
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
                System.out.print("\n ---- unregisteredSeeProduct ---- \n"); // Es para debug, borrar
                System.out.println("Enter the number of the desired product:");
                int productNum = scanner.nextInt();

                StoreProduct product =
                        Pager.getInstance().getProductFromPage(products, this.currentScreenPageNum, productNum);
                ProductType type = product.getType();
                resetCurrentPageNum();

                switch (type) {
                    case COMIC:
                        managerManageComic();
                    case GAME:
                        managerManageGame();
                    case FIGURINE:
                        managerManageFigurine();
                    default: // Este NO debería saltar nunca, lo pongo por si acaso
                        System.out.println("You shouldn't be able to see this :(");
                        manageStoreProducts();
                }

                break;
            case 2:
                this.currentScreenPageNum = (this.currentScreenPageNum - 1) > 0 ? this.currentScreenPageNum - 1 : 1;
                manageStoreProducts();
                break;
            case 3:
                this.currentScreenPageNum =
                        (this.currentScreenPageNum + 1) < Pager.getInstance().getProductMaxPageNum(products) ?
                        this.currentScreenPageNum + 1 : this.currentScreenPageNum;
                manageStoreProducts();
                break;
            case 4:
                managerLoop();
                break;
            case 5:
                main();
                break;
            case 6:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                manageStoreProducts();
        }
    }

    private void managerManageComic() {
        // DUE
    }

    private void managerManageGame() {
        // DUE
    }

    private void managerManageFigurine() {
        // DUE
    }

    private void managerAddStoreProduct() {
        System.out.print("\n ---- managerAddStoreProduct ---- \n"); // Es para debug, borrar
        // DUE
    }

    private void manageEmployees() {
        System.out.print("\n ---- manageEmployees ---- \n"); // Es para debug, borrar
        // DUE
    }

    private void generateStatistics() {
        System.out.print("\n ---- generateStatistics ---- \n"); // Es para debug, borrar
        // DUE
    }

    private void manageDiscounts() {
        System.out.print("\n ---- manageDiscounts ---- \n"); // Es para debug, borrar
        // DUE
    }

    private void manageParameters() throws IOException {
        System.out.print("\n ---- manageParameters ---- \n"); // Es para debug, borrar
        Manager manager = (Manager) currentUser;
        System.out.println("What do you wish to change? (enter the nº)");
        System.out.println("\t[1] Items per page: [" + Parameter.getParam().getItemsPerPage() + "]");
        System.out.println(
                "\t[2] Score a parameter: [" + Parameter.getParam().getScoreAParam() + "] <- scoreWeight = " +
                "a*<score> + b");
        System.out.println(
                "\t[3] Score b parameter: [" + Parameter.getParam().getScoreBParam() + "] <- scoreWeight = " +
                "a*<score> + b");
        System.out.println("\t[4] Offer time: [" + Parameter.getParam().getOfferTime() + "]");
        System.out.println("\t[5] Order time: [" + Parameter.getParam().getOrderTime() + "]");
        System.out.println("\t[6] Valuation cost: [" + Parameter.getParam().getValuationCost() + "]");
        System.out.println("\t[7] <- Go back");
        System.out.println("\t[8] <<- Go to main page");
        System.out.println("\t[9] x Exit app");
        int chosenOption3 = scanner.nextInt();

        switch (chosenOption3) {
            case 1:
                System.out.println("Enter the new Items per page value:");
                int newItemsPerPage = scanner.nextInt();
                manager.changeItemsPerPage(newItemsPerPage);
                break;
            case 2:
                System.out.println("Enter the new Score a parameter value:");
                int newScoreAParam = scanner.nextInt();
                manager.changeScoreAParam(newScoreAParam);
                break;
            case 3:
                System.out.println("Enter the new Score b parameter value:");
                int newScoreBParam = scanner.nextInt();
                manager.changeScoreBParam(newScoreBParam);
                break;
            case 4:
                System.out.println("Enter the new Offer time:");
                Period newOfferTime = Period.parse(scanner.next());
                manager.changeOfferTime(newOfferTime);
                break;
            case 5:
                System.out.println("Enter the new Order time:");
                Period newOrderTime = Period.parse(scanner.next());
                manager.changeOrderTime(newOrderTime);
                break;
            case 6:
                System.out.println("Enter the new Valuation cost:");
                double newValuationCost = scanner.nextDouble();
                manager.changeValuationCost(newValuationCost);
                break;
            case 7:
                managerLoop();
                break;
            case 8:
                main();
                break;
            case 9:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                manageParameters();
        }
    }

    private void managerSeeProfile() {
        System.out.print("\n ---- managerSeeProfile ---- \n"); // Es para debug, borrar
        // DUE
    }

    /**
     * It exists the app and saves the store
     * @throws IOException the io exception
     */
    private void exit() throws IOException {
        System.out.print("\n ---- exit ---- \n"); // Es para debug, borrar
        System.out.println("See you soon!");

        /*SaverLoader.getInstance()
                   .saveStore("parameter", "categories", "reviews", "storeProducts", "secondHandProducts", "packs",
                           "discounts", "offers", "exchanges", "orders", "users");*/ // DUE

    }

}