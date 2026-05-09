
	package view;

import javax.swing.*;
import java.awt.*;

public class ManagerCreateComicP extends JPanel {

    // ======== ATRIBUTOS ========
    private JTextField txtCategoria;

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JTextField txtDescripcion;

    private JTextField txtNumPages;
    private JTextField txtYear;
    private JTextField txtAuthor;
    private JTextField txtEditorial;

    private JButton btnConfirmar;
    private JButton btnSubir;

    private JLabel vistaPrevia;

    public ManagerCreateComicP() {

        this.setLayout(new BorderLayout());

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

    // ======== GETTERS PARA EL CONTROLADOR ========
    public JTextField getTxtCategoria() { return txtCategoria; }
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtPrecio() { return txtPrecio; }
    public JTextField getTxtStock() { return txtStock; }
    public JTextField getTxtDescripcion() { return txtDescripcion; }

    public JTextField getTxtNumPages() { return txtNumPages; }
    public JTextField getTxtYear() { return txtYear; }
    public JTextField getTxtAuthor() { return txtAuthor; }
    public JTextField getTxtEditorial() { return txtEditorial; }

    public JButton getBtnConfirmar() { return btnConfirmar; }
    public JButton getBtnSubir() { return btnSubir; }
    public JLabel getVistaPrevia() { return vistaPrevia; }
}