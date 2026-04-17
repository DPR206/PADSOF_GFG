package ejemplosSwing.mvc.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelAltaTarea extends JPanel {

	private JTextField nombreTarea;
	private JButton botonAceptar;
	private JButton botonCancelar;

	public PanelAltaTarea() {
		// asignar layout
		this.setLayout(new GridLayout(2,2));

		// crear componentes
		JLabel etiqueta = new JLabel("Nombre de la tarea:");
		nombreTarea     = new JTextField();
		botonAceptar    = new JButton("Aceptar");
		botonCancelar   = new JButton("Cancelar");

		// añadir componentes al panel
		this.add(etiqueta);
		this.add(nombreTarea);
		this.add(botonAceptar);
		this.add(botonCancelar);
	}

	// método para asignar un controlador a los botones
	public void setControlador(ActionListener c) {
		botonAceptar.addActionListener(c);
		botonCancelar.addActionListener(c);
	}

	// método que devuelve el nombre de una tarea (contenido del campo JTextField)
	public String getNombreTarea () {
		return nombreTarea.getText();
	}

	// método que actualiza el valor de los campos
	public void update () {
		nombreTarea.setText("");
		nombreTarea.grabFocus();
	}
}