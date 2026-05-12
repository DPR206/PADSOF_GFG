package view.employeePanels;

import java.awt.*;

import javax.swing.*;

public class EmployeeAccess extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField userField;
    private JPasswordField passwordField;

    public EmployeeAccess(Frame padre) {
    	
    	setLayout(new BorderLayout(10, 10));
    	JPanel information = new JPanel();
		information.setLayout(new BoxLayout(information, BoxLayout.Y_AXIS));
		information.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    	
    	JLabel label1 = new JLabel("Enter a username:");
    	label1.setFont(new Font("SansSerif", Font.BOLD, 14));
    	label1.setAlignmentX(Component.LEFT_ALIGNMENT);
    	
        userField = new JTextField(10);
        
        userField.setPreferredSize(new Dimension(200, 30));
        userField.setMaximumSize(new Dimension(200, 30)); // Evita que se estire al máximo
        userField.setAlignmentX(Component.LEFT_ALIGNMENT);
        
    	JLabel label2 = new JLabel("Enter a password:");
    	label2.setFont(new Font("SansSerif", Font.BOLD, 14));
    	label2.setAlignmentX(Component.LEFT_ALIGNMENT);
    	
        passwordField = new JPasswordField(10);
        
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.setMaximumSize(new Dimension(200, 30)); // Evita que se estire al máximo
        passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        information.add(label1);
        information.add(userField);
        information.add(Box.createVerticalStrut(40));
        information.add(label2);
        information.add(passwordField);
        
        add(information, BorderLayout.CENTER);
    }
    
    public String getUsername() {
        return userField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

}
