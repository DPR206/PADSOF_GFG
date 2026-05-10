package controller.browserControllers;

import controller.miniControllers.SecondHandAddToOfferMiniC;
import model.product.SecondHandProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseSecondHandProductsP;
import view.miniPanels.MiniPanel;
import view.miniPanels.SecondHandMiniP;

public class BrowseSecondHandProductsC extends BrowserController<SecondHandProduct> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseSecondHandProductsC(App frame, BrowseSecondHandProductsP view, Store model) {
        super(frame, view, model);
    }

    @Override
    public void setActionsForMiniPanels() {
        for (MiniPanel miniPanel : super.getView().getMiniPanels()) {
            miniPanel.setController(
                    new SecondHandAddToOfferMiniC(super.getFrame(), super.getModel(), (SecondHandMiniP) miniPanel, this,
                            super.getView()));
        }
    }
}