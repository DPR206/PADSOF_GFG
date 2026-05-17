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
public class BrowseSomeonesWalletC extends AbstractBrowserC<SecondHandProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     * @param view  the controller's view
     * @throws BadLocationException the bad location exception
     */
    public BrowseSomeonesWalletC(App frame, Store model, BrowseSomeonesWalletP view) throws BadLocationException {
        super(frame, view, model);
        super.initializeActions();
    }

    @Override
    public void refreshData() {
        BrowseSomeonesWalletP view = (BrowseSomeonesWalletP) super.getView();
        try {
            view.setItemList(((RegisteredClient) view.getOwner()).getWallet().getAvailableProducts());
            super.getView().setCurrentPageNum(1);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void refreshCurrentPage() {
        try {
            int currentPage = super.getView().getCurrentPageNum();
            BrowseSomeonesWalletP view = (BrowseSomeonesWalletP) super.getView();
            view.setItemList(((RegisteredClient) view.getOwner()).getWallet().getAvailableProducts());
            int maxPage = super.getView().getMaxPageNum();
            if (currentPage > maxPage) {
                currentPage = maxPage;
            }
            super.getView().setCurrentPageNum(currentPage);

        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void initializeActionsForMiniPanels() {
        for (AbstractMiniP miniPanel : super.getView().getMiniPanels()) {
            new SecondHandAddToOfferMiniC(super.getFrame(), super.getModel(), (SecondHandMiniP) miniPanel, this);
        }
    }
}