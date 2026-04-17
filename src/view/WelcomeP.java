package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WelcomeP extends JPanel {
    private final JButton browseButton;
    private final JButton loginButton;
    private final JButton signupButton;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public WelcomeP() {
        // asignar layout
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

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