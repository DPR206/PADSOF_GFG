package view;

import model.product.StoreProduct;
import model.user.UnregisteredClient;
import view.browserPanels.BrowseSecondHandP;
import view.browserPanels.BrowseWalletOwnersP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class OfferMainP extends JPanel {
    private JPanel banner;
    private JButton users = new JButton("Browse users");
    private JButton products = new JButton("Browse all products");
    private BrowseWalletOwnersP browseWalletOwners;
    private BrowseSecondHandP browseSecondHand;
    private JPanel productSearch;
    private List<StoreProduct> p;
    private UnregisteredClient mainU;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public OfferMainP(UnregisteredClient mainU, App app) {
        super();
        this.mainU = mainU;
        this.banner = new JPanel();
        this.productSearch = new JPanel();
        this.productSearch.setLayout(new BorderLayout());

        this.browseSecondHand = new BrowseSecondHandP();
        this.browseSecondHand.setVisible(false);

        this.setLayout(new BorderLayout());

        this.banner.add(new JLabel("GIFTS FOR GEEKS"), BorderLayout.NORTH);
        this.add(banner, BorderLayout.NORTH);

        JPanel others = new JPanel(new BorderLayout());

        JPanel botones = new JPanel(new GridLayout(0, 2));
        botones.add(this.users);
        botones.add(this.products);
        others.add(botones, BorderLayout.NORTH);

        this.add(others, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new CardLayout());
        bottom.add(this.browseSecondHand, "SecondHand");
        bottom.add(this.browseWalletOwners, "Owners");
        others.add(bottom, BorderLayout.SOUTH);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    public void setController(ActionListener e) {
        this.users.addActionListener(e);
        this.products.addActionListener(e);
    }
}