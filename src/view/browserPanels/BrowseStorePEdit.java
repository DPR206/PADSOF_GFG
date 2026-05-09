package view.browserPanels;

import static main.Main.brownColour;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.text.BadLocationException;

import model.product.Pack;
import model.product.StoreProduct;
import model.store.Parameter;
import model.store.Store;
import view.App;
import view.miniPanels.PackMiniEdit;
import view.miniPanels.PackMiniP;
import view.miniPanels.StoreProductMiniEdit;
import view.miniPanels.StoreProductMiniP;

public class BrowseStorePEdit extends MixedBrowserPanel<Pack, StoreProduct>{
	/**
     * This panel's constructor
     */
    public BrowseStorePEdit(App app) throws BadLocationException {
        super(app);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        paintEverything();
    }

    /**
     * It allows this page's components to be repainted (revalidate() & repaint() didn't work)
     * @throws BadLocationException bad locations within a document model (that is, attempts to reference a location
     *                              that doesn't exist)
     */
    public void paintEverything() throws BadLocationException {
        this.removeAll();

        super.setFirstItemList(Store.getInstance().getPacks()); // DUE: Esto debe darlo el controlador
        super.setSecondItemList(Store.getInstance().getStoreProductList()); // DUE: Esto debe darlo el controlador

        super.addAllMiniPanels();
        this.add(super.getPageTurner());
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();
    }


    public void addFirstMiniPanel(Pack item, int index) throws BadLocationException {
        PackMiniEdit miniPack = new PackMiniEdit(item, index);
        super.addFirstMiniPanel(miniPack);
        this.add(miniPack);
    }

    @Override
    public void addSecondMiniPanel(StoreProduct item, int index) throws BadLocationException {
        StoreProductMiniEdit miniPack = new StoreProductMiniEdit(item, index);
        super.addSecondMiniPanel(miniPack);
        this.add(miniPack);
    }
}
