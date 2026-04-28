package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * It implements the app's welcome panel view
 * @author Ana O.R.
 * @version 1.0
 */
public class WelcomeP extends JPanel {
    private final JButton browseButton;
    private final JButton loginButton;
    private final JButton signupButton;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This page's constructor
     */
    public WelcomeP() {
        // asignar layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // crear componentes
        JLabel label = new JLabel("Welcome to Gifts for Geeks!");
        browseButton = new JButton("Browse as unregistered client");
        loginButton = new JButton("Log in");
        signupButton = new JButton("Sign up");

        // añadir componentes al panel
        this.add(label);
        this.add(browseButton);
        this.add(loginButton);
        this.add(signupButton);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        browseButton.addActionListener(c);
        loginButton.addActionListener(c);
        signupButton.addActionListener(c);
    }
}