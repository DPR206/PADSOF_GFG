package controller.browserControllers;

import controller.miniControllers.StoreProductMiniC;
import model.product.StoreProduct;
import model.store.*;
import model.user.RegisteredClient;
import model.user.UserType;
import view.App;
import view.browserPanels.BrowseStoreProductsP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.StoreProductMiniP;

import javax.swing.text.BadLocationException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Browse store products c.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowseRecomStoreProductsC extends AbstractBrowserC<StoreProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseRecomStoreProductsC(App frame, BrowseStoreProductsP view, Store model) {
        super(frame, view, model);
        super.initializeActions();
        refreshData();
        initializeActionsForMiniPanels();
    }

    @Override
    public void refreshData() {
        try {
            List<StoreProduct> products = new ArrayList<>();
            if (super.getFrame().getUser().getType() == UserType.REGISTERED_CLIENT) {
                products = Recommender.getInstance()
                                      .recommendSimilarProducts((RegisteredClient) super.getFrame().getUser());
            }
            if (products.isEmpty()) {
                products =
                        Statistics.getINSTANCE().getProductsBySales().subList(0, Parameter.getParam().getkRecommend());
            }
            if (products.isEmpty()) {
                products = Store.getInstance().getStoreProductList().subList(0, Parameter.getParam().getkRecommend());
            }
            super.getView().setItemList(products);
            super.getView().setCurrentPageNum(1);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void refreshCurrentPage() {
        try {
            int currentPage = super.getView().getCurrentPageNum();
            List<StoreProduct> products = new ArrayList<>();
            if (super.getFrame().getUser().getType() == UserType.REGISTERED_CLIENT) {
                products = Recommender.getInstance()
                                      .recommendSimilarProducts((RegisteredClient) super.getFrame().getUser());
            }
            if (products.isEmpty()) {
                products =
                        Statistics.getINSTANCE().getProductsBySales().subList(0, Parameter.getParam().getkRecommend());
            }
            if (products.isEmpty()) {
                products = Store.getInstance().getStoreProductList().subList(0, Parameter.getParam().getkRecommend());
            }
            super.getView().setItemList(products);
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
            new StoreProductMiniC(super.getFrame(), (StoreProductMiniP) miniPanel, this, super.getView());
        }
    }
}