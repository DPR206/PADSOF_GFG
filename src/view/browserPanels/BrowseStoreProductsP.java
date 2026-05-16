package view.browserPanels;

import model.product.StoreProduct;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import static main.Main.brownColour;

public class BrowseStoreProductsP extends AbstractBrowserP<StoreProduct> {
    private final String buttonName;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public BrowseStoreProductsP(String buttonName) throws BadLocationException {
        super();
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
        StoreProductMiniP miniProduct = new StoreProductMiniP(item, index, buttonName, ".\\resources\\app\\cart.png");
        super.addMiniPanel(miniProduct);
        this.add(miniProduct);
    }

}