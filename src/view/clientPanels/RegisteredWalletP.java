package view.clientPanels;

import model.product.ProductType;
import model.user.RegisteredClient;
import view.browserPanels.BrowseMyWalletP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

/**
 * The type Registered wallet p.
 * @author Ana O.R.
 * @version 1.0
 */
public class RegisteredWalletP extends JPanel {
    private final BrowseMyWalletP browseMyWalletP;
    private final JComboBox<String> productTypeCmbBox;
    private final HashMap<String, ProductType> typesHashMap = new HashMap<>();
    private final JButton addProduct = new JButton("Add Product");
    private final JButton photoChooser = new JButton("Upload Photo");
    private final JTextField nameField = new JTextField();
    private final JTextField descriptionField = new JTextField();
    private final RegisteredClient client;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Registered wallet p.
     * @param client the client
     * @throws BadLocationException the bad location exception
     */
    public RegisteredWalletP(RegisteredClient client) throws BadLocationException {
        this.setLayout(new BorderLayout());

        this.client = client;
        browseMyWalletP = new BrowseMyWalletP(client);

        typesHashMap.put("Comic", ProductType.COMIC);
        typesHashMap.put("Game", ProductType.GAME);
        typesHashMap.put("Figurine", ProductType.FIGURINE);
        productTypeCmbBox = new JComboBox<>(typesHashMap.keySet().toArray(new String[0]));

        paintEverything();
    }

    /**
     * Paint everything.
     */
    public void paintEverything() {
        this.removeAll();

        if (client == null) {
            this.revalidate();
            this.repaint();
            return;
        }

        JPanel addProductPanel = new JPanel();
        addProductPanel.setLayout(new BoxLayout(addProductPanel, BoxLayout.Y_AXIS));

        addProductPanel.add(Box.createVerticalGlue());

        JLabel title = new JLabel("Add a product to my wallet");
        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 15));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProductPanel.add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProductPanel.add(nameLabel);

        nameField.setColumns(15);
        nameField.setMaximumSize(nameField.getPreferredSize());
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProductPanel.add(nameField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProductPanel.add(descriptionLabel);

        descriptionField.setMaximumSize(new Dimension(150, 500));
        descriptionField.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProductPanel.add(descriptionField);

        productTypeCmbBox.setMaximumSize(productTypeCmbBox.getPreferredSize());
        productTypeCmbBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProductPanel.add(productTypeCmbBox);

        photoChooser.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProductPanel.add(photoChooser);

        addProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        addProductPanel.add(addProduct);

        addProductPanel.add(Box.createVerticalGlue());

        this.add(browseMyWalletP, BorderLayout.CENTER);
        this.add(addProductPanel, BorderLayout.EAST);

        this.revalidate();
        this.repaint();
    }

    /**
     * It gets the add product
     * @return the add product
     */
    public JButton getAddProduct() {
        return addProduct;
    }

    /**
     * It gets the browse my wallet p
     * @return the browse my wallet p
     */
    public BrowseMyWalletP getBrowseMyWalletP() {
        return browseMyWalletP;
    }

    /**
     * It gets the client
     * @return the client
     */
    public RegisteredClient getClient() {
        return client;
    }

    /**
     * It gets the description field
     * @return the description field
     */
    public JTextField getDescriptionField() {
        return descriptionField;
    }

    /**
     * It gets the name field
     * @return the name field
     */
    public JTextField getNameField() {
        return nameField;
    }

    /**
     * It gets the photo chooser
     * @return the photo chooser
     */
    public JButton getPhotoChooser() {
        return photoChooser;
    }

    /**
     * It gets the product type cmb box
     * @return the product type cmb box
     */
    public JComboBox<String> getProductTypeCmbBox() {
        return productTypeCmbBox;
    }

    /**
     * It gets the type
     * @return the type
     */
    public ProductType getType() {
        return typesHashMap.get(Objects.requireNonNull(productTypeCmbBox.getSelectedItem()).toString());
    }
}