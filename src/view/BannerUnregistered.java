package view;

import java.awt.*;
import javax.swing.*;

public class BannerUnregistered extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton home;
	private JButton btnCarrito;
	private JButton btnPerfil;

	/**
	 * Create the panel.
	 */
	public BannerUnregistered() {
		
		setLayout(new BorderLayout());
        setBackground(new Color(45, 52, 54)); // Un color oscuro elegante
        setPreferredSize(new Dimension(800, 60)); // Altura fija de 60px
        setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        
        home = new JButton("\u2302");
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
        
        //Título
        JLabel lblTitulo = new JLabel("GIFTS FOR GEEKS", SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(223, 230, 233));
        lblTitulo.setFont(new Font("Book Antiqua", Font.BOLD, 25));

        //Botón de usuario
        btnPerfil = new JButton("👤");
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
        
        btnCarrito = new JButton("\uD83D\uDED2");
        btnCarrito.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
        
        btnCarrito.setBackground(new Color(45, 52, 54));
        btnCarrito.setForeground(Color.WHITE);
        
        btnCarrito.setBorderPainted(false);
        btnCarrito.setContentAreaFilled(false);
        btnCarrito.setFocusPainted(false);
        
        btnCarrito.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 14));
        panelAcciones.setOpaque(false); // Para que se vea el fondo del banner

        panelAcciones.add(btnCarrito);
        panelAcciones.add(btnPerfil);

        // Añadir componentes al banner
        add(home, BorderLayout.WEST);
        add(lblTitulo, BorderLayout.CENTER);
        add(panelAcciones, BorderLayout.EAST);
	}

	/**
	 * @return the home
	 */
	public JButton getHome() {
		return home;
	}

	/**
	 * @return the btnCarrito
	 */
	public JButton getBtnCarrito() {
		return btnCarrito;
	}

	/**
	 * @return the btnPerfil
	 */
	public JButton getBtnPerfil() {
		return btnPerfil;
	}
}
