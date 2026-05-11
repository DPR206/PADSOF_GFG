package view.accessPanels;

import view.ImageAdder;

import javax.swing.*;

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
        this.add(ImageAdder.getImageLabel(".\\resources\\app\\default_user.png", 50, 50));
        this.add(label1);
        this.add(username);
        this.add(label2);
        this.add(password);
        this.add(login);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
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

    public JPasswordField getPasswordField() {
        return password;
    }

    /**
     * It gets this panel's inputted username
     * @return this panel's inputted username
     */
    public String getUsername() {
        return username.getText();
    }
}