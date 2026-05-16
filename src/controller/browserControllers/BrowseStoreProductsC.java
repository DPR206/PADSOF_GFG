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

public class BrowseStoreProductsC extends AbstractBrowserC<StoreProduct> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param view  the controller's view
     * @param model the controller's model
     */
    public BrowseStoreProductsC(App frame, BrowseStoreProductsP view, Store model) {
        super(frame, view, model);
        super.initializeActions();
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {
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
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        for (AbstractMiniP miniPanel : super.getView().getMiniPanels()) {
            new StoreProductMiniC(super.getFrame(), super.getModel(), (StoreProductMiniP) miniPanel, this,
                    super.getView());
        }
    }
}