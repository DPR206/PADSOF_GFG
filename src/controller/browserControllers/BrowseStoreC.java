package controller.browserControllers;

import controller.miniControllers.PackMiniPC;
import controller.miniControllers.StoreProductMiniC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseStoreP;
import view.miniPanels.*;

import javax.swing.text.BadLocationException;

public class BrowseStoreC extends MixedBrowserController<Pack, StoreProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseStoreC(App frame, Store model, BrowseStoreP view) throws BadLocationException {
        super(frame, view, model);
        view.setFirstItemList(model.getPacks());
        view.setSecondItemList(model.getStoreProductList());
        super.initializeActions();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        System.out.println("Initialize actions for MiniPanels");
        for (MiniPanel miniPanel : super.getView().getFirstMiniPanels()) {
            new PackMiniPC(super.getFrame(), super.getModel(), (PackMiniP) miniPanel, this, super.getView());
        }
        for (MiniPanel miniPanel : super.getView().getSecondMiniPanels()) {
            new StoreProductMiniC(super.getFrame(), super.getModel(), (StoreProductMiniP) miniPanel, this,
                    super.getView());
        }
    }
}