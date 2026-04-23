package controller;

import model.store.Store;
import model.user.User;
import model.utilities.exceptions.*;
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
        if (e.getActionCommand().equals("DNI")) {
            view.deselectNie();
        }
        if (e.getActionCommand().equals("NIE")) {
            view.deselectDni();
        }
        if (e.getActionCommand().equals("Sign up")) { /* "Sign up" pressed */
            if (view.getIdType() == null) {
                JOptionPane.showMessageDialog(null, "You must select an idType", "Warning :(",
                        JOptionPane.ERROR_MESSAGE);
            } else if (!(view.getPassword().equals(view.getPassword2()))) {
                JOptionPane.showMessageDialog(null, "Passwords did not match", "Warning :(", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    User user = model.signIn(view.getUsername(), view.getPassword(), view.getDni(), view.getIdType());
                    if (user != null) {
                        JOptionPane.showMessageDialog(null,
                                "Signed up successfully :)\n" + "Welcome abroad " + view.getUsername());
                    }
                } catch (UsernameTaken exception1) {
                    JOptionPane.showMessageDialog(null, exception1.toString(), "Warning :(", JOptionPane.ERROR_MESSAGE);
                } catch (PasswordNotValid exception2) {
                    JOptionPane.showMessageDialog(null, exception2.toString(), "Warning :(", JOptionPane.ERROR_MESSAGE);
                } catch (InvalidDni exception3) {
                    JOptionPane.showMessageDialog(null, exception3.toString(), "Warning :(", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}