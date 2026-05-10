package view.clientPanels;

import model.store.Store;
import view.browserPanels.BrowseSecondHandProductsP;
import view.browserPanels.BrowseWalletOwnersP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;

public class RegisteredMakeOfferP extends JPanel {
    private JButton browseAvailableProducts = new JButton("Browse available products");
    private JButton browseUsers = new JButton("Browse users");
    private BrowseSecondHandProductsP browseSecondHandProductsP;
    private BrowseWalletOwnersP browseUsersP;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public RegisteredMakeOfferP() throws BadLocationException {
        super();

        this.add(new JLabel("Select a product:"));

        browseSecondHandProductsP = new BrowseSecondHandProductsP();
        browseUsersP = new BrowseWalletOwnersP(Store.getInstance().getRegisteredClientList(), "Browse Wallet"); //
        // DUE: Tiene que asignar los productos el controlador

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

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public BrowseSecondHandProductsP getBrowseSecondHandProductsP() {
        return browseSecondHandProductsP;
    }

    public BrowseWalletOwnersP getBrowseWalletOwnersP() {
        return browseUsersP;
    }

    public void setController(ActionListener e) {
        this.browseAvailableProducts.addActionListener(e);
        this.browseUsers.addActionListener(e);
    }
}