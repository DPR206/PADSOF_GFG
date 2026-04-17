package ejemplosSwing.mvc.vista;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelProyecto extends JPanel {

	private JButton boton;
	private DefaultListModel<String> tareas;

	public PanelProyecto() {
		// asignar layout
		this.setLayout(new BorderLayout());

		// crear componentes
		JLabel etiqueta = new JLabel("Proyecto con tareas:");
		boton  = new JButton("Nueva Tarea");
		tareas = new DefaultListModel<String>();
		JPanel botonera = new JPanel();

		// añadir componentes al panel
		JList<String> listaTareas = new JList<String>(tareas);
		JScrollPane scrollTareas  = new JScrollPane(listaTareas);
		scrollTareas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTareas.setPreferredSize(new Dimension(100, 80));
		this.add(etiqueta, BorderLayout.NORTH);
		this.add(scrollTareas, BorderLayout.CENTER);
		botonera.add(boton);
		this.add(botonera, BorderLayout.SOUTH);
	}

	// método para asignar un controlador a los botones
	public void setControlador(ActionListener c) {
		boton.addActionListener(c);
	}

	// método que actualiza el valor de los campos
	public void update(String tareas) {
		this.tareas.addElement(tareas);
	}
}