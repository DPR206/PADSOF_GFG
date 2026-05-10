package controller.browserControllers;

import controller.miniControllers.PackMiniEditC;
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
        view.setFirstItemList(model.getPacks());
        view.setSecondItemList(model.getStoreProductList());
        super.initializeActions();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        for (MiniPanel miniPanel : super.getView().getFirstMiniPanels()) {
            new PackMiniEditC(super.getFrame(), super.getModel(), (PackMiniEdit) miniPanel, this, super.getView());
        }
        for (MiniPanel miniPanel : super.getView().getSecondMiniPanels()) {
            new StoreProductMiniEditC(super.getFrame(), super.getModel(), (StoreProductMiniEdit) miniPanel, this,
                    super.getView());
        }
    }
}