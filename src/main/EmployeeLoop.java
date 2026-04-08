package main;

import user.Employee;

import java.io.IOException;
import java.util.Scanner;

/**
 * It implements the general employee's loop
 * @author Ana O.R.
 * @version 1.0
 */
public class EmployeeLoop extends Loop {
    /** This loop's instance */
    private static EmployeeLoop INSTANCE;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The Registered client loop's constructor
     */
    EmployeeLoop() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the Employee loop's instance
     * @return the Employee loop's instance
     */
    protected static EmployeeLoop getInstance() {
        if (EmployeeLoop.INSTANCE == null) {
            EmployeeLoop.INSTANCE = new EmployeeLoop();
        }
        return INSTANCE;
    }

    /**
     * The employee's main loop
     */
    private void employeeLoop() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        System.out.println("\n <<<<<<<<<< employeeLoop >>>>>>>>>> \n"); // Es para debug, borrar
        switch (((Employee) currentUser).getPerm()) {
            case STORE -> StorePermissionLoop.getInstance().storePermissionLoop();
            case ORDER -> OrderPermissionLoop.getInstance().orderPermissionLoop();
            case EXCHANGE -> ExchangePermissionLoop.getInstance().exchangePermissionLoop();
            default -> System.out.println("Invalid Input");
        }
    }

    /**
     * It allows the employee to see its profile and change their password
     * @throws IOException the io exception
     */
    protected void seeProfile() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean exitLoop = false;
        while (!appExited && !exitLoop) {
            System.out.print("\n <<<<<<<<<< seeProfile >>>>>>>>>> \n"); // Es para debug, borrar
            currentUser.printInfo();

            System.out.println("What do you wish to do? (enter the nº)");
            int i = 1;
            basicLoopPrinter(i);
            chosenOption = scanner.nextInt();
            switch (chosenOption) {
                case 1: /* Browse notifications */
                    browseNotifications();
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

    /**
     * It allows a registered client to browse and modify their notifications
     */
    protected void browseNotifications() {
        // DUE
    }
}