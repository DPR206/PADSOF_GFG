import store.Store;
import user.User;
import user.UserType;

import java.time.LocalTime;
import java.util.Scanner;

public class MainApp {
    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LocalTime todayTime = LocalTime.now();
        int chosenOption;
        User currentUser;

        // NOTE: Me hacía ilu :7
        if (todayTime.isBefore(LocalTime.of(17, 0))) {
            System.out.println("Good morning!");
        } else {
            System.out.println("Good evening!");
        }

        System.out.println("What do you wish to do? (enter the nº)");
        System.out.println("\t[1] Browse as unregistered client");
        System.out.println("\t[2] Log in");
        System.out.println("\t[3] Register");
        chosenOption = sc.nextInt();

        switch (chosenOption) {
            case 1:
                unregisteredClientLoop();
                break;
            case 2:
                currentUser = Store.getInstance().logIn();
                if (currentUser != null) {
                    loopSelector(currentUser.getType());
                }
                break;
            case 3:
                currentUser = Store.getInstance().getUtility().signIn();
                if (currentUser != null) {
                    loopSelector(currentUser.getType());
                }
                break;
            default:
                main(null);
        }

    }

    public void loopSelector(UserType userType) {
        switch (userType) {
            case UNREGISTERED_CLIENT -> unregisteredClientLoop();
            case REGISTERED_CLIENT -> registeredClientLoop();
            case EMPLOYEE -> employeeLoop();
            case MANAGER -> managerLoop();
        }
    }

    public void unregisteredClientLoop() {
        // DUE
    }

    public void registeredClientLoop() {
        // DUE
    }

    public void employeeLoop() {
        // DUE
    }

    public void managerLoop() {
        // DUE
    }

}