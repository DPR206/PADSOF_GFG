package controller.browserControllers;

import controller.miniControllers.SecondHandAddToOfferMiniC;
import model.product.SecondHandProduct;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.BrowseSomeonesWalletP;
import view.miniPanels.MiniPanel;
import view.miniPanels.SecondHandMiniP;

import javax.swing.text.BadLocationException;
import java.util.List;

public class BrowseSomeonesWalletC extends BrowserController<SecondHandProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseSomeonesWalletC(App frame, Store model, BrowseSomeonesWalletP view) throws BadLocationException {
        super(frame, view, model);
        view.setItemList(List.of(((RegisteredClient) view.getOwner()).getWallet().getProducts()));
        super.initializeActions();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        for (MiniPanel miniPanel : super.getView().getMiniPanels()) {
            new SecondHandAddToOfferMiniC(super.getFrame(), super.getModel(), (SecondHandMiniP) miniPanel, this,
                    super.getView());
        }
    }
}