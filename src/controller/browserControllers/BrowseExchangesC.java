package controller.browserControllers;

import controller.miniControllers.ExchangeMiniC;
import model.exchange.Exchange;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseExchangesP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.ExchangeMiniP;

import javax.swing.text.BadLocationException;

public class BrowseExchangesC extends AbstractBrowserC<Exchange> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Browse orders c.
     * @param frame the frame
     * @param view  the view
     * @param model the model
     */
    public BrowseExchangesC(App frame, BrowseExchangesP view, Store model) {
        super(frame, view, model);
        super.initializeActions();
    }

    @Override
    public void refreshData() {
        try {
            super.getView().setItemList(getModel().getExchanges());
            super.getView().setCurrentPageNum(1);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void refreshCurrentPage() {
        try {
            int currentPage = super.getView().getCurrentPageNum();
            super.getView().setItemList(getModel().getExchanges());
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
            new ExchangeMiniC(super.getFrame(), super.getModel(), (ExchangeMiniP) miniPanel, this,
                    (BrowseExchangesP) super.getView());
        }
    }
}