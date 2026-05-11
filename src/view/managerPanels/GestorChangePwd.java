package view.managerPanels;

import javax.swing.*;
import java.awt.*;

public class GestorChangePwd extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButton boton = new JButton("CAMBIAR");
    //private JPanel title;
    private JPanel main;
    private JTextField name = new JTextField();
    private JPasswordField pwd = new JPasswordField();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public GestorChangePwd() {
        super();

        //this.username = username;
        //this.title = new JPanel();
        //this.title.add(new JLabel("GIFTS FOR GEEKS: GESTOR"), BorderLayout.NORTH);
        //this.add(title, BorderLayout.CENTER);

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

    public JButton getBoton() {
        return boton;
    }

    public JPanel getMain() {
        return main;
    }

    public JPasswordField getPwd() {
        return this.pwd;
    }

    public JTextField getUserName() {
        return this.name;
    }
}