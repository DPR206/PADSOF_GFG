package view.clientPanels;

import model.product.ProductType;
import model.user.RegisteredClient;
import view.browserPanels.BrowseMyWalletEditP;
import view.browserPanels.BrowseOffersP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

import static view.ImageAdder.getScaledImage;

/**
 * The type Registered wallet p.
 * @author Ana O.R.
 * @version 1.0
 */
public class RegisteredWalletP extends JPanel {
    private final JButton btnOffers = new JButton("Offers");
    private final BrowseMyWalletEditP browseMyWalletP;
    private final JComboBox<String> productTypeCmbBox;
    private final HashMap<String, ProductType> typesHashMap = new HashMap<>();
    private final JButton addProduct = new JButton("Add Product");
    private final JButton photoChooser = new JButton("Upload Photo");
    private final JTextField nameField = new JTextField();
    private final JTextField descriptionField = new JTextField();
    private final RegisteredClient client;
    private final JPanel centerPanel = new JPanel();
    private final BrowseOffersP browseOffersP;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    /**
     * Instantiates a new Registered wallet p.
     * @param client the client
     * @throws BadLocationException the bad location exception
     */
    public RegisteredWalletP(RegisteredClient client) throws BadLocationException {
        this.setLayout(new BorderLayout());

        this.client = client;
        browseMyWalletP = new BrowseMyWalletEditP();
        browseOffersP = new BrowseOffersP();
        centerPanel.add(browseMyWalletP);

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

        JPanel lateralPanel = new JPanel();
        lateralPanel.setLayout(new BoxLayout(lateralPanel, BoxLayout.Y_AXIS));

        lateralPanel.add(Box.createVerticalGlue());

        btnOffers.setIcon(getScaledImage(new ImageIcon(".\\resources\\app\\exchange.png"), 32, 32));
        btnOffers.setAlignmentX(Component.CENTER_ALIGNMENT);
        lateralPanel.add(btnOffers);

        JLabel title = new JLabel("Add a product to my wallet");
        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 15));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        lateralPanel.add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lateralPanel.add(nameLabel);

        nameField.setColumns(15);
        nameField.setMaximumSize(nameField.getPreferredSize());
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        lateralPanel.add(nameField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        lateralPanel.add(descriptionLabel);

        descriptionField.setMaximumSize(new Dimension(150, 500));
        descriptionField.setAlignmentX(Component.CENTER_ALIGNMENT);
        lateralPanel.add(descriptionField);

        productTypeCmbBox.setMaximumSize(productTypeCmbBox.getPreferredSize());
        productTypeCmbBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        lateralPanel.add(productTypeCmbBox);

        photoChooser.setAlignmentX(Component.CENTER_ALIGNMENT);
        lateralPanel.add(photoChooser);

        addProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        lateralPanel.add(addProduct);

        lateralPanel.add(Box.createVerticalGlue());

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(lateralPanel, BorderLayout.EAST);

        this.revalidate();
        this.repaint();
    }

    /**
     * Show offers.
     */
    public void showOffers() {
        centerPanel.removeAll();
        centerPanel.add(browseOffersP);
        this.revalidate();
        this.repaint();
    }

    /**
     * Show products.
     */
    public void showProducts() {
        centerPanel.removeAll();
        centerPanel.add(browseMyWalletP);
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
    public BrowseMyWalletEditP getBrowseMyWalletP() {
        return browseMyWalletP;
    }

    public BrowseOffersP getBrowseOffersP() {
        return browseOffersP;
    }

    /**
     * It gets the btn offers
     * @return the btn offers
     */
    public JButton getBtnOffers() {
        return btnOffers;
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