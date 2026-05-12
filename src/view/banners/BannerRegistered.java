package view.banners;

import javax.swing.*;
import java.awt.*;

public class BannerRegistered extends JPanel {

    private static final long serialVersionUID = 1L;
    private final JButton btnGoBack;
    private JButton home;
    private JButton cartera;
    private JButton btnCarrito;
    private JButton btnPerfil;
    private JButton btnNots;
    private JButton btnExit;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Create the panel.
     */
    public BannerRegistered() {

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

        // Botón de go back
        btnGoBack = new JButton("\uD83D\uDD19");
        btnGoBack.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 22));
        btnGoBack.setOpaque(false);
        btnGoBack.setBorderPainted(false);
        btnGoBack.setCursor(new Cursor(Cursor.HAND_CURSOR));

        cartera = new JButton("\uD83D\uDCBC");
        cartera.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));

        cartera.setBackground(new Color(45, 52, 54));
        cartera.setForeground(Color.WHITE);

        cartera.setBorderPainted(false);

        cartera.setFocusPainted(false);
        cartera.setContentAreaFilled(true);

        cartera.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Título
        JLabel lblTitulo = new JLabel("GIFTS FOR GEEKS", SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(223, 230, 233));
        lblTitulo.setFont(new Font("Book Antiqua", Font.BOLD, 25));

        //Botón de usuario
        btnPerfil = new JButton("👤");
        btnPerfil.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 22));

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
        btnCarrito.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 22));

        btnCarrito.setBackground(new Color(45, 52, 54));
        btnCarrito.setForeground(Color.WHITE);

        btnCarrito.setBorderPainted(false);
        btnCarrito.setContentAreaFilled(false);
        btnCarrito.setFocusPainted(false);

        btnCarrito.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnNots = new JButton("\uD83D\uDD14");

        btnNots.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));

        btnNots.setBackground(new Color(45, 52, 54));
        btnNots.setForeground(Color.WHITE);

        btnNots.setBorderPainted(false);

        btnNots.setFocusPainted(false);
        btnNots.setContentAreaFilled(true);

        btnNots.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnExit = new JButton("\u23FB");
        btnExit.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 22));

        btnExit.setBackground(new Color(45, 52, 54));
        btnExit.setForeground(Color.WHITE);

        btnExit.setBorderPainted(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setFocusPainted(false);

        btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel panelAccionesD = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 14));
        panelAccionesD.setOpaque(false); // Para que se vea el fondo del banner

        panelAccionesD.add(btnNots);
        panelAccionesD.add(btnCarrito);
        panelAccionesD.add(btnPerfil);
        panelAccionesD.add(btnExit);

        JPanel panelAccionesI = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panelAccionesI.setOpaque(false); // Para que se vea el fondo del banner

        panelAccionesI.add(home);
        panelAccionesI.add(btnGoBack);
        panelAccionesI.add(cartera);

        // Añadir componentes al banner
        add(panelAccionesI, BorderLayout.WEST);
        add(lblTitulo, BorderLayout.CENTER);
        add(panelAccionesD, BorderLayout.EAST);

    }

    /**
     * @return the btnCarrito
     */
    public JButton getBtnCarrito() {
        return btnCarrito;
    }

    /**
     * @return the btnExit
     */
    public JButton getBtnExit() {
        return btnExit;
    }

    public JButton getBtnGoBack() {
        return btnGoBack;
    }

    /**
     * @return the btnNots
     */
    public JButton getBtnNots() {
        return btnNots;
    }

    /**
     * @return the btnPerfil
     */
    public JButton getBtnPerfil() {
        return btnPerfil;
    }

    /**
     * @return the cartera
     */
    public JButton getCartera() {
        return cartera;
    }

    /**
     * @return the home
     */
    public JButton getHome() {
        return home;
    }

}