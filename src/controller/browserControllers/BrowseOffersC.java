package controller.browserControllers;

import controller.miniControllers.OfferMiniC;
import model.exchange.Offer;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.BrowseOffersP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.OfferMiniP;

import javax.swing.text.BadLocationException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Browse offers c.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowseOffersC extends AbstractBrowserC<Offer> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseOffersC(App frame, BrowseOffersP view, Store model) {
        super(frame, view, model);
        super.initializeActions();
    }

    @Override
    public void refreshData() {
        try {
            List<Offer> offers =
                    new ArrayList<>(((RegisteredClient) super.getFrame().getUser()).getOfferHistory().getOffers());
            super.getView().setItemList(offers);
            super.getView().setCurrentPageNum(1);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void refreshCurrentPage() {
        try {
            int currentPage = super.getView().getCurrentPageNum();
            super.getView().setItemList(super.getView().getItemList());
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
            new OfferMiniC(super.getFrame(), super.getModel(), (OfferMiniP) miniPanel, this,
                    (BrowseOffersP) super.getView());
        }
    }
}