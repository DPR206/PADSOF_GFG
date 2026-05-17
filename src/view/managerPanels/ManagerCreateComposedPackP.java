package view.managerPanels;

import javax.swing.*;
import java.awt.*;

/**
 * The type Manager create composed pack p.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerCreateComposedPackP extends JPanel {

    private final JTextField name = new JTextField();
    private final JTextField price = new JTextField();
    private final JTextField productIdAdd = new JTextField();
    private final JTextField packIdAdd = new JTextField();
    private final JTextField pictureDirectory = new JTextField();
    private final JButton nombre = new JButton("BUSCAR PRODUCTO");
    private final JButton addPackButton = new JButton("BUSCAR PACK");
    private final JButton confirmar = new JButton("Confirmar");

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager create composed pack p.
     */
    public ManagerCreateComposedPackP() {

        super();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        this.add(new JLabel("Nombre del pack:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        this.add(name, gbc);

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

        this.add(nombre, gbc);

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

    /**
     * It gets the add pack button
     * @return the add pack button
     */
    public JButton getAddPackButton() {
        return addPackButton;
    }

    /**
     * It gets the confirmar
     * @return the confirmar
     */
    public JButton getConfirmar() {
        return confirmar;
    }

    /**
     * It gets the nombre
     * @return the nombre
     */
    public JButton getNombre() {
        return this.nombre;
    }

    /**
     * It gets the pack id add
     * @return the pack id add
     */
    public JTextField getPackIdAdd() {
        return packIdAdd;
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