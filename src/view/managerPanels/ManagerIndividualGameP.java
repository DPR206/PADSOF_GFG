package view.managerPanels;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ManagerIndividualGameP extends JPanel {

    private JTextField txtNombreProducto = new JTextField(15);
    private JTextField txtTipoJuego = new JTextField(15);
    private JTextField txtNumJugadores = new JTextField(15);
    private JTextField txtRangoEdad = new JTextField(15);
    private JTextField txtPrecio = new JTextField(15);
    private JTextField txtCategorias = new JTextField(15);

    private JButton btnConfirmarNombre = new JButton("CONFIRMAR");
    private JButton btnConfirmarTipoJuego = new JButton("CONFIRMAR");
    private JButton btnConfirmarJugadores = new JButton("CONFIRMAR");
    private JButton btnConfirmarEdad = new JButton("CONFIRMAR");
    private JButton btnConfirmarPrecio = new JButton("CONFIRMAR");
    private JButton btnConfirmarCategorias = new JButton("CONFIRMAR");

    public ManagerIndividualGameP() {
        super();
        this.setLayout(new GridLayout(6, 3, 10, 10)); // 6 filas, 3 columnas

        // Nombre producto
        this.add(new JLabel("Nombre producto:"));
        this.add(txtNombreProducto);
        this.add(btnConfirmarNombre);

        // Tipo de juego
        this.add(new JLabel("Tipo de juego:"));
        this.add(txtTipoJuego);
        this.add(btnConfirmarTipoJuego);

        // Nº de jugadores
        this.add(new JLabel("Nº de jugadores:"));
        this.add(txtNumJugadores);
        this.add(btnConfirmarJugadores);

        // Rango de edad
        this.add(new JLabel("Rango de edad:"));
        this.add(txtRangoEdad);
        this.add(btnConfirmarEdad);

        // Precio
        this.add(new JLabel("Precio:"));
        this.add(txtPrecio);
        this.add(btnConfirmarPrecio);

        // Categorías
        this.add(new JLabel("Categorías:"));
        this.add(txtCategorias);
        this.add(btnConfirmarCategorias);
    }
    public JTextField getTxtNombreProducto() {
        return txtNombreProducto;
    }

    public JTextField getTxtTipoJuego() {
        return txtTipoJuego;
    }

    public JTextField getTxtNumJugadores() {
        return txtNumJugadores;
    }

    public JTextField getTxtRangoEdad() {
        return txtRangoEdad;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JTextField getTxtCategorias() {
        return txtCategorias;
    }

    public JButton getBtnConfirmarNombre() {
        return btnConfirmarNombre;
    }

    public JButton getBtnConfirmarTipoJuego() {
        return btnConfirmarTipoJuego;
    }

    public JButton getBtnConfirmarJugadores() {
        return btnConfirmarJugadores;
    }

    public JButton getBtnConfirmarEdad() {
        return btnConfirmarEdad;
    }

    public JButton getBtnConfirmarPrecio() {
        return btnConfirmarPrecio;
    }

    public JButton getBtnConfirmarCategorias() {
        return btnConfirmarCategorias;
    }
}
