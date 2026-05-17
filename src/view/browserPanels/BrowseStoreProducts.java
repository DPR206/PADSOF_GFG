package view.browserPanels;

import model.product.StoreProduct;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import static main.Main.brownColour;

/**
 * The type Browse store products.
 * @author Sofia C.L.
 * @version 1.0
 */
public class BrowseStoreProducts extends AbstractBrowserP<StoreProduct> {
    private final String buttonName;
    private final String iconPath;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Browse store products.
     * @param buttonName the button name
     * @param iconPath   the icon path
     * @throws BadLocationException the bad location exception
     */
    public BrowseStoreProducts(String buttonName, String... iconPath) throws BadLocationException {
        super();
        this.buttonName = buttonName;
        this.iconPath = iconPath[0];
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
        StoreProductMiniP miniPack = new StoreProductMiniP(item, index, buttonName, iconPath);
        super.addMiniPanel(miniPack);
        this.add(miniPack);
    }

}