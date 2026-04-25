package model.app;

import model.order.Cart;
import model.product.*;
import model.store.Store;
import model.user.*;
import model.utilities.IdType;
import model.utilities.exceptions.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * It implements the general loop
 * @author Ana O.R.
 * @version 1.0
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
    protected int chosenOption = 2;
    /** The chosen item's number when selecting from a list */
    protected int itemNum = 1;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Protected constructor
     */
    protected Loop() {
    }

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
    protected void logger() throws IOException, UsernameTaken, PasswordNotValid, InvalidDni {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n <<<<<<<<<< logger >>>>>>>>>> \n"); // Es para debug, borrar

        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        currentUser = Store.getInstance().logIn(username, password);
        if (currentUser == null) {
            System.err.println("Invalid username or password");
            exit();
        }
        loopSelector();
    }

    /**
     * It handles the sign-up and updates the current user accordingly
     * @throws IOException the io exception
     */
    protected void signer() throws IOException, UsernameTaken, PasswordNotValid, InvalidDni {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n <<<<<<<<<< signer >>>>>>>>>> \n"); // Es para debug, borrar
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        System.out.print("Enter your dni: ");
        String dni = scanner.next();
        currentUser = Store.getInstance().signIn(username, password, dni, IdType.DNI);
        MainLoop.getInstance().loopSelector();
    }

    /**
     * Shortcut to the main.Main Loop's main() method
     * @throws IOException the io exception
     */
    protected void main() throws IOException, UsernameTaken, PasswordNotValid, InvalidDni {
        MainLoop.getInstance().main();
    }

    /**
     * It chooses which loop will be triggered according to the current user
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    protected void loopSelector()
            throws IOException, IllegalArgumentException, NullPointerException, UsernameTaken, PasswordNotValid,
                   InvalidDni {
        System.out.print("\n <<<<<<<<<< loopSelector >>>>>>>>>> \n"); // Es para debug, borrar
        if (currentUser == null) {
            currentUser = new UnregisteredClient(true);
        }

        UserType userType = currentUser.getType();

        switch (userType) {
            case UNREGISTERED_CLIENT:
                UnregisteredClientLoop.getInstance().unregisteredClientLoop();
                break;
            case REGISTERED_CLIENT:
                RegisteredClientLoop.getInstance().registeredClientLoop();
                break;
            case EMPLOYEE:
                EmployeeLoop.getInstance().employeeLoop();
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

    /**
     * It allows for a user to switch to the next page when viewing a store product's list of reviews
     * @param product the desired store products
     */
    protected void nextPageReview(StoreProduct product) {
        currentScreenPageNum =
                (currentScreenPageNum) < Pager.getInstance().getReviewMaxPageNum(product) ? currentScreenPageNum + 1 :
                currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing a list of store products
     * @param products the list of store products
     */
    protected void nextPageStoreProduct(List<StoreProduct> products) {
        currentScreenPageNum = (currentScreenPageNum) < Pager.getInstance().getStoreProductMaxPageNum(products) ?
                               currentScreenPageNum + 1 : currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing a list of second hand products
     * @param products the list of second hand products
     */
    protected void nextPageSecondHandProduct(List<SecondHandProduct> products) {
        currentScreenPageNum = (currentScreenPageNum) < Pager.getInstance().getSecondHandProductMaxPageNum(products) ?
                               currentScreenPageNum + 1 : currentScreenPageNum;
    }

    /**
     * It prints the selections: previous page, next page, see notification, see profile, exit, go back. Used when
     * prompting the user, notification and profile options will only be printed it they can be chosen by the current
     * user
     * @param firstOptionNum the number that will resemble the "see notifications" action in the prompt
     * @param products       the products
     */
    protected void pagedSecondHandLoopPrinter(int firstOptionNum, List<SecondHandProduct> products) {
        previousPagePrinter(firstOptionNum);
        firstOptionNum++;
        nextPagePrinterSecondHandProduct(firstOptionNum, products);
        firstOptionNum++;
        basicLoopPrinter(firstOptionNum);
    }

    /**
     * It allows for a user to switch to the next page when viewing the store's list of registered clients
     */
    protected void nextPageRegisteredClient() {
        currentScreenPageNum = (currentScreenPageNum) < Store.getInstance().getRegisteredClientMaxPageNum() ?
                               currentScreenPageNum + 1 : currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing a list of registered clients
     * @param clients the desired client list
     */
    protected void nextPageRegisteredClient(List<RegisteredClient> clients) {
        currentScreenPageNum = (currentScreenPageNum) < Pager.getInstance().getRegisteredClientMaxPageNum(clients) ?
                               currentScreenPageNum + 1 : currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing the store's list of employees
     */
    protected void nextPageEmployee() {
        currentScreenPageNum =
                (currentScreenPageNum) < Store.getInstance().getEmployeeMaxPageNum() ? currentScreenPageNum + 1 :
                currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing the store's list of packs
     */
    protected void nextPageStorePack() {
        currentScreenPageNum =
                (currentScreenPageNum) < Store.getInstance().getPackMaxPageNum() ? currentScreenPageNum + 1 :
                currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing the store's list of discounts
     */
    protected void nextPageDiscount() {
        currentScreenPageNum =
                (currentScreenPageNum) < Store.getInstance().getDiscountMaxPageNum() ? currentScreenPageNum + 1 :
                currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing the store's list of categories
     */
    protected void nextPageCategory() {
        currentScreenPageNum =
                (currentScreenPageNum) < Store.getInstance().getCategoryMaxPageNum() ? currentScreenPageNum + 1 :
                currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing a list of categories
     * @param categories the desired categories list
     */
    protected void nextPageCategory(List<Category> categories) {
        currentScreenPageNum = (currentScreenPageNum) < Pager.getInstance().getCategoryMaxPageNum(categories) ?
                               currentScreenPageNum + 1 : currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing the store's list of orders
     */
    protected void nextPageOrder() {
        currentScreenPageNum =
                (currentScreenPageNum) < Store.getInstance().getOrderMaxPageNum() ? currentScreenPageNum + 1 :
                currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing the store's list of exchanges
     */
    protected void nextPageExchange() {
        currentScreenPageNum =
                (currentScreenPageNum) < Store.getInstance().getExchangeMaxPageNum() ? currentScreenPageNum + 1 :
                currentScreenPageNum;
    }

    /**
     * It allows for a user to switch to the next page when viewing the store's list of packs
     * @param cart the cart
     */
    protected void nextPageCartPack(Cart cart) {
        currentScreenPageNum =
                (currentScreenPageNum) < cart.getPackMaxPageNum() ? currentScreenPageNum + 1 : currentScreenPageNum;
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
        if ((currentScreenPageNum) < Pager.getInstance().getReviewMaxPageNum(product)) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the selections: previous page, next page, see notification, see profile, exit, go back. Used when
     * prompting the user, notification and profile options will only be printed it they can be chosen by the current
     * user
     * @param firstOptionNum the number that will resemble the "see notifications" action in the prompt
     * @param product        the product
     */
    protected void pagedReviewLoopPrinter(int firstOptionNum, StoreProduct product) {
        previousPagePrinter(firstOptionNum);
        firstOptionNum++;
        nextPagePrinterReview(firstOptionNum, product);
        firstOptionNum++;
        basicLoopPrinter(firstOptionNum);
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing a list of
     * store products
     * @param optionNum the number that will resemble the previous page action in the prompt
     * @param products  the list of products
     */
    protected void nextPagePrinterStoreProduct(int optionNum, List<StoreProduct> products) {
        if ((currentScreenPageNum) < Pager.getInstance().getStoreProductMaxPageNum(products)) {
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
    protected void nextPagePrinterSecondHandProduct(int optionNum, List<SecondHandProduct> products) {
        if ((currentScreenPageNum) < Pager.getInstance().getSecondHandProductMaxPageNum(products)) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the selections: previous page, next page, see notification, see profile, exit, go back. Used when
     * prompting the user, notification and profile options will only be printed it they can be chosen by the current
     * user
     * @param firstOptionNum the number that will resemble the "see notifications" action in the prompt
     * @param products       the products
     */
    protected void pagedStoreProductLoopPrinter(int firstOptionNum, List<StoreProduct> products) {
        previousPagePrinter(firstOptionNum);
        firstOptionNum++;
        nextPagePrinterStoreProduct(firstOptionNum, products);
        firstOptionNum++;
        basicLoopPrinter(firstOptionNum);
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing the
     * store's list of registered clients
     * @param optionNum the number that will resemble the previous page action in the prompt
     */
    protected void nextPagePrinterRegisteredClient(int optionNum) {
        if ((currentScreenPageNum) < Store.getInstance().getRegisteredClientMaxPageNum()) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the selections: previous page, next page, see notification, see profile, exit, go back. Used when
     * prompting the user, notification and profile options will only be printed it they can be chosen by the current
     * user
     * @param firstOptionNum the number that will resemble the "see notifications" action in the prompt
     */
    protected void pagedRegisteredClientLoopPrinter(int firstOptionNum) {
        previousPagePrinter(firstOptionNum);
        firstOptionNum++;
        nextPagePrinterRegisteredClient(firstOptionNum);
        firstOptionNum++;
        basicLoopPrinter(firstOptionNum);
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing the
     * store's list of employees
     * @param optionNum the number that will resemble the previous page action in the prompt
     */
    protected void nextPagePrinterEmployee(int optionNum) {
        if ((currentScreenPageNum) < Store.getInstance().getEmployeeMaxPageNum()) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the selections: previous page, next page, see notification, see profile, exit, go back. Used when
     * prompting the user, notification and profile options will only be printed it they can be chosen by the current
     * user
     * @param firstOptionNum the number that will resemble the "see notifications" action in the prompt
     */
    protected void pagedEmployeeLoopPrinter(int firstOptionNum) {
        previousPagePrinter(firstOptionNum);
        firstOptionNum++;
        nextPagePrinterEmployee(firstOptionNum);
        firstOptionNum++;
        basicLoopPrinter(firstOptionNum);
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing the
     * store's list of packs
     * @param optionNum the number that will resemble the previous page action in the prompt
     */
    protected void nextPagePrinterPack(int optionNum) {
        if ((currentScreenPageNum) < Store.getInstance().getPackMaxPageNum()) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing the
     * store's list of packs
     * @param optionNum the number that will resemble the previous page action in the prompt
     * @param packList  the pack list
     */
    protected void nextPagePrinterPack(int optionNum, List<Pack> packList) {
        if ((currentScreenPageNum) < Pager.getInstance().getPackMaxPageNum(packList)) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the selections: previous page, next page, see notification, see profile, exit, go back. Used when
     * prompting the user, notification and profile options will only be printed it they can be chosen by the current
     * user
     * @param firstOptionNum the number that will resemble the "see notifications" action in the prompt
     * @param packs          the packs
     */
    protected void pagedPackLoopPrinter(int firstOptionNum, List<Pack> packs) {
        previousPagePrinter(firstOptionNum);
        firstOptionNum++;
        nextPagePrinterPack(firstOptionNum, packs);
        firstOptionNum++;
        basicLoopPrinter(firstOptionNum);
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing the
     * store's list of discounts
     * @param optionNum the number that will resemble the previous page action in the prompt
     */
    protected void nextPagePrinterDiscount(int optionNum) {
        if ((currentScreenPageNum) < Store.getInstance().getDiscountMaxPageNum()) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the selections: previous page, next page, see notification, see profile, exit, go back. Used when
     * prompting the user, notification and profile options will only be printed it they can be chosen by the current
     * user
     * @param firstOptionNum the number that will resemble the "see notifications" action in the prompt
     */
    protected void pagedDiscountLoopPrinter(int firstOptionNum) {
        previousPagePrinter(firstOptionNum);
        firstOptionNum++;
        nextPagePrinterDiscount(firstOptionNum);
        firstOptionNum++;
        basicLoopPrinter(firstOptionNum);
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing the
     * store's list of categories
     * @param optionNum the number that will resemble the previous page action in the prompt
     */
    protected void nextPagePrinterCategory(int optionNum) {
        if ((currentScreenPageNum) < Store.getInstance().getCategoryMaxPageNum()) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the selections: previous page, next page, see notification, see profile, exit, go back. Used when
     * prompting the user, notification and profile options will only be printed it they can be chosen by the current
     * user
     * @param firstOptionNum the number that will resemble the "see notifications" action in the prompt
     */
    protected void pagedCategoryLoopPrinter(int firstOptionNum) {
        previousPagePrinter(firstOptionNum);
        firstOptionNum++;
        nextPagePrinterCategory(firstOptionNum);
        firstOptionNum++;
        basicLoopPrinter(firstOptionNum);
    }

    /**
     * It prints the next page selection when the user is prompted to choose their next action whilst viewing the
     * store's list of orders
     * @param optionNum the number that will resemble the previous page action in the prompt
     */
    protected void nextPagePrinterOrder(int optionNum) {
        if ((currentScreenPageNum) < Store.getInstance().getOrderMaxPageNum()) {
            System.out.println("\t[" + optionNum + "] Next page >");
        } else {
            System.out.println("\t[" + optionNum + "] Reload page");
        }
    }

    /**
     * It prints the selections: previous page, next page, see notification, see profile, exit, go back. Used when
     * prompting the user, notification and profile options will only be printed it they can be chosen by the current
     * user
     * @param firstOptionNum the number that will resemble the "see notifications" action in the prompt
     */
    protected void pagedOrderLoopPrinter(int firstOptionNum) {
        previousPagePrinter(firstOptionNum);
        firstOptionNum++;
        nextPagePrinterOrder(firstOptionNum);
        firstOptionNum++;
        basicLoopPrinter(firstOptionNum);
    }

    /* Useful things */

    /**
     * It prints the selections: see notification, see profile, exit, go back. Used when prompting the user,
     * notification and profile options will only be printed it they can be chosen by the current user
     * @param firstOptionNum the number that will resemble the "see notifications" action in the prompt
     */
    protected void basicLoopPrinter(int firstOptionNum) {
        if (currentUser.getType() == UserType.REGISTERED_CLIENT || currentUser.getType() == UserType.EMPLOYEE) {
            System.out.println("\t[" + firstOptionNum++ + "] See my notifications");
        }
        if (currentUser.getType() != UserType.UNREGISTERED_CLIENT) {

            System.out.println("\t[" + firstOptionNum++ + "] See my profile");
        }
        System.out.println("\t[" + firstOptionNum++ + "] x Exit app");
        System.out.println("\t[" + firstOptionNum + "] <- Go back");
    }
}