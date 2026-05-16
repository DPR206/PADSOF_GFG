package controller.browserControllers;

import controller.miniControllers.MixedStoreProductMiniC;
import controller.miniControllers.PackAddToCartMiniC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.*;
import view.App;
import view.browserPanels.MixedBrowseStoreAddToCartP;
import view.miniPanels.*;

import javax.swing.text.BadLocationException;

/**
 * The type Mixed browse store c.
 * @author Ana O.R.
 * @version 1.0
 */
public class MixedBrowseStoreC extends AbstractMixedBrowserC<Pack, StoreProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     * @param view  the controller's view
     * @throws BadLocationException the bad location exception
     */
    public MixedBrowseStoreC(App frame, Store model, MixedBrowseStoreAddToCartP view) throws BadLocationException {
        super(frame, view, model);
        super.initializeActions();
        initializeActionsForMiniPanels();
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
            throw new RuntimeException(ex);
        }

        for (AbstractMiniP miniPanel : super.getView().getFirstMiniPanels()) {
            new PackAddToCartMiniC(super.getFrame(), super.getModel(), (PackToBuyMiniP) miniPanel, this,
                    super.getView());
        }
        for (AbstractMiniP miniPanel : super.getView().getSecondMiniPanels()) {
            new MixedStoreProductMiniC(super.getFrame(), super.getModel(), (StoreProductMiniP) miniPanel, this,
                    super.getView());
        }
    }
}