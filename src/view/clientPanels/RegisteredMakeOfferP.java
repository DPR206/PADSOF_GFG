package view.clientPanels;

import view.browserPanels.BrowseSecondHandProductsP;
import view.browserPanels.BrowseWalletOwnersP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The type Registered make offer p.
 * @author Ana O.R.
 * @version 1.0
 */
public class RegisteredMakeOfferP extends JPanel {
    private final JButton browseAvailableProducts = new JButton("Browse available products");
    private final JButton browseUsers = new JButton("Browse users");
    private final BrowseSecondHandProductsP browseSecondHandProductsP;
    private final BrowseWalletOwnersP browseUsersP;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Registered make offer p.
     * @throws BadLocationException the bad location exception
     */
    public RegisteredMakeOfferP() throws BadLocationException {
        super();

        this.add(new JLabel("Select a product:"));

        browseSecondHandProductsP = new BrowseSecondHandProductsP("Add to offer", null);
        browseUsersP = new BrowseWalletOwnersP("Browse Wallet");

        this.setLayout(new BorderLayout());

        JPanel others = new JPanel(new BorderLayout());

        JPanel botones = new JPanel(new GridLayout(0, 2));
        botones.add(this.browseAvailableProducts);
        botones.add(this.browseUsers);

        others.add(botones, BorderLayout.NORTH);

        JPanel bottom = new JPanel(new CardLayout());
        bottom.add(this.browseSecondHandProductsP, "Browse available products");
        bottom.add(this.browseUsersP, "Browse users");

        others.add(bottom, BorderLayout.SOUTH);

        this.add(others, BorderLayout.CENTER);
    }

    /**
     * It gets the browse available products
     * @return the browse available products
     */
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public JButton getBrowseAvailableProducts() {
        return browseAvailableProducts;
    }

    /**
     * It gets the browse second hand products p
     * @return the browse second hand products p
     */
    public BrowseSecondHandProductsP getBrowseSecondHandProductsP() {
        return browseSecondHandProductsP;
    }

    /**
     * It gets the browse users
     * @return the browse users
     */
    public JButton getBrowseUsers() {
        return browseUsers;
    }

    /**
     * It gets the browse wallet owners p
     * @return the browse wallet owners p
     */
    public BrowseWalletOwnersP getBrowseWalletOwnersP() {
        return browseUsersP;
    }

    /**
     * It sets the controller
     * @param e the e
     */
    public void setController(ActionListener e) {
        this.browseAvailableProducts.addActionListener(e);
        this.browseUsers.addActionListener(e);
    }
}