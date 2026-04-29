package view;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AjustesNotificacionesP extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AjustesNotificacionesP(ActionListener volverAction) {
		
		JFrame nots = new JFrame("Notificaciones");
		nots.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    nots.setSize(300, 450);
	}

}
