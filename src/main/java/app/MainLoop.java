package app;

import user.*;

import java.io.IOException;
import java.time.LocalTime;
import java.time.Year;
import java.util.Scanner;

/**
 * It implements the app's main loop
 * @author Ana O.R.
 * @version 1.0
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n <<<<<<<<<< main >>>>>>>>>> \n"); // Es para debug, borrar

        /*SaverLoader.getInstance()
                   .loadStore("parameter", "categories", "reviews", "storeProducts", "secondHandProducts", "packs",
                           "discounts", "offers", "exchanges", "orders", "users"); // DUE*/

        /* BORRAR: */
        System.out.println("Adding products...");
        StorePermission storePermission = new StorePermission();
        //storePermission.addProductByFile("resources\\productos.csv");
        storePermission.addComic(3.5, "Batman", "Batman comic vol.3", "yes.png", 2, 207, Year.of(2006), "Stan Lee",
                "ComicInc");
        storePermission.addFigurine(6.43, "Funko pop", "Harry Potter funko", "no.png", 5, "50x15", "Funko", "Vinyl");
        storePermission.addComic(3.5, "Batman2", "Batman comic vol.3", "yes.png", 2, 207, Year.of(2006), "Stan Lee",
                "ComicInc");
        storePermission.addFigurine(6.43, "Funko pop2", "Harry Potter funko", "no.png", 5, "50x15", "Funko", "Vinyl");
        storePermission.addComic(3.5, "Batman3", "Batman comic vol.3", "yes.png", 2, 207, Year.of(2006), "Stan Lee",
                "ComicInc");
        storePermission.addFigurine(6.43, "Funko pop3", "Harry Potter funko", "no.png", 5, "50x15", "Funko", "Vinyl");
        storePermission.addComic(3.5, "Batman5", "Batman comic vol.3", "yes.png", 2, 207, Year.of(2006), "Stan Lee",
                "ComicInc");
        storePermission.addFigurine(6.43, "Funko pop5", "Harry Potter funko", "no.png", 5, "50x15", "Funko", "Vinyl");
        storePermission.addComic(3.5, "Batman4", "Batman comic vol.3", "yes.png", 2, 207, Year.of(2006), "Stan Lee",
                "ComicInc");
        storePermission.addFigurine(6.43, "Funko pop4", "Harry Potter funko", "no.png", 5, "50x15", "Funko", "Vinyl");
        storePermission.addComic(3.5, "Batman5", "Batman comic vol.3", "yes.png", 2, 207, Year.of(2006), "Stan Lee",
                "ComicInc");
        storePermission.addFigurine(6.43, "Funko pop6", "Harry Potter funko", "no.png", 5, "50x15", "Funko", "Vinyl");
        storePermission.addComic(3.5, "Batman6", "Batman comic vol.3", "yes.png", 2, 207, Year.of(2006), "Stan Lee",
                "ComicInc");
        storePermission.addFigurine(6.43, "Funko pop7", "Harry Potter funko", "no.png", 5, "50x15", "Funko", "Vinyl");

        Manager.getInstance().addEmployee("p123456@", "client", Permission.ORDER);
        /* BORRAR */
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
                currentUser = new UnregisteredClient(true);
                loopSelector();
                break;
            case 2:
                logger();
                break;
            case 3:
                signer();
                break;
            default: /* Go back */
                exit();
                break;
        }
    }
}