package main;

import exchange.Exchange;
import store.Store;

import java.io.IOException;
import java.util.Scanner;

/**
 * It implements the exchange permission's loop
 * @author Ana O.R.
 * @version 1.0
 */
public class ExchangePermissionLoop extends Loop {
    /** This loop's instance */
    private static ExchangePermissionLoop INSTANCE;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The Exchange Permission loop's constructor
     */
    ExchangePermissionLoop() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the Exchange Permission loop's instance
     * @return the Exchange Permission loop's instance
     */
    protected static ExchangePermissionLoop getInstance() {
        if (ExchangePermissionLoop.INSTANCE == null) {
            ExchangePermissionLoop.INSTANCE = new ExchangePermissionLoop();
        }
        return INSTANCE;
    }

    /**
     * The exchange permission's main loop
     */
    public void exchangePermissionLoop() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.println("\n <<<<<<<<<< exchangePermissionLoop >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Manage an exchange");
            System.out.println("\t[" + i++ + "] Valuate a product");
            pagedLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Manage an exchange */
                    System.out.print("\n <<<<<<<<<< manageExchange >>>>>>>>>> \n"); // Es para debug, borrar
                    System.out.println("Do you wish to select it via: ID or list number?");
                    System.out.println("[1] List number");
                    System.out.println("[2] ID");
                    int chosenOption2 = scanner.nextInt();

                    switch (chosenOption2) {
                        case 1:
                            System.out.println("Enter the number of the desired pack:");
                            this.itemNum = scanner.nextInt();
                            leavePagedScreen();
                            manageExchange();
                            break;
                        case 2:
                            System.out.println("Enter the ID of the desired exchange:");
                            int exchangeID = scanner.nextInt();
                            int index = Store.getInstance().getExchangeIndex(exchangeID);
                            if (index != -1) {
                                currentScreenPageNum = Pager.getInstance().getPageNumFromIndex(index);
                                itemNum = Pager.getInstance().getItemNumFromIndex(index);

                                leavePagedScreen();
                                manageExchange();
                                break;
                            }
                            System.out.println("The ID wasn't valid");
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case 2:
                    valuateProducts();
                    break;
                case 3: /* Previous page */
                    previousPage();
                    break;
                case 4: /* Next page */
                    nextPageExchange();
                    break;
                case 5: /* Browse notifications */
                    EmployeeLoop.getInstance().browseNotifications();
                    break;
                case 6: /* See profile */
                    EmployeeLoop.getInstance().seeProfile();
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
     * It allows an employee with store permission to manage an exchange
     * @throws IOException the io exception
     */
    public void manageExchange() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.println("\n <<<<<<<<<< manageExchange >>>>>>>>>> \n"); // Es para debug, borrar

            Exchange exchange = Pager.getInstance()
                                     .selectExchangeFromPage(Store.getInstance().getExchanges(), currentScreenPageNum,
                                             itemNum);
            exchange.bigPrintInfo();

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Mark as exchanged");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Mark as exchanged */
                    exchange.setExchanged(true);
                    break;
                case 2: /* Browse notifications */
                    EmployeeLoop.getInstance().browseNotifications();
                    break;
                case 3: /* See profile */
                    EmployeeLoop.getInstance().seeProfile();
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

    public void valuateProducts() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.println("\n <<<<<<<<<< DUE >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] ");// DUE
            basicLoopPrinter(i); // DUE: Change
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* DUE */
                    // DUE
                    break;
                case 2: /* DUE */
                    // DUE
                    break;
                case 2: /* Exit */
                    exit();
                    break;
                default: /* Go back */
                    exitLoop = true;
                    break;
            }
        }
    }
}