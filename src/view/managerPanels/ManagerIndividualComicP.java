package view.managerPanels;

import javax.swing.*;
import java.awt.*;

/**
 * The type Manager individual comic p.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerIndividualComicP extends JPanel {

    private final JTextField txtNombreProducto = new JTextField(15);
    private final JTextField txtAutor = new JTextField(15);
    private final JTextField txtEditorial = new JTextField(15);
    private final JTextField txtAnioPublicacion = new JTextField(15);
    private final JTextField txtNumPaginas = new JTextField(15);
    private final JTextField txtPrecio = new JTextField(15);
    private final JTextField txtCategorias = new JTextField(15);
    private final JTextField txtDescripcion = new JTextField(15);
    private final JTextField txtStock = new JTextField(15);

    private final JButton btnConfirmarNombre = new JButton("CONFIRMAR");
    private final JButton btnConfirmarAutor = new JButton("CONFIRMAR");
    private final JButton btnConfirmarEditorial = new JButton("CONFIRMAR");
    private final JButton btnConfirmarAnio = new JButton("CONFIRMAR");
    private final JButton btnConfirmarPaginas = new JButton("CONFIRMAR");
    private final JButton btnConfirmarPrecio = new JButton("CONFIRMAR");
    private final JButton btnConfirmarCategorias = new JButton("CONFIRMAR");
    private final JButton btnConfirmarDescripcion = new JButton("CONFIRMAR");
    private final JButton btnConfirmarStock = new JButton("CONFIRMAR");

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager individual comic p.
     */
    public ManagerIndividualComicP() {
        super();
        this.setLayout(new GridLayout(9, 3, 10, 10));

        // Nombre producto
        this.add(new JLabel("Nombre producto:"));
        this.add(txtNombreProducto);
        this.add(btnConfirmarNombre);

        // Autor
        this.add(new JLabel("Autor:"));
        this.add(txtAutor);
        this.add(btnConfirmarAutor);

        // Editorial
        this.add(new JLabel("Editorial:"));
        this.add(txtEditorial);
        this.add(btnConfirmarEditorial);

        // Año de publicación
        this.add(new JLabel("Año de publicación:"));
        this.add(txtAnioPublicacion);
        this.add(btnConfirmarAnio);

        // Nº de páginas
        this.add(new JLabel("Nº de páginas:"));
        this.add(txtNumPaginas);
        this.add(btnConfirmarPaginas);

        // Precio
        this.add(new JLabel("Precio:"));
        this.add(txtPrecio);
        this.add(btnConfirmarPrecio);

        // Categorías
        this.add(new JLabel("Categorías:"));
        this.add(txtCategorias);
        this.add(btnConfirmarCategorias);

        // NUEVO: Descripción
        this.add(new JLabel("Descripción:"));
        this.add(txtDescripcion);
        this.add(btnConfirmarDescripcion);

        // NUEVO: Stock
        this.add(new JLabel("Stock:"));
        this.add(txtStock);
        this.add(btnConfirmarStock);
    }

    /**
     * It gets the btn confirmar anio
     * @return the btn confirmar anio
     */
    public JButton getBtnConfirmarAnio() {
        return btnConfirmarAnio;
    }

    /**
     * It gets the btn confirmar autor
     * @return the btn confirmar autor
     */
    public JButton getBtnConfirmarAutor() {
        return btnConfirmarAutor;
    }

    /**
     * It gets the btn confirmar categorias
     * @return the btn confirmar categorias
     */
    public JButton getBtnConfirmarCategorias() {
        return btnConfirmarCategorias;
    }

    /**
     * It gets the btn confirmar descripcion
     * @return the btn confirmar descripcion
     */
    public JButton getBtnConfirmarDescripcion() {
        return btnConfirmarDescripcion;
    }

    /**
     * It gets the btn confirmar editorial
     * @return the btn confirmar editorial
     */
    public JButton getBtnConfirmarEditorial() {
        return btnConfirmarEditorial;
    }

    /**
     * It gets the btn confirmar nombre
     * @return the btn confirmar nombre
     */
    public JButton getBtnConfirmarNombre() {
        return btnConfirmarNombre;
    }

    /**
     * It gets the btn confirmar paginas
     * @return the btn confirmar paginas
     */
    public JButton getBtnConfirmarPaginas() {
        return btnConfirmarPaginas;
    }

    /**
     * It gets the btn confirmar precio
     * @return the btn confirmar precio
     */
    public JButton getBtnConfirmarPrecio() {
        return btnConfirmarPrecio;
    }

    /**
     * It gets the btn confirmar stock
     * @return the btn confirmar stock
     */
    public JButton getBtnConfirmarStock() {
        return btnConfirmarStock;
    }

    /**
     * It gets the txt anio publicacion
     * @return the txt anio publicacion
     */
    public JTextField getTxtAnioPublicacion() {
        return txtAnioPublicacion;
    }

    /**
     * It gets the txt autor
     * @return the txt autor
     */
    public JTextField getTxtAutor() {
        return txtAutor;
    }

    /**
     * It gets the txt categorias
     * @return the txt categorias
     */
    public JTextField getTxtCategorias() {
        return txtCategorias;
    }

    /**
     * It gets the txt descripcion
     * @return the txt descripcion
     */
    public JTextField getTxtDescripcion() {
        return txtDescripcion;
    }

    /**
     * It gets the txt editorial
     * @return the txt editorial
     */
    public JTextField getTxtEditorial() {
        return txtEditorial;
    }

    /**
     * It gets the txt nombre producto
     * @return the txt nombre producto
     */
// Getters
    public JTextField getTxtNombreProducto() {
        return txtNombreProducto;
    }

    /**
     * It gets the txt num paginas
     * @return the txt num paginas
     */
    public JTextField getTxtNumPaginas() {
        return txtNumPaginas;
    }

    /**
     * It gets the txt precio
     * @return the txt precio
     */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    /**
     * It gets the txt stock
     * @return the txt stock
     */
    public JTextField getTxtStock() {
        return txtStock;
    }
}