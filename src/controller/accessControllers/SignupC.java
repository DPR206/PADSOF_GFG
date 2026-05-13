package controller.accessControllers;

import model.store.Store;
import model.user.User;
import model.utilities.exceptions.*;
import view.App;
import view.accessPanels.SignupP;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * It implements the sign-up controller
 * @author Ana O.R.
 * @version 1.0
 */
public class SignupC extends MainLoopSelector {
    private final SignupP view;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public SignupC(App frame, Store model) {
        super(frame, model);
        this.view = frame.getSignupPanel();
        initializeActions();
    }

    @Override
    public void initializeActions() {
        /* Enter to press key */
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    view.getSignup().doClick();
                }
            }
        });

        /* Sign up button */
        view.getSignup().addActionListener(e -> {
            signUp();
        });

        view.getFirstShowPassword().addActionListener(e -> {
            view.toggleShownPassword();
        });

        view.getSecondShowPassword().addActionListener(e -> {
            view.toggleShownPassword();
        });

        view.getLoginBtn().addActionListener(e -> {
            super.getFrame().changeVisibleCard("LOGIN");
        });
    }

    /**
     * It triggers and checks whether the signup was successful
     */
    private void signUp() {
        if (view.getIdType() == null) {
            JOptionPane.showMessageDialog(null, "You must select an idType", "Warning :(", JOptionPane.ERROR_MESSAGE);
        } else if (!(view.getFirstPassword().equals(view.getSecondPassword()))) {
            JOptionPane.showMessageDialog(null, "Passwords did not match", "Warning :(", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                User user = super.getModel().signIn(view.getUsername(), view.getFirstPassword(), view.getIdNumber(),
                        view.getIdType());
                if (user != null) {
                    JOptionPane.showMessageDialog(null,
                            "Signed up successfully :]\n" + "Welcome abroad " + view.getUsername());
                    super.getFrame().changeCurrentUser(user);
                    super.loopSelector();
                }
            } catch (UsernameTaken e) {
                JOptionPane.showMessageDialog(null, e.toString(), "Signup failed", JOptionPane.ERROR_MESSAGE);
            } catch (PasswordNotValid e) {
                JOptionPane.showMessageDialog(null, e.toString(), "Signup failed", JOptionPane.ERROR_MESSAGE);
            } catch (InvalidDni e) {
                JOptionPane.showMessageDialog(null, e.toString(), "Signup failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}