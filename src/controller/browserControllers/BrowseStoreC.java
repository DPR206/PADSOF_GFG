package controller.browserControllers;

import controller.miniControllers.PackMiniPC;
import controller.miniControllers.StoreProductMiniC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.*;
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