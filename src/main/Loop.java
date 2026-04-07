package main;

import order.Cart;
import product.StoreProduct;
import store.Pager;
import store.Store;
import user.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * The type Loop.
 */
public abstract class Loop {
    /** The current screen's page number, if a list is being browsed */
    protected static int currentScreenPageNum = 1;
    /** The previous screen's page number, if a list is being browsed */
    protected static int previousScreenPageNum = 1;
    /** The apps current user */
    protected static User currentUser;
    /** Whether the app has been exited or not */
    protected static boolean appExited = false;
    /** The last chosen option when prompted */
    protected int chosenOption;

    /**
     * It updates the previous and current page numbers when leaving a paged screen, allowing for the user to go back to
     * the page they were viewing in the previous screen
     */
    protected void leavePagedScreen() {
        previousScreenPageNum = currentScreenPageNum;
        currentScreenPageNum = 1;
    }

    /**
     * It updates the previous and current page numbers when entering a paged screen for the first time, allowing for
     * the user to go back to the page they were viewing in the previous screen
     */
    protected void enterPagedScreen() {
        leavePagedScreen();
    }

    /**
     * It updates the previous and current page numbers when going back to a paged screen, allowing for the user to go
     * back to the page they were viewing in the previous screen
     */
    protected void returnToPagedScreen() {
        int aux = previousScreenPageNum;
        previousScreenPageNum = currentScreenPageNum;
        currentScreenPageNum = aux;
    }

    /**
     * It handles the log-in and updates the current user accordingly
     * @throws IOException the io exception
     */
    protected void logger() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n <<<<<<<<<< logger >>>>>>>>>> \n"); // Es para debug, borrar
        System.out.print("Enter your username: ");
        String userName = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        currentUser = Store.getInstance().getUtility().logIn(userName, password);
        if (currentUser == null) {
            exit();
        }
        loopSelector();
    }

    /**
     * It handles the sign-up and updates the current user accordingly
     * @throws IOException the io exception
     */
    protected void signer() throws IOException {
        System.out.print("\n <<<<<<<<<< signer >>>>>>>>>> \n"); // Es para debug, borrar
        currentUser = Store.getInstance().signIn();
        MainLoop.getInstance().loopSelector();
    }

    /**
     * Shortcut to the Main Loop's main() method
     * @throws IOException the io exception
     */
    protected void main() throws IOException {
        MainLoop.getInstance().main();
    }

    /**
     * It chooses which loop will be triggered according to the current user
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    protected void loopSelector() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n <<<<<<<<<< loopSelector >>>>>>>>>> \n"); // Es para debug, borrar
        UserType userType = currentUser.getType();
        if (currentUser == null) {
            currentUser = new UnregisteredClient(true); // Podría ser un default de la tienda tmb aunque habría que
            // limpiar el carro cada vez que se invoque, no sé qué es asc
        }

        switch (userType) {
            case UNREGISTERED_CLIENT:
                UnregisteredClientLoop.getInstance().unregisteredClientLoop();
                break;
            case REGISTERED_CLIENT:
                RegisteredClientLoop.getInstance().registeredClientLoop();
                break;
            case EMPLOYEE:
                // DUE employeeLoop();
                break;
            case MANAGER:
                ManagerLoop.getInstance().managerLoop();
                break;
            default: /* Go back */ // Este NO debería saltar nunca, lo pongo por si acaso
                System.out.println("You shouldn't be able to see this :(");
                main();
                break;
        }
    }

    /**
     * It exits the app and saves the store
     * @throws IOException the io exception
     */
    protected void exit() throws IOException {
        System.out.print("\n <<<<<<<<<< exit >>>>>>>>>> \n"); // Es para debug, borrar
        System.out.println("See you soon!");
        appExited = true;
        System.out.print("appExited=" + appExited + "\n");

	        /*SaverLoader.getInstance()
	                   .saveStore("parameter", "categories", "reviews", "storeProducts", "secondHandProducts", "packs",
	                           "discounts", "offers", "exchanges", "orders", "users");*/ // DUE

    }

    /* Paging stuff */

    /**
     * It allows for a user to switch to the previous page when viewing a list
     */
    protected void previousPage() {
        currentScreenPageNum = (currentScreenPageNum - 1) > 0 ? currentScreenPageNum - 1 : 1;
    }

    /* Next Pages:
        [x] Review
        [x] Store Product
        [x] Registered Client
        [ ] Employee DUE
        [x] Pack
        [ ] Discount DUE
        [ ] Category DUE
     */

    /**
     * It allows for a user to switch to the next page when viewing a store product's list of reviews
     * @param product the desired store products
     */
    protected void nextPageReview(StoreProduct product) {
        currentScreenPageNum = (currentScreenPageNum + 1) < Pager.getInstance().getReviewMaxPageNum(product) ?
                               currentScreenPageNum + 1 : currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing a list of store products
     * @param products the list of store products
     */
    protected void nextPageStoreProduct(List<StoreProduct> products) {
        currentScreenPageNum = (currentScreenPageNum + 1) < Pager.getInstance().getStoreProductMaxPageNum(products) ?
                               currentScreenPageNum + 1 : currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing the store's list of registered clients
     */
    protected void nextPageRegisteredClient() {
        currentScreenPageNum = (currentScreenPageNum + 1) < Store.getInstance().getRegisteredClientMaxPageNum() ?
                               currentScreenPageNum + 1 : currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing the store's list of packs
     */
    protected void nextPageStorePack() {
        currentScreenPageNum =
                (currentScreenPageNum + 1) < Store.getInstance().getPackMaxPageNum() ? currentScreenPageNum + 1 :
                currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing the store's list of packs
     * @param cart the cart
     */
    protected void nextPageCartPack(Cart cart) {
        currentScreenPageNum =
                (currentScreenPageNum + 1) < cart.getPackMaxPageNum() ? currentScreenPageNum + 1 : currentScreenPageNum;
    }

    /**
     * It prints the previous page selection when the user is prompted to choose their next action
     * @param optionNum the number that will resemble the previous page action in the prompt
     */
    protected void previousPagePrinter(int optionNum) {
        if ((currentScreenPageNum - 1) > 0) {
            System.out.println("\t[" + optionNum + "] < Previous page");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing a store
     * product's list of reviews
     * @param optionNum the number that will resemble the previous page action in the prompt
     * @param product   the desired product
     */
    protected void nextPagePrinterReview(int optionNum, StoreProduct product) {
        if ((currentScreenPageNum + 1) < Pager.getInstance().getReviewMaxPageNum(product)) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing a list of
     * store products
     * @param optionNum the number that will resemble the previous page action in the prompt
     * @param products  the list of products
     */
    protected void nextPagePrinterStoreProduct(int optionNum, List<StoreProduct> products) {
        if ((currentScreenPageNum + 1) < Pager.getInstance().getStoreProductMaxPageNum(products)) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing the
     * store's list of registered clients
     * @param optionNum the number that will resemble the previous page action in the prompt
     */
    protected void nextPagePrinterRegisteredClient(int optionNum) {
        if ((currentScreenPageNum + 1) < Store.getInstance().getRegisteredClientMaxPageNum()) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing the
     * store's list of employees
     * @param optionNum the number that will resemble the previous page action in the prompt
     */
    protected void nextPagePrinterEmployee(int optionNum) {
        if ((currentScreenPageNum + 1) < Store.getInstance().getEmployeeMaxPageNum()) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing the
     * store's list of packs
     * @param optionNum the number that will resemble the previous page action in the prompt
     */
    protected void nextPagePrinterStorePack(int optionNum) {
        if ((currentScreenPageNum + 1) < Store.getInstance().getPackMaxPageNum()) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing the
     * store's list of discounts
     * @param optionNum the number that will resemble the previous page action in the prompt
     */
    protected void nextPagePrinterDiscount(int optionNum) {
        if ((currentScreenPageNum + 1) < Store.getInstance().getDiscountMaxPageNum()) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing the
     * store's list of categories
     * @param optionNum the number that will resemble the previous page action in the prompt
     */
    protected void nextPagePrinterCategory(int optionNum) {
        if ((currentScreenPageNum + 1) < Store.getInstance().getCategoryMaxPageNum()) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /* Useful things */

    /**
     * It prints the selections: see notification, see profile, exit, go back. Used when prompting the user,
     * notification and profile options will only be printed it they can be chosen by the current user
     * @param firstOptionNum the number that will resemble the "see notifications" action in the prompt
     */
    protected void basicLoopPrinter(int firstOptionNum) {
        if (currentUser.getType() == UserType.REGISTERED_CLIENT || currentUser.getType() == UserType.EMPLOYEE) {
            System.out.println("\t[" + firstOptionNum++ + "] ");
        }
        if (currentUser.getType() != UserType.UNREGISTERED_CLIENT) {

            System.out.println("\t[" + firstOptionNum++ + "] See profile");
        }
        System.out.println("\t[" + firstOptionNum++ + "] x Exit app");
        System.out.println("\t[" + firstOptionNum + "] <- Go back");
    }

    /**
     * It prints the selections: previous page, next page, see notification, see profile, exit, go back. Used when
     * prompting the user, notification and profile options will only be printed it they can be chosen by the current
     * user
     * @param firstOptionNum the number that will resemble the "see notifications" action in the prompt
     */
    protected void pagedLoopPrinter(int firstOptionNum) {
        previousPagePrinter(firstOptionNum);
        firstOptionNum++;
        nextPagePrinterRegisteredClient(firstOptionNum);
        firstOptionNum++;
        basicLoopPrinter(firstOptionNum);
    }
}