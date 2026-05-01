package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ManagerGestionarPacks extends JPanel{
	
	private JButton home = new JButton("Home");
	private List<JButton> packsButtons = new ArrayList<>();
	private JTextField text = new JTextField(20);
	
	public ManagerGestionarPacks() {
		super();
		
		this.setLayout(new BorderLayout());
		
		JPanel titleScreen = new JPanel();
		
		//Banner temporal
		titleScreen.add(this.home);
    	titleScreen.add(new JLabel("Gifts for Geeks: Manager"));
    	titleScreen.add(this.home);
    	this.add(titleScreen, BorderLayout.NORTH);
    	
    	JPanel mainThings = new JPanel();
    	mainThings.setLayout(new BorderLayout());
    	
    	JPanel packs = new JPanel();
    	packs.setLayout(new GridLayout(0,1));
    	
	}	
}
