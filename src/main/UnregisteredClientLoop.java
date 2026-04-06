package main;

import order.Cart;
import product.Category;
import product.StoreProduct;
import store.Pager;
import store.Store;
import user.RegisteredClient;
import user.UnregisteredClient;

import java.io.IOException;
import java.util.ArrayList;
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
        int i = 1;
        System.out.println("\t[" + i++ + "] Browse products");
        System.out.println("\t[" + i++ + "] Log in");
        System.out.println("\t[" + i++ + "] Sign up");
        basicLoopPrinter(i);
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1: /* Browse products */
                enterPagedScreen();
                browseStore(new ArrayList<>());
                break;
            case 2: /* Log in */
                logger();
                break;
            case 3: /* Sign up */
                signer();
                break;
            case 4, 5: /* Go back */ /* Go to main page */
                main();
                break;
            case 6: /* Exit */
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
    private void browseStore(List<StoreProduct> filteredStore)
            throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- browseStore ---- \n"); // Es para debug, borrar
        System.out.println("Page: " + currentScreenPageNum);

        if (filteredStore.isEmpty()) { // Si no hay filtros se muestran todos los productos
            filteredStore = ((UnregisteredClient) currentUser).searchStoreProduct();
        }
        Pager.getInstance().printStoreProductListPage(filteredStore, currentScreenPageNum);

        System.out.println("What do you wish to do? (enter the nº)");
        int i = 1;
        System.out.println("\t[" + i++ + "] Add a filter to the search");
        System.out.println("\t[" + i++ + "] See a product");
        System.out.println("\t[" + i++ + "] See my cart");
        System.out.println("\t[" + i++ + "] Sign up");
        pagedLoopPrinter(i);
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1: /* Filter search */
                filteredStore = filterSearch();
                System.out.println("Applying filters...");
                browseStore(filteredStore);
                break;
            case 2: /* See a product */
                System.out.print("\n ---- seeStoreProduct ---- \n"); // Es para debug, borrar
                System.out.println("Enter the number of the desired product:");
                int productNum = scanner.nextInt();
                leavePagedScreen();
                seeStoreProduct(productNum);
                break;
            case 3: /* See my cart */
                leavePagedScreen();
                seeCart();
                break;
            case 4: /* Sign up */
                leavePagedScreen();
                signer();
                break;
            case 5: /* Previous page */
                previousPage();
                browseStore(filteredStore);
                break;
            case 6: /* Next page */
                nextPageStoreProduct(filteredStore);
                browseStore(filteredStore);
                break;
            case 7: /* Go back */
                leavePagedScreen();
                unregisteredClientLoop();
                break;
            case 8: /* Go to main page */
                leavePagedScreen();
                main();
                break;
            case 9: /* Exit */
                leavePagedScreen();
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                browseStore(filteredStore);
                break;
        }
    }

    /**
     * It allows the unregistered client to filter the store's store products
     * @return the filtered list of store products
     */
    public List<StoreProduct> filterSearch() throws IOException {
        System.out.println("Choose which filter you wish to apply next (those already applied will be reset)");
        int i = 1;
        List<Category> categories = new ArrayList<>();
        System.out.println("\t[" + i++ + "] Categories");
        System.out.println("\t[" + i++ + "] Price");
        System.out.println("\t[" + i++ + "] Review Score");
        System.out.println("\t[" + i++ + "] Sign up");
        basicLoopPrinter(i);
        chosenOption = scanner.nextInt();

        boolean stop = false;
        switch (chosenOption) {
            case 1: /* Filter by categories */
                while (!stop) {
                    System.out.println("Enter the desired category (type \"stop\" to apply the ones entered so far):");
                    String categoryName = scanner.next();
                    if (categoryName.equals("stop")) {
                        stop = true;
                    }
                    Category category = Store.getInstance().getCategoryFromName(categoryName);
                    if (category == null) {
                        System.out.println("A category which such a name doesn't exist, reloading...");
                        return filterSearch();
                    }
                    categories.add(category);
                }
                break;
            case 2: /* Filter by price */
                System.out.println("Enter the minimum desired price:");
                int minPrice = scanner.nextInt();
                System.out.println("Enter the maximum desired price:");
                int maxPrice = scanner.nextInt();
                if (maxPrice < minPrice) {
                    System.out.println("Maximum price must be greater or equal to minimum price, reloading...");
                    return filterSearch();
                }
                currentUser.addPriceFilter(minPrice, maxPrice);
                break;
            case 3: /* Filter by review score */
                System.out.println("Enter the minimum desired review score:");
                int minScore = scanner.nextInt();
                System.out.println("Enter the maximum desired review score:");
                int maxScore = scanner.nextInt();
                if (maxScore < minScore) {
                    System.out.println("Maximum score must be greater or equal to minimum score, reloading...");
                    return filterSearch();
                }
                currentUser.addPunctuationFilter(minScore, maxScore);
                break;
            case 4: /* Sign up */
                signer();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                return filterSearch();
        }

        return ((UnregisteredClient) currentUser).searchStoreProductByCategory(categories.toArray(new Category[0]));
    }

    /**
     * It allows an unregistered client to place an order via log-in or sign-up
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    private void placeOrder() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- placeOrder ---- \n"); // Es para debug, borrar
        System.out.println("You must log in or sign up to proceed");
        System.out.println("What do you wish to do? (enter the nº)");
        int i = 1;
        System.out.println("\t[" + i++ + "] Log in");
        System.out.println("\t[" + i++ + "] Sign up");
        basicLoopPrinter(i);
        int chosenOption3 = scanner.nextInt();

        switch (chosenOption3) {
            case 1: /* Log in */
                cartLogger();
                break;
            case 2: /* Sign up */
                cartSigner();
                break;
            case 3: /* Go back */
                returnToPagedScreen();
                seeCart();
                break;
            case 4: /* Go to main page */
                main();
                break;
            case 5: /* Exit */
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                placeOrder();
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
        int i = 1;
        System.out.println("\t[" + i++ + "] See reviews");
        System.out.println("\t[" + i++ + "] See my cart");
        System.out.println("\t[" + i++ + "] Sign up");
        System.out.println("\t[" + i++ + "] Add to cart");
        basicLoopPrinter(i);
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1: /* See reviews */
                enterPagedScreen();
                seeReviews(product, productNum);
                break;
            case 2: /* See my cart */
                enterPagedScreen();
                seeCart();
                break;
            case 3: /* Sign up */
                signer();
                break;
            case 4: /* Add to cart */
                System.out.println("Enter the number of copies you desire");
                int numProds = scanner.nextInt();
                for (int j = 0; j < numProds; j++) {
                    ((UnregisteredClient) currentUser).addCart(product);
                }
                System.out.println("Added " + numProds + " copies to your cart");
                browseStore(new ArrayList<>());
                break;
            case 5: /* Go back */
                returnToPagedScreen();
                browseStore(new ArrayList<>());
                break;
            case 6: /* Go to main page */
                main();
                break;
            case 7: /* Exit */
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
     * @param product the desired product
     */
    private void seeReviews(StoreProduct product, int productNum) throws IOException {
        System.out.print("\n ---- seeReviews ---- \n"); // Es para debug, borrar
        System.out.println("Page: " + currentScreenPageNum);

        product.printReviews(currentScreenPageNum);

        System.out.println("What do you wish to do? (enter the nº)");
        int i = 1;
        System.out.println("\t[" + i++ + "] Sign up");
        pagedLoopPrinter(i);
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1: /* Sign up */
                leavePagedScreen();
                signer();
                break;
            case 2: /* Previous page */
                previousPage();
                seeReviews(product, currentScreenPageNum);
                break;
            case 3: /* Next page */
                nextPageReview(product);
                seeReviews(product, currentScreenPageNum);
                break;
            case 4: /* Go back */
                leavePagedScreen();
                seeStoreProduct(productNum);
                break;
            case 5: /* Go to main page */
                leavePagedScreen();
                main();
                break;
            case 6: /* Exit */
                leavePagedScreen();
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
        // DUE: printCartListPage()
        System.out.println("What do you wish to do? (enter the nº)");
        int i = 1;
        System.out.println("\t[" + i++ + "] Place order");
        System.out.println("\t[" + i++ + "] See an item");
        pagedLoopPrinter(i);
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1: /* Place order */
                placeOrder();
                break;
            case 2: /* See an item */
                // DUE: selectItemfromCartPage()
                // DUE: seeCartItem
                break;
            case 3: /* Previous page */
                previousPage();
                seeCart();
                break;
            case 4: /* Next page */
                // DUE: nextPageCart();
                seeCart();
                break;
            case 5: /* Go back */
                returnToPagedScreen();
                browseStore(new ArrayList<>());
                break;
            case 6: /* Go to main page */
                leavePagedScreen();
                main();
                break;
            case 7: /* Exit */
                leavePagedScreen();
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                seeCart();
                break;
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

        System.out.print("Enter your username: ");
        String userName = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        currentUser = Store.getInstance().getUtility().logIn(userName, password);
        if (currentUser == null) {
            System.out.println("Invalid username or password :[");
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Try again");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Try again */
                    cartLogger();
                    break;
                case 2: /* Go back */
                    placeOrder();
                    break;
                case 3: /* Go to main page */
                    main();
                    break;
                case 4: /* Exit */
                    exit();
                    break;
                default:
                    System.out.println("Uh oh, something went wrong :/, reloading...");
                    cartLogger();
                    break;
            }
        }

        /* Cart preservation */
        for (StoreProduct product : currrentCart.getProducts()) {
            ((RegisteredClient) currentUser).addCart(product);
        }

        loopSelector();
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

        currentUser = Store.getInstance().signIn();

        /* Cart preservation */
        for (StoreProduct product : currrentCart.getProducts()) {
            ((RegisteredClient) currentUser).addCart(product);
        }

        loopSelector();
    }
}