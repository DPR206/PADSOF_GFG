package view.managerPanels;

import javax.swing.JPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManageIndividualFiguraP extends JPanel {
    
    // JTextFields como atributos
    private JTextField txtNombre = new JTextField(15);
    private JTextField txtMarca = new JTextField(15);
    private JTextField txtMaterial = new JTextField(15);
    private JTextField txtDimensiones = new JTextField(15);
    private JTextField txtPrecio = new JTextField(15);
    private JTextField txtCategorias = new JTextField(15);
    private JTextField stock = new JTextField(15);
    private JTextField description = new JTextField(15);
    
    // JButtons como atributos
    private JButton btnConfirmarNombre = new JButton("CONFIRMAR");
    private JButton btnConfirmarMarca = new JButton("CONFIRMAR");
    private JButton btnConfirmarMaterial = new JButton("CONFIRMAR");
    private JButton btnConfirmarDimensiones = new JButton("CONFIRMAR");
    private JButton btnConfirmarPrecio = new JButton("CONFIRMAR");
    private JButton btnConfirmarCategorias = new JButton("CONFIRMAR");
    private JButton btnConfirmarStock = new JButton("CONFIRMAR");
    private JButton btnConfirmarDescription = new JButton("CONFIRMAR");

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
        this.add(stock);
        this.add(btnConfirmarStock);

        // Fila 8: Descripción
        this.add(new JLabel("Descripción:"));
        this.add(description);
        this.add(btnConfirmarDescription);   
    }
    

    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtMarca() { return txtMarca; }
    public JTextField getTxtMaterial() { return txtMaterial; }
    public JTextField getTxtDimensiones() { return txtDimensiones; }
    public JTextField getTxtPrecio() { return txtPrecio; }
    public JTextField getTxtCategorias() { return txtCategorias; }

    public JButton getBtnConfirmarNombre() { return btnConfirmarNombre; }
    public JButton getBtnConfirmarMarca() { return btnConfirmarMarca; }
    public JButton getBtnConfirmarMaterial() { return btnConfirmarMaterial; }
    public JButton getBtnConfirmarDimensiones() { return btnConfirmarDimensiones; }
    public JButton getBtnConfirmarPrecio() { return btnConfirmarPrecio; }
    public JButton getBtnConfirmarCategorias() { return btnConfirmarCategorias; }
}