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
    private final JButton employeeAccess;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public WelcomeP() {
        // asignar layout
        //this.setLayout(new GridLayout(2, 2));
    	this.setLayout(new GridLayout(2, 3));

        // crear componentes
        loginButton = new JButton("Log in");
        signupButton = new JButton("Sign up");
        browseButton = new JButton("Browse as unregistered client");
        managerAccess = new JButton("Manager Access");
        employeeAccess = new JButton("Employee Access");
        
        JLabel imagenLabel = new JLabel();
        
        // Usamos el método que ya tienes para escalar la imagen
        // Ajusta la ruta y el tamaño (por ejemplo 100x100) según necesites
        ImageIcon icono = new ImageIcon("resources/app/logo.png");
        imagenLabel.setIcon(getScaledImage(icono, 300, 300));
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // añadir componentes al panel
        this.add(browseButton);
        this.add(loginButton);
        this.add(signupButton);
        this.add(managerAccess);
        this.add(imagenLabel);
        this.add(employeeAccess);
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

	public JButton getEmployeeAccess() {
		return employeeAccess;
	}
    
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
    
}