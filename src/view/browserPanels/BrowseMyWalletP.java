package view.browserPanels;

import model.product.SecondHandProduct;
import model.user.RegisteredClient;
import view.miniPanels.ThreeButtonSecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import static main.Main.brownColour;

public class BrowseMyWalletP extends AbstractBrowserP<SecondHandProduct> {
    private final RegisteredClient owner;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public BrowseMyWalletP(RegisteredClient owner) throws BadLocationException {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.owner = owner;
        paintEverything();
    }

    @Override
    public void paintEverything() throws BadLocationException {
        this.removeAll();

        JLabel title = new JLabel("My wallet'");
        title.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, brownColour));
        this.add(title);

        super.addAllMiniPanels();
        this.add(super.getPageTurner());
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();
    }

    @Override
    public void addMiniPanel(SecondHandProduct item, int index) throws BadLocationException {
        ThreeButtonSecondHandMiniP miniProduct =
                new ThreeButtonSecondHandMiniP(item, index, "Add to Offer", null, "Request valuation", null,
                        "Remove from wallet", null);
        super.addMiniPanel(miniProduct);
        this.add(miniProduct);
    }
}