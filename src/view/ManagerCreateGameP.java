package view;

import javax.swing.*;
import view.banners.BannerManager;
import java.awt.*;

public class ManagerCreateGameP extends JPanel {

    // ======== ATRIBUTOS ========
    private JTextField txtCategoria;

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JTextField txtDescripcion;

    private JTextField txtNumPlayers;
    private JTextField txtAgeRange;

    private JRadioButton rbMesa;
    private JRadioButton rbRol;
    private JRadioButton rbCartas;
    private ButtonGroup groupGameStyle;

    private JButton btnConfirmar;
    private JButton btnSubir;

    private JLabel vistaPrevia;
    private BannerManager banner = new BannerManager();

    public ManagerCreateGameP() {

        this.setLayout(new BorderLayout());
        this.add(banner, BorderLayout.NORTH);

        JPanel todo = new JPanel(new BorderLayout());
        todo.setBackground(new Color(245, 241, 236));

        JPanel superior = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        superior.setBackground(todo.getBackground());

        btnSubir = new JButton("⬆ Subir fichero");
        btnSubir.setBackground(new Color(190, 150, 130));
        btnSubir.setForeground(Color.DARK_GRAY);
        btnSubir.setPreferredSize(new Dimension(150, 60));

        vistaPrevia = new JLabel();
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
        groupGameStyle = new ButtonGroup();
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

    public JTextField getTxtCategoria() { return txtCategoria; }
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtPrecio() { return txtPrecio; }
    public JTextField getTxtStock() { return txtStock; }
    public JTextField getTxtDescripcion() { return txtDescripcion; }

    public JTextField getTxtNumPlayers() { return txtNumPlayers; }
    public JTextField getTxtAgeRange() { return txtAgeRange; }

    public JRadioButton getRbMesa() { return rbMesa; }
    public JRadioButton getRbRol() { return rbRol; }
    public JRadioButton getRbCartas() { return rbCartas; }

    public JButton getBtnConfirmar() { return btnConfirmar; }
    public JButton getBtnSubir() { return btnSubir; }
    public JLabel getVistaPrevia() { return vistaPrevia; }
}
