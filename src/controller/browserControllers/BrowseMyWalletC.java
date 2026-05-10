package controller.browserControllers;

import controller.miniControllers.SecondHandMyWalletMiniC;
import model.product.SecondHandProduct;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.BrowseMyWalletP;
import view.miniPanels.MiniPanel;
import view.miniPanels.SecondHandMiniP;

import javax.swing.text.BadLocationException;
import java.util.Arrays;

public class BrowseMyWalletC extends BrowserController<SecondHandProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public BrowseMyWalletC(App frame, Store model, BrowseMyWalletP view) throws BadLocationException {
        super(frame, view, model);

        view.setItemList(Arrays.asList(((RegisteredClient) frame.getUser()).getWallet().getProducts()));
        super.initializeActions();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        for (MiniPanel miniPanel : super.getView().getMiniPanels()) {
            new SecondHandMyWalletMiniC(super.getFrame(), super.getModel(), (SecondHandMiniP) miniPanel, this,
                    super.getView());
        }
    }
}