package view.managerPanels;

import javax.swing.*;
import java.awt.*;

/**
 * The type Manager create simple pack.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerCreateSimplePack extends JPanel {

    private final JTextField name = new JTextField();
    private final JTextField price = new JTextField();
    private final JTextField productIdAdd = new JTextField();
    private final JTextField pictureDirectory = new JTextField();

    private final JButton id = new JButton("BUSCAR PRODUCTO");
    private final JButton confirmar = new JButton("Confirmar");

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager create simple pack.
     */
    public ManagerCreateSimplePack() {

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

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        this.add(new JLabel("Directorio de la foto:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        this.add(pictureDirectory, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(id, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(confirmar, gbc);
    }

    /**
     * It gets the confirmar
     * @return the confirmar
     */
    public JButton getConfirmar() {
        return confirmar;
    }

    /**
     * It gets the id
     * @return the id
     */
    public JButton getId() {
        return id;
    }

    /**
     * It gets the pack name
     * @return the pack name
     */
    public JTextField getPackName() {
        return this.name;
    }

    /**
     * It gets the picture directory
     * @return the picture directory
     */
    public JTextField getPictureDirectory() {
        return pictureDirectory;
    }

    /**
     * It gets the price
     * @return the price
     */
    public JTextField getPrice() {
        return price;
    }

    /**
     * It gets the product name add
     * @return the product name add
     */
    public JTextField getProductNameAdd() {
        return productIdAdd;
    }
}