package ejemplosSwing.basico;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ejemplo2 {

	public static void main(String[] args) {
		JFrame ventana = new JFrame("Mi GUI. Ejemplo 2");

		// obtener contenedor, asignar layout
		Container contenedor = ventana.getContentPane();
		contenedor.setLayout(new FlowLayout());

		// crear pantalla 1
		JPanel pantalla1 = new JPanel();
		JButton boton = new JButton("siguiente");
		pantalla1.add(boton);
		pantalla1.setVisible(true);

		// crear pantalla 2
		JPanel pantalla2 = new JPanel();
		JLabel etiqueta = new JLabel("segunda pantalla");
		pantalla2.add(etiqueta);
		pantalla2.setVisible(false);

		// asociar acciones a componentes
		boton.addActionListener(
				new ActionListener() {
			       public void actionPerformed(ActionEvent e) {
		        	   pantalla1.setVisible(false);
		        	   pantalla2.setVisible(true);
		           }
				}
		       );

		// aniadir componentes al contenedor
		contenedor.add(pantalla1);
		contenedor.add(pantalla2);

		// mostrar ventana
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(300,140);
		ventana.setVisible(true);
	}

}