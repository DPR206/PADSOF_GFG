package model.app;

import model.product.*;
import model.store.Recommender;
import model.store.Store;
import model.user.RegisteredClient;
import model.utilities.exceptions.InvalidDni;
import model.utilities.exceptions.PasswordNotValid;

import java.io.IOException;
import java.util.*;

/**
 * It implements the registered client's loop
 * @author Ana O.R.
 * @version 1.0
 */
public class RegisteredClientLoop extends Loop {
    /** This loop's instance */
    private static RegisteredClientLoop INSTANCE;
    /** The store's filtered list of products */
    private List<StoreProduct> filteredStore = null;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The Registered client loop's constructor
     */
    RegisteredClientLoop() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the Registered client loop's instance
     * @return the Registered client loop's instance
     */
    protected static RegisteredClientLoop getInstance() {
        if (RegisteredClientLoop.INSTANCE == null) {
            RegisteredClientLoop.INSTANCE = new RegisteredClientLoop();
        }
        return INSTANCE;
    }

    /**
     * The registered client's main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    void registeredClientLoop()
            throws IOException, IllegalArgumentException, NullPointerException, InvalidDni, PasswordNotValid {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.println("\n <<<<<<<<<< registeredClientLoop >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Browse store products");
            System.out.println("\t[" + i++ + "] Browse recommendations");
            System.out.println("\t[" + i++ + "] Browse second hand products");
            System.out.println("\t[" + i++ + "] Manage my wallet");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Browse store products */
                    browseStore();
                    break;
                case 2: /* Browse recommendations */
                    browseRecommendations();
                    break;
                case 3: /* Browse second hand products */
                    browseSecondHand();
                    break;
                case 4: /* Manage my wallet */
                    browseWallet();
                    break;
                case 5: /* See my notifications */
                    browseNotifications();
                    break;
                case 6: /* See my profile */
                    seeProfile();
                    break;
                case 7: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }

    /**
     * It allows a registered client to browse products and add them to their cart
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    private void browseStore()
            throws IOException, IllegalArgumentException, NullPointerException, InvalidDni, PasswordNotValid {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< browseStore >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Page: " + currentScreenPageNum);

            if (filteredStore == null) { // Si no hay filtros se muestran todos los productos
                this.filteredStore = ((RegisteredClient) currentUser).searchStoreProduct();
            }
            Pager.getInstance().printStoreProductListPage(filteredStore, currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Add a filter to the search");
            System.out.println("\t[" + i++ + "] See a product");
            System.out.println("\t[" + i++ + "] See my cart");
            pagedStoreProductLoopPrinter(i, filteredStore);
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
                case 4: /* See my notifications */
                    browseNotifications();
                    break;
                case 5: /* See my profile */
                    seeProfile();
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
     * It allows the registered client to filter the store's store products
     * @return the filtered list of store products
     * @throws IOException the io exception
     */
    public List<StoreProduct> filterSearch() throws IOException, InvalidDni, PasswordNotValid {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose which filter you wish to apply next (those already applied will be reset)");
        int i = 1;
        List<Category> categories = new ArrayList<>();
        System.out.println("\t[" + i++ + "] Categories");
        System.out.println("\t[" + i++ + "] Price");
        System.out.println("\t[" + i++ + "] Review Score");
        System.out.println("\t[" + i++ + "] Change search order");
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
                break;
            case 5: /* See my notifications */
                browseNotifications();
                break;
            case 6: /* See my profile */
                seeProfile();
                break;
            case 7: /* Exit */
                exit();
                break;
            default: /* Go back */
                exit();
                break;
        }

        return ((RegisteredClient) currentUser).searchStoreProduct();
    }

    /**
     * It allows a registered client to place an order via log-in or sign-up
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    private void placeOrder() throws IOException, IllegalArgumentException, NullPointerException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        System.out.print("\n <<<<<<<<<< placeOrder >>>>>>>>>> \n"); // Es para debug, borrar
        ((RegisteredClient) currentUser).buy();
        System.out.println("Order placed!, Thank you");
    }

    /**
     * It allows a registered client to see a certain product's info
     * @throws IOException the io exception
     */
    private void seeStoreProduct() throws IOException, InvalidDni, PasswordNotValid {
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
                case 3: /* Add to cart */
                    System.out.println("Enter the number of copies you desire");
                    int numProds = scanner.nextInt();
                    for (int j = 0; j < numProds; j++) {
                        ((RegisteredClient) currentUser).addCart(product);
                    }
                    System.out.println("Added " + numProds + " copies to your cart");
                    break;
                case 4: /* See my notifications */
                    browseNotifications();
                    break;
                case 5: /* See my profile */
                    seeProfile();
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
     * It allows a registered client to see a certain product's reviews
     */
    private void browseReviews() throws IOException, InvalidDni, PasswordNotValid {
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
            pagedReviewLoopPrinter(i, product);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Previous page */
                    previousPage();
                    break;
                case 2: /* Next page */
                    nextPageReview(product);
                    break;
                case 3: /* See my notifications */
                    browseNotifications();
                    break;
                case 4: /* See my profile */
                    seeProfile();
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
     * It allows a registered client to see their cart's products
     * @throws IOException the io exception
     */
    private void seeCart() throws IOException, InvalidDni, PasswordNotValid {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< seeCart >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Place order");
            System.out.println("\t[" + i++ + "] See products");
            System.out.println("\t[" + i++ + "] See packs");
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
                case 4: /* See my notifications */
                    browseNotifications();
                    break;
                case 5: /* See my profile */
                    seeProfile();
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
     * It allows a registered client to browse their cart's products
     * @throws IOException the io exception
     */
    public void browseCartProducts() throws IOException, InvalidDni, PasswordNotValid {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< browseCartProducts >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Page: " + currentScreenPageNum);

            ((RegisteredClient) currentUser).getC().printStoreProductListPage(currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] See a product");
            pagedStoreProductLoopPrinter(i, ((RegisteredClient) currentUser).getC().getProducts());
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* See a product */
                    System.out.print("\n <<<<<<<<<< seeCartProduct >>>>>>>>>> \n"); // Es para debug, borrar
                    System.out.println("Enter the number of the desired product:");
                    this.itemNum = scanner.nextInt();

                    leavePagedScreen();
                    seeCartProduct();
                    break;
                case 2: /* Previous page */
                    previousPage();
                    break;
                case 3: /* Next page */
                    nextPageStoreProduct(((RegisteredClient) currentUser).getC().getProducts()); // DUE: Revisar esto
                    break;
                case 4: /* See my notifications */
                    browseNotifications();
                    break;
                case 5: /* See my profile */
                    seeProfile();
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
     * It allows a registered client to see a product from their cart
     * @throws IOException the io exception
     */
    public void seeCartProduct() throws IOException, InvalidDni, PasswordNotValid {
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
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Take one out */
                    ((RegisteredClient) currentUser).deleteCart(product);
                    break;
                case 2: /* Add another one */
                    ((RegisteredClient) currentUser).addCart(product);
                    break;
                case 3: /* See my notifications */
                    browseNotifications();
                    break;
                case 4: /* See my profile */
                    seeProfile();
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
     * It allows a registered client to see their cart's packs
     * @throws IOException the io exception
     */
    public void browseCartPacks() throws IOException, InvalidDni, PasswordNotValid {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< browseCartPacks >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("Page: " + currentScreenPageNum);

            ((RegisteredClient) currentUser).getC().printPackListPage(currentScreenPageNum);

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] See a pack");
            pagedPackLoopPrinter(i, ((RegisteredClient) currentUser).getC().getPacks());
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* See a product */
                    System.out.print("\n <<<<<<<<<< seeCartPack >>>>>>>>>> \n"); // Es para debug, borrar
                    System.out.println("Enter the number of the desired product:");
                    this.itemNum = scanner.nextInt();

                    leavePagedScreen();
                    seeCartPack();
                    break;
                case 4: /* Previous page */
                    previousPage();
                    break;
                case 5: /* Next page */
                    nextPageCartPack(((RegisteredClient) currentUser).getC());
                    break;
                case 6: /* See my notifications */
                    browseNotifications();
                    break;
                case 7: /* See my profile */
                    seeProfile();
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
     * It allows a registered client to see a pack from their cart
     * @throws IOException the io exception
     */
    public void seeCartPack() throws IOException, InvalidDni, PasswordNotValid {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< seeCartProduct 2 >>>>>>>>>> \n"); // Es para debug, borrar
            Pack pack = Pager.getInstance().selectPackFromPage((((RegisteredClient) currentUser).getC().getPacks()),
                    currentScreenPageNum, itemNum);
            pack.bigPrintInfo();

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Take one out");
            System.out.println("\t[" + i++ + "] Add another one");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Take one out */
                    ((RegisteredClient) currentUser).deleteCart(pack);
                    break;
                case 2: /* Add another one */
                    ((RegisteredClient) currentUser).addCart(pack);
                    break;
                case 3: /* See my notifications */
                    browseNotifications();
                    break;
                case 4: /* See my profile */
                    seeProfile();
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
     * It allows a registered client to browse the store's recommended products for them based on their interests
     * @throws IOException the io exception
     */
    public void browseRecommendations() throws IOException, InvalidDni, PasswordNotValid {
        System.out.print("\n <<<<<<<<<< browseRecommendations >>>>>>>>>> \n"); // Es para debug, borra
        filteredStore = Recommender.getInstance().recommendSimilarProducts((RegisteredClient) currentUser);
        browseStore();
    }

    /**
     * Browses the second-hand products
     */
    public void browseSecondHand() {
        // DUE
    }

    /**
     * Browses the second-hand products in the wallet
     */
    public void browseWallet() {
        // DUE
    }

    /**
     * It allows the registered client to see its profile and change their password
     * @throws IOException the io exception
     */
    private void seeProfile() throws IOException, InvalidDni, PasswordNotValid {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< seeProfile >>>>>>>>>> \n"); // Es para debug, borrar
            currentUser.printInfo();

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Change my password");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();
            switch (chosenOption) {
                case 1:
                    System.out.println("Enter new password:");
                    String newPassword = scanner.next();
                    currentUser.changePassword(newPassword);
                    break;
                case 2:
                    browseNotifications();
                    break;
                default:
                    exit();
                    break;
            }
        }

    }

    /**
     * It allows a registered client to browse and modify their notifications
     */
    private void browseNotifications() {
        // DUE
    }
}