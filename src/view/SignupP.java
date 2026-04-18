package view;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * It implements the sign-up panel view
 * @author Ana O.R.
 * @version 1.0
 */
public class SignupP extends JPanel {
    private final JTextField username;
    private final JTextField password;
    private final JTextField dni;
    private final JButton signup;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public SignupP() {
        // asignar layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // crear componentes
        JTextArea textArea = new JTextArea(
                "Make sure your password has: \n " + "-At least 8 characters\n" + "-Upper case letters\n" +
                "-Lower case letters\n" + "-Numbers\n" + "-Special characters");
        JLabel label1 = new JLabel("Enter your Username:");
        username = new JTextField();
        username.setColumns(10);
        JLabel label2 = new JLabel("Enter you password:");
        password = new JTextField();
        password.setColumns(10);
        JLabel label3 = new JLabel("Enter your dni:");
        dni = new JTextField();
        password.setColumns(10);
        signup = new JButton("Sign up");

        // añadir componentes al panel
        this.add(textArea);
        this.add(label1);
        this.add(username);
        this.add(label2);
        this.add(password);
        this.add(label3);
        this.add(dni);
        this.add(signup);
    }

    // método que actualiza el valor de los campos
    public void update() {
        username.setText("");
        username.grabFocus();
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public String getDni() {
        return dni.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public String getUsername() {
        return username.getText();
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        signup.addActionListener(c);
    }
}