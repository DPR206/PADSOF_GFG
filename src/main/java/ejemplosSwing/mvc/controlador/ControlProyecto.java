package ejemplosSwing.mvc.controlador;

import java.awt.event.*;

import ejemplosSwing.mvc.modelo.*;
import ejemplosSwing.mvc.vista.*;

public class ControlProyecto implements ActionListener {

	private Aplicacion frame;
	private PanelProyecto vista;
	private Proyecto modelo;

	public ControlProyecto(Aplicacion frame, Proyecto modelo) {
		this.frame = frame;
		this.vista = this.frame.getPanelProyecto();
		this.modelo = modelo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.vista.setVisible(false);
		this.frame.getPanelAltaTarea().setVisible(true);
		this.frame.getPanelAltaTarea().update();
	}
}