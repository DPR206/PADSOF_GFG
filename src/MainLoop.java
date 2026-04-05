import order.Cart;
import product.*;
import store.*;
import user.*;

import java.io.IOException;
import java.time.*;
import java.util.*;

/**
 * The type Main app.
 */
public class MainLoop {
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
                break;
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
                break;
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
                pageNumGoForward();
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
                break;
        }
    }

    private void pageNumGoForward() { // Se ejecuta siempre que se salga de un paged loop
        this.previousScreenPageNum = this.currentScreenPageNum;
        this.currentScreenPageNum = 1;
    }

    private void pageNumGoBack() { // Se ejecuta siempre que se vuelva a un paged loop
        int aux = previousScreenPageNum;
        this.previousScreenPageNum = this.currentScreenPageNum;
        this.currentScreenPageNum = aux;
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
        if ((this.currentScreenPageNum + 1) < Pager.getInstance().getStoreProductMaxPageNum(products)) {
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
                pageNumGoForward();
                unregisteredSeeProduct(productNum);
                break;
            case 2:
                pageNumGoForward();
                unregisteredSeeCart();
                break;
            case 3:
                pageNumGoForward();
                signer();
                break;
            case 4:
                this.currentScreenPageNum = (this.currentScreenPageNum - 1) > 0 ? this.currentScreenPageNum - 1 : 1;
                unregisteredClientOrderLoop();
                break;
            case 5:
                this.currentScreenPageNum =
                        (this.currentScreenPageNum + 1) < Pager.getInstance().getStoreProductMaxPageNum(products) ?
                        this.currentScreenPageNum + 1 : this.currentScreenPageNum;
                unregisteredClientOrderLoop();
                break;
            case 6:
                pageNumGoForward();
                unregisteredClientOrderLoop();
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
                unregisteredClientOrderLoop();
                break;
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
                break;
        }
    }

    /**
     * It prints a certain product's info
     * @throws IOException the io exception
     */
    private void unregisteredSeeProduct(int productNum) throws IOException {
        System.out.print("\n ---- unregisteredSeeProduct 2 ---- \n"); // Es para debug, borrar
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
                pageNumGoForward();
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
                pageNumGoBack();
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
                unregisteredSeeProduct(productNum);
                break;
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
                pageNumGoForward();
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
                pageNumGoForward();
                unregisteredSeeProduct(productNum);
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
                unregisteredSeeReviews(product, productNum);
                break;
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
                break;
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
        if ((this.currentScreenPageNum + 1) < Pager.getInstance().getStoreProductMaxPageNum(products)) {
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
                pageNumGoForward();

                switch (type) {
                    case COMIC:
                        managerManageComic((Comic) product);
                    case GAME:
                        managerManageGame((Game) product);
                    case FIGURINE:
                        managerManageFigurine((Figurine) product);
                    default: // Este NO debería saltar nunca, lo pongo por si acaso
                        System.out.println("You shouldn't be able to see this :(");
                        manageStoreProducts();
                        break;
                }

                break;
            case 2:
                this.currentScreenPageNum = (this.currentScreenPageNum - 1) > 0 ? this.currentScreenPageNum - 1 : 1;
                manageStoreProducts();
                break;
            case 3:
                this.currentScreenPageNum =
                        (this.currentScreenPageNum + 1) < Pager.getInstance().getStoreProductMaxPageNum(products) ?
                        this.currentScreenPageNum + 1 : this.currentScreenPageNum;
                manageStoreProducts();
                break;
            case 4:
                pageNumGoForward();
                managerLoop();
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
                manageStoreProducts();
                break;
        }
    }

    private void managerManageComic(Comic comic) throws IOException {
        System.out.print("\n ---- managerManageComic ---- \n"); // Es para debug, borrar
        comic.printAllInfo();

        System.out.println("What do you wish to change? (enter the nº)");
        System.out.println("\t[1] Name");
        System.out.println("\t[2] Description");
        System.out.println("\t[3] Price");
        System.out.println("\t[4] Photo");
        System.out.println("\t[5] Stock");
        System.out.println("\t[6] Categories");
        System.out.println("\t[7] Number of pages");
        System.out.println("\t[8] Author");
        System.out.println("\t[9] Editorial");
        System.out.println("\t[10] Publishing year");
        System.out.println("\t[11] <- Go back");
        System.out.println("\t[12] <<- Go to main page");
        System.out.println("\t[13] x Exit app");
        int chosenOption3 = scanner.nextInt();

        switch (chosenOption3) {
            case 1:
                System.out.println("Enter the product's new Name:");
                String newName = scanner.next();
                comic.setName(newName);
                break;
            case 2:
                System.out.println("Enter the product's new Description:");
                String newDesc = scanner.next();
                comic.setName(newDesc);
                break;
            case 3:
                System.out.println("Enter the product's new Price:");
                double newPrice = scanner.nextDouble();
                comic.setPrice(newPrice);
                break;
            case 4:
                System.out.println("Enter the product's new Photo's path:");
                String newPhoto = scanner.next();
                comic.setPhoto(newPhoto);
                break;
            case 5:
                System.out.println("Enter the product's new Stock:");
                int newStock = scanner.nextInt();
                comic.setStock(newStock);
                break;
            case 6:
                managerCategoryChanger(comic);
                break;
            case 7:
                System.out.println("Enter the product's new Number of pages:");
                int newNumPages = scanner.nextInt();
                comic.setNumPages(newNumPages);
                break;
            case 8:
                System.out.println("Enter the product's new Author:");
                String newAuthor = scanner.next();
                comic.setAuthor(newAuthor);
                break;
            case 9:
                System.out.println("Enter the product's new Editorial:");
                String newEditorial = scanner.next();
                comic.setEditorial(newEditorial);
                break;
            case 10:
                System.out.println("Enter the product's new Publishing year:");
                Year newYear = Year.parse(scanner.next());
                comic.setYear(newYear);
                break;
            case 11:
                pageNumGoBack();
                manageStoreProducts();
                break;
            case 12:
                main();
                break;
            case 13:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                managerManageComic(comic);
                break;
        }
    }

    private void managerManageGame(Game game) throws IOException {
        System.out.print("\n ---- managerManageGame ---- \n"); // Es para debug, borrar
        game.printAllInfo();

        System.out.println("What do you wish to change? (enter the nº)");
        System.out.println("\t[1] Name");
        System.out.println("\t[2] Description");
        System.out.println("\t[3] Price");
        System.out.println("\t[4] Photo");
        System.out.println("\t[5] Stock");
        System.out.println("\t[6] Categories");
        System.out.println("\t[7] Number of players");
        System.out.println("\t[8] Age range");
        System.out.println("\t[9] Game Style");
        System.out.println("\t[10] <- Go back");
        System.out.println("\t[11] <<- Go to main page");
        System.out.println("\t[12] x Exit app");
        int chosenOption3 = scanner.nextInt();

        switch (chosenOption3) {
            case 1:
                System.out.println("Enter the product's new Name:");
                String newName = scanner.next();
                game.setName(newName);
                break;
            case 2:
                System.out.println("Enter the product's new Description:");
                String newDesc = scanner.next();
                game.setName(newDesc);
                break;
            case 3:
                System.out.println("Enter the product's new Price:");
                double newPrice = scanner.nextDouble();
                game.setPrice(newPrice);
                break;
            case 4:
                System.out.println("Enter the product's new Photo's path:");
                String newPhoto = scanner.next();
                game.setPhoto(newPhoto);
                break;
            case 5:
                System.out.println("Enter the product's new Stock:");
                int newStock = scanner.nextInt();
                game.setStock(newStock);
                break;
            case 6:
                managerCategoryChanger(game);
                break;
            case 7:
                System.out.println("Enter the product's new Number of players:");
                int newNumPlayers = scanner.nextInt();
                game.setNumPlayers(newNumPlayers);
                break;
            case 8:
                System.out.println("Enter the product's new Age range:");
                String newAgeRange = scanner.next();
                game.setAgeRange(newAgeRange);
                break;
            case 9:
                System.out.println("Enter the product's new Game Style (" + GameStyle.CARDS.getFormatedName() + "/" +
                                   GameStyle.DICE.getFormatedName() + "/" + GameStyle.GAMEBOARD.getFormatedName() +
                                   "/" + GameStyle.MINIATURE.getFormatedName() + "):");
                GameStyle newGameStyle = GameStyle.valueOf(scanner.next());
                game.setGameStyle(newGameStyle);
                break;
            case 10:
                pageNumGoBack();
                manageStoreProducts();
                break;
            case 11:
                main();
                break;
            case 12:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                managerManageGame(game);
                break;
        }
    }

    private void managerManageFigurine(Figurine figurine) throws IOException {
        System.out.print("\n ---- managerManageFigurine ---- \n"); // Es para debug, borrar
        figurine.printAllInfo();

        System.out.println("What do you wish to change? (enter the nº)");
        System.out.println("\t[1] Name");
        System.out.println("\t[2] Description");
        System.out.println("\t[3] Price");
        System.out.println("\t[4] Photo");
        System.out.println("\t[5] Stock");
        System.out.println("\t[6] Categories");
        System.out.println("\t[7] Brand");
        System.out.println("\t[8] Material");
        System.out.println("\t[9] Dimensions");
        System.out.println("\t[10] <- Go back");
        System.out.println("\t[11] <<- Go to main page");
        System.out.println("\t[12] x Exit app");
        int chosenOption3 = scanner.nextInt();

        switch (chosenOption3) {
            case 1:
                System.out.println("Enter the product's new Name:");
                String newName = scanner.next();
                figurine.setName(newName);
                break;
            case 2:
                System.out.println("Enter the product's new Description:");
                String newDesc = scanner.next();
                figurine.setName(newDesc);
                break;
            case 3:
                System.out.println("Enter the product's new Price:");
                double newPrice = scanner.nextDouble();
                figurine.setPrice(newPrice);
                break;
            case 4:
                System.out.println("Enter the product's new Photo's path:");
                String newPhoto = scanner.next();
                figurine.setPhoto(newPhoto);
                break;
            case 5:
                System.out.println("Enter the product's new Stock:");
                int newStock = scanner.nextInt();
                figurine.setStock(newStock);
                break;
            case 6:
                managerCategoryChanger(figurine);
                break;
            case 7:
                System.out.println("Enter the product's new Brand:");
                String newBrand = scanner.next();
                figurine.setBrand(newBrand);
                break;
            case 8:
                System.out.println("Enter the product's new Material:");
                String newMaterial = scanner.next();
                figurine.setMaterial(newMaterial);
                break;
            case 9:
                System.out.println("Enter the product's new Dimensions (in cm):");
                String newDimension = scanner.next();
                figurine.setDimension(newDimension);
                break;
            case 10:
                pageNumGoBack();
                manageStoreProducts();
                break;
            case 11:
                main();
                break;
            case 12:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                managerManageFigurine(figurine);
                break;
        }
    }

    private void managerCategoryChanger(StoreProduct storeProduct) throws IOException {
        String categoryName, newCategoryName;
        Category category, newCategory;
        System.out.print("\n ---- managerCategoryChanger ---- \n"); // Es para debug, borrar
        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Replace an existing product's category");
        System.out.println("\t[2] Add a new category to the product");
        System.out.println("\t[3] Remove a product's category");
        chosenOption = scanner.nextInt();
        switch (chosenOption) {
            case 1:
                System.out.println("Which category do you want to change? (enter its name):");
                categoryName = scanner.next();
                category = Store.getInstance().getCategoryFromName(categoryName);
                if (category == null) {
                    System.out.println("A category which such a name doesn't exist, reloading...");
                    managerCategoryChanger(storeProduct);
                    break;
                }
                System.out.println("Enter the replacement category's name:");
                newCategoryName = scanner.next();
                newCategory = Store.getInstance().getCategoryFromName(categoryName);
                if (newCategory == null) {
                    System.out.println("A category which such a name doesn't exist, reloading...");
                    managerCategoryChanger(storeProduct);
                    break;
                }
                storeProduct.removeCategory(category);
                storeProduct.addCategory(newCategory);
                break;
            case 2:
                System.out.println("Which category do you want to add? (enter its name):");
                categoryName = scanner.next();
                category = Store.getInstance().getCategoryFromName(categoryName);
                if (category == null) {
                    System.out.println("A category which such a name doesn't exist, reloading...");
                    managerCategoryChanger(storeProduct);
                    break;
                }
                storeProduct.addCategory(category);
                break;
            case 3:
                System.out.println("Which category do you want to add? (enter its name):");
                categoryName = scanner.next();
                category = Store.getInstance().getCategoryFromName(categoryName);
                if (category == null) {
                    System.out.println("A category which such a name doesn't exist, reloading...");
                    managerCategoryChanger(storeProduct);
                    break;
                }
                storeProduct.removeCategory(category);
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                managerCategoryChanger(storeProduct);
                break;
        }

    }

    private void managerAddStoreProduct() {
        System.out.print("\n ---- managerAddStoreProduct ---- \n"); // Es para debug, borrar
        // DUE
    }

    private void manageEmployees() {
        System.out.print("\n ---- manageEmployees ---- \n"); // Es para debug, borrar
        // DUE
    }

    private void generateStatistics() throws IOException {
        System.out.print("\n ---- generateStatistics ---- \n"); // Es para debug, borrar
        System.out.println("Which statistic do you wish to generate? (enter the nº)");
        System.out.println("\t[1] List of store products by sales");
        System.out.println("\t[2] List of clients by orders");
        System.out.println("\t[3] List of clients by exchanges");
        System.out.println("\t[4] List of revenue by month");
        System.out.println("\t[5] List of categories by revenue");
        System.out.println("\t[6] List of store products by sales with percentage regarding total revenues");
        System.out.println("\t[7] List of store products by sales with percentage regarding total revenues on a " +
                           "certain month");
        System.out.println("\t[8] Store's total revenue");
        System.out.println("\t[9] Store's total valuation's revenue");
        System.out.println("\t[10] A certain category's revenue");
        // No sé si es útil-> System.out.println("\t[11] A certain store product's revenue");
        // No sé si es útil-> System.out.println("\t[12] A certain client's number of orders");
        // No sé si es útil-> System.out.println("\t[13] A certain client's number of exchanges");
        System.out.println("\t[11] <- Go back");
        System.out.println("\t[12] <<- Go to main page");
        System.out.println("\t[13] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                productBySales();
                break;
            case 2:
                clientsByOrders();
                break;
            case 3:
                clientsByExchanges();
                break;
            case 4:
                revenueByMonth();
                break;
            case 5:
                categoriesByRevenue();
                break;
            case 6:
                productBySalesWithPercentage();
                break;
            case 7:
                productBySalesWithPercentageCertainMonth();
                break;
            case 8:
                System.out.println("The store's total revenue is " + Statistics.getTotal_revenue() + "€");
                break;
            case 9:
                System.out.println("The store's total revenue from valuations is " +
                                   Statistics.getINSTANCE().getRevenue_valuation() + "€");
                break;
            case 10:
                System.out.println("Which category do you want to see? (enter its name):");
                String categoryName = scanner.next();
                Category category = Store.getInstance().getCategoryFromName(categoryName);
                if (category == null) {
                    System.out.println("A category which such a name doesn't exist, reloading...");
                    generateStatistics();
                    break;
                }
                System.out.println("The " + categoryName + " category's total revenue is " +
                                   Statistics.getINSTANCE().getRevenueByCategory(categoryName) + "€");
                break;
            case 11:
                managerLoop();
                break;
            case 12:
                main();
                break;
            case 13:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                generateStatistics();
                break;
        }
    }

    public void productBySales() throws IOException {
        System.out.print("\n ---- productBySales ---- \n"); // Es para debug, borrar
        System.out.println("Page: " + this.currentScreenPageNum);
        List<StoreProduct> products = Statistics.getINSTANCE().getProductsBySales();
        Pager.getInstance().printStoreProductListPage(products, this.currentScreenPageNum);

        System.out.println("What do you wish to do? (enter the nº)");
        if ((this.currentScreenPageNum - 1) > 0) {
            System.out.println("\t[1] < Previous page");
        } else {
            System.out.println("\t[1] Reload page");
        }
        if ((this.currentScreenPageNum + 1) < Pager.getInstance().getStoreProductMaxPageNum(products)) {
            System.out.println("\t[2] Next page >");
        } else {
            System.out.println("\t[2] Reload page");
        }
        System.out.println("\t[3] <- Go back");
        System.out.println("\t[4] <<- Go to main page");
        System.out.println("\t[5] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                this.currentScreenPageNum = (this.currentScreenPageNum - 1) > 0 ? this.currentScreenPageNum - 1 : 1;
                productBySales();
                break;
            case 2:
                this.currentScreenPageNum =
                        (this.currentScreenPageNum + 1) < Pager.getInstance().getStoreProductMaxPageNum(products) ?
                        this.currentScreenPageNum + 1 : this.currentScreenPageNum;
                productBySales();
                break;
            case 3:
                pageNumGoForward();
                generateStatistics();
                break;
            case 4:
                pageNumGoForward();
                main();
                break;
            case 5:
                pageNumGoForward();
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                productBySales();
                break;
        }
    }

    public void clientsByOrders() throws IOException {
        System.out.print("\n ---- clientsByOrders ---- \n"); // Es para debug, borrar
        System.out.println("Page: " + this.currentScreenPageNum);
        List<RegisteredClient> clients = Statistics.getINSTANCE().getUsersMostOrders();
        Pager.getInstance().printRegisteredClientListPage(clients, this.currentScreenPageNum);

        System.out.println("What do you wish to do? (enter the nº)");
        if ((this.currentScreenPageNum - 1) > 0) {
            System.out.println("\t[1] < Previous page");
        } else {
            System.out.println("\t[1] Reload page");
        }
        if ((this.currentScreenPageNum + 1) < Pager.getInstance().getRegisteredClientMaxPageNum(clients)) {
            System.out.println("\t[2] Next page >");
        } else {
            System.out.println("\t[2] Reload page");
        }
        System.out.println("\t[3] <- Go back");
        System.out.println("\t[4] <<- Go to main page");
        System.out.println("\t[5] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                this.currentScreenPageNum = (this.currentScreenPageNum - 1) > 0 ? this.currentScreenPageNum - 1 : 1;
                clientsByOrders();
                break;
            case 2:
                this.currentScreenPageNum =
                        (this.currentScreenPageNum + 1) < Pager.getInstance().getRegisteredClientMaxPageNum(clients) ?
                        this.currentScreenPageNum + 1 : this.currentScreenPageNum;
                clientsByOrders();
                break;
            case 3:
                pageNumGoForward();
                clientsByOrders();
                break;
            case 4:
                pageNumGoForward();
                main();
                break;
            case 5:
                pageNumGoForward();
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                clientsByOrders();
                break;
        }
    }

    public void clientsByExchanges() throws IOException {
        System.out.print("\n ---- clientsByExchanges ---- \n"); // Es para debug, borrar
        System.out.println("Page: " + this.currentScreenPageNum);
        List<RegisteredClient> clients = Statistics.getINSTANCE().getUsersMostExchanges();
        Pager.getInstance().printRegisteredClientListPage(clients, this.currentScreenPageNum);

        System.out.println("What do you wish to do? (enter the nº)");
        if ((this.currentScreenPageNum - 1) > 0) {
            System.out.println("\t[1] < Previous page");
        } else {
            System.out.println("\t[1] Reload page");
        }
        if ((this.currentScreenPageNum + 1) < Pager.getInstance().getRegisteredClientMaxPageNum(clients)) {
            System.out.println("\t[2] Next page >");
        } else {
            System.out.println("\t[2] Reload page");
        }
        System.out.println("\t[3] <- Go back");
        System.out.println("\t[4] <<- Go to main page");
        System.out.println("\t[5] x Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                this.currentScreenPageNum = (this.currentScreenPageNum - 1) > 0 ? this.currentScreenPageNum - 1 : 1;
                clientsByExchanges();
                break;
            case 2:
                this.currentScreenPageNum =
                        (this.currentScreenPageNum + 1) < Pager.getInstance().getRegisteredClientMaxPageNum(clients) ?
                        this.currentScreenPageNum + 1 : this.currentScreenPageNum;
                clientsByExchanges();
                break;
            case 3:
                pageNumGoForward();
                clientsByExchanges();
                break;
            case 4:
                pageNumGoForward();
                main();
                break;
            case 5:
                pageNumGoForward();
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                clientsByExchanges();
                break;
        }
    }

    public void revenueByMonth() {
        System.out.print("\n ---- revenueByMonth ---- \n"); // Es para debug, borrar
        HashMap<Month, Double> revenueByMonth = Statistics.getINSTANCE().getRevenueByMonth();
        for (Map.Entry<Month, Double> entry : revenueByMonth.entrySet()) {
            System.out.printf(entry.getKey() + ": " + entry.getValue() + "€");
        }
    }

    public void categoriesByRevenue() {
        System.out.print("\n ---- categoriesByRevenue ---- \n"); // Es para debug, borrar
        // DUE
    }

    public void productBySalesWithPercentage() {
        System.out.print("\n ---- productBySalesWithPercentage ---- \n"); // Es para debug, borrar
        // DUE
    }

    public void productBySalesWithPercentageCertainMonth() {
        System.out.print("\n ---- productBySalesWithPercentageCertainMonth ---- \n"); // Es para debug, borrar
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
                break;
        }
    }

    private void managerSeeProfile() throws IOException { // DUE: Que esto sea opción en todos los loops de manager:/
        System.out.print("\n ---- managerSeeProfile ---- \n"); // Es para debug, borrar
        this.currentUser.printInfo();

        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Change my password");
        System.out.println("\t[2] <- Go back");
        System.out.println("\t[3] <<- Go to main page");
        System.out.println("\t[4] x Exit app");
        chosenOption = scanner.nextInt();
        switch (chosenOption) {
            case 1:
                System.out.println("Enter new password:");
                String newPassword = scanner.next();
                this.currentUser.changePassword(newPassword);
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
                managerSeeProfile();
                break;
        }
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