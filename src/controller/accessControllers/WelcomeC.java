package controller.accessControllers;

import model.store.Store;
import model.user.Manager;
import model.user.UnregisteredClient;
import view.App;
import view.accessPanels.WelcomeP;

import javax.swing.*;
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
                frame.changeVisibleCard("UNREGISTERED_MAIN");
            }
            case "Log in" -> frame.changeVisibleCard("LOGIN");
            case "Sign up" -> frame.changeVisibleCard("SIGNUP");
            case "Manager Access" -> {
                boolean stop = false;
                while (!stop) {
                    JPanel panel = new JPanel();
                    JLabel label = new JLabel("Enter a password:");
                    JPasswordField passwordField = new JPasswordField(10);
                    panel.add(label);
                    panel.add(passwordField);
                    String[] options = new String[]{"OK", "Cancel"};
                    int option =
                            JOptionPane.showOptionDialog(null, panel, "Manager Access", JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    if (option == 0) {
                        String password = new String(passwordField.getPassword());

                        if (password.equals("password")) {
                            this.frame.changeCurrentUser(Manager.getInstance());
                            stop = true;
                            super.loopSelector();
                        } else {
                            int chosen_option = JOptionPane.showConfirmDialog(null, "Incorrect password, retry?");
                            switch (chosen_option) {
                                case JOptionPane.NO_OPTION, JOptionPane.CANCEL_OPTION -> stop = true;
                            }
                        }
                    } else {
                        stop = true;
                    }
                }
            }
        }
    }
}