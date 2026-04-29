package view;

import java.awt.event.ActionListener;

import javax.swing.*;

public class NotificacionsSettingsClientP extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public NotificacionsSettingsClientP(ActionListener volverAction) {
		
		JFrame nots = new JFrame("Notificaciones");
		nots.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    nots.setSize(300, 450);
	}

}
