package view.managerPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The type Manager gestion empl individual.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerGestionEmplIndividual extends JPanel {

    private final JCheckBox storePerm = new JCheckBox("Trabajar con productos");
    private final JCheckBox orderPerm = new JCheckBox("Trabajar con pedidos");
    private final JCheckBox exchangePerm = new JCheckBox("Trabajar con intercambios");
    private final JTextField userName = new JTextField();
    private final JTextField pwd = new JTextField();
    private final JButton confirmar = new JButton("CONFIRMAR");

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager gestion empl individual.
     */
    public ManagerGestionEmplIndividual() {

        super();

        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Usuario
        JLabel lblUsuario = new JLabel("NOMBRE DE USUARIO");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblUsuario);

        panel.add(this.userName);

        panel.add(Box.createVerticalStrut(15));

        // Contraseña
        JLabel lblPass = new JLabel("CONTRASEÑA");
        lblPass.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblPass);

        panel.add(this.pwd);

        panel.add(Box.createVerticalStrut(15));

        // Permisos
        JLabel lblPermisos = new JLabel("PERMISOS");
        lblPermisos.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(lblPermisos);

        panel.add(this.storePerm);
        panel.add(this.orderPerm);
        panel.add(this.exchangePerm);

        panel.add(Box.createVerticalGlue()); // empuja el botón abajo

        // Botón a la derecha
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.confirmar.setPreferredSize(new Dimension(140, 40));
        panelBoton.add(this.confirmar);

        panel.add(panelBoton);

        this.add(panel, BorderLayout.CENTER);
    }

    /**
     * It gets the confirmar
     * @return the confirmar
     */
    public JButton getConfirmar() {
        return confirmar;
    }

    /**
     * It gets the exchange perm
     * @return the exchange perm
     */
    public JCheckBox getExchangePerm() {
        return this.exchangePerm;
    }

    /**
     * It gets the order perm
     * @return the order perm
     */
    public JCheckBox getOrderPerm() {
        return this.orderPerm;
    }

    /**
     * It gets the pwd
     * @return the pwd
     */
    public JTextField getPwd() {
        return this.pwd;
    }

    /**
     * It gets the store perm
     * @return the store perm
     */
    public JCheckBox getStorePerm() {
        return this.storePerm;
    }

    /**
     * It gets the user name
     * @return the user name
     */
    public JTextField getUserName() {
        return this.userName;
    }

    /**
     * It sets the controller
     * @param e the e
     */
    public void setController(ActionListener e) {
        this.confirmar.addActionListener(e);
    }
}