package controller.accessControllers;

import model.store.Store;
import model.user.User;
import view.App;
import view.accessPanels.LoginP;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * It implements the log-in controller
 * @author Ana O.R.
 * @version 1.0
 */
public class LoginC extends MainLoopSelector {
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
        initializeActions();
    }

    @Override
    public void initializeActions() {
        /* Enter to press key */
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    view.getLogin().doClick();
                }
            }
        });

        view.getLogin().addActionListener(e -> {
            User user = super.getModel().logIn(view.getUsername(), view.getPassword());
            if (user == null) {
                JOptionPane.showMessageDialog(super.getFrame(), "Incorrect username or password", "Error log in",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                super.getFrame().changeCurrentUser(user);
                super.loopSelector();
            }
        });
    }
}