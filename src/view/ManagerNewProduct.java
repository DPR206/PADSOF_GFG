package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ManagerNewProduct extends JPanel{

	    // 🔹 ATRIBUTOS
	    private JTextField nombreField;
	    private JTextField precioField;
	    private JTextField stockField;
	    private JTextArea descArea;

	    private JTextField marcaField;
	    private JTextField materialField;
	    private JTextField dim1;
	    private JTextField dim2;
	    private JTextField dim3;

	    private JButton btnSubir;
	    private JButton confirmarBtn;

	    private JCheckBox cbMesa;
	    private JCheckBox cbRol;
	    private JCheckBox cbCartas;
	    private JCheckBox cbFiguras;
	    private JCheckBox cbComics;

	    public ManagerNewProduct() {
	        super();
	        setSize(900, 450);
	      

	        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
	        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

	        JPanel formPanel = new JPanel(new GridLayout(1, 2, 20, 0));

	        JPanel leftPanel = new JPanel();
	        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

	        btnSubir = new JButton("Subir fichero");

	        nombreField = new JTextField();
	        precioField = new JTextField();
	        stockField = new JTextField();
	        descArea = new JTextArea(4, 20);

	        JScrollPane descScroll = new JScrollPane(descArea);

	        leftPanel.add(btnSubir);
	        leftPanel.add(Box.createVerticalStrut(10));
	        leftPanel.add(new JLabel("Nombre producto:"));
	        leftPanel.add(nombreField);
	        leftPanel.add(Box.createVerticalStrut(10));
	        leftPanel.add(new JLabel("Precio:"));
	        leftPanel.add(precioField);
	        leftPanel.add(Box.createVerticalStrut(10));
	        leftPanel.add(new JLabel("Unidades en stock:"));
	        leftPanel.add(stockField);
	        leftPanel.add(Box.createVerticalStrut(10));
	        leftPanel.add(new JLabel("Descripción:"));
	        leftPanel.add(descScroll);

	        // 🔹 DERECHA
	        JPanel rightPanel = new JPanel();
	        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

	        cbMesa = new JCheckBox("Juegos de mesa");
	        cbRol = new JCheckBox("Juegos de rol");
	        cbCartas = new JCheckBox("Juegos de cartas");
	        cbFiguras = new JCheckBox("Figuras");
	        cbComics = new JCheckBox("Cómics");

	        JPanel catPanel = new JPanel(new GridLayout(3, 2));
	        catPanel.add(cbMesa);
	        catPanel.add(cbRol);
	        catPanel.add(cbCartas);
	        catPanel.add(cbFiguras);
	        catPanel.add(cbComics);

	        marcaField = new JTextField();
	        materialField = new JTextField();

	        dim1 = new JTextField(5);
	        dim2 = new JTextField(5);
	        dim3 = new JTextField(5);

	        JPanel dimPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        dimPanel.add(dim1);
	        dimPanel.add(new JLabel("x"));
	        dimPanel.add(dim2);
	        dimPanel.add(new JLabel("x"));
	        dimPanel.add(dim3);

	        confirmarBtn = new JButton("CONFIRMAR");
	        

	        rightPanel.add(new JLabel("Categoría (mínimo 1):"));
	        rightPanel.add(catPanel);
	        rightPanel.add(Box.createVerticalStrut(10));
	        rightPanel.add(new JLabel("Marca:"));
	        rightPanel.add(marcaField);
	        rightPanel.add(Box.createVerticalStrut(10));
	        rightPanel.add(new JLabel("Material:"));
	        rightPanel.add(materialField);
	        rightPanel.add(Box.createVerticalStrut(10));
	        rightPanel.add(new JLabel("Dimensiones:"));
	        rightPanel.add(dimPanel);
	        rightPanel.add(Box.createVerticalStrut(20));
	        rightPanel.add(confirmarBtn);

	        formPanel.add(leftPanel);
	        formPanel.add(rightPanel);

	        mainPanel.add(formPanel, BorderLayout.CENTER);
	        add(mainPanel);
	    }

	    
	    public void setController(ActionListener c) {
	        btnSubir.addActionListener(c);
	        confirmarBtn.addActionListener(c);

	        // Opcional (si quieres que el controlador escuche más eventos)
	        cbMesa.addActionListener(c);
	        cbRol.addActionListener(c);
	        cbCartas.addActionListener(c);
	        cbFiguras.addActionListener(c);
	        cbComics.addActionListener(c);

	        nombreField.addActionListener(c);
	        precioField.addActionListener(c);
	        stockField.addActionListener(c);
	        marcaField.addActionListener(c);
	        materialField.addActionListener(c);
	        dim1.addActionListener(c);
	        dim2.addActionListener(c);
	        dim3.addActionListener(c);
	    }
}
