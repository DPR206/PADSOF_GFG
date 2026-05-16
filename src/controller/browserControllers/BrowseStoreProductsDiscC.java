package controller.browserControllers;

import controller.miniControllers.StoreProductDiscMiniC;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseStoreProductsDiscP;
import view.managerPanels.ManagerDiscountsP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.StoreProductDiscMiniP;

import javax.swing.text.BadLocationException;
import java.util.ArrayList;
import java.util.List;

public class BrowseStoreProductsDiscC extends AbstractBrowserC<StoreProduct> {
    private final ManagerDiscountsP parentView;
    private final boolean onlyOnce;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseStoreProductsDiscC(App frame, BrowseStoreProductsDiscP view, Store model, ManagerDiscountsP parentView,
                                    boolean onlyOnce) {
        super(frame, view, model);
        this.parentView = parentView;
        this.onlyOnce = onlyOnce;
        super.initializeActions();
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        try {
            super.getView().setItemList(Store.getInstance().getStoreProductList());
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        for (AbstractMiniP miniPanel : super.getView().getMiniPanels()) {
            if (onlyOnce) {
                List<StoreProduct> giftList = new ArrayList<>();
                giftList.add(parentView.getGift());
                new StoreProductDiscMiniC(super.getFrame(), super.getModel(), (StoreProductDiscMiniP) miniPanel, this,
                        (BrowseStoreProductsDiscP) super.getView(), giftList, true);
            } else {
                new StoreProductDiscMiniC(super.getFrame(), super.getModel(), (StoreProductDiscMiniP) miniPanel, this,
                        (BrowseStoreProductsDiscP) super.getView(), parentView.getSelectedProductsList(), false);
            }
        }
    }
}