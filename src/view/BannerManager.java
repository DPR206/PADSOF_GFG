package view;

import java.awt.*;

import javax.swing.*;

public class BannerManager extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton home;
	private JButton btnPerfil;

	/**
	 * Create the panel.
	 */
	public BannerManager() {

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
        
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setOpaque(false); // Para que mantenga el color oscuro del fondo

        //Título Principal
        JLabel lblTitulo = new JLabel("GIFTS FOR GEEKS");
        lblTitulo.setForeground(new Color(223, 230, 233));
        lblTitulo.setFont(new Font("Book Antiqua", Font.BOLD, 25));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar horizontalmente

        //Subtítulo
        JLabel lblSubtitulo = new JLabel("Manager");
        lblSubtitulo.setForeground(new Color(178, 190, 195)); // Un gris un poco más claro
        lblSubtitulo.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar horizontalmente

        // Añadir los labels al panel central
        panelCentral.add(Box.createVerticalGlue()); // Espacio flexible arriba
        panelCentral.add(lblTitulo);
        panelCentral.add(lblSubtitulo);
        panelCentral.add(Box.createVerticalGlue()); // Espacio flexible abajo

        //Ajuste de altura del Banner
        this.setPreferredSize(new Dimension(800, 70));

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
        
        JPanel panelAccionesD = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 14));
        panelAccionesD.setOpaque(false); // Para que se vea el fondo del banner

        panelAccionesD.add(btnPerfil);

        // Añadir componentes al banner
        add(home, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);
        add(panelAccionesD, BorderLayout.EAST);
	}

	/**
	 * @return the home
	 */
	public JButton getHome() {
		return home;
	}

	/**
	 * @return the btnPerfil
	 */
	public JButton getBtnPerfil() {
		return btnPerfil;
	}

}
