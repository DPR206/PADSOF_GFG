package view.managerPanels;

import javax.swing.*;
import java.awt.*;

/**
 * The type Pagina principal gestor.
 * @author Sofia C.L.
 * @version 1.0
 */
public class PaginaPrincipalGestor extends JPanel {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Pagina principal gestor.
     */
    public PaginaPrincipalGestor() {
        super();
        this.setLayout(new BorderLayout());

        JPanel title = new JPanel();
        title.add(new JLabel("GIFTS FOR GEEKS: GESTOR"), BorderLayout.NORTH);
        this.add(title, BorderLayout.NORTH);

        JPanel main = new JPanel();
        main.setLayout(new GridLayout(2, 1));
        main.add(new JLabel("GESTIÓN TIENDA"));

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(2, 6));
        buttons.add(new JButton("PACKS"));
        buttons.add(new JButton("PRODUCTOS NUEVOS"));
        buttons.add(new JButton("AÑADIR PRODUCTOS"));
        buttons.add(new JButton("EMPLEADOS"));
        buttons.add(new JButton("ESTADÍSTICAS"));
        buttons.add(new JButton("DESCUENTOS"));
        buttons.add(new JButton("PARÁMETROS"));
        main.add(buttons);
        this.add(main, BorderLayout.CENTER);
    }
}