package view;

import model.utilities.IdType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * It implements the sign-up panel view
 * @author Ana O.R.
 * @version 1.0
 */
public class SignupP extends JPanel {
    private final JTextField username;
    private final JPasswordField password;
    private final JPasswordField password2;
    private final JTextField idNumber;
    private final JButton signup;
    private final JRadioButton idDniType;
    private final JRadioButton idNieType;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public SignupP() {
        // asignar layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // crear componentes
        JTextArea textArea = new JTextArea(
                " Make sure your password has: \n" + "  -At least 8 characters\n" + "  -Upper case letters\n" +
                "  -Lower case letters\n" + "  -Numbers\n" + "  -Special characters");
        textArea.setEditable(false);
        textArea.setFocusable(false);
        JLabel usernameLabel = new JLabel("Enter your Username:");
        username = new JTextField();
        username.setColumns(10);
        JLabel passwordLabel = new JLabel("Enter you password:");
        password = new JPasswordField();
        JLabel password2Label = new JLabel("Repeat you password:");
        password2 = new JPasswordField();
        password.setColumns(10);
        JLabel idNumberLabel = new JLabel("Enter your identification number:");
        idNumber = new JTextField();
        password.setColumns(10);
        signup = new JButton("Sign up");
        JLabel idTypeLabel = new JLabel("Select identification type:");
        idDniType = new JRadioButton("DNI");
        idNieType = new JRadioButton("NIE");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(idDniType);
        buttonGroup.add(idNieType);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(idDniType);
        buttonPanel.add(idNieType);
        buttonPanel.setBackground(new Color(246, 243, 238)); // Beige

        // añadir componentes al panel
        this.add(ImageAdder.getImageLabel(".\\resources\\default_user.png", 50, 50));
        this.add(textArea);
        /* Username */
        this.add(usernameLabel);
        this.add(username);
        /* Password */
        this.add(passwordLabel);
        this.add(password);
        this.add(password2Label);
        this.add(password2);
        /* ID */
        this.add(idTypeLabel);
        this.add(buttonPanel);
        this.add(idNumberLabel);
        this.add(idNumber);
        /* Button :) */
        this.add(signup);

        // Enter para pulsar botón en último campo
        idNumber.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    signup.doClick();
                }
            }
        });
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public String getDni() {
        return idNumber.getText();
    }

    public IdType getIdType() {
        if (this.idDniType.isSelected()) {
            return IdType.DNI;
        } else if (this.idNieType.isSelected()) {
            return IdType.NIE;
        }
        return null;
    }

    public String getPassword() {
        return new String(password.getPassword());
    }

    public String getPassword2() {
        return new String(password2.getPassword());
    }

    public String getUsername() {
        return username.getText();
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        idDniType.addActionListener(c);
        idNieType.addActionListener(c);
        signup.addActionListener(c);
    }
}