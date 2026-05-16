package controller.browserControllers;

import controller.miniControllers.SecondHandValuateMiniC;
import model.product.SecondHandProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseSecondHandProductsP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.SecondHandMiniP;

import javax.swing.text.BadLocationException;
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
    }

    @Override
    public void refreshData() {
        List<SecondHandProduct> shownProducts = Store.getInstance().getSecondHandProductList();
        shownProducts.removeIf(product -> !product.isPaidValuation());
        shownProducts.removeIf(SecondHandProduct::isAvailable);
        try {
            super.getView().setItemList(shownProducts);
            super.getView().setCurrentPageNum(1);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void refreshCurrentPage() {
        try {
            int currentPage = super.getView().getCurrentPageNum();
            List<SecondHandProduct> shownProducts = Store.getInstance().getSecondHandProductList();
            shownProducts.removeIf(product -> !product.isPaidValuation());
            shownProducts.removeIf(SecondHandProduct::isAvailable);
            super.getView().setItemList(shownProducts);
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
            new SecondHandValuateMiniC(super.getFrame(), super.getModel(), (SecondHandMiniP) miniPanel);
        }
    }
}