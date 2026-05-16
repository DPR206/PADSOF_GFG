package controller.browserControllers;

import controller.miniControllers.PackCartMiniC;
import controller.miniControllers.StoreProductCartMiniC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.*;
import view.App;
import view.browserPanels.MixedBrowseCartP;
import view.miniPanels.*;

import javax.swing.text.BadLocationException;
import java.util.ArrayList;

/**
 * The type Mixed browse cart c.
 * @author Ana O.R.
 * @version 1.0
 */
public class MixedBrowseCartC extends AbstractMixedBrowserC<Pack, StoreProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     * @param view  the controller's view
     * @throws BadLocationException the bad location exception
     */
    public MixedBrowseCartC(App frame, Store model, MixedBrowseCartP view) throws BadLocationException {
        super(frame, view, model);
        super.initializeActions();
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        if (super.getFrame().getUser().getType() == UserType.REGISTERED_CLIENT) {
            ((MixedBrowseCartP) super.getView()).setCart(((RegisteredClient) super.getFrame().getUser()).getC());
        } else if (super.getFrame().getUser().getType() == UserType.UNREGISTERED_CLIENT) {
            ((MixedBrowseCartP) super.getView()).setCart(((UnregisteredClient) super.getFrame().getUser()).getCart());
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
            throw new RuntimeException(ex);
        }

        for (AbstractMiniP miniPanel : super.getView().getFirstMiniPanels()) {
            new PackCartMiniC(super.getFrame(), super.getModel(), (PackCartMiniP) miniPanel);
        }
        for (AbstractMiniP miniPanel : super.getView().getSecondMiniPanels()) {
            new StoreProductCartMiniC(super.getFrame(), super.getModel(), (StoreProductMiniCart) miniPanel);
        }
    }
}