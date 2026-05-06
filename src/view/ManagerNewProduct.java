package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import view.banners.BannerManager;

public class ManagerNewProduct extends JPanel{
	   private JButton comics = new JButton("AÑADIR UN CÓMIC");
	   private JButton figuras = new JButton("AÑADIR UNA FIGURA");
	   private JButton juegos = new JButton("AÑADIR UN JUEGO");
	   private BannerManager banner = new BannerManager();
	   
	   public ManagerNewProduct() {
		   super();
		   
		   this.setLayout(new BorderLayout());
		   
		   JPanel cosoDeBotones = new JPanel();
	    	
	    	//añadimos los botoncitos
	    	cosoDeBotones.setLayout(new GridLayout(3, 1));
	    	cosoDeBotones.add(comics);
	    	cosoDeBotones.add(figuras);
	    	cosoDeBotones.add(juegos);
	    	
	    	this.add(cosoDeBotones, BorderLayout.CENTER);
	    	this.add(banner, BorderLayout.NORTH);
	   }
	   
	   public void setController(ActionListener c) {
		   this.comics.addActionListener(c);
		   this.figuras.addActionListener(c);
		   this.juegos.addActionListener(c);
	   }
}
