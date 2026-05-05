package controller;

import model.store.Store;
import model.user.Manager;
import model.user.UnregisteredClient;
import view.App;
import view.WelcomeP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * It implements the app's welcome controller
 * @author Ana O.R.
 * @version 1.0
 */
public class WelcomeC extends MainLoopSelector implements ActionListener {
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
        super(frame, model);
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
            case "Manager Access" -> {
                boolean stop = false;
                while (!stop) {
                    String password = JOptionPane.showInputDialog("Please enter the password: ");
                    if (password.equals("password")) {
                        this.view.setVisible(false);
                        this.frame.changeCurrentUser(Manager.getInstance());
                        super.loopSelector();
                    } else {
                        int chosen_option = JOptionPane.showConfirmDialog(null, "Incorrect password, retry?");
                        switch (chosen_option) {
                            case JOptionPane.NO_OPTION, JOptionPane.CANCEL_OPTION -> stop = true;
                        }
                    }
                }
            }
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