package main;

import order.Cart;
import product.*;
import store.Pager;
import store.Store;
import user.RegisteredClient;
import user.UnregisteredClient;

import java.io.IOException;
import java.util.*;

/**
 * It implements the unregistered client's loop
 * @author Ana O.R.
 * @version 1.0
 */
public class UnregisteredClientLoop extends Loop {
    /** This loop's instance */
    private static UnregisteredClientLoop INSTANCE;
    /** The store's filtered list of products */
    private List<StoreProduct> filteredStore = null;

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
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.println("\n <<<<<<<<<< unregisteredClientLoop >>>>>>>>>> \n");
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Browse products");
            System.out.println("\t[" + i++ + "] Log in");
            System.out.println("\t[" + i++ + "] Sign up");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Browse products */
                    browseStore();
                    break;
                case 2: /* Log in */
                    logger();
                    break;
                case 3: /* Sign up */
                    signer();
                    break;
                case 4: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows an unregistered client to browse products and add them to their cart
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    private void browseStore() throws IOException, IllegalArgumentException, NullPointerException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< browseStore >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Page: " + currentScreenPageNum);

            if (filteredStore == null) { // Si no hay filtros se muestran todos los productos
                this.filteredStore = ((UnregisteredClient) currentUser).searchStoreProduct();
            }
            Pager.getInstance().printStoreProductListPage(filteredStore, currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Add a filter to the search");
            System.out.println("\t[" + i++ + "] See a product");
            System.out.println("\t[" + i++ + "] See my cart");
            System.out.println("\t[" + i++ + "] Log in");
            System.out.println("\t[" + i++ + "] Sign up");
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Filter search */
                    this.filteredStore = filterSearch();
                    System.out.println("Applying filters...");
                    break;
                case 2: /* See a product */
                    System.out.print("\n <<<<<<<<<< seeStoreProduct >>>>>>>>>> \n"); // Es para debug, borrar
                    System.out.println("Do you wish to select it via: ID or list number?");
                    System.out.println("[1] List number");
                    System.out.println("[2] ID");
                    int chosenOption2 = scanner.nextInt();

                    switch (chosenOption2) {
                        case 1:
                            System.out.println("Enter the number of the desired product:");
                            this.itemNum = scanner.nextInt();
                            leavePagedScreen();
                            seeStoreProduct();
                            break;
                        case 2:
                            System.out.println("Enter the ID of the desired product:");
                            String productID = scanner.next();
                            int index = Pager.getInstance().getStoreProductIndex(filteredStore, productID);
                            if (index != -1) {
                                currentScreenPageNum = Pager.getInstance().getPageNumFromIndex(index);
                                itemNum = Pager.getInstance().getItemNumFromIndex(index);

                                leavePagedScreen();
                                seeStoreProduct();
                                break;
                            }
                            System.out.println("The ID wasn't valid");
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case 3: /* See my cart */
                    leavePagedScreen();
                    seeCart();
                    break;
                case 4: /* Log in*/
                    logger();
                    break;
                case 5: /* Sign up */
                    signer();
                    break;
                case 6: /* Previous page */
                    previousPage();
                    break;
                case 7: /* Next page */
                    nextPageStoreProduct(this.filteredStore);
                    break;
                case 8: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows the unregistered client to filter the store's store products
     * @return the filtered list of store products
     * @throws IOException the io exception
     */
    public List<StoreProduct> filterSearch() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose which filter you wish to apply next (those already applied will be reset)");
        int i = 1;
        List<Category> categories = new ArrayList<>();
        System.out.println("\t[" + i++ + "] Categories");
        System.out.println("\t[" + i++ + "] Price");
        System.out.println("\t[" + i++ + "] Review Score");
        System.out.println("\t[" + i++ + "] Change search order");
        System.out.println("\t[" + i++ + "] Log in");
        System.out.println("\t[" + i++ + "] Sign up");
        basicLoopPrinter(i);
        chosenOption = scanner.nextInt();

        boolean stop = false;
        switch (chosenOption) {
            case 1: /* Filter by categories */
                while (!appExited && !stop) {
                    System.out.println("Enter the desired category (type \"stop\" to apply the ones entered so far):");
                    String categoryName = scanner.next();
                    if (categoryName.equals("stop")) {
                        stop = true;
                    }
                    Category category = Store.getInstance().getCategoryFromName(categoryName);
                    if (category == null) {
                        System.out.println("A category which such a name doesn't exist, reloading...");
                    } else {
                        categories.add(category);
                    }
                }
                break;
            case 2: /* Filter by price */
                while (!appExited && !stop) {
                    System.out.println("Enter the minimum desired price:");
                    int minPrice = scanner.nextInt();
                    System.out.println("Enter the maximum desired price:");
                    int maxPrice = scanner.nextInt();
                    if (maxPrice < minPrice) {
                        System.out.println("Maximum price must be greater or equal to minimum price, reloading...");
                    } else {
                        currentUser.addPriceFilter(minPrice, maxPrice);
                        stop = true;
                    }
                }
                break;
            case 3: /* Filter by review score */
                while (!appExited && !stop) {
                    System.out.println("Enter the minimum desired review score:");
                    int minScore = scanner.nextInt();
                    System.out.println("Enter the maximum desired review score:");
                    int maxScore = scanner.nextInt();
                    if (maxScore < minScore) {
                        System.out.println("Maximum score must be greater or equal to minimum score, reloading...");
                    } else {
                        currentUser.addPunctuationFilter(minScore, maxScore);
                        stop = true;
                    }
                }
                break;
            case 4: /* Change search order */
                currentUser.toggleSearchOrder();
            case 5: /* Log in */
                logger();
                break;
            case 6: /* Sign up */
                signer();
                break;
            case 7: /* Exit */
                exit();
                break;
            default: /* Go back */
                exit();
                break;
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
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< placeOrder >>>>>>>>>> \n"); // Es para debug, borrar
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
                case 3: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows an unregistered client to see a certain product's info
     * @throws IOException the io exception
     */
    private void seeStoreProduct() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< seeStoreProduct 2 >>>>>>>>>> \n"); // Es para debug, borrar
            StoreProduct product =
                    Pager.getInstance().selectStoreProductFromPage(filteredStore, currentScreenPageNum, itemNum);
            product.bigPrintInfo();

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] See reviews");
            System.out.println("\t[" + i++ + "] See my cart");
            System.out.println("\t[" + i++ + "] Log in");
            System.out.println("\t[" + i++ + "] Sign up");
            System.out.println("\t[" + i++ + "] Add to cart");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* See reviews */
                    browseReviews();
                    break;
                case 2: /* See my cart */
                    seeCart();
                    break;
                case 3: /* Log in */
                    logger();
                    break;
                case 4: /* Sign up */
                    signer();
                    break;
                case 5: /* Add to cart */
                    System.out.println("Enter the number of copies you desire");
                    int numProds = scanner.nextInt();
                    for (int j = 0; j < numProds; j++) {
                        ((UnregisteredClient) currentUser).addCart(product);
                    }
                    System.out.println("Added " + numProds + " copies to your cart");
                    break;
                case 6: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    returnToPagedScreen();
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows an unregistered client to see a certain product's reviews
     */
    private void browseReviews() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< browseReviews >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Page: " + currentScreenPageNum);

            StoreProduct product =
                    Pager.getInstance().selectStoreProductFromPage(filteredStore, currentScreenPageNum, itemNum);
            product.printReviews(currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Log in");
            System.out.println("\t[" + i++ + "] Sign up");
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Log in */
                    logger();
                    break;
                case 2: /* Sign up */
                    signer();
                    break;
                case 3: /* Previous page */
                    previousPage();
                    break;
                case 4: /* Next page */
                    nextPageReview(product);
                    break;
                case 5: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows an unregistered client to see their cart's products
     * @throws IOException the io exception
     */
    private void seeCart() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< seeCart >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Place order");
            System.out.println("\t[" + i++ + "] See products");
            System.out.println("\t[" + i++ + "] See packs");
            System.out.println("\t[" + i++ + "] Log in");
            System.out.println("\t[" + i++ + "] Sign up");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Place order */
                    placeOrder();
                    break;
                case 2: /* See products */
                    browseCartProducts();
                    break;
                case 3: /* See packs */
                    browseCartPacks();
                    break;
                case 4: /* Log in */
                    cartLogger();
                    break;
                case 5: /* Sign up */
                    cartSigner();
                    break;
                case 6: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    returnToPagedScreen();
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows an unregistered client to browse their cart's products
     * @throws IOException the io exception
     */
    public void browseCartProducts() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< browseCartProducts >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Page: " + currentScreenPageNum);

            ((UnregisteredClient) currentUser).getCart().printStoreProductListPage(currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] See a product");
            System.out.println("\t[" + i++ + "] Log in");
            System.out.println("\t[" + i++ + "] Sign up");
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* See a product */
                    System.out.print("\n <<<<<<<<<< seeCartProduct >>>>>>>>>> \n"); // Es para debug, borrar
                    System.out.println("Enter the number of the desired product:");
                    this.itemNum = scanner.nextInt();

                    leavePagedScreen();
                    seeCartProduct();
                    break;
                case 2: /* Log in */
                    logger();
                    break;
                case 3: /* Sign up */
                    signer();
                    break;
                case 4: /* Previous page */
                    previousPage();
                    break;
                case 5: /* Next page */
                    nextPageStoreProduct(
                            ((UnregisteredClient) currentUser).getCart().getProducts()); // DUE: Revisar esto
                    break;
                case 6: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows an unregistered client to see a product from their cart
     * @throws IOException the io exception
     */
    public void seeCartProduct() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< seeCartProduct 2 >>>>>>>>>> \n"); // Es para debug, borrar
            StoreProduct product =
                    Pager.getInstance().selectStoreProductFromPage(filteredStore, currentScreenPageNum, itemNum);
            product.bigPrintInfo();

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Take one out");
            System.out.println("\t[" + i++ + "] Add another one");
            System.out.println("\t[" + i++ + "] Log in");
            System.out.println("\t[" + i++ + "] Sign up");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Take one out */
                    ((UnregisteredClient) currentUser).deleteCart(product);
                    break;
                case 2: /* Add another one */
                    ((UnregisteredClient) currentUser).addCart(product);
                    // Trozos de mis sueños rotos: seeCart();
                    break;
                case 3: /* Log in */
                    logger();
                    break;
                case 4: /* Sign up */
                    signer();
                    break;
                case 5: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    returnToPagedScreen();
                    exitLoop = true;
                    break;
            }
        }

    }

    /**
     * It allows an unregistered client to see their cart's packs
     * @throws IOException the io exception
     */
    public void browseCartPacks() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< browseCartPacks >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Page: " + currentScreenPageNum);

            ((UnregisteredClient) currentUser).getCart().printPackListPage(currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] See a pack");
            System.out.println("\t[" + i++ + "] Log in");
            System.out.println("\t[" + i++ + "] Sign up");
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* See a product */
                    System.out.print("\n <<<<<<<<<< seeCartPack >>>>>>>>>> \n"); // Es para debug, borrar
                    System.out.println("Enter the number of the desired product:");
                    this.itemNum = scanner.nextInt();

                    leavePagedScreen();
                    seeCartPack();
                    break;
                case 2: /* Log in */
                    logger();
                    break;
                case 3: /* Sign up */
                    signer();
                    break;
                case 4: /* Previous page */
                    previousPage();
                    break;
                case 5: /* Next page */
                    nextPageCartPack(((UnregisteredClient) currentUser).getCart());
                    break;
                case 6: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows an unregistered client to see a pack from their cart
     * @throws IOException the io exception
     */
    public void seeCartPack() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< seeCartProduct 2 >>>>>>>>>> \n"); // Es para debug, borrar
            Pack pack = Pager.getInstance()
                             .selectPackFromPage((((UnregisteredClient) currentUser).getCart().getPacks()),
                                     currentScreenPageNum, itemNum);
            pack.bigPrintInfo();

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Take one out");
            System.out.println("\t[" + i++ + "] Add another one");
            System.out.println("\t[" + i++ + "] Log in");
            System.out.println("\t[" + i++ + "] Sign up");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Take one out */
                    ((UnregisteredClient) currentUser).deleteCart(pack);
                    break;
                case 2: /* Add another one */
                    ((UnregisteredClient) currentUser).addCart(pack);
                    break;
                case 3: /* Log in */
                    logger();
                    break;
                case 4: /* Sign up */
                    signer();
                    break;
                case 5: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    returnToPagedScreen();
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It handles the log-in whilst preserving the unregistered client's cart
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    private void cartLogger() throws IOException, IllegalArgumentException, NullPointerException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< cartLogger >>>>>>>>>> \n"); // Es para debug, borrar
            UnregisteredClient unregisteredClient = (UnregisteredClient) currentUser;
            Cart currrentCart = unregisteredClient.getCart();

            System.out.print("Enter your username: ");
            String userName = scanner.next();
            System.out.print("Enter your password: ");
            String password = scanner.next();

            currentUser = Store.getInstance().getUtility().logIn(userName, password);
            if (currentUser == null) {
                exitLoop = true;
            }

            /* Cart preservation */
            for (StoreProduct product : currrentCart.getProducts()) {
                ((RegisteredClient) currentUser).addCart(product);
            }

            loopSelector();
        }
    }

    /**
     * It handles the sign-up whilst preserving the unregistered client's cart
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    private void cartSigner() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n <<<<<<<<<< cartSigner >>>>>>>>>> \n"); // Es para debug, borrar
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