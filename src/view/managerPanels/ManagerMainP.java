package view.managerPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The type Manager main p.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerMainP extends JPanel {
    /*Botones para administrar cosas*/
    private final JButton packs = new JButton("Packs");
    private final JButton productoNuevo = new JButton("Productos nuevos");
    private final JButton annadirProductos = new JButton("Añadir productos");
    private final JButton empleados = new JButton("Empleados");
    private final JButton estadisticas = new JButton("Estadísticas");
    private final JButton descuentos = new JButton("Descuentos");
    private final JButton parametros = new JButton("Parámetros");

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager main p.
     */
    public ManagerMainP() {
        super();
        this.setLayout(new BorderLayout());

        //ahora el cacho principal

        JPanel cosoDeBotones = new JPanel();

        //añadimos los botoncitos
        cosoDeBotones.setLayout(new GridLayout(2, 6));
        cosoDeBotones.add(this.packs);
        cosoDeBotones.add(this.productoNuevo);
        cosoDeBotones.add(this.annadirProductos);
        cosoDeBotones.add(this.empleados);
        cosoDeBotones.add(this.estadisticas);
        cosoDeBotones.add(this.descuentos);
        cosoDeBotones.add(this.parametros);

        this.add(cosoDeBotones, BorderLayout.CENTER);
    }

    /**
     * It gets the annadir productos
     * @return the annadir productos
     */
    public JButton getAnnadirProductos() {
        return annadirProductos;
    }

    /**
     * It gets the descuentos
     * @return the descuentos
     */
    public JButton getDescuentos() {
        return descuentos;
    }

    /**
     * It gets the empleados
     * @return the empleados
     */
    public JButton getEmpleados() {
        return empleados;
    }

    /**
     * It gets the estadisticas
     * @return the estadisticas
     */
    public JButton getEstadisticas() {
        return estadisticas;
    }

    /**
     * It gets the packs
     * @return the packs
     */
    public JButton getPacks() {
        return packs;
    }

    /**
     * It gets the parametros
     * @return the parametros
     */
    public JButton getParametros() {
        return parametros;
    }

    /**
     * It gets the producto nuevo
     * @return the producto nuevo
     */
    public JButton getProductoNuevo() {
        return productoNuevo;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        this.packs.addActionListener(c);
        this.productoNuevo.addActionListener(c);
        this.annadirProductos.addActionListener(c);
        this.empleados.addActionListener(c);
        this.estadisticas.addActionListener(c);
        this.parametros.addActionListener(c);
    }
}