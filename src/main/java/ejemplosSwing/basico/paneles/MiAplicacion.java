package ejemplosSwing.basico.paneles;

import java.awt.*;
import javax.swing.*;

public class MiAplicacion extends JFrame {

	public MiAplicacion (String title) {
		super(title);

		// obtener contenedor, asignar layout
		Container contenedor = this.getContentPane();
		CardLayout layout    = new CardLayout();
		contenedor.setLayout(layout);

		// crear paneles
		JPanel panel = new MiPanel();

		// añadir paneles
		contenedor.add(panel, "panel");
		layout.show(contenedor, "panel");

		// mostrar ventana
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
	}
}