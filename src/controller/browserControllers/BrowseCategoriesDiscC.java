package controller.browserControllers;

import controller.miniControllers.CategoryDiscMiniC;
import model.product.Category;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseCategoriesDiscP;
import view.managerPanels.ManagerDiscountsP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.CategoryDiscMiniP;

import javax.swing.text.BadLocationException;

/**
 * The type Browse categories disc c.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowseCategoriesDiscC extends AbstractBrowserC<Category> {
    private final ManagerDiscountsP parentView;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame      the controller's frame
     * @param view       the controller's view
     * @param model      the controller's model
     * @param parentView the parent view
     */
    public BrowseCategoriesDiscC(App frame, BrowseCategoriesDiscP view, Store model, ManagerDiscountsP parentView) {
        super(frame, view, model);
        this.parentView = parentView;
        super.initializeActions();
        initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActionsForMiniPanels() {
        try {
            super.getView().setItemList(Store.getInstance().getCategoryList());
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }

        for (AbstractMiniP miniPanel : super.getView().getMiniPanels()) {
            new CategoryDiscMiniC(super.getFrame(), (CategoryDiscMiniP) miniPanel, this,
                    (BrowseCategoriesDiscP) super.getView(), parentView.getSelectedCategoriesList());
        }
    }
}