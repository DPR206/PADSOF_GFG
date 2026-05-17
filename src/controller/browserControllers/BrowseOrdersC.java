package controller.browserControllers;

import controller.miniControllers.OrderMiniC;
import model.order.Order;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.BrowseOrdersP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.OrderMini;

import javax.swing.text.BadLocationException;
import java.util.List;

/**
 * The type Browse orders c.
 * @author Duna P.R.
 * @version 1.0
 */
public class BrowseOrdersC extends AbstractBrowserC<Order> {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Browse orders c.
     * @param frame the frame
     * @param view  the view
     * @param model the model
     */
    public BrowseOrdersC(App frame, BrowseOrdersP view, Store model) {
        super(frame, view, model);
        super.initializeActions();
    }

    @Override
    public void refreshData() {
        try {
            List<Order> orders = ((RegisteredClient) super.getFrame().getUser()).getOrderHistory().getOrders();
            super.getView().setItemList(orders);
            super.getView().setCurrentPageNum(1);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void refreshCurrentPage() {
        try {
            int currentPage = super.getView().getCurrentPageNum();
            List<Order> orders = ((RegisteredClient) super.getFrame().getUser()).getOrderHistory().getOrders();
            super.getView().setItemList(orders);
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
        List<AbstractMiniP> miniPanels = super.getView().getMiniPanels();
        List<Order> itemList = super.getView().getItemList();

        for (int i = 0; i < miniPanels.size(); i++) {
            if (i < itemList.size()) {
                Order currentOrder = itemList.get(i);
                AbstractMiniP currentPanel = miniPanels.get(i);

                new OrderMiniC((OrderMini) currentPanel, super.getFrame(), super.getModel(), currentOrder, this,
                        super.getView());
            }

        }
    }
}