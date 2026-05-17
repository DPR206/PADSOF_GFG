package view.clientPanels;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

/**
 * The type Registered profile.
 * @author Duna P.R.
 * @version 1.0
 */
public class RegisteredProfile extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;
    private JLabel nom, dni, pwd;
    private JButton btnMostrar;
    private JButton btnCambiar;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Registered profile.
     */
    public RegisteredProfile() {
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

        JLabel tituloDni = new JLabel("DNI OR NIE");
        tituloDni.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloDni.setAlignmentX(Component.LEFT_ALIGNMENT);

        dni = new JLabel("");
        dni.setFont(new Font("SansSerif", Font.PLAIN, 14));
        dni.setAlignmentX(Component.LEFT_ALIGNMENT);

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

        information.add(tituloDni);
        information.add(dni);

        information.add(Box.createVerticalStrut(40));

        information.add(tituloPwd);
        information.add(panelPassword);

        add(information, BorderLayout.CENTER);
    }

    /**
     * Actualizar password vista.
     * @param texto the texto
     */
    public void actualizarPasswordVista(String texto) {
        pwd.setText(texto);
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
     * @return the btn mostrar
     */
    public JButton getBtnMostrar() {
        return btnMostrar;
    }

    /**
     * It gets the dni
     * @return the dni
     */
    public JLabel getDni() {
        return dni;
    }

    /**
     * It sets the dni
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni.setText(dni);
    }

    /**
     * It gets the nom
     * @return the nom
     */
    public JLabel getNom() {
        return nom;
    }

    /**
     * It sets the nom
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom.setText(nom);
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

}