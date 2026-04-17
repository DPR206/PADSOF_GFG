package ejemplosSwing.mvc.controlador;

import java.awt.event.*;
import javax.swing.*;

import ejemplosSwing.mvc.modelo.*;
import ejemplosSwing.mvc.vista.*;

public class ControlAltaTarea implements ActionListener {

	private PanelAltaTarea vista;
	private Aplicacion frame;
	private Proyecto modelo;

	public ControlAltaTarea(Aplicacion frame, Proyecto modelo) {
		this.frame = frame;
		this.vista = frame.getPanelAltaTarea();
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Aceptar"))			// si se ha pulsado "Aceptar"
			this.crearTarea();
		else if (e.getActionCommand().equals("Cancelar"))	// si se ha pulsado "Cancelar"
			this.mostrarPanelProyecto();
	}

	private void mostrarPanelProyecto() {
		// mostrar nueva vista
		this.vista.setVisible(false);
		this.frame.getPanelProyecto().setVisible(true);
	}

	private void crearTarea() {
		// validar valores en la vista
		String nombreTarea = vista.getNombreTarea();
		if (nombreTarea.equals("")) {
			JOptionPane.showMessageDialog(vista, "Debe introducir un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// modificar modelo
		Tarea tarea = new Tarea(nombreTarea);
		modelo.addTarea(tarea);

		// mostrar nueva vista
		this.vista.setVisible(false);
		this.frame.getPanelProyecto().setVisible(true);
		this.frame.getPanelProyecto().update(tarea.getNombre());
	}
}