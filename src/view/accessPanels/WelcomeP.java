package view.accessPanels;

import view.ImageAdder;

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
    private final JButton employeeAccess;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public WelcomeP() {
//
        loginButton = new JButton("Log in");
        loginButton.setPreferredSize(new Dimension(200, 30));
        loginButton.setMinimumSize(loginButton.getPreferredSize());
        signupButton = new JButton("Sign up");
        signupButton.setPreferredSize(new Dimension(200, 30));
        signupButton.setMinimumSize(signupButton.getPreferredSize());
        browseButton = new JButton("Browse as unregistered client");
        browseButton.setPreferredSize(new Dimension(200, 30));
        browseButton.setMinimumSize(browseButton.getPreferredSize());
        managerAccess = new JButton("Manager Access");
        managerAccess.setPreferredSize(new Dimension(200, 30));
        managerAccess.setMinimumSize(managerAccess.getPreferredSize());
        employeeAccess = new JButton("Employee Access");
        employeeAccess.setPreferredSize(new Dimension(200, 30));
        employeeAccess.setMinimumSize(employeeAccess.getPreferredSize());

        JLabel imagenLabel = new JLabel();

        ImageIcon icono = new ImageIcon("resources/app/logo.png");
        imagenLabel.setIcon(getScaledImage(icono, 180, 180));
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        JPanel columns = new JPanel(new GridLayout(1, 3, 20, 0));

        JPanel clientPanel = new JPanel();
        clientPanel.add(Box.createVerticalGlue());
        clientPanel.setLayout(new BoxLayout(clientPanel, BoxLayout.Y_AXIS));
        JLabel clientIcon = ImageAdder.getImageLabel(".\\resources\\app\\client.png", 180, 180);
        clientIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        clientPanel.add(clientIcon);
        JLabel forClients = new JLabel("For clients:");
        forClients.setAlignmentX(Component.CENTER_ALIGNMENT);
        clientPanel.add(forClients);
        clientPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clientPanel.add(loginButton);
        clientPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        clientPanel.add(signupButton);
        clientPanel.add(Box.createVerticalGlue());

        JPanel unregisteredPanel = new JPanel();
        unregisteredPanel.add(Box.createVerticalGlue());
        unregisteredPanel.setLayout(new BoxLayout(unregisteredPanel, BoxLayout.Y_AXIS));
        imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        unregisteredPanel.add(imagenLabel);
        unregisteredPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel firstTime = new JLabel("First time here?:");
        firstTime.setAlignmentX(Component.CENTER_ALIGNMENT);
        unregisteredPanel.add(firstTime);
        unregisteredPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        browseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        unregisteredPanel.add(browseButton);
        unregisteredPanel.add(Box.createVerticalGlue());

        JPanel staffPanel = new JPanel();
        staffPanel.add(Box.createVerticalGlue());
        staffPanel.setLayout(new BoxLayout(staffPanel, BoxLayout.Y_AXIS));
        JLabel staffIcon = ImageAdder.getImageLabel(".\\resources\\app\\employee.png", 180, 180);
        staffIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        staffPanel.add(staffIcon);
        JLabel forStaff = new JLabel("For staff:");
        forStaff.setAlignmentX(Component.CENTER_ALIGNMENT);
        staffPanel.add(forStaff);
        staffPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        employeeAccess.setAlignmentX(Component.CENTER_ALIGNMENT);
        staffPanel.add(employeeAccess);
        staffPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        managerAccess.setAlignmentX(Component.CENTER_ALIGNMENT);
        staffPanel.add(managerAccess);
        staffPanel.add(Box.createVerticalGlue());

        columns.add(clientPanel);
        columns.add(unregisteredPanel);
        columns.add(staffPanel);

        JPanel titlePanel = new JPanel();
        JLabel title = new JLabel("WELCOME TO GIFTS FOR GEEKS!");
        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(title);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(columns, BorderLayout.CENTER);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the browse button
     * @return the browse button
     */
    public JButton getBrowseButton() {
        return browseButton;
    }

    /**
     * It gets the employee access
     * @return the employee access
     */
    public JButton getEmployeeAccess() {
        return employeeAccess;
    }

    /**
     * It gets the login button
     * @return the login button
     */
    public JButton getLoginButton() {
        return loginButton;
    }

    /**
     * It gets the manager access
     * @return the manager access
     */
    public JButton getManagerAccess() {
        return managerAccess;
    }

    /**
     * It gets an icon from an image icon with a certain width and height
     * @param imageIcon the desired image icon
     * @param width     the desired width
     * @param height    the desired height
     * @return the icon
     */
    private Icon getScaledImage(ImageIcon imageIcon, int width, int height) {
        try {
            if (imageIcon == null || imageIcon.getImage() == null) {
                System.err.println("Error: La imagen es nula.");
                return null;
            }

            Image img = imageIcon.getImage();
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            return new ImageIcon(scaledImg);
        } catch (Exception e) {
            System.err.println("Error al escalar la imagen: " + e.getMessage());
            return null;
        }
    }

    /**
     * It gets the signup button
     * @return the signup button
     */
    public JButton getSignupButton() {
        return signupButton;
    }

}