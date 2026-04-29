package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.notification.Notification;

public class NotificacionDetallesP extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnVolver;

	/**
	 * Create the dialog.
	 * @param seleccionada 
	 * @param nots 
	 */
	public NotificacionDetallesP(Notification n, ActionListener volverAction) {
	        setLayout(new BorderLayout(10, 10));
	        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	        // Título
	        JLabel titulo = new JLabel("Detalle de Notificación", SwingConstants.CENTER);
	        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
	        
	        // Cuerpo de texto
	        JTextArea areaTexto = new JTextArea(n.toString());
	        areaTexto.setEditable(false);
	        areaTexto.setLineWrap(true);
	        areaTexto.setWrapStyleWord(true);

	        // Botón Volver (usamos el listener que nos pasan desde la principal)
	        btnVolver = new JButton("← Volver");
	        btnVolver.setPreferredSize(new Dimension(0, 50));
	        btnVolver.addActionListener(volverAction);

	        add(titulo, BorderLayout.NORTH);
	        add(new JScrollPane(areaTexto), BorderLayout.CENTER);
	        add(btnVolver, BorderLayout.SOUTH);
	    
	    }

}
