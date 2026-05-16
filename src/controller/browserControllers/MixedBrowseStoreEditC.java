package controller.browserControllers;

import controller.miniControllers.StoreProductEditMiniC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.MixedBrowseStoreEditP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.StoreProductMiniP;

import javax.swing.text.BadLocationException;

/**
 * The type Mixed browse store edit c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class MixedBrowseStoreEditC extends AbstractMixedBrowserC<Pack, StoreProduct> {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     * @param view  the controller's view
     */
    public MixedBrowseStoreEditC(App frame, Store model, MixedBrowseStoreEditP view) {
        super(frame, view, model);
        super.initializeActions();
    }

    @Override
    public void refreshData() {
        try {
            super.getView().setFirstItemList(super.getModel().getPacks());
            super.getView().setSecondItemList(super.getModel().getStoreProductList());
            super.getView().setCurrentPageNum(1);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void refreshCurrentPage() {
        try {
            int currentPage = super.getView().getCurrentPageNum();
            super.getView().setFirstItemList(super.getModel().getPacks());
            super.getView().setSecondItemList(super.getModel().getStoreProductList());
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
        for (AbstractMiniP miniPanel : super.getView().getFirstMiniPanels()) {
            //new PackMiniC(super.getFrame(), super.getModel(), (PackMiniP) miniPanel, this, super.getView());
        }
        for (AbstractMiniP miniPanel : super.getView().getSecondMiniPanels()) {
            new StoreProductEditMiniC(super.getFrame(), (StoreProductMiniP) miniPanel, this, super.getView());
        }
    }
}