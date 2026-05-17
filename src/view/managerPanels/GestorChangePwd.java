package view.managerPanels;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

/**
 * The type Gestor change pwd.
 * @author Sofia C.L.
 * @version 1.0
 */
public class GestorChangePwd extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private final JButton boton = new JButton("CAMBIAR");
    private final JPanel main;
    private final JTextField name = new JTextField();
    private final JPasswordField pwd = new JPasswordField();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Gestor change pwd.
     */
    public GestorChangePwd() {
        super();

        this.main = new JPanel();
        this.main.setLayout(new GridLayout(3, 1));

        JPanel aux1 = new JPanel();
        aux1.setLayout(new GridLayout(2, 1));
        aux1.add(new JLabel("NOMBRE DE USUARIO:"));
        aux1.add(this.name);

        this.main.add(aux1);

        JPanel aux2 = new JPanel();
        aux2.setLayout(new GridLayout(2, 1));
        aux2.add(new JLabel("CONTRASEÑA:"));
        aux2.add(this.pwd);

        this.main.add(aux2);

        JPanel aux3 = new JPanel();
        aux3.add(this.boton);

        this.main.add(aux3);
        //title.add(main);
        this.add(main, BorderLayout.CENTER);
    }

    /**
     * It gets the boton
     * @return the boton
     */
    public JButton getBoton() {
        return boton;
    }

    /**
     * It gets the main
     * @return the main
     */
    public JPanel getMain() {
        return main;
    }

    /**
     * It gets the pwd
     * @return the pwd
     */
    public JPasswordField getPwd() {
        return this.pwd;
    }

    /**
     * It gets the user name
     * @return the user name
     */
    public JTextField getUserName() {
        return this.name;
    }
}