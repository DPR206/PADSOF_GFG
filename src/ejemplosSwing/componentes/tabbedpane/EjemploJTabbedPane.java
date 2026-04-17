package ejemplosSwing.componentes.tabbedpane;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class EjemploJTabbedPane {
	public static void main(String[] args) {
		JFrame ventana = new JFrame("Ejemplo de uso de JTabbedPane");

		// obtener contenedor, asignar layout
		Container contenedor = ventana.getContentPane();
		contenedor.setLayout(new FlowLayout());

		// crear componentes
		JLabel etiqueta = new JLabel("Uso de pestanyas (JTabbedPane)");

		 // Creamos un panel por cada pestanya
		PanelPares panelPares =  new PanelPares(ventana, "Numero", "Check par");
		PanelPalindromos panelPalindromos = new PanelPalindromos(ventana, "Cadena", "Palindromo?");

		// Crear contenedor JTabbedPane para varios panels
		JTabbedPane pestanyas = new JTabbedPane();
		pestanyas.addTab("Pares",  panelPares);
		pestanyas.addTab("Palindromos",  panelPalindromos);

		// asociar acciones a componentes
		// Para acciones al cambiar de pesta�as definimos un ChangeListener
		pestanyas.addChangeListener(new ChangeListener() {
		       public void stateChanged(ChangeEvent ev) {
		    	   	 panelPares.limpiaCampos();
		    	   	 panelPalindromos.limpiaCampos();
		       }
		});

		// anyadir componentes al contenedor
		contenedor.add(etiqueta);
		contenedor.add(pestanyas);

		// mostrar ventana
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(360,240);
		ventana.setVisible(true);
	}
}