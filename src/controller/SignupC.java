package controller;

import model.store.Store;
import view.App;
import view.SignupP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * It implements the sign-up controller
 * @author Ana O.R.
 * @version 1.0
 */
public class SignupC implements ActionListener {
    private final SignupP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public SignupC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getSignupPanel();
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Sign up")) { /* "Sign up" pressed */
            /*try {
                model.signIn(view.getUsername(), view.getPassword(), view.getDni());
            } catch (PasswordNotValid exception) {}*/
        }
    }
}