package view;

import model.store.Store;
import view.banners.BannerRegistered;
import view.browserPanels.BrowseSecondHandProductsP;
import view.browserPanels.BrowseWalletOwnersP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;

public class BrowseForOffersP extends JPanel {
    private JButton browseAvailableProducts = new JButton("Browse available products");
    private JButton browseUsers = new JButton("Browse users");
    private App app;
    private BrowseSecondHandProductsP browseSecondHandProductsP;
    private BrowseWalletOwnersP browseUsersP;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public BrowseForOffersP(App app) throws BadLocationException {
        super();
        this.app = app;

        this.add(new JLabel("Select a product:"));

        browseSecondHandProductsP = new BrowseSecondHandProductsP(app);
        browseUsersP = new BrowseWalletOwnersP(app, Store.getInstance().getRegisteredClientList(), "Browse Wallet");

        this.setLayout(new BorderLayout());

        this.add(new BannerRegistered(), BorderLayout.NORTH);

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

    public void setController(ActionListener e) {
        this.browseAvailableProducts.addActionListener(e);
        this.browseUsers.addActionListener(e);
    }

    public BrowseSecondHandProductsP getBrowseSecondHandProductsP() {
        return browseSecondHandProductsP;
    }

    public BrowseWalletOwnersP getBrowseWalletOwnersP() {
        return browseUsersP;
    }
}