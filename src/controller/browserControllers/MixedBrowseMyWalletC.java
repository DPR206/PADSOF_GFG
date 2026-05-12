package controller.browserControllers;

import controller.miniControllers.SecondHandMyWalletMiniC;
import model.product.SecondHandProduct;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.BrowseMyWalletP;
import view.miniPanels.MiniPanel;
import view.miniPanels.ThreeButtonSecondHandMiniP;

import javax.swing.text.BadLocationException;

public class MixedBrowseMyWalletC extends AbstractBrowserC<SecondHandProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public MixedBrowseMyWalletC(App frame, Store model, BrowseMyWalletP view) {
        super(frame, view, model);
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        try {
            super.getView().setItemList(((RegisteredClient) super.getFrame().getUser()).getWallet().getProducts());
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }

        for (MiniPanel miniPanel : super.getView().getMiniPanels()) {
            new SecondHandMyWalletMiniC(super.getFrame(), super.getModel(), (ThreeButtonSecondHandMiniP) miniPanel,
                    this, super.getView());
        }
    }
}