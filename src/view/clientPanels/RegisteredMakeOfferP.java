package view.clientPanels;

import model.user.RegisteredClient;
import view.App;
import view.browserPanels.BrowseSomeonesWalletP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

/**
 * The type Registered make offer p.
 * @author Ana O.R.
 * @version 1.0
 */
public class RegisteredMakeOfferP extends JPanel {
    private final JButton makeOfferButton = new JButton("Make Offer");
    private final BrowseSomeonesWalletP browseTheirWallet;
    private final BrowseSomeonesWalletP browseMyWallet;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Registered make offer p.
     */
    public RegisteredMakeOfferP(App frame, RegisteredClient them, RegisteredClient me) throws BadLocationException {
        this.setLayout(new BorderLayout());

        browseTheirWallet = new BrowseSomeonesWalletP(frame, them);
        browseMyWallet = new BrowseSomeonesWalletP(frame, me);

        paintEverything();
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

    public BrowseSomeonesWalletP getBrowseMyWallet() {
        return browseMyWallet;
    }

    public BrowseSomeonesWalletP getBrowseTheirWallet() {
        return browseTheirWallet;
    }

    public JButton getMakeOfferButton() {
        return makeOfferButton;
    }
}