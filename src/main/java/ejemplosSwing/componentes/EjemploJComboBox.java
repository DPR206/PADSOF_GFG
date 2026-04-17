package ejemplosSwing.componentes;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EjemploJComboBox {
	public static void main(String[] args) {
		JFrame ventana = new JFrame("Ventana Ejemplo");

		// obtener contenedor, asignar layout
		Container contenedor = ventana.getContentPane();
		contenedor.setLayout(new FlowLayout());

		// crear componentes
		// Crear un array con las opciones del combo
		String[] dias = {"lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo"};
		// Crear el combo, pasando el array como par�metro
		JLabel etiqueta = new JLabel("Selecciona tu dia");
		JButton boton   = new JButton("Haz click");
		JComboBox<String> combo = new JComboBox<>(dias);
		combo.setSelectedIndex(2);

		// asociar acciones a componentes
		boton.addActionListener(
		  new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		      String salida = (String) combo.getSelectedItem();
		      int indxSelec = combo.getSelectedIndex();
		      JOptionPane.showMessageDialog(null, salida + " : " + indxSelec);
		    }
		  }
		);

		// Anyadir el combo al panel donde se quiera mostrar
		JPanel ejemploComboBox = new JPanel(new GridLayout(1,2,10,0));
		ejemploComboBox.add(new JLabel("Selecciona un dia"));
		ejemploComboBox.add(combo); // anyadir las casillas al panel donde se quieran mostrar
		JPanel ejemploRadioButton = new JPanel(new GridLayout(3,1,0,10));

		// anyadir componentes al contenedor
		contenedor.add(etiqueta);
		contenedor.add(combo);
		contenedor.add(boton);
		ejemploRadioButton.setVisible(true);

		// mostrar ventana
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(280,340);
		ventana.setVisible(true);
	}
}