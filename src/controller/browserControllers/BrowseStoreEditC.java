package controller.browserControllers;

import controller.miniControllers.PackMiniEditC;
import controller.miniControllers.PackMiniPC;
import controller.miniControllers.StoreProductMiniC;
import controller.miniControllers.StoreProductMiniEditC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseStoreP;
import view.browserPanels.BrowseStorePEdit;
import view.miniPanels.*;

public class BrowseStoreEditC extends MixedBrowserController<Pack, StoreProduct> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseStoreEditC(App frame, Store model, BrowseStorePEdit view) {
        super(frame, view, model);
    }

    @Override
    public void setActionsForMiniPanels() {
        for (MiniPanel miniPanel : super.getView().getFirstMiniPanels()) {
            PackMiniEdit view = (PackMiniEdit) miniPanel;
            PackMiniEditC controller = new PackMiniEditC(super.getFrame(), super.getModel(), view, this, super.getView());
            view.setController(controller); // IMPORTANTE: Vincular el listener al botón
        }
        for (MiniPanel miniPanel : super.getView().getSecondMiniPanels()) {
            StoreProductMiniEdit view = (StoreProductMiniEdit) miniPanel;
            StoreProductMiniEditC controller = new StoreProductMiniEditC(super.getFrame(), super.getModel(), view, this, super.getView());
            view.setController(controller); // IMPORTANTE: Vincular el listener al botón
        }
    }
}