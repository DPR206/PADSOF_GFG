package view.clientPanels;

import view.browserPanels.BrowseSomeonesWalletP;

import javax.swing.*;
import java.awt.*;

/**
 * The type Registered make offer p.
 * @author Ana O.R.
 * @version 1.0
 */
public class RegisteredMakeOfferP extends JPanel {
    private final JButton makeOfferButton = new JButton("Make Offer");
    private BrowseSomeonesWalletP browseTheirWallet = null;
    private BrowseSomeonesWalletP browseMyWallet = null;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Registered make offer p.
     */
    public RegisteredMakeOfferP() {
        this.setLayout(new BorderLayout());
    }

    public void paintEverything() {
        this.removeAll();

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));

        centerPanel.add(browseTheirWallet);
        centerPanel.add(browseMyWallet);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(makeOfferButton, BorderLayout.SOUTH);

        this.revalidate();
        this.repaint();
    }

    public JButton getMakeOfferButton() {
        return makeOfferButton;
    }

    public void setBrowseMyWallet(BrowseSomeonesWalletP newBrowseMyWallet) {
        this.browseMyWallet = newBrowseMyWallet;
    }

    public void setBrowseTheirWallet(BrowseSomeonesWalletP newBrowseTheirWallet) {
        this.browseTheirWallet = newBrowseTheirWallet;
    }
}