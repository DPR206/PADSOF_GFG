package view.accessPanels;

import model.utilities.IdType;
import view.ImageAdder;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

import static main.Main.brownColour;

/**
 * It implements the sign-up panel view
 * @author Ana O.R.
 * @version 1.0
 */
public class SignupP extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private final JButton firstShowPassword = new JButton("\uD83D\uDC41");
    private final JButton secondShowPassword = new JButton("\uD83D\uDC41");
    private final JPanel westPanel = new JPanel();
    private final JPanel eastPanel = new JPanel();
    private final JButton loginBtn = new JButton("Login");
    private JTextField username;
    private JPasswordField hiddenPassword1;
    private JPasswordField hiddenPassword2;
    private JTextField shownPassword1;
    private JTextField shownPassword2;
    private JTextField idNumber;
    private JButton signup;
    private JRadioButton idDniType;
    private JRadioButton idNieType;
    private boolean shownPassword = false;
    private String firstPasswordInput = "";
    private String secondPasswordInput = "";

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public SignupP() {
        this.setLayout(new BorderLayout());

        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setPreferredSize(new Dimension(200, 400));
        eastPanel.setBorder(BorderFactory.createLineBorder(brownColour));
        firstShowPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        secondShowPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        paintEverything();
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public void paintEverything() {
        this.removeAll();
        westPanel.removeAll();
        eastPanel.removeAll();

        JLabel imageLabel = ImageAdder.getImageLabel(".\\resources\\app\\default_user.png", 100, 100);

        JTextArea textArea = getJTextArea();

        JLabel usernameLabel = new JLabel("Enter your Username:");
        username = new JTextField();
        username.setColumns(50);
        username.setMaximumSize(username.getPreferredSize());

        JPanel firstPasswordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JLabel passwordLabel = new JLabel("Enter you password:");
        hiddenPassword1 = new JPasswordField(firstPasswordInput);
        hiddenPassword1.setColumns(50);
        hiddenPassword1.setMaximumSize(hiddenPassword1.getPreferredSize());
        shownPassword1 = new JTextField(firstPasswordInput);
        shownPassword1.setColumns(50);
        shownPassword1.setMaximumSize(shownPassword1.getPreferredSize());

        firstShowPassword.setForeground(new Color(64, 0, 0));
        firstShowPassword.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        firstShowPassword.setBorderPainted(false);
        firstShowPassword.setContentAreaFilled(false);

        if (shownPassword) {
            firstPasswordPanel.add(shownPassword1);
        } else {
            firstPasswordPanel.add(hiddenPassword1);
        }
        firstPasswordPanel.add(firstShowPassword);

        JPanel secondPasswordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JLabel hiddenPassword2Label = new JLabel("Repeat you password:");
        hiddenPassword2 = new JPasswordField(secondPasswordInput);
        hiddenPassword2.setColumns(50);
        hiddenPassword2.setMaximumSize(hiddenPassword2.getPreferredSize());
        shownPassword2 = new JTextField(secondPasswordInput);
        shownPassword2.setColumns(50);
        shownPassword2.setMaximumSize(shownPassword2.getPreferredSize());

        secondShowPassword.setForeground(new Color(64, 0, 0));
        secondShowPassword.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        secondShowPassword.setBorderPainted(false);
        secondShowPassword.setContentAreaFilled(false);

        if (shownPassword) {
            secondPasswordPanel.add(shownPassword2);
        } else {
            secondPasswordPanel.add(hiddenPassword2);
        }
        secondPasswordPanel.add(secondShowPassword);

        JLabel idNumberLabel = new JLabel("Enter your identification number:");
        idNumber = new JTextField();
        idNumber.setColumns(50);
        idNumber.setMaximumSize(idNumber.getPreferredSize());

        signup = new JButton("Sign up");
        signup.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JLabel idTypeLabel = new JLabel("Select identification type:");
        idDniType = new JRadioButton("DNI");
        idDniType.setCursor(new Cursor(Cursor.HAND_CURSOR));
        idNieType = new JRadioButton("NIE");
        idNieType.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(idDniType);
        buttonGroup.add(idNieType);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        buttonPanel.add(idDniType);
        buttonPanel.add(idNieType);

        /* West panel */
        westPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0)); // Padding :)
        westPanel.add(Box.createVerticalGlue());

        textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPanel.add(textArea);
        westPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        /* Username */
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPanel.add(usernameLabel);
        westPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        username.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPanel.add(username);
        westPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        /* Password */
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPanel.add(passwordLabel);
        westPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        firstPasswordPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPanel.add(firstPasswordPanel);
        westPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        hiddenPassword1.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPanel.add(hiddenPassword2Label);
        westPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        secondPasswordPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPanel.add(secondPasswordPanel);
        westPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        /* ID */
        idTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPanel.add(idTypeLabel);
        westPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPanel.add(buttonPanel);
        westPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        idNumberLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPanel.add(idNumberLabel);
        westPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        idNumber.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPanel.add(idNumber);
        westPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        /* Button */
        signup.setAlignmentX(Component.LEFT_ALIGNMENT);
        westPanel.add(signup);

        westPanel.add(Box.createVerticalGlue());

        /* East panel */
        eastPanel.add(Box.createVerticalGlue());
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eastPanel.add(imageLabel);
        eastPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel alreadyHave = new JLabel("Already have an account?");
        alreadyHave.setAlignmentX(Component.CENTER_ALIGNMENT);
        eastPanel.add(alreadyHave);
        eastPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        eastPanel.add(loginBtn);
        eastPanel.add(Box.createVerticalGlue());

        this.add(westPanel, BorderLayout.CENTER);
        this.add(eastPanel, BorderLayout.EAST);

        this.revalidate();
        this.repaint();
    }

    public void toggleShownPassword() {
        if (this.shownPassword) {
            firstShowPassword.setText("\uD83D\uDD12");
            secondShowPassword.setText("\uD83D\uDD12");
            this.firstPasswordInput = shownPassword1.getText();
            this.secondPasswordInput = shownPassword2.getText();
        } else {
            firstShowPassword.setText("\uD83D\uDC41");
            secondShowPassword.setText("\uD83D\uDC41");
            this.firstPasswordInput = new String(hiddenPassword1.getPassword());
            this.secondPasswordInput = new String(hiddenPassword2.getPassword());
        }
        this.shownPassword = !this.shownPassword;
        this.paintEverything();
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the password
     * @return the password
     */
    public String getFirstPassword() {
        if (shownPassword) {
            return shownPassword1.getText();
        }
        return new String(hiddenPassword1.getPassword());
    }

    public JButton getFirstShowPassword() {
        return firstShowPassword;
    }

    /**
     * It gets this panel's inputted password
     * @return this panel's inputted password
     */
    public String getIdNumber() {
        return idNumber.getText();
    }

    /**
     * It gets the id type
     * @return the id type
     */
    public IdType getIdType() {
        if (this.idDniType.isSelected()) {
            return IdType.DNI;
        } else if (this.idNieType.isSelected()) {
            return IdType.NIE;
        }
        return null;
    }

    /**
     * It gets the JTextArea
     * @return the JTextArea
     */
    private JTextArea getJTextArea() {
        JTextArea textArea = new JTextArea("""
                                            Make sure your password has:\s
                                             -At least 8 characters
                                             -Upper case letters
                                             -Lower case letters
                                             -Numbers
                                             -Special characters\
                                           """);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setLineWrap(true);
        textArea.setColumns(50);
        textArea.setRows(6);
        textArea.setMaximumSize(textArea.getPreferredSize());
        return textArea;
    }

    public JButton getLoginBtn() {
        return loginBtn;
    }

    /**
     * It gets the password 2
     * @return the password 2
     */
    public String getSecondPassword() {
        if (shownPassword) {
            return shownPassword2.getText();
        }
        return new String(hiddenPassword2.getPassword());
    }

    public JButton getSecondShowPassword() {
        return secondShowPassword;
    }

    /**
     * It gets the signup
     * @return the signup
     */
    public JButton getSignup() {
        return signup;
    }

    /**
     * It gets the username
     * @return the username
     */
    public String getUsername() {
        return username.getText();
    }
}