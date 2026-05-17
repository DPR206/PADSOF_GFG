package view.managerPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The type Manager create game p.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerCreateGameP extends JPanel {

    // ======== ATRIBUTOS ========
    private final JTextField txtCategoria;

    private final JTextField txtNombre;
    private final JTextField txtPrecio;
    private final JTextField txtStock;
    private final JTextField txtDescripcion;

    private final JTextField txtNumPlayers;
    private final JTextField txtAgeRange;

    private final JRadioButton rbMesa;
    private final JRadioButton rbRol;
    private final JRadioButton rbCartas;
    private final JButton btnConfirmar;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager create game p.
     */
    public ManagerCreateGameP() {

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

        // Campo categoría
        JPanel categoriaPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        categoriaPanel.setBackground(todo.getBackground());
        categoriaPanel.add(new JLabel("Categoría (Juego, Cómic, Figura):"));
        txtCategoria = new JTextField("Juego");
        categoriaPanel.add(txtCategoria);

        superior.add(btnSubir);
        superior.add(vistaPrevia);
        superior.add(categoriaPanel);

        todo.add(superior, BorderLayout.NORTH);

        // ================= PANEL CENTRAL =================
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

        // ================= PANEL INFERIOR (JUEGO) =================
        JPanel inferior = new JPanel(new GridLayout(3, 2, 10, 10));
        inferior.setBackground(todo.getBackground());

        inferior.add(new JLabel("Número de jugadores:"));
        txtNumPlayers = new JTextField();
        inferior.add(txtNumPlayers);

        inferior.add(new JLabel("Rango de edad:"));
        txtAgeRange = new JTextField();
        inferior.add(txtAgeRange);

        inferior.add(new JLabel("Tipo de juego:"));

        // Panel para los radio buttons
        JPanel estiloPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        estiloPanel.setBackground(todo.getBackground());

        rbMesa = new JRadioButton("Mesa");
        rbRol = new JRadioButton("Rol");
        rbCartas = new JRadioButton("Cartas");

        rbMesa.setBackground(todo.getBackground());
        rbRol.setBackground(todo.getBackground());
        rbCartas.setBackground(todo.getBackground());

        // Agruparlos
        ButtonGroup groupGameStyle = new ButtonGroup();
        groupGameStyle.add(rbMesa);
        groupGameStyle.add(rbRol);
        groupGameStyle.add(rbCartas);

        // Selección por defecto
        rbMesa.setSelected(true);

        estiloPanel.add(rbMesa);
        estiloPanel.add(rbRol);
        estiloPanel.add(rbCartas);

        inferior.add(estiloPanel);

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
     * It gets the rb cartas
     * @return the rb cartas
     */
    public JRadioButton getRbCartas() {
        return rbCartas;
    }

    /**
     * It gets the rb mesa
     * @return the rb mesa
     */
    public JRadioButton getRbMesa() {
        return rbMesa;
    }

    /**
     * It gets the rb rol
     * @return the rb rol
     */
    public JRadioButton getRbRol() {
        return rbRol;
    }

    /**
     * It gets the txt age range
     * @return the txt age range
     */
    public JTextField getTxtAgeRange() {
        return txtAgeRange;
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
     * It gets the txt nombre
     * @return the txt nombre
     */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /**
     * It gets the txt num players
     * @return the txt num players
     */
    public JTextField getTxtNumPlayers() {
        return txtNumPlayers;
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