package controller.browserControllers;

import controller.miniControllers.PackMiniPC;
import controller.miniControllers.StoreProductMiniEditC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseStorePEdit;
import view.miniPanels.*;

import javax.swing.text.BadLocationException;

public class BrowseStoreEditC extends MixedBrowserController<Pack, StoreProduct> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseStoreEditC(App frame, Store model, BrowseStorePEdit view) throws BadLocationException {
        super(frame, view, model);
    }

    @Override
    public void initializeActionsForMiniPanels() {
        try {
            super.getView().setFirstItemList(super.getModel().getPacks());
            super.getView().setSecondItemList(super.getModel().getStoreProductList());
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        for (MiniPanel miniPanel : super.getView().getFirstMiniPanels()) {
            new PackMiniPC(super.getFrame(), super.getModel(), (PackMiniP) miniPanel, this, super.getView());
        }
        for (MiniPanel miniPanel : super.getView().getSecondMiniPanels()) {
            new StoreProductMiniEditC(super.getFrame(), super.getModel(), (StoreProductMiniP) miniPanel, this,
                    super.getView());
        }
    }
}