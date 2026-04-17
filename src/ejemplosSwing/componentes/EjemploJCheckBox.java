package ejemplosSwing.componentes;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EjemploJCheckBox {
	public static void main(String[] args) {
		JFrame ventana = new JFrame("Ventana Ejemplo");

		// obtener contenedor, asignar layout
		Container contenedor = ventana.getContentPane();
		contenedor.setLayout(new FlowLayout());

		// crear componentes
		JButton boton   = new JButton("Haz click");
		JCheckBox casilla1 = new JCheckBox("pepino");
		JCheckBox casilla2 = new JCheckBox("cebolla");
		JCheckBox casilla3 = new JCheckBox("bacon");
		JCheckBox casilla4 = new JCheckBox("queso");
		casilla1.setSelected(true);

		// asociar acciones a componentes
		boton.addActionListener(
		  new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		      String salida = "";
		      if (casilla1.isSelected()) salida += " con " + casilla1.getText();
		      if (casilla2.isSelected()) salida += " con " + casilla2.getText();
		      if (casilla3.isSelected()) salida += " con " + casilla3.getText();
		      if (casilla4.isSelected()) salida += " con " + casilla4.getText();
		      JOptionPane.showMessageDialog(ventana, salida);
		    }
		  }
		);

		// anyadir las casillas al panel donde se quieran mostrar
		JPanel ejemploCheckbox = new JPanel(new GridLayout(5,1));
		ejemploCheckbox.add( new JLabel("Selecciona tus complementos") );
		ejemploCheckbox.add(casilla1);
		ejemploCheckbox.add(casilla2);
		ejemploCheckbox.add(casilla3);
		ejemploCheckbox.add(casilla4);
		ejemploCheckbox.setVisible(true);
		// anyadir componentes al contenedor
		contenedor.add(ejemploCheckbox);
		contenedor.add(boton);

		// mostrar ventana
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(260,300);
		ventana.setVisible(true);
	}
}