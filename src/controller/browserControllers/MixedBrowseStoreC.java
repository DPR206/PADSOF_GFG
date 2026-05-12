package controller.browserControllers;

import controller.miniControllers.PackAddToCartMiniC;
import controller.miniControllers.StoreProductMiniC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.*;
import view.App;
import view.browserPanels.MixedBrowseStoreAddToCartP;
import view.miniPanels.*;

import javax.swing.text.BadLocationException;

public class MixedBrowseStoreC extends AbstractMixedBrowserC<Pack, StoreProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public MixedBrowseStoreC(App frame, Store model, MixedBrowseStoreAddToCartP view) throws BadLocationException {
        super(frame, view, model);
    }

    @Override
    public void initializeActionsForMiniPanels() {
        try {
            super.getView().setFirstItemList(super.getModel().getPacks());
            if (super.getFrame().getUser().getType() == UserType.REGISTERED_CLIENT) {
                super.getView().setSecondItemList(((RegisteredClient) super.getFrame().getUser()).searchStoreProduct());
            } else if (super.getFrame().getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                super.getView()
                     .setSecondItemList(((UnregisteredClient) super.getFrame().getUser()).searchStoreProduct());
            } else {
                super.getView().setSecondItemList(super.getModel().getStoreProductList());
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        for (AbstractMiniP miniPanel : super.getView().getFirstMiniPanels()) {
            new PackAddToCartMiniC(super.getFrame(), super.getModel(), (PackToBuyMiniP) miniPanel, this, super.getView());
        }
        for (AbstractMiniP miniPanel : super.getView().getSecondMiniPanels()) {
            new StoreProductMiniC(super.getFrame(), super.getModel(), (StoreProductMiniP) miniPanel, this,
                    super.getView());
        }
    }
}