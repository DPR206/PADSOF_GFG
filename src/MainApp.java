import store.SaverLoader;
import store.Store;
import user.User;
import user.UserType;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class MainApp {
    int chosenOption;
    User currentUser;
    private Scanner scanner = new Scanner(System.in);

    public void main(String[] args) throws IOException, IllegalArgumentException, NullPointerException {

        SaverLoader.getInstance()
                   .loadStore("parameter", "categories", "reviews", "storeProducts", "secondHandProducts", "packs",
                           "discounts", "offers", "exchanges", "orders", "users");

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
                unregisteredClientLoop();
                break;
            case 2:
                logger();
                break;
            case 3:
                signer();
                break;
            case 4:
                exit();
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                main(null);
        }

    }

    public void loopSelector(UserType userType) throws IOException, IllegalArgumentException, NullPointerException {
        switch (userType) {
            case REGISTERED_CLIENT -> registeredClientLoop();
            case EMPLOYEE -> employeeLoop();
            case MANAGER -> managerLoop();
            case UNKNOWN -> unregisteredClientLoop();
        }
    }

    public void unregisteredClientLoop() throws IOException, IllegalArgumentException, NullPointerException {
        System.out.println("---- Unregistered Client ----");
        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Browse products");
        System.out.println("\t[2] Log in");
        System.out.println("\t[3] Sign up");
        System.out.println("\t[4] Go to main page");
        System.out.println("\t[5] Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                unregisteredClientOrderLoop();
                break;
            case 2:
                logger();
                break;
            case 3:
                signer();
                break;
            case 4:
                main(null);
                break;
            case 5:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                unregisteredClientLoop();
        }
    }

    public void unregisteredClientOrderLoop() throws IOException, IllegalArgumentException, NullPointerException {
        // browseStoreProducts(); <- DUE
        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] See a product");
        System.out.println("\t[2] See my cart");
        System.out.println("\t[3] Go to main page");
        System.out.println("\t[4] Exit app");
        chosenOption = scanner.nextInt();

        switch (chosenOption) {
            case 1:
                seeProduct();
                unregisteredClientLoop();
                break;
            case 2:
                seeCart();
                System.out.println("What do you wish to do? (enter the nº)");
                System.out.println("\t[1] Place order");
                System.out.println("\t[2] Go back");
                int chosenOption2 = scanner.nextInt();

                if (chosenOption2 == 1) {
                    unregisteredClientPlaceOrder();
                } else {
                    unregisteredClientOrderLoop();
                }
                break;
            case 3:
                signer();
                break;
            case 4:
                main(null);
                break;
            case 5:
                exit();
                break;
            default:
                System.out.println("Uh oh, something went wrong :/, reloading...");
                unregisteredClientLoop();
        }
    }

    public void unregisteredClientPlaceOrder() throws IOException, IllegalArgumentException, NullPointerException {
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
        }
    }

    public void seeProduct() {
        // DUE
    }

    public void seeCart() {
        // DUE
    }

    public void cartLogger() throws IllegalArgumentException, NullPointerException {
        // DUE
    }

    public void cartSigner() throws IllegalArgumentException, NullPointerException {
        // DUE
    }

    public void logger() throws IOException {
        currentUser = Store.getInstance().getUtility().signIn();
        if (currentUser != null) {
            loopSelector(currentUser.getType());
        }
    }

    public void signer() throws IOException {
        currentUser = Store.getInstance().logIn();
        if (currentUser != null) {
            loopSelector(currentUser.getType());
        }
    }

    public void registeredClientLoop() throws IOException, IllegalArgumentException, NullPointerException {
        // DUE
    }

    public void employeeLoop() throws IOException, IllegalArgumentException, NullPointerException {
        // DUE
    }

    public void managerLoop() throws IOException, IllegalArgumentException, NullPointerException {
        // DUE
    }

    public void exit() throws IOException {
        System.out.println("See you soon!");

        SaverLoader.getInstance()
                   .saveStore("parameter", "categories", "reviews", "storeProducts", "secondHandProducts", "packs",
                           "discounts", "offers", "exchanges", "orders", "users");

    }

}