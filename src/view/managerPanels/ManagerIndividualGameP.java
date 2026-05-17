package view.managerPanels;

import javax.swing.*;
import java.awt.*;

/**
 * The type Manager individual game p.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerIndividualGameP extends JPanel {

    private final JTextField txtNombreProducto = new JTextField(15);
    private final JTextField txtTipoJuego = new JTextField(15);
    private final JTextField txtNumJugadores = new JTextField(15);
    private final JTextField txtRangoEdad = new JTextField(15);
    private final JTextField txtPrecio = new JTextField(15);
    private final JTextField txtCategorias = new JTextField(15);

    private final JButton btnConfirmarNombre = new JButton("CONFIRMAR");
    private final JButton btnConfirmarTipoJuego = new JButton("CONFIRMAR");
    private final JButton btnConfirmarJugadores = new JButton("CONFIRMAR");
    private final JButton btnConfirmarEdad = new JButton("CONFIRMAR");
    private final JButton btnConfirmarPrecio = new JButton("CONFIRMAR");
    private final JButton btnConfirmarCategorias = new JButton("CONFIRMAR");

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager individual game p.
     */
    public ManagerIndividualGameP() {
        super();
        this.setLayout(new GridLayout(6, 3, 10, 10)); // 6 filas, 3 columnas

        // Nombre producto
        this.add(new JLabel("Nombre producto:"));
        this.add(txtNombreProducto);
        this.add(btnConfirmarNombre);

        // Tipo de juego
        this.add(new JLabel("Tipo de juego:"));
        this.add(txtTipoJuego);
        this.add(btnConfirmarTipoJuego);

        // Nº de jugadores
        this.add(new JLabel("Nº de jugadores:"));
        this.add(txtNumJugadores);
        this.add(btnConfirmarJugadores);

        // Rango de edad
        this.add(new JLabel("Rango de edad:"));
        this.add(txtRangoEdad);
        this.add(btnConfirmarEdad);

        // Precio
        this.add(new JLabel("Precio:"));
        this.add(txtPrecio);
        this.add(btnConfirmarPrecio);

        // Categorías
        this.add(new JLabel("Categorías:"));
        this.add(txtCategorias);
        this.add(btnConfirmarCategorias);
    }

    /**
     * It gets the btn confirmar categorías
     * @return the btn confirmar categorías
     */
    public JButton getBtnConfirmarCategorias() {
        return btnConfirmarCategorias;
    }

    /**
     * It gets the btn confirmar edad
     * @return the btn confirmar edad
     */
    public JButton getBtnConfirmarEdad() {
        return btnConfirmarEdad;
    }

    /**
     * It gets the btn confirmar jugadores
     * @return the btn confirmar jugadores
     */
    public JButton getBtnConfirmarJugadores() {
        return btnConfirmarJugadores;
    }

    /**
     * It gets the btn confirmar nombre
     * @return the btn confirmar nombre
     */
    public JButton getBtnConfirmarNombre() {
        return btnConfirmarNombre;
    }

    /**
     * It gets the btn confirmar precio
     * @return the btn confirmar precio
     */
    public JButton getBtnConfirmarPrecio() {
        return btnConfirmarPrecio;
    }

    /**
     * It gets the btn confirmar tipo juego
     * @return the btn confirmar tipo juego
     */
    public JButton getBtnConfirmarTipoJuego() {
        return btnConfirmarTipoJuego;
    }

    /**
     * It gets the txt categorías
     * @return the txt categorías
     */
    public JTextField getTxtCategorias() {
        return txtCategorias;
    }

    /**
     * It gets the txt nombre producto
     * @return the txt nombre producto
     */
    public JTextField getTxtNombreProducto() {
        return txtNombreProducto;
    }

    /**
     * It gets the txt num jugadores
     * @return the txt num jugadores
     */
    public JTextField getTxtNumJugadores() {
        return txtNumJugadores;
    }

    /**
     * It gets the txt precio
     * @return the txt precio
     */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    /**
     * It gets the txt rango edad
     * @return the txt rango edad
     */
    public JTextField getTxtRangoEdad() {
        return txtRangoEdad;
    }

    /**
     * It gets the txt tipo juego
     * @return the txt tipo juego
     */
    public JTextField getTxtTipoJuego() {
        return txtTipoJuego;
    }
}