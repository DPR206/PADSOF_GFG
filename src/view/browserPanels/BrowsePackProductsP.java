package view.browserPanels;

import model.product.Pack;
import model.product.StoreProduct;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import static main.Main.brownColour;

/**
 * The type Browse pack products p.
 * @author Sofia C.L.
 * @version 1.0
 */
public class BrowsePackProductsP extends AbstractBrowserP<StoreProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Browse pack products p.
     * @param p the p
     */
    public BrowsePackProductsP(Pack p) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        try {
            paintEverything();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

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
        StoreProductMiniP miniProduct = new StoreProductMiniP(item, index, "DELETE", null);
        super.addMiniPanel(miniProduct);
        this.add(miniProduct);
    }

}