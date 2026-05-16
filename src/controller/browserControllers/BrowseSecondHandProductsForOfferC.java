package controller.browserControllers;

import controller.miniControllers.SecondHandAddToOfferMiniC;
import model.product.SecondHandProduct;
import model.store.Store;
import model.user.RegisteredClient;
import model.user.UserType;
import view.App;
import view.browserPanels.BrowseSecondHandProductsP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.SecondHandMiniP;

import java.util.List;

/**
 * The type Browse second hand products for offer c.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowseSecondHandProductsForOfferC extends AbstractBrowserC<SecondHandProduct> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseSecondHandProductsForOfferC(App frame, BrowseSecondHandProductsP view, Store model) {
        super(frame, view, model);
        super.initializeActions();
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        List<SecondHandProduct> shownProducts = Store.getInstance().getAvailableSecondHandProductList();
        if (super.getFrame().getUser().getType() == UserType.REGISTERED_CLIENT) {
            shownProducts.removeAll(((RegisteredClient) super.getFrame().getUser()).getWallet().getAvailableProducts());
        }
        try {
            super.getView().setItemList(shownProducts);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        for (AbstractMiniP miniPanel : super.getView().getMiniPanels()) {
            new SecondHandAddToOfferMiniC(super.getFrame(), super.getModel(), (SecondHandMiniP) miniPanel, this,
                    super.getView());
        }
    }
}