import java.time.LocalTime;
import java.util.Scanner;

public class MainApp {
    public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LocalTime todayTime = LocalTime.now();
        int chosenOption;

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
            case 2:
                logIn();
            case 3:
                register();
        }

    }

    public void unregisteredClientLoop() {
        // DUE
    }

    public void logIn() {
        // DUE
    }

    public void register() {
        // DUE
    }
}