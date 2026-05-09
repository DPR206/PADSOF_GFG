package view.managerPanels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ManagerNewProduct extends JPanel{
	   private JButton comics = new JButton("AÑADIR UN CÓMIC");
	   private JButton figuras = new JButton("AÑADIR UNA FIGURA");
	   private JButton juegos = new JButton("AÑADIR UN JUEGO");

	   private CardLayout layout = new CardLayout();
	   private JPanel cards = new JPanel(layout);

	   public static final String PANEL_MENU = "MENU";
	   public static final String PANEL_COMIC = "COMIC";
	   public static final String PANEL_FIGURA = "FIGURA";
	   public static final String PANEL_JUEGO = "JUEGO";

	   public ManagerNewProduct() {
		   super();

		   this.setLayout(new BorderLayout());

		   JPanel cosoDeBotones = new JPanel();

	    	//añadimos los botoncitos
	    	cosoDeBotones.setLayout(new GridLayout(3, 1));
	    	cosoDeBotones.add(comics);
	    	cosoDeBotones.add(figuras);
	    	cosoDeBotones.add(juegos);

	    	cards.add(cosoDeBotones, PANEL_MENU);

	    	this.add(cards, BorderLayout.CENTER);
	   }

	   public void setController(ActionListener c) {
		   this.comics.addActionListener(c);
		   this.figuras.addActionListener(c);
		   this.juegos.addActionListener(c);
	   }

	   public void showPanel(String name) {
	       layout.show(cards, name);
	   }

	   public JPanel getCards() {
	        return cards;
	   }
}