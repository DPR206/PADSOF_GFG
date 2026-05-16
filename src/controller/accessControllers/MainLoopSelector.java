package controller.accessControllers;

import controller.Controller;
import controller.bannerControllers.*;
import model.store.Store;
import model.user.User;
import view.App;
import view.banners.*;

import javax.swing.*;

/**
 * Auxiliary class for shared Login & Signup events
 */
public abstract class MainLoopSelector implements Controller {
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
                new BannerUnregisteredC((BannerUnregistered) this.frame.getViewFromName("BANNER_UNREGISTERED"), frame,
                        model);
                break;
            case REGISTERED_CLIENT:
                this.frame.changeVisibleBanner("BANNER_REGISTERED");
                this.frame.changeVisibleCard("REGISTERED_MAIN");
                new BannerRegisteredC((BannerRegistered) this.frame.getViewFromName("BANNER_REGISTERED"), frame, model);
                break;
            case EMPLOYEE:
                JOptionPane.showMessageDialog(this.frame, "Welcome Employee!");
                this.frame.changeVisibleBanner("BANNER_EMPLOYEE");
                this.frame.changeVisibleCard("EMPLOYEE_MAIN");
                new BannerEmployeeC((BannerEmployee) this.frame.getViewFromName("BANNER_EMPLOYEE"), frame);
                this.frame.getEmployeeMainPanel().paintEverything();
                break;
            case MANAGER:
                JOptionPane.showMessageDialog(this.frame, "Welcome Manager!");
                this.frame.changeVisibleBanner("BANNER_MANAGER");
                this.frame.changeVisibleCard("MANAGER_MAIN");
                new BannerManagerC((BannerManager) this.frame.getViewFromName("BANNER_MANAGER"), frame);
                break;
            default:
                break;
        }
    }

    /**
     * It gets the controller's frame
     * @return the controller's frame
     */
    public App getFrame() {
        return frame;
    }

    /**
     * It gets the controller's model
     * @return the controller's model
     */
    public Store getModel() {
        return model;
    }
}