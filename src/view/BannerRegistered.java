package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BannerRegistered extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public BannerRegistered() {
		
		setLayout(new BorderLayout());
        setBackground(new Color(45, 52, 54)); // Un color oscuro elegante
        setPreferredSize(new Dimension(800, 60)); // Altura fija de 60px
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        
        JButton home = new JButton("\u2302");
        home.setFont(new Font("Courier New", Font.BOLD, 30));
        
        home.setBackground(new Color(45, 52, 54)); 
        home.setForeground(Color.WHITE); // Color del icono/texto

        //Quitar el borde (para que no se vea el relieve)
        home.setBorderPainted(false);

        //Quitar el foco y el área de contenido por defecto si es necesario
        home.setFocusPainted(false);
        home.setContentAreaFilled(true); // Asegura que use su color de fondo

        //Cambiar el cursor para que el usuario sepa que es clickable
        home.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton cartera = new JButton("\uD83D\uDCBC");
        cartera.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
        
        cartera.setBackground(new Color(45, 52, 54)); 
        cartera.setForeground(Color.WHITE);
        
        cartera.setBorderPainted(false);
        
        cartera.setFocusPainted(false);
        cartera.setContentAreaFilled(true);
        
        cartera.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // --- Título Dinámico de la pantalla actual ---
        JLabel lblTitulo = new JLabel("GIFTS FOR GEEKS", SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(223, 230, 233));
        lblTitulo.setFont(new Font("Book Antiqua", Font.BOLD, 25));

        // --- Botón de Usuario / Perfil (Opcional) ---
        JButton btnPerfil = new JButton("👤");
        btnPerfil.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));

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
        
        JButton btnCarrito = new JButton("\uD83D\uDED2");
        btnCarrito.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
        
        btnCarrito.setBackground(new Color(45, 52, 54));
        btnCarrito.setForeground(Color.WHITE);
        
        btnCarrito.setBorderPainted(false);
        btnCarrito.setContentAreaFilled(false);
        btnCarrito.setFocusPainted(false);
        
        btnCarrito.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JButton btnNots = new JButton("\uD83D\uDD14");
        
        btnNots.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
        
        btnNots.setBackground(new Color(45, 52, 54)); 
        btnNots.setForeground(Color.WHITE);
        
        btnNots.setBorderPainted(false);
        
        btnNots.setFocusPainted(false);
        btnNots.setContentAreaFilled(true);
        
        
        JPanel panelAccionesD = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 14));
        panelAccionesD.setOpaque(false); // Para que se vea el fondo del banner

        panelAccionesD.add(btnNots);
        panelAccionesD.add(btnCarrito);
        panelAccionesD.add(btnPerfil);
        
        JPanel panelAccionesI = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panelAccionesI.setOpaque(false); // Para que se vea el fondo del banner

        panelAccionesI.add(home);
        panelAccionesI.add(cartera);

        // Añadir componentes al banner
        add(panelAccionesI, BorderLayout.WEST);
        add(lblTitulo, BorderLayout.CENTER);
        add(panelAccionesD, BorderLayout.EAST);

	}

}
