package view.managerPanels;

import javax.swing.*;
import java.awt.*;

/**
 * The type Manage individual figura p.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManageIndividualFiguraP extends JPanel {

    // JTextFields como atributos
    private final JTextField txtNombre = new JTextField(15);
    private final JTextField txtMarca = new JTextField(15);
    private final JTextField txtMaterial = new JTextField(15);
    private final JTextField txtDimensiones = new JTextField(15);
    private final JTextField txtPrecio = new JTextField(15);
    private final JTextField txtCategorias = new JTextField(15);

    // JButtons como atributos
    private final JButton btnConfirmarNombre = new JButton("CONFIRMAR");
    private final JButton btnConfirmarMarca = new JButton("CONFIRMAR");
    private final JButton btnConfirmarMaterial = new JButton("CONFIRMAR");
    private final JButton btnConfirmarDimensiones = new JButton("CONFIRMAR");
    private final JButton btnConfirmarPrecio = new JButton("CONFIRMAR");
    private final JButton btnConfirmarCategorias = new JButton("CONFIRMAR");

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manage individual figura p.
     */
    public ManageIndividualFiguraP() {
        super();

        // 8 filas (una por atributo), 3 columnas (label, textfield, button)
        this.setLayout(new GridLayout(8, 3, 10, 10));

        // Fila 1: Nombre
        this.add(new JLabel("Nombre del producto:"));
        this.add(txtNombre);
        this.add(btnConfirmarNombre);

        // Fila 2: Marca
        this.add(new JLabel("Marca:"));
        this.add(txtMarca);
        this.add(btnConfirmarMarca);

        // Fila 3: Material
        this.add(new JLabel("Material:"));
        this.add(txtMaterial);
        this.add(btnConfirmarMaterial);

        // Fila 4: Dimensiones
        this.add(new JLabel("Dimensiones:"));
        this.add(txtDimensiones);
        this.add(btnConfirmarDimensiones);

        // Fila 5: Precio
        this.add(new JLabel("Precio:"));
        this.add(txtPrecio);
        this.add(btnConfirmarPrecio);

        // Fila 6: Categorías
        this.add(new JLabel("Categorías:"));
        this.add(txtCategorias);
        this.add(btnConfirmarCategorias);

        // Fila 7: Stock
        this.add(new JLabel("Stock:"));
        JTextField stock = new JTextField(15);
        this.add(stock);
        JButton btnConfirmarStock = new JButton("CONFIRMAR");
        this.add(btnConfirmarStock);

        // Fila 8: Descripción
        this.add(new JLabel("Descripción:"));
        JTextField description = new JTextField(15);
        this.add(description);
        JButton btnConfirmarDescription = new JButton("CONFIRMAR");
        this.add(btnConfirmarDescription);
    }

    /**
     * It gets the btn confirmar categorias
     * @return the btn confirmar categorias
     */
    public JButton getBtnConfirmarCategorias() {
        return btnConfirmarCategorias;
    }

    /**
     * It gets the btn confirmar dimensiones
     * @return the btn confirmar dimensiones
     */
    public JButton getBtnConfirmarDimensiones() {
        return btnConfirmarDimensiones;
    }

    /**
     * It gets the btn confirmar marca
     * @return the btn confirmar marca
     */
    public JButton getBtnConfirmarMarca() {
        return btnConfirmarMarca;
    }

    /**
     * It gets the btn confirmar material
     * @return the btn confirmar material
     */
    public JButton getBtnConfirmarMaterial() {
        return btnConfirmarMaterial;
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
     * It gets the txt categorias
     * @return the txt categorias
     */
    public JTextField getTxtCategorias() {
        return txtCategorias;
    }

    /**
     * It gets the txt dimensiones
     * @return the txt dimensiones
     */
    public JTextField getTxtDimensiones() {
        return txtDimensiones;
    }

    /**
     * It gets the txt marca
     * @return the txt marca
     */
    public JTextField getTxtMarca() {
        return txtMarca;
    }

    /**
     * It gets the txt material
     * @return the txt material
     */
    public JTextField getTxtMaterial() {
        return txtMaterial;
    }

    /**
     * It gets the txt nombre
     * @return the txt nombre
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * It gets the txt precio
     * @return the txt precio
     */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }
}