package view.accessPanels;

import javax.swing.*;
import java.awt.*;

/**
 * It implements the app's welcome panel view
 * @author Ana O.R.
 * @version 1.0
 */
public class WelcomeP extends JPanel {
    private final JButton browseButton;
    private final JButton loginButton;
    private final JButton signupButton;
    private final JButton managerAccess;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public WelcomeP() {
        // asignar layout
        this.setLayout(new GridLayout(2, 2));

        // crear componentes
        browseButton = new JButton("Browse as unregistered client");
        loginButton = new JButton("Log in");
        signupButton = new JButton("Sign up");
        managerAccess = new JButton("Manager Access");

        // añadir componentes al panel
        this.add(browseButton);
        this.add(loginButton);
        this.add(signupButton);
        this.add(managerAccess);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public JButton getBrowseButton() {
        return browseButton;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getManagerAccess() {
        return managerAccess;
    }

    public JButton getSignupButton() {
        return signupButton;
    }
}