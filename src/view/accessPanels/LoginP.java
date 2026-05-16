package view.accessPanels;

import view.ImageAdder;

import javax.swing.*;
import java.awt.*;

/**
 * It implements the log-in panel view
 * @author Ana O.R.
 * @version 1.0
 */
public class LoginP extends JPanel {
    private final JTextField username;
    private final JPasswordField password;
    private final JButton login;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public LoginP() {
        // asignar layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("LOGIN");
        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 20));

        JLabel image = ImageAdder.getImageLabel(".\\resources\\app\\default_user.png", 100, 100);

        // crear componentes
        JLabel label1 = new JLabel("Enter your Username:");
        username = new JTextField();
        username.setColumns(20);
        username.setMaximumSize(username.getPreferredSize());
        JLabel label2 = new JLabel("Enter you password:");
        password = new JPasswordField();
        password.setColumns(20);
        password.setMaximumSize(password.getPreferredSize());
        login = new JButton("Log in");

        // añadir componentes al panel
        this.add(Box.createVerticalGlue());

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(image);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(label1);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(username);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(label2);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(password);
        this.add(Box.createRigidArea(new Dimension(0, 10)));

        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(login);

        this.add(Box.createVerticalGlue());
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the login
     * @return the login
     */
    public JButton getLogin() {
        return login;
    }

    /**
     * It gets this panel's inputted password
     * @return this panel's inputted password
     */
    public String getPassword() {
        return new String(password.getPassword());
    }

    /**
     * It gets this panel's inputted username
     * @return this panel's inputted username
     */
    public String getUsername() {
        return username.getText();
    }
}