package view.managerPanels;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerCreateFiguritaP extends JPanel {

    private JTextField txtCategoria;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JTextField txtDescripcion;
    private JTextField txtMarca;
    private JTextField txtMaterial;
    private JTextField txtLargo;
    private JTextField txtAncho;
    private JTextField txtAlto;
    private JButton btnConfirmar;
    private JButton btnSubir;
    private JLabel vistaPrevia;

/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerCreateFiguritaP() {
        this.setLayout(new BorderLayout());

        JPanel todo = new JPanel();

        setBackground(new Color(245, 241, 236));

        JPanel superior = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        superior.setBackground(getBackground());

        btnSubir = new JButton("⬆ Subir fichero");
        btnSubir.setBackground(new Color(190, 150, 130));
        btnSubir.setForeground(Color.DARK_GRAY);
        btnSubir.setPreferredSize(new Dimension(150, 60));

        vistaPrevia = new JLabel();
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

    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }

    public JButton getBtnSubir() {
        return btnSubir;
    }

    public JTextField getTxtAlto() {
        return txtAlto;
    }

    public JTextField getTxtAncho() {
        return txtAncho;
    }

    public JTextField getTxtCategoria() {
        return txtCategoria;
    }

    public JTextField getTxtDescripcion() {
        return txtDescripcion;
    }

    public JTextField getTxtLargo() {
        return txtLargo;
    }

    public JTextField getTxtMarca() {
        return txtMarca;
    }

    public JTextField getTxtMaterial() {
        return txtMaterial;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JTextField getTxtStock() {
        return txtStock;
    }

    public JLabel getVistaPrevia() {
        return vistaPrevia;
    }

    public void setController(ActionListener e) {
        this.btnConfirmar.addActionListener(e);
    }
}