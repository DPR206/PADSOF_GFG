package view.managerPanels;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

/**
 * The type Manager profile.
 * @author Duna P.R.
 * @version 1.0
 */
public class ManagerProfile extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;
    private JLabel nom, pwd;
    private JButton btnMostrar;
    private JButton btnCambiar;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager profile.
     */
    public ManagerProfile() {
        configurarEstructura();
    }

    private void configurarEstructura() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel information = new JPanel();
        information.setLayout(new BoxLayout(information, BoxLayout.Y_AXIS));
        information.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel tituloNom = new JLabel("USERNAME");
        tituloNom.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloNom.setAlignmentX(Component.LEFT_ALIGNMENT);

        nom = new JLabel("");
        nom.setFont(new Font("SansSerif", Font.PLAIN, 14));
        nom.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel tituloPwd = new JLabel("PASSWORD");
        tituloPwd.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloPwd.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel panelPassword = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
        panelPassword.setOpaque(false);
        panelPassword.setAlignmentX(Component.LEFT_ALIGNMENT);

        pwd = new JLabel("********"); // Estado inicial oculto
        pwd.setFont(new Font("SansSerif", Font.PLAIN, 14));

        btnMostrar = new JButton("👁️");
        btnMostrar.setForeground(new Color(64, 0, 0));
        btnMostrar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        btnMostrar.setBorderPainted(false);
        btnMostrar.setContentAreaFilled(false);
        btnMostrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnCambiar = new JButton("Cambiar");
        btnCambiar.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnCambiar.setBorderPainted(true);
        btnCambiar.setContentAreaFilled(true);
        btnCambiar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelPassword.add(pwd);
        panelPassword.add(btnMostrar);
        panelPassword.add(btnCambiar);

        information.add(tituloNom);
        information.add(nom);

        information.add(Box.createVerticalStrut(40));

        information.add(tituloPwd);
        information.add(panelPassword);

        add(information, BorderLayout.CENTER);
    }

    /**
     * It gets the btn cambiar
     * @return the btnCambiar
     */
    public JButton getBtnCambiar() {
        return btnCambiar;
    }

    /**
     * It gets the btn mostrar
     * @return the btnMostrar
     */
    public JButton getBtnMostrar() {
        return btnMostrar;
    }

    /**
     * It gets the pwd
     * @return the pwd
     */
    public JLabel getPwd() {
        return pwd;
    }

    /**
     * It sets the pwd
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd.setText(pwd);
    }

    /**
     * It sets the nom
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom.setText(nom);
    }
}