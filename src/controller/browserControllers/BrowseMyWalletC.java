package controller.browserControllers;

import controller.miniControllers.SecondHandMyWalletMiniC;
import model.product.SecondHandProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseMyWalletP;
import view.miniPanels.MiniPanel;
import view.miniPanels.SecondHandMiniP;

public class BrowseMyWalletC extends BrowserController<SecondHandProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public BrowseMyWalletC(App frame, Store model, BrowseMyWalletP view) {
        super(frame, view, model);
    }

    @Override
    public void setActionsForMiniPanels() {
        for (MiniPanel miniPanel : super.getView().getMiniPanels()) {
            miniPanel.setController(
                    new SecondHandMyWalletMiniC(super.getFrame(), super.getModel(), (SecondHandMiniP) miniPanel, this,
                            super.getView()));
        }
    }
}