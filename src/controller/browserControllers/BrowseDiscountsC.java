package controller.browserControllers;

import controller.miniControllers.DiscountDiscMiniC;
import model.discount.Discount;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseDiscountsP;
import view.managerPanels.ManagerDiscountsP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.DiscountMiniP;

import javax.swing.text.BadLocationException;

/**
 * The type Browse discounts c.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowseDiscountsC extends AbstractBrowserC<Discount> {
    private final ManagerDiscountsP parentView;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame      the controller's frame
     * @param view       the controller's view
     * @param model      the controller's model
     * @param parentView the parent view
     */
    public BrowseDiscountsC(App frame, BrowseDiscountsP view, Store model, ManagerDiscountsP parentView) {
        super(frame, view, model);
        this.parentView = parentView;
        super.initializeActions();
    }

    @Override
    public void refreshData() {
        try {
            super.getView().setItemList(parentView.getBrowseDiscountsP().getItemList());
            super.getView().setCurrentPageNum(1);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void refreshCurrentPage() {
        try {
            int currentPage = super.getView().getCurrentPageNum();
            super.getView().setItemList(parentView.getBrowseDiscountsP().getItemList());
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
            new DiscountDiscMiniC(super.getFrame(), super.getModel(), (DiscountMiniP) miniPanel, this,
                    (BrowseDiscountsP) super.getView());
        }
    }
}