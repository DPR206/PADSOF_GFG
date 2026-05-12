package controller.browserControllers;

import controller.miniControllers.PackMiniCartC;
import controller.miniControllers.StoreProductMiniCartC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.*;
import view.App;
import view.browserPanels.BrowseCartP;
import view.miniPanels.*;

import javax.swing.text.BadLocationException;
import java.util.ArrayList;

public class BrowseCartC extends MixedBrowserController<Pack, StoreProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseCartC(App frame, Store model, BrowseCartP view) throws BadLocationException {
        super(frame, view, model);
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        if (super.getFrame().getUser().getType() == UserType.REGISTERED_CLIENT) {
            ((BrowseCartP) super.getView()).setCart(((RegisteredClient) super.getFrame().getUser()).getC());
        } else if (super.getFrame().getUser().getType() == UserType.UNREGISTERED_CLIENT) {
            ((BrowseCartP) super.getView()).setCart(((UnregisteredClient) super.getFrame().getUser()).getCart());
        }

        try {
            if (super.getFrame().getUser().getType() == UserType.REGISTERED_CLIENT) {
                super.getView().setFirstItemList(((RegisteredClient) super.getFrame().getUser()).getC().getPacks());
                super.getView().setSecondItemList(((RegisteredClient) super.getFrame().getUser()).getC().getProducts());
            } else if (super.getFrame().getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                super.getView()
                     .setFirstItemList(((UnregisteredClient) super.getFrame().getUser()).getCart().getPacks());
                super.getView()
                     .setSecondItemList(((UnregisteredClient) super.getFrame().getUser()).getCart().getProducts());
            } else {
                super.getView().setFirstItemList(new ArrayList<>());
                super.getView().setSecondItemList(new ArrayList<>());
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        for (MiniPanel miniPanel : super.getView().getFirstMiniPanels()) {
            new PackMiniCartC(super.getFrame(), super.getModel(), (PackMiniCartP) miniPanel, this, super.getView());
        }
        for (MiniPanel miniPanel : super.getView().getSecondMiniPanels()) {
            new StoreProductMiniCartC(super.getFrame(), super.getModel(), (StoreProductMiniCart) miniPanel, this,
                    super.getView());
        }
    }
}