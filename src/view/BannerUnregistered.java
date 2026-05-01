package view;

import java.awt.*;
import javax.swing.*;

public class BannerUnregistered extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public BannerUnregistered() {
		
		setLayout(new BorderLayout());
        setBackground(new Color(45, 52, 54)); // Un color oscuro elegante
        setPreferredSize(new Dimension(800, 60)); // Altura fija de 60px
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        
        JLabel lblLogo = new JLabel("MY APP");
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("SansSerif", Font.BOLD, 20));
        
        
        // --- Título Dinámico de la pantalla actual ---
        JLabel lblTitulo = new JLabel("GIFTS FOR GEEKS", SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(223, 230, 233));
        lblTitulo.setFont(new Font("Book Antiqua", Font.BOLD, 25));

        // --- Botón de Usuario / Perfil (Opcional) ---
        JButton btnPerfil = new JButton("👤");
        btnPerfil.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));

        //Establecer el mismo color que el banner
        btnPerfil.setBackground(new Color(45, 52, 54)); 
        btnPerfil.setForeground(Color.WHITE); // Color del icono/texto

        //Quitar el borde (para que no se vea el relieve)
        btnPerfil.setBorderPainted(false);

        //Quitar el foco y el área de contenido por defecto si es necesario
        btnPerfil.setFocusPainted(false);
        btnPerfil.setContentAreaFilled(true); // Asegura que use su color de fondo

        //Cambiar el cursor para que el usuario sepa que es clickable
        btnPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Añadir componentes al banner
        add(lblLogo, BorderLayout.WEST);
        add(lblTitulo, BorderLayout.CENTER);
        add(btnPerfil, BorderLayout.EAST);
	}

}
