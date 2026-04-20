package graphicInt;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class HomePanel extends JPanel{
	
	public HomePanel() {
		super();
		this.setLayout(new BorderLayout());
		
		JPanel titles = new JPanel();
		titles.setLayout(new GridLayout(1,3));
		JPanel home = new JPanel();
		home.add(new JLabel("Home"));
		titles.add(home);
		
		JPanel name = new JPanel();
		name.add(new JLabel("GIFTS FOR GEEKS"));
		titles.add(name);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,2));
		
		buttons.add(new JButton("Cart"));
		buttons.add(new JButton("Account"));
		titles.add(buttons);
		
		this.add(titles, BorderLayout.NORTH);
		
		/*PRODUCTOS*/
		JPanel products = new JPanel();
		products.setLayout(new BorderLayout());
		/*SearchBar*/
		JPanel searcher = new JPanel(new GridLayout(1,2));
		searcher.add(new JTextField());
		searcher.add(new JButton("BUSCAR"));
		products.add(searcher, BorderLayout.NORTH);
		
		this.add(products, BorderLayout.CENTER);
		/*Añadimos products a homepanel*/
		
	}
}
