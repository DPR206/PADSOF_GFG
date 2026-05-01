package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManagerGestionarPacks extends JPanel{
	
	private JButton home = new JButton("Home");
	
	public ManagerGestionarPacks() {
		super();
		
		this.setLayout(new BorderLayout());
		
		JPanel titleScreen = new JPanel();
		
		//Banner temporal
		titleScreen.add(this.home);
    	titleScreen.add(new JLabel("Gifts for Geeks: Manager"));
    	titleScreen.add(this.home);
    	this.add(titleScreen, BorderLayout.NORTH);
	}	
}
