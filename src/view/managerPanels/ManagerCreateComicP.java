package view.managerPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The type Manager create comic p.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerCreateComicP extends JPanel {

    // ======== ATRIBUTOS ========
    private final JTextField txtCategoria;

    private final JTextField txtNombre;
    private final JTextField txtPrecio;
    private final JTextField txtStock;
    private final JTextField txtDescripcion;

    private final JTextField txtNumPages;
    private final JTextField txtYear;
    private final JTextField txtAuthor;
    private final JTextField txtEditorial;

    private final JButton btnConfirmar;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager create comic p.
     */
    public ManagerCreateComicP() {

        this.setLayout(new BorderLayout());

        JPanel todo = new JPanel(new BorderLayout());
        todo.setBackground(new Color(245, 241, 236));

        JPanel superior = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        superior.setBackground(todo.getBackground());

        JButton btnSubir = new JButton("⬆ Subir fichero");
        btnSubir.setBackground(new Color(190, 150, 130));
        btnSubir.setForeground(Color.DARK_GRAY);
        btnSubir.setPreferredSize(new Dimension(150, 60));

        JLabel vistaPrevia = new JLabel();
        vistaPrevia.setPreferredSize(new Dimension(150, 60));
        vistaPrevia.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel categoriaPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        categoriaPanel.setBackground(todo.getBackground());
        categoriaPanel.add(new JLabel("Categoría (Juego, Cómic, Figura):"));
        txtCategoria = new JTextField("Cómic");
        categoriaPanel.add(txtCategoria);

        superior.add(btnSubir);
        superior.add(vistaPrevia);
        superior.add(categoriaPanel);

        todo.add(superior, BorderLayout.NORTH);

        JPanel central = new JPanel(new GridLayout(4, 2, 10, 10));
        central.setBackground(todo.getBackground());

        central.add(new JLabel("Nombre producto:"));
        txtNombre = new JTextField();
        central.add(txtNombre);

        central.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        central.add(txtPrecio);

        central.add(new JLabel("Unidades en stock:"));
        txtStock = new JTextField();
        central.add(txtStock);

        central.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        central.add(txtDescripcion);

        todo.add(central, BorderLayout.CENTER);

        JPanel inferior = new JPanel(new GridLayout(4, 2, 10, 10));
        inferior.setBackground(todo.getBackground());

        inferior.add(new JLabel("Número de páginas:"));
        txtNumPages = new JTextField();
        inferior.add(txtNumPages);

        inferior.add(new JLabel("Año de publicación:"));
        txtYear = new JTextField();
        inferior.add(txtYear);

        inferior.add(new JLabel("Autor:"));
        txtAuthor = new JTextField();
        inferior.add(txtAuthor);

        inferior.add(new JLabel("Editorial:"));
        txtEditorial = new JTextField();
        inferior.add(txtEditorial);

        JPanel parteInferior = new JPanel(new BorderLayout());
        parteInferior.setBackground(todo.getBackground());
        parteInferior.add(inferior, BorderLayout.CENTER);

        btnConfirmar = new JButton("CONFIRMAR");
        btnConfirmar.setBackground(new Color(60, 50, 45));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnConfirmar.setPreferredSize(new Dimension(150, 40));

        JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botonPanel.setBackground(todo.getBackground());
        botonPanel.add(btnConfirmar);

        parteInferior.add(botonPanel, BorderLayout.SOUTH);

        todo.add(parteInferior, BorderLayout.SOUTH);

        this.add(todo, BorderLayout.CENTER);
    }

    /**
     * It gets the btn confirmar
     * @return the btn confirmar
     */
    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }

    /**
     * It gets the txt author
     * @return the txt author
     */
    public JTextField getTxtAuthor() {
        return txtAuthor;
    }

    /**
     * It gets the txt categoria
     * @return the txt categoria
     */
    public JTextField getTxtCategoria() {
        return txtCategoria;
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
     * It gets the txt nombre
     * @return the txt nombre
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * It gets the txt num pages
     * @return the txt num pages
     */
    public JTextField getTxtNumPages() {
        return txtNumPages;
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

    /**
     * It gets the txt year
     * @return the txt year
     */
    public JTextField getTxtYear() {
        return txtYear;
    }

    /**
     * It sets the controller
     * @param e the e
     */
    public void setController(ActionListener e) {
        this.btnConfirmar.addActionListener(e);
    }
}