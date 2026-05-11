package controller.browserControllers;

import controller.miniControllers.WalletOwnerMiniC;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.BrowseWalletOwnersP;
import view.miniPanels.MiniPanel;
import view.miniPanels.UserMiniP;

import javax.swing.text.BadLocationException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Browse wallet owners c.
 */
public class BrowseWalletOwnersC extends BrowserController<RegisteredClient> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseWalletOwnersC(App frame, BrowseWalletOwnersP view, Store model) throws BadLocationException {
        super(frame, view, model);
    }

    @Override
    public void initializeActionsForMiniPanels() {
        List<RegisteredClient> users = new ArrayList<>(super.getModel().getRegisteredClientList());
        users.remove(super.getFrame().getUser());
        users.removeIf(user -> user.getWallet().getVisibleProducts().isEmpty());
        try {
            super.getView().setItemList(users);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        for (MiniPanel miniPanel : super.getView().getMiniPanels()) {
            new WalletOwnerMiniC(super.getFrame(), super.getModel(), (UserMiniP) miniPanel);
        }
    }
}