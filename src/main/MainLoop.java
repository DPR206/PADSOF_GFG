package main;

import user.UnregisteredClient;

import java.io.IOException;
import java.time.LocalTime;

/**
 * The type Main app.
 */
public class MainLoop extends Loop {
    /** This loop's instance */
    private static MainLoop INSTANCE;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * The Main loop's constructor
     */
    MainLoop() {

    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * It gets the Main loop's instance
     * @return the main loop's instance
     */
    protected static MainLoop getInstance() {
        if (MainLoop.INSTANCE == null) {
            MainLoop.INSTANCE = new MainLoop();
        }
        return INSTANCE;
    }

    /**
     * The apps main loop
     * @throws IOException              the io exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws NullPointerException     the null pointer exception
     */
    protected void main() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.print("\n ---- main ---- \n"); // Es para debug, borrar

        /*SaverLoader.getInstance()
                   .loadStore("parameter", "categories", "reviews", "storeProducts", "secondHandProducts", "packs",
                           "discounts", "offers", "exchanges", "orders", "users"); // DUE*/

        System.out.println("\n<<<<<<< Welcome to Gifts for Geeks >>>>>>>\n");
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
                currentUser = new UnregisteredClient(true); // DUE: revisar esto
                loopSelector();
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
}