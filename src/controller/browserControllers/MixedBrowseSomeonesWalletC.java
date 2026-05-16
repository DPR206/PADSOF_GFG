package controller.browserControllers;

import controller.miniControllers.SecondHandAddToOfferMiniC;
import model.product.SecondHandProduct;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.BrowseSomeonesWalletP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.SecondHandMiniP;

import javax.swing.text.BadLocationException;

/**
 * The type Mixed browse someone's wallet c.
 * @author Ana O.R.
 * @version 1.0
 */
public class MixedBrowseSomeonesWalletC extends AbstractBrowserC<SecondHandProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     * @param view  the controller's view
     * @throws BadLocationException the bad location exception
     */
    public MixedBrowseSomeonesWalletC(App frame, Store model, BrowseSomeonesWalletP view) throws BadLocationException {
        super(frame, view, model);
        super.initializeActions();
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

        for (AbstractMiniP miniPanel : super.getView().getMiniPanels()) {
            new SecondHandAddToOfferMiniC(super.getFrame(), (SecondHandMiniP) miniPanel, this, super.getView());
        }
    }
}