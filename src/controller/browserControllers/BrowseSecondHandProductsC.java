package controller.browserControllers;

import controller.miniControllers.SecondHandAddToOfferMiniC;
import model.product.SecondHandProduct;
import model.store.Store;
import model.user.RegisteredClient;
import model.user.UserType;
import view.App;
import view.browserPanels.BrowseSecondHandProductsP;
import view.miniPanels.MiniPanel;
import view.miniPanels.SecondHandMiniP;

import javax.swing.text.BadLocationException;
import java.util.List;

public class BrowseSecondHandProductsC extends BrowserController<SecondHandProduct> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseSecondHandProductsC(App frame, BrowseSecondHandProductsP view, Store model)
            throws BadLocationException {
        super(frame, view, model);

        super.initializeActions();
        List<SecondHandProduct> shownProducts = Store.getInstance().getSecondHandProductList();
        if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
            SecondHandProduct[] notShownProducts = ((RegisteredClient) frame.getUser()).getWallet().getProducts();
            for (SecondHandProduct product : notShownProducts) {
                shownProducts.remove(product);
            }
        }
        view.setItemList(shownProducts);
        super.initializeActions();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        for (MiniPanel miniPanel : super.getView().getMiniPanels()) {
            new SecondHandAddToOfferMiniC(super.getFrame(), super.getModel(), (SecondHandMiniP) miniPanel, this,
                    super.getView());
        }
    }
}