package controller;

import model.store.Store;
import model.user.User;
import view.App;
import view.LoginP;

import javax.swing.*;
import java.awt.event.*;

/**
 * It implements the log-in controller
 * @author Ana O.R.
 * @version 1.0
 */
public class LoginC extends MainLoopSelector implements ActionListener {
    private final LoginP view; /* view -> panel */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public LoginC(App frame, Store model) {
        super(frame, model);
        this.view = frame.getLoginPanel();

        // Enter para pulsar botón en último campo
        view.getPasswordField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    view.getLogin().doClick();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Log in")) {
            User user = super.getModel().logIn(view.getUsername(), view.getPassword());
            if (user == null) {
                JOptionPane.showMessageDialog(super.getFrame(), "Incorrect username or password", "",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                super.getFrame().changeCurrentUser(user);
                super.loopSelector();
            }
        }
    }
}