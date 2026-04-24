package controller;

import model.store.Store;
import model.user.User;
import view.App;

import javax.swing.*;

public class MainLoopSelector {
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public MainLoopSelector(App frame, Store model) {
        this.frame = frame;
        this.model = model;
    }

    public void loopSelector() {
        User user = frame.getUser();
        this.frame.getLoginPanel().setVisible(false);
        this.frame.getSignupPanel().setVisible(false);
        switch (user.getType()) {
            case UNREGISTERED_CLIENT:
                JOptionPane.showMessageDialog(this.frame, "Welcome Unregistered Client!");
                this.frame.getUnregisteredMainPanel().setVisible(true);
                break;
            case REGISTERED_CLIENT:
                JOptionPane.showMessageDialog(this.frame, "Welcome Registered Client!");
                this.frame.getRegisteredMainPanel().setVisible(true);
                break;
            case EMPLOYEE:
                JOptionPane.showMessageDialog(this.frame, "Welcome Employee!");
                this.frame.getEmployeeMainPanel().setVisible(true);
                break;
            case MANAGER:
                JOptionPane.showMessageDialog(this.frame, "Welcome Manager!");
                this.frame.getManagerMainPanel().setVisible(true);
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