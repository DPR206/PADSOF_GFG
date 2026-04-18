package controller;

import model.utilities.exceptions.PasswordNotValid;
import model.utilities.exceptions.UsernameTaken;
import model.store.Store;
import model.user.User;
import view.App;
import view.SignupP;

import javax.swing.*;
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
            try {
                User user = model.signIn(view.getUsername(), view.getPassword(), view.getDni());
                if (user != null) {
                    JOptionPane.showMessageDialog(null,
                            "Signed up successfully :)\n" + "Welcome abroad " + view.getUsername());
                }
            } catch (UsernameTaken exception1) {
                JOptionPane.showMessageDialog(null, exception1.toString());
            } catch (PasswordNotValid exception2) {
                JOptionPane.showMessageDialog(null, exception2.toString());
            }
        }
    }
}