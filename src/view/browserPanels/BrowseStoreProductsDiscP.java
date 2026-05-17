package view.browserPanels;

import model.product.StoreProduct;
import view.miniPanels.StoreProductDiscMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.util.List;

import static main.Main.brownColour;

/**
 * The type Browse store products disc p.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowseStoreProductsDiscP extends AbstractBrowserP<StoreProduct> {
    private final List<StoreProduct> alreadyChosen;
    private final String buttonName;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     * @param alreadyChosen the already chosen
     * @param buttonName    the button name
     * @throws BadLocationException the bad location exception
     */
    public BrowseStoreProductsDiscP(List<StoreProduct> alreadyChosen, String buttonName) throws BadLocationException {
        super();
        this.alreadyChosen = alreadyChosen;
        this.buttonName = buttonName;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        paintEverything();
    }

    @Override
    public void paintEverything() throws BadLocationException {
        this.removeAll();

        super.addAllMiniPanels();
        this.add(super.getPageTurner());
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();
    }

    @Override
    public void addMiniPanel(StoreProduct item, int index) throws BadLocationException {
        StoreProductDiscMiniP miniProduct = new StoreProductDiscMiniP(item, index, buttonName, alreadyChosen);
        super.addMiniPanel(miniProduct);
        this.add(miniProduct);
    }

}