package controller.browserControllers;

import controller.miniControllers.SecondHandValuateMiniC;
import model.product.SecondHandProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseSecondHandProductsP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.SecondHandMiniP;

import java.util.List;

/**
 * The type Browse valuation products c.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowseValuationProductsC extends AbstractBrowserC<SecondHandProduct> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseValuationProductsC(App frame, BrowseSecondHandProductsP view, Store model) {
        super(frame, view, model);
        super.initializeActions();
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        List<SecondHandProduct> shownProducts = Store.getInstance().getSecondHandProductList();
        shownProducts.removeIf(product -> !product.isPaidValuation());
        shownProducts.removeIf(SecondHandProduct::isAvailable);
        try {
            super.getView().setItemList(shownProducts);
        } catch (Exception e) {
            throw new RuntimeException();
        }

        for (AbstractMiniP miniPanel : super.getView().getMiniPanels()) {
            new SecondHandValuateMiniC(super.getFrame(), super.getModel(), (SecondHandMiniP) miniPanel, this,
                    super.getView());
        }
    }
}