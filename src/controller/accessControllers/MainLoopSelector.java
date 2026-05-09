package controller.accessControllers;

import model.store.Store;
import model.user.User;
import view.App;

import javax.swing.*;

/**
 * Auxiliary class for shared Login & Signup events
 */
public abstract class MainLoopSelector {
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor for the Main Loop Selector
     * @param frame the frame to be used
     * @param model the model to be used
     */
    public MainLoopSelector(App frame, Store model) {
        this.frame = frame;
        this.model = model;
    }

    /**
     * It selects which main page should be seen by the user via its type
     */
    public void loopSelector() {
        User user = frame.getUser();
        switch (user.getType()) {
            case UNREGISTERED_CLIENT:
                JOptionPane.showMessageDialog(this.frame, "Welcome Unregistered Client!");
                this.frame.changeVisibleBanner("BANNER_UNREGISTERED");
                this.frame.changeVisibleCard("UNREGISTERED_MAIN");
                break;
            case REGISTERED_CLIENT:
                JOptionPane.showMessageDialog(this.frame, "Welcome Registered Client!");
                this.frame.changeVisibleBanner("BANNER_REGISTERED");
                this.frame.changeVisibleCard("REGISTERED_MAIN");
                break;
            case EMPLOYEE:
                JOptionPane.showMessageDialog(this.frame, "Welcome Employee!");
                this.frame.changeVisibleBanner("BANNER_EMPLOYEE");
                this.frame.changeVisibleCard("EMPLOYEE_MAIN");
                this.frame.getEmployeeMainPanel().paintEverything();
                break;
            case MANAGER:
                JOptionPane.showMessageDialog(this.frame, "Welcome Manager!");
                this.frame.changeVisibleBanner("BANNER_MANAGER");
                this.frame.changeVisibleCard("MANAGER_MAIN");
                break;
            default:
                JOptionPane.showMessageDialog(frame, "You shouldn't be able to see this, burn the app");
                break;
        }
    }

    public App getFrame() {
        return frame;
    }

    public Store getModel() {
        return model;
    }
}