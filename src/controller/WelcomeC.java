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
    public WelcomeC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getWelcomePanel();
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Browse as unregistered client")) { /*"Browse as unregistered client" pressed */
            this.frame.setUnregisteredClient(new UnregisteredClient(false));
        	this.showBrowseAsUnregistered();
        } else if (e.getActionCommand().equals("Log in")) { /* "Log in" pressed */
            this.showLogin();
        } else if (e.getActionCommand().equals("Sign up")) { /* "Sign up" pressed */
            this.showSignUp();
        }
    }

    private void showBrowseAsUnregistered() {
        // No hace falta validar la vista ni modificar el modelo
        /* Show new view */
        this.view.setVisible(false);
        this.frame.getUnregisteredMainPanel().setVisible(true);
    }

    private void showLogin() {
        /* Show new view */
        this.view.setVisible(false);
        this.frame.getLoginPanel().setVisible(true);
    }

    private void showSignUp() {
        /* Show new view */
        this.view.setVisible(false);
        this.frame.getSignupPanel().setVisible(true);
    }
}