package view.managerPanels;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The type Manager create figurita p.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerCreateFiguritaP extends JPanel {

    private final JTextField txtCategoria;
    private final JTextField txtNombre;
    private final JTextField txtPrecio;
    private final JTextField txtStock;
    private final JTextField txtDescripcion;
    private final JTextField txtMarca;
    private final JTextField txtMaterial;
    private final JTextField txtLargo;
    private final JTextField txtAncho;
    private final JTextField txtAlto;
    private final JButton btnConfirmar;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager create figurita p.
     */
    public ManagerCreateFiguritaP() {
        this.setLayout(new BorderLayout());

        JPanel todo = new JPanel();

        setBackground(new Color(245, 241, 236));

        JPanel superior = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        superior.setBackground(getBackground());

        JButton btnSubir = new JButton("⬆ Subir fichero");
        btnSubir.setBackground(new Color(190, 150, 130));
        btnSubir.setForeground(Color.DARK_GRAY);
        btnSubir.setPreferredSize(new Dimension(150, 60));

        JLabel vistaPrevia = new JLabel();
        vistaPrevia.setPreferredSize(new Dimension(150, 60));
        vistaPrevia.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel categoriaPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        categoriaPanel.setBackground(getBackground());
        categoriaPanel.add(new JLabel("Categoría (Juego, Cómic, Figura):"));
        txtCategoria = new JTextField();
        categoriaPanel.add(txtCategoria);

        superior.add(btnSubir);
        superior.add(vistaPrevia);
        superior.add(categoriaPanel);

        todo.add(superior, BorderLayout.NORTH);

        JPanel central = new JPanel(new GridLayout(4, 2, 10, 10));
        central.setBackground(getBackground());

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

        JPanel inferior = new JPanel(new GridLayout(3, 2, 10, 10));
        inferior.setBackground(getBackground());

        inferior.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        inferior.add(txtMarca);

        inferior.add(new JLabel("Material:"));
        txtMaterial = new JTextField();
        inferior.add(txtMaterial);

        inferior.add(new JLabel("Dimensiones (L x A x H):"));

        JPanel dimensiones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dimensiones.setBackground(getBackground());

        txtLargo = new JTextField(5);
        txtAncho = new JTextField(5);
        txtAlto = new JTextField(5);

        dimensiones.add(txtLargo);
        dimensiones.add(new JLabel("x"));
        dimensiones.add(txtAncho);
        dimensiones.add(new JLabel("x"));
        dimensiones.add(txtAlto);

        inferior.add(dimensiones);

        todo.add(inferior, BorderLayout.SOUTH);

        btnConfirmar = new JButton("CONFIRMAR");
        btnConfirmar.setBackground(new Color(60, 50, 45));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnConfirmar.setPreferredSize(new Dimension(150, 40));

        JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botonPanel.setBackground(getBackground());
        botonPanel.add(btnConfirmar);

        todo.add(botonPanel, BorderLayout.PAGE_END);
        this.add(todo);
    }

    /**
     * It gets the btn confirmar
     * @return the btn confirmar
     */
    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }

    /**
     * It gets the txt alto
     * @return the txt alto
     */
    public JTextField getTxtAlto() {
        return txtAlto;
    }

    /**
     * It gets the txt ancho
     * @return the txt ancho
     */
    public JTextField getTxtAncho() {
        return txtAncho;
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
     * It gets the txt largo
     * @return the txt largo
     */
    public JTextField getTxtLargo() {
        return txtLargo;
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

    /**
     * It gets the txt stock
     * @return the txt stock
     */
    public JTextField getTxtStock() {
        return txtStock;
    }

    /**
     * It sets the controller
     * @param e the e
     */
    public void setController(ActionListener e) {
        this.btnConfirmar.addActionListener(e);
    }
}