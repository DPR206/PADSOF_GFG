package ejemplosSwing.basico.paneles;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MiPanel extends JPanel {
	MiPanel () {
		// asignar layout
		this.setLayout(new FlowLayout());

		// crear componentes
		JLabel     etiqueta = new JLabel("Nombre");
		JTextField campo    = new JTextField(10);
		JButton    boton    = new JButton("Haz click");

		// asociar acciones a componentes
		boton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, campo.getText());
					}
				}
			);

		// añadir componentes  al contenedor
		this.add(etiqueta);
		this.add(campo);
		this.add(boton);
	}
}