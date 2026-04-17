package ejemplosSwing.mvc.vista;

import javax.swing.*;
import java.awt.*;

import ejemplosSwing.mvc.controlador.*;
import ejemplosSwing.mvc.modelo.*;

public class Aplicacion extends JFrame {

	private PanelProyecto  panelProyecto;
	private PanelAltaTarea panelAltaTarea;

	public Aplicacion() {
		super("Gestor de proyectos");

		// vistas
		panelProyecto  = new PanelProyecto();
		panelAltaTarea = new PanelAltaTarea();

		// modelo
		Proyecto modelo = new Proyecto();

		// controladores
		ControlProyecto  controlProyecto  = new ControlProyecto(this, modelo);
		ControlAltaTarea controlAltaTarea = new ControlAltaTarea(this, modelo);

		// configurar las vistas con los controladores
		panelProyecto.setControlador(controlProyecto);
		panelAltaTarea.setControlador(controlAltaTarea);

		// añadir vistas a la ventana principal
		Container contenedor = this.getContentPane();
		contenedor.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

		contenedor.add(panelProyecto, gbc);
		contenedor.add(panelAltaTarea, gbc);
		panelProyecto.setVisible(true);
		panelAltaTarea.setVisible(false);

		 // configurar tamaño de la ventana principal
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(250, 190);
		this.setLocationRelativeTo(null);
	}

	public PanelProyecto getPanelProyecto() {
		return this.panelProyecto;
	}

	public PanelAltaTarea getPanelAltaTarea() {
		return this.panelAltaTarea;
	}
}