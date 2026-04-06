package main;

import order.Cart;
import product.StoreProduct;
import store.Pager;
import store.Store;
import user.UnregisteredClient;

import java.io.IOException;
import java.util.List;

/**
 * The type Unregistered client loop.
 */
public class UnregisteredClientLoop extends Loop {
    /** This loop's instance */
    private static UnregisteredClientLoop INSTANCE;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The Unregistered client loop's constructor
     */
    UnregisteredClientLoop() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the Unregistered client loop's instance
     * @return the Unregistered client's instance
     */
    protected static UnregisteredClientLoop getInstance() {
        if (UnregisteredClientLoop.INSTANCE == null) {
            UnregisteredClientLoop.INSTANCE = new UnregisteredClientLoop();
        }
        return INSTANCE;
    }

    /**
     * The unregistered client's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    void unregisteredClientLoop() throws IOException, IllegalArgumentException, NullPointerException {
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
                pageNumGoForward();
                browseStore();
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
                break;
        }
    }

    /**
     * It allows an unregistered client to browse products and add them to their cart
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    private void browseStore() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- browseStore ---- \n"); // Es para debug, borrar
        System.out.println("Page: " + currentScreenPageNum);

        List<StoreProduct> products = ((UnregisteredClient) currentUser).searchStoreProduct(); // DUE: Añadir filtrado
        Pager.getInstance().printStoreProductListPage(products, currentScreenPageNum);

        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] See a product");
        System.out.println("\t[2] See my cart");
        System.out.println("\t[3] Sign up");
        previousPagePrinter(4);
        if ((currentScreenPageNum + 1) < Pager.getInstance().getStoreProductMaxPageNum(products)) {
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
                System.out.print("\n ---- seeStoreProduct ---- \n"); // Es para debug, borrar
                System.out.println("Enter the number of the desired product:");
                int productNum = scanner.nextInt();
                pageNumGoForward();
                seeStoreProduct(productNum);
                break;
            case 2:
                pageNumGoForward();
                seeCart();
                break;
            case 3:
                pageNumGoForward();
                signer();
                break;
            case 4:
                previousPage();
                browseStore();
                break;
            case 5:
                nextPageStoreProduct(products);
                browseStore();
                break;
            case 6:
                pageNumGoForward();
                browseStore();
                break;
            case 7:
                pageNumGoForward();
                main();
                break;
            case 8:
                pageNumGoForward();
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                browseStore();
                break;
        }
    }

    /**
     * It allows an unregistered client to place an order via log in or sign up
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    private void placeOrder() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- placeOrder ---- \n"); // Es para debug, borrar
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
                browseStore();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                unregisteredClientLoop();
                break;
        }
    }

    /**
     * It allows an unregistered client to see a certain product's info
     * @throws IOException the io exception
     */
    private void seeStoreProduct(int productNum) throws IOException {
        System.out.print("\n ---- seeStoreProduct 2 ---- \n"); // Es para debug, borrar
        List<StoreProduct> products = ((UnregisteredClient) currentUser).searchStoreProduct();
        StoreProduct product =
                Pager.getInstance().selectStoreProductFromPage(products, currentScreenPageNum, productNum);
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
                pageNumGoForward();
                seeReviews(product, productNum);
                break;
            case 2:
                seeCart();
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
                browseStore();
                break;
            case 5:
                pageNumGoBack();
                browseStore();
                break;
            case 6:
                main();
                break;
            case 7:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                seeStoreProduct(productNum);
                break;
        }

    }

    /**
     * It allows an unregistered client to see a certain product's reviews
     * @param product the product
     */
    private void seeReviews(StoreProduct product, int productNum) throws IOException {
        System.out.print("\n ---- seeReviews ---- \n"); // Es para debug, borrar
        System.out.println("Page: " + currentScreenPageNum);

        product.printReviews(currentScreenPageNum);

        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Sign up");
        previousPagePrinter(2);
        if ((currentScreenPageNum + 1) < Pager.getInstance().getReviewMaxPageNum(product)) {
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
                pageNumGoForward();
                signer();
                break;
            case 2:
                previousPage();
                seeReviews(product, currentScreenPageNum);
                break;
            case 3:
                nextPageReview(product);
                seeReviews(product, currentScreenPageNum);
                break;
            case 4:
                pageNumGoForward();
                seeStoreProduct(productNum);
                break;
            case 5:
                pageNumGoForward();
                main();
                break;
            case 6:
                pageNumGoForward();
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                seeReviews(product, productNum);
                break;
        }
    }

    /**
     * It allows an unregistered client to see their cart's products
     * @throws IOException the io exception
     */
    private void seeCart() throws IOException {
        System.out.print("\n ---- seeCart ---- \n"); // Es para debug, borrar
        ((UnregisteredClient) currentUser).getCart().getPrintInfo();
        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Place order");
        System.out.println("\t[2] Go back"); // DUE: Cancelar productos por n.º de impresión (ver getPrintInfo)
        int chosenOption2 = scanner.nextInt();

        if (chosenOption2 == 1) {
            placeOrder();
        } else {
            browseStore();
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
                placeOrder();
            }
        }
        /* Logger */
        for (StoreProduct product : currrentCart.getProducts()) {
            currrentCart.addProduct(product);
        }
        /* Logger */
        loopSelector();
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
        loopSelector();
    }
}