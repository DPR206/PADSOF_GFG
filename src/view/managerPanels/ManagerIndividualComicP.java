package view.managerPanels;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ManagerIndividualComicP extends JPanel {

    private JTextField txtNombreProducto = new JTextField(15);
    private JTextField txtAutor = new JTextField(15);
    private JTextField txtEditorial = new JTextField(15);
    private JTextField txtAnioPublicacion = new JTextField(15);
    private JTextField txtNumPaginas = new JTextField(15);
    private JTextField txtPrecio = new JTextField(15);
    private JTextField txtCategorias = new JTextField(15);
    private JTextField txtDescripcion = new JTextField(15);
    private JTextField txtStock = new JTextField(15);

    private JButton btnConfirmarNombre = new JButton("CONFIRMAR");
    private JButton btnConfirmarAutor = new JButton("CONFIRMAR");
    private JButton btnConfirmarEditorial = new JButton("CONFIRMAR");
    private JButton btnConfirmarAnio = new JButton("CONFIRMAR");
    private JButton btnConfirmarPaginas = new JButton("CONFIRMAR");
    private JButton btnConfirmarPrecio = new JButton("CONFIRMAR");
    private JButton btnConfirmarCategorias = new JButton("CONFIRMAR");
    private JButton btnConfirmarDescripcion = new JButton("CONFIRMAR");
    private JButton btnConfirmarStock = new JButton("CONFIRMAR");

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

    // Getters
    public JTextField getTxtNombreProducto() { return txtNombreProducto; }
    public JTextField getTxtAutor() { return txtAutor; }
    public JTextField getTxtEditorial() { return txtEditorial; }
    public JTextField getTxtAnioPublicacion() { return txtAnioPublicacion; }
    public JTextField getTxtNumPaginas() { return txtNumPaginas; }
    public JTextField getTxtPrecio() { return txtPrecio; }
    public JTextField getTxtCategorias() { return txtCategorias; }

    public JTextField getTxtDescripcion() { return txtDescripcion; }
    public JTextField getTxtStock() { return txtStock; }

    public JButton getBtnConfirmarNombre() { return btnConfirmarNombre; }
    public JButton getBtnConfirmarAutor() { return btnConfirmarAutor; }
    public JButton getBtnConfirmarEditorial() { return btnConfirmarEditorial; }
    public JButton getBtnConfirmarAnio() { return btnConfirmarAnio; }
    public JButton getBtnConfirmarPaginas() { return btnConfirmarPaginas; }
    public JButton getBtnConfirmarPrecio() { return btnConfirmarPrecio; }
    public JButton getBtnConfirmarCategorias() { return btnConfirmarCategorias; }

    public JButton getBtnConfirmarDescripcion() { return btnConfirmarDescripcion; }
    public JButton getBtnConfirmarStock() { return btnConfirmarStock; }
}
