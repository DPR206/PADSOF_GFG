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

public class MixedBrowseSomeonesWalletC extends AbstractBrowserC<SecondHandProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public MixedBrowseSomeonesWalletC(App frame, Store model, BrowseSomeonesWalletP view) throws BadLocationException {
        super(frame, view, model);
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        BrowseSomeonesWalletP view = (BrowseSomeonesWalletP) super.getView();
        try {
            view.setItemList(((RegisteredClient) view.getOwner()).getWallet().getAvailableProducts());
        } catch (Exception e) {
            throw new RuntimeException();
        }

        for (MiniPanel miniPanel : super.getView().getMiniPanels()) {
            new SecondHandAddToOfferMiniC(super.getFrame(), super.getModel(), (SecondHandMiniP) miniPanel, this,
                    super.getView());
        }
    }
}