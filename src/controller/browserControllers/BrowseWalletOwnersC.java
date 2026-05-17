package controller.browserControllers;

import controller.miniControllers.WalletOwnerMiniC;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.BrowseWalletOwnersP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.UserMiniP;

import javax.swing.text.BadLocationException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Browse wallet owners c.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowseWalletOwnersC extends AbstractClusterBrowserC<RegisteredClient> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseWalletOwnersC(App frame, BrowseWalletOwnersP view, Store model) {
        super(frame, view, model);
        super.initializeActions();
    }

    @Override
    public void refreshData() {
        List<RegisteredClient> users = new ArrayList<>(super.getModel().getRegisteredClientList());
        users.remove(super.getFrame().getUser());
        users.removeIf(user -> user.getWallet().getVisibleProducts().isEmpty());
        try {
            super.getView().setItemList(users);
            super.getView().setCurrentPageNum(1);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void refreshCurrentPage() {
        try {
            int currentPage = super.getView().getCurrentPageNum();
            List<RegisteredClient> users = new ArrayList<>(super.getModel().getRegisteredClientList());
            users.remove(super.getFrame().getUser());
            users.removeIf(user -> user.getWallet().getVisibleProducts().isEmpty());
            super.getView().setItemList(users);
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
            new WalletOwnerMiniC(super.getFrame(), super.getModel(), (UserMiniP) miniPanel);
        }
    }
}