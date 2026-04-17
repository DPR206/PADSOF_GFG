package ejemplosSwing.componentes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EjemploGroupJRadioButton {
	public static void main(String[] args) {
		JFrame ventana = new JFrame("Ventana Ejemplo");

		// obtener contenedor, asignar layout
		Container contenedor = ventana.getContentPane();
		contenedor.setLayout(new FlowLayout());

		// crear componentes
		JLabel etiqueta = new JLabel("Selecciona UNA opcion:");
		JButton boton   = new JButton("Haz click");
		JRadioButton opcion1 = new JRadioButton("menor de 18 anyos");
		JRadioButton opcion2 = new JRadioButton("entre 18 y 40 anyos");
		JRadioButton opcion3 = new JRadioButton("mayor de 40 anyos");
		opcion1.setSelected(true);

		// Crear un grupo para las opciones, el cual garantiza
		// que  solo una de las opciones esta seleccionada
		ButtonGroup grupo = new ButtonGroup();
		// Anyadir las opciones al grupo
		grupo.add(opcion1);
		grupo.add(opcion2);
		grupo.add(opcion3);

		// asociar acciones a componentes
		boton.addActionListener(
		  new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		      String salida = "";
		      if (opcion1.isSelected()) salida += " seleccion " + opcion1.getText();
		      if (opcion2.isSelected()) salida += " seleccion " + opcion2.getText();
		      if (opcion3.isSelected()) salida += " seleccion " + opcion3.getText();
		      JOptionPane.showMessageDialog(ventana, salida);
		    }
		  }
		);

		// anyadir las casillas al panel donde se quieran mostrar
		JPanel ejemploRadioButton = new JPanel(new GridLayout(3,1,0,10));
		ejemploRadioButton.add(opcion1);
		ejemploRadioButton.add(opcion2);
		ejemploRadioButton.add(opcion3);

		// anyadir componentes al contenedor
		contenedor.add(etiqueta);
		contenedor.add(ejemploRadioButton);
		contenedor.add(boton);
		ejemploRadioButton.setVisible(true);

		// mostrar ventana
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(230,300);
		ventana.setVisible(true);
	}
}