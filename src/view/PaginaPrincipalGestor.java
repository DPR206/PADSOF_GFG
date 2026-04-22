package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PaginaPrincipalGestor extends JPanel{
	
	private JPanel title;
	private JPanel main;
	
	public PaginaPrincipalGestor() {
		super();
		this.setLayout(new BorderLayout());
		
		this.title = new JPanel();
		this.title.add(new JLabel("GIFTS FOR GEEKS: GESTOR"), BorderLayout.NORTH);
		this.add(title, BorderLayout.NORTH);
		
		this.main = new JPanel();
		this.main.setLayout(new GridLayout(2,1));
		this.main.add(new JLabel("GESTIÓN TIENDA"));
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(2,6));
		buttons.add(new JButton("PACKS"));
		buttons.add(new JButton("PRODUCTOS NUEVOS"));
		buttons.add(new JButton("AÑADIR PRODUCTOS"));
		buttons.add(new JButton("EMPLEADOS"));
		buttons.add(new JButton("ESTADÍSTICAS"));
		buttons.add(new JButton("DESCUENTOS"));
		buttons.add(new JButton("PARÁMETROS"));
		this.main.add(buttons);
		this.add(main, BorderLayout.CENTER);
	}
}
