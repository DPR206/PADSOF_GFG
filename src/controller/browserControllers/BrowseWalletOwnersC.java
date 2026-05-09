package controller.browserControllers;

import controller.miniControllers.WalletOwnerMiniC;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.BrowseWalletOwnersP;
import view.miniPanels.MiniPanel;
import view.miniPanels.UserMiniP;

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
    public BrowseWalletOwnersC(App frame, BrowseWalletOwnersP view, Store model) {
        super(frame, view, model);
    }

    @Override
    public void updateControllers() {
        for (MiniPanel miniPanel : super.getView().getMiniPanels()) {
            miniPanel.setController(new WalletOwnerMiniC(super.getFrame(), super.getModel(), (UserMiniP) miniPanel));
        }
    }
}