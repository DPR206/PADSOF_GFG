package view;

import model.user.RegisteredClient;
import model.user.UnregisteredClient;
import view.banners.BannerUnregistered;
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

        browseSecondHandProductsP = new BrowseSecondHandProductsP(app);
        browseUsersP = new BrowseWalletOwnersP();

        this.setLayout(new BorderLayout());

        add(new BannerUnregistered(), BorderLayout.NORTH);

        JPanel others = new JPanel(new BorderLayout());

        JPanel botones = new JPanel(new GridLayout(0, 2));
        botones.add(this.browseAvailableProducts);
        botones.add(this.browseUsers);
        others.add(botones, BorderLayout.NORTH);

        this.add(others, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new CardLayout());
        bottom.add(this.browseSecondHandProductsP, "Search");
        bottom.add(this.browseUsersP, "Search");
        others.add(bottom, BorderLayout.SOUTH);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    public void setController(ActionListener e) {
        this.browseAvailableProducts.addActionListener(e);
        this.browseUsers.addActionListener(e);
    }
}