package view.browserPanels;

import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.miniPanels.PackMiniP;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

import static main.Main.brownColour;

public class MixedBrowseStoreEditP extends AbstractMixedBrowserP<Pack, StoreProduct> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public MixedBrowseStoreEditP() throws BadLocationException {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setFirstItemList(Store.getInstance().getPacks());
        super.setSecondItemList(Store.getInstance().getStoreProductList());
        paintEverything();
    }

    @Override
    public void paintEverything() throws BadLocationException {
        this.clearItemsContainer();
        this.removeAll();

        super.addAllMiniPanels();

        this.add(containerItems, BorderLayout.NORTH);

        JPanel northWrapper = new JPanel(new BorderLayout());
        northWrapper.setOpaque(false);
        northWrapper.add(containerItems, BorderLayout.NORTH);

        this.add(northWrapper, BorderLayout.NORTH);

        JPanel filler = new JPanel();
        filler.setOpaque(false);
        this.add(filler, BorderLayout.CENTER);

        this.add(super.getPageTurner(), BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();
    }

    @Override
    public void addFirstMiniPanel(Pack item, int index) throws BadLocationException {
        PackMiniP miniPack = new PackMiniP(item, index, "Manage", null);;
        super.addFirstMiniPanel(miniPack);
        this.add(miniPack);
    }

    @Override
    public void addSecondMiniPanel(StoreProduct item, int index) throws BadLocationException {
        StoreProductMiniP miniPack = new StoreProductMiniP(item, index, "Manage", ".\\resources\\app\\cart.png");
        super.addSecondMiniPanel(miniPack);
        this.add(miniPack);
    }
}