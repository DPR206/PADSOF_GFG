package view.browserPanels;

import model.product.SecondHandProduct;
import view.miniPanels.ThreeButtonSecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;

import static main.Main.brownColour;

/**
 * The type Browse my wallet p.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowseMyWalletEditP extends AbstractBrowserP<SecondHandProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     * @throws BadLocationException the bad location exception
     */
    public BrowseMyWalletEditP() throws BadLocationException {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        paintEverything();
    }

    @Override
    public void paintEverything() throws BadLocationException {
        this.removeAll();

        JLabel title = new JLabel("My wallet'");
        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 20));
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
                new ThreeButtonSecondHandMiniP(item, index, "Add to Offer", ".\\resources\\app\\add.png", "Request valuation", null,
                        "Remove from wallet", null);
        super.addMiniPanel(miniProduct);
        this.add(miniProduct);
    }
}