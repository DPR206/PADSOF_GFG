package controller.clientControllers;

import controller.Controller;
import controller.browserControllers.BrowseOrdersC;
import model.order.Order;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.clientPanels.OrdersP;

import javax.swing.text.BadLocationException;
import java.util.List;

/**
 * The type Orders c.
 * @author Duna P.R.
 * @version 1.0
 */
public class OrdersC implements Controller {

    private final App frame;
    private final OrdersP view;
    private final RegisteredClient user;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Orders c.
     * @param pagOrders the pag orders
     * @param frame     the frame
     */
    public OrdersC(OrdersP pagOrders, App frame) {
        this.frame = frame;
        this.view = pagOrders;
        this.user = (RegisteredClient) frame.getUser();

        initializeActions();
    }

    @Override
    public void initializeActions() {
        try {
            List<Order> historial = user.getOrderHistory().getOrders();
            view.getOrderItems().setItemList(historial);
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

        new BrowseOrdersC(frame, view.getOrderItems(), Store.getInstance());

    }

    /**
     * Refresh.
     */
    public void refresh() {
        try {
            view.getOrderItems().paintEverything();
            new BrowseOrdersC(frame, view.getOrderItems(), Store.getInstance());
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }
}