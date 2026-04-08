package main;

import order.Order;
import order.OrderState;
import store.Store;

import java.io.IOException;
import java.util.Scanner;

/**
 * It implements the order permission's loop
 * @author Ana O.R.
 * @version 1.0
 */
public class OrderPermissionLoop extends Loop {
    /** This loop's instance */
    private static OrderPermissionLoop INSTANCE;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The Order Permission loop's constructor
     */
    OrderPermissionLoop() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the Store Permission loop's instance
     * @return the Store Permission loop's instance
     */
    protected static OrderPermissionLoop getInstance() {
        if (OrderPermissionLoop.INSTANCE == null) {
            OrderPermissionLoop.INSTANCE = new OrderPermissionLoop();
        }
        return INSTANCE;
    }

    /**
     * The order permission's main loop
     * @throws IOException Error while scanning
     */
    public void orderPermissionLoop() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.println("\n <<<<<<<<<< orderPermissionLoop >>>>>>>>>> \n"); // Es para debug, borrar
            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Manage an order");
            pagedOrderLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Manage an order */
                    System.out.print("\n <<<<<<<<<< manageOrder >>>>>>>>>> \n"); // Es para debug, borrar
                    System.out.println("Do you wish to select it via: ID or list number?");
                    System.out.println("[1] List number");
                    System.out.println("[2] ID");
                    int chosenOption2 = scanner.nextInt();

                    switch (chosenOption2) {
                        case 1:
                            System.out.println("Enter the number of the desired pack:");
                            this.itemNum = scanner.nextInt();
                            leavePagedScreen();
                            manageOrder();
                            break;
                        case 2:
                            System.out.println("Enter the ID of the desired order:");
                            int orderID = scanner.nextInt();
                            int index = Store.getInstance().getOrderIndex(orderID);
                            if (index != -1) {
                                currentScreenPageNum = Pager.getInstance().getPageNumFromIndex(index);
                                itemNum = Pager.getInstance().getItemNumFromIndex(index);

                                leavePagedScreen();
                                manageOrder();
                                break;
                            }
                            System.out.println("The ID wasn't valid");
                            break;
                        default:
                            System.out.println("Invalid option");
                    }
                    break;
                case 3: /* Previous page */
                    previousPage();
                    break;
                case 4: /* Next page */
                    nextPageOrder();
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
     * It allows an employee with store permission to manage an order
     * @throws IOException the io exception
     */
    public void manageOrder() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.println("\n <<<<<<<<<< manageOrder >>>>>>>>>> \n"); // Es para debug, borrar

            Order order = Pager.getInstance()
                               .selectOrderFromPage(Store.getInstance().getOrders(), currentScreenPageNum, itemNum);
            order.bigPrintInfo();

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            System.out.println("\t[" + i++ + "] Change its status");
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();

            switch (chosenOption) {
                case 1: /* Change status */
                    System.out.println("Enter the new state (" + OrderState.PAID.getString() + "/" +
                                       OrderState.READY_TO_PICKUP.getString() + "/" +
                                       OrderState.IN_PREPARATION.getString() + "/" + OrderState.PICKED_UP.getString() +
                                       "):");
                    OrderState state = OrderState.valueOf(scanner.next());
                    order.setState(state);
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
}