package view.browserPanels;

import model.product.StoreProduct;
import view.miniPanels.StoreProductDiscMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.util.List;

import static main.Main.brownColour;

public class BrowseStoreProductsDiscP extends AbstractBrowserP<StoreProduct> {
    private final List<StoreProduct> alreadyChosen;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public BrowseStoreProductsDiscP(List<StoreProduct> alreadyChosen) throws BadLocationException {
        super();
        this.alreadyChosen = alreadyChosen;
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
        StoreProductDiscMiniP miniProduct = new StoreProductDiscMiniP(item, index, "Choose this Gift", alreadyChosen);
        super.addMiniPanel(miniProduct);
        this.add(miniProduct);
    }

}