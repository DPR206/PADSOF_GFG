package view.managerPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ManagerCreateComposedPackP extends JPanel {

    private JTextField name = new JTextField();
    private JTextField price = new JTextField();
    private JTextField productIdAdd = new JTextField();
    private JTextField packIdAdd = new JTextField();
    private JTextField pictureDirectory = new JTextField();
    private JButton id = new JButton("BUSCAR PRODUCTO");
    private JButton addPackButton = new JButton("BUSCAR PACK");
    private JButton confirmar = new JButton("Confirmar");
    public ManagerCreateComposedPackP() {

        super();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // =========================
        // Nombre del pack
        // =========================
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        this.add(new JLabel("Nombre del pack:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        this.add(name, gbc);

        // =========================
        // Precio
        // =========================
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;

        this.add(new JLabel("Precio del pack:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        this.add(price, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;

        this.add(new JLabel("Nombre del producto a insertar:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        this.add(productIdAdd, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;

        this.add(id, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;

        this.add(new JLabel("Nombre del pack a insertar:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        this.add(packIdAdd, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;

        this.add(addPackButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 0;

        this.add(new JLabel("Directorio de la foto:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        this.add(pictureDirectory, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        this.add(confirmar, gbc);
    }

    public JTextField getPackName() {
        return this.name;
    }

    public JTextField getPrice() {
        return price;
    }

    public JTextField getProductNameAdd() {
        return productIdAdd;
    }

    public JTextField getPackIdAdd() {
        return packIdAdd;
    }

    public JTextField getPictureDirectory() {
        return pictureDirectory;
    }

    public JButton getId() {
        return id;
    }

    public JButton getAddPackButton() {
        return addPackButton;
    }

    public JButton getConfirmar() {
        return confirmar;
    }
}