package controller.browserControllers;

import controller.miniControllers.StoreProductEditMiniC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.MixedBrowseStoreEditP;
import view.miniPanels.*;

import javax.swing.text.BadLocationException;

public class MixedBrowseStoreEditC extends AbstractMixedBrowserC<Pack, StoreProduct> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public MixedBrowseStoreEditC(App frame, Store model, MixedBrowseStoreEditP view) throws BadLocationException {
        super(frame, view, model);
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        try {
            super.getView().setFirstItemList(super.getModel().getPacks());
            super.getView().setSecondItemList(super.getModel().getStoreProductList());
        } catch (BadLocationException ex) {
            ex.printStackTrace();
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