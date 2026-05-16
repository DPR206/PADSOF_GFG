package controller.browserControllers;

import controller.miniControllers.SecondHandMyWalletMiniC;
import model.product.SecondHandProduct;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.BrowseMyWalletP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.ThreeButtonSecondHandMiniP;

import javax.swing.text.BadLocationException;

/**
 * The type Mixed browse my wallet c.
 * @author Ana O.R.
 * @version 1.0
 */
public class MixedBrowseMyWalletC extends AbstractBrowserC<SecondHandProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     * @param view  the view
     */
    public MixedBrowseMyWalletC(App frame, Store model, BrowseMyWalletP view) {
        super(frame, view, model);
        super.initializeActions();
    }

    @Override
    public void refreshData() {
        try {
            super.getView().setItemList(((RegisteredClient) super.getFrame().getUser()).getWallet().getProducts());
            super.getView().setCurrentPageNum(1);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void refreshCurrentPage() {
        try {
            int currentPage = super.getView().getCurrentPageNum();
            super.getView().setItemList(((RegisteredClient) super.getFrame().getUser()).getWallet().getProducts());
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
            new SecondHandMyWalletMiniC(super.getFrame(), (ThreeButtonSecondHandMiniP) miniPanel, this,
                    super.getView());
        }
    }
}