package controller.browserControllers;

import controller.miniControllers.StoreProductEditMiniC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.MixedBrowseStoreEditP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.StoreProductMiniP;

import javax.swing.text.BadLocationException;

/**
 * The type Mixed browse store edit c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class MixedBrowseStoreEditC extends AbstractMixedBrowserC<Pack, StoreProduct> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     * @param view  the controller's view
     */
    public MixedBrowseStoreEditC(App frame, Store model, MixedBrowseStoreEditP view) {
        super(frame, view, model);
        super.initializeActions();
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        try {
            super.getView().setFirstItemList(super.getModel().getPacks());
            super.getView().setSecondItemList(super.getModel().getStoreProductList());
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }

        for (AbstractMiniP miniPanel : super.getView().getFirstMiniPanels()) {
            //new PackMiniC(super.getFrame(), super.getModel(), (PackMiniP) miniPanel, this, super.getView());
        }
        for (AbstractMiniP miniPanel : super.getView().getSecondMiniPanels()) {
            new StoreProductEditMiniC(super.getFrame(), super.getModel(), (StoreProductMiniP) miniPanel, this,
                    super.getView());
        }
    }
}