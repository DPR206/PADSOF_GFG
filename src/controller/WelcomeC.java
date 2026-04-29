package controller;

import model.store.Store;
import model.user.UnregisteredClient;
import view.App;
import view.WelcomeP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * It implements the app's welcome controller
 * @author Ana O.R.
 * @version 1.0
 */
public class WelcomeC implements ActionListener {
    private final WelcomeP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public WelcomeC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getWelcomePanel();
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Browse as unregistered client" -> {
                this.frame.setUnregisteredClient(new UnregisteredClient(false));
                this.showBrowseAsUnregistered();
            }
            case "Log in" -> this.showLogin();
            case "Sign up" -> this.showSignUp();
        }
    }

    /**
     * It shows the unregistered client's main panel
     */
    private void showBrowseAsUnregistered() {
        this.view.setVisible(false);
        this.frame.getUnregisteredMainPanel().setVisible(true);
    }

    /**
     * It shows the login page
     */
    private void showLogin() {
        this.view.setVisible(false);
        this.frame.getLoginPanel().setVisible(true);
    }

    /**
     * It shows the signup page
     */
    private void showSignUp() {
        this.view.setVisible(false);
        this.frame.getSignupPanel().setVisible(true);
    }
}