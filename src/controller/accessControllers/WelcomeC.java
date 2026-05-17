package controller.accessControllers;

import model.store.Store;
import model.user.*;
import view.App;
import view.accessPanels.WelcomeP;
import view.employeePanels.EmployeeAccess;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * It implements the app's welcome controller
 * @author Ana O.R.
 * @version 1.0
 */
public class WelcomeC extends MainLoopSelector {
    private final WelcomeP view;
    private final App frame;

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

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.getBrowseButton().addActionListener(e -> {
            this.frame.setUnregisteredClient(new UnregisteredClient(false));
            try {
                frame.changeVisibleCard("UNREGISTERED_MAIN");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getLoginButton().addActionListener(e -> {
            try {
                frame.changeVisibleCard("LOGIN");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getSignupButton().addActionListener(e -> {
            try {
                frame.changeVisibleCard("SIGNUP");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getManagerAccess().addActionListener(e -> {
            boolean stop = false;
            while (!stop) {
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Enter a password:");
                JPasswordField passwordField = new JPasswordField(10);
                panel.add(label);
                panel.add(passwordField);
                String[] options = new String[]{"OK", "Cancel"};
                int option = JOptionPane.showOptionDialog(null, panel, "Manager Access", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (option == 0) {
                    String password = new String(passwordField.getPassword());

                    if (password.equals("password")) {
                        Manager managerSesion = Manager.getInstance();
                        Store.getInstance().setManager(managerSesion);
                        this.frame.changeCurrentUser(managerSesion);
                        stop = true;
                        try {
                            super.loopSelector();
                        } catch (BadLocationException ex) {
                            throw new RuntimeException(ex);
                        }
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
        });

        view.getEmployeeAccess().addActionListener(e -> {
            boolean stop = false;
            while (!stop) {
                EmployeeAccess accessPanel = new EmployeeAccess(frame);
                String[] options = new String[]{"OK", "Cancel"};
                int option =
                        JOptionPane.showOptionDialog(null, accessPanel, "Employee Access", JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                if (option == 0) {
                    String username = accessPanel.getUsername();
                    String password = accessPanel.getPassword();
                    User user;

                    if ((user = super.getModel().logIn(username, password)) != null) {
                        this.frame.changeCurrentUser(user);
                        stop = true;
                        try {
                            super.loopSelector();
                        } catch (BadLocationException ex) {
                            throw new RuntimeException(ex);
                        }
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
        });
    }
}