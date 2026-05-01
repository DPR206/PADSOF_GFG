package view;

import javax.swing.*;
import java.awt.event.ActionListener;

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

        // crear componentes
        JLabel label1 = new JLabel("Enter your Username:");
        username = new JTextField();
        username.setColumns(10);
        JLabel label2 = new JLabel("Enter you password:");
        password = new JPasswordField();
        password.setColumns(10);
        login = new JButton("Log in");

        // añadir componentes al panel
        this.add(ImageAdder.getImageLabel(".\\resources\\default_user.png", 50, 50));
        this.add(label1);
        this.add(username);
        this.add(label2);
        this.add(password);
        this.add(login);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

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

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        login.addActionListener(c);
    }

    public JPasswordField getPasswordField() {
        return password;
    }

    public JButton getLogin() {
        return login;
    }
}