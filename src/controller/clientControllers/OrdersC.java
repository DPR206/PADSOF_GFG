package controller.clientControllers;

import java.util.List;

import javax.swing.text.BadLocationException;

import controller.Controller;
import controller.browserControllers.BrowseOrdersC;
import model.order.Order;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.clientPanels.OrdersP;

public class OrdersC implements Controller {
    
    private App frame;
    private OrdersP view;
    private RegisteredClient user; 

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
            e.printStackTrace();
        }

        new BrowseOrdersC(frame, view.getOrderItems(), Store.getInstance());

    }


    public void refresh() {
        try {
            view.getOrderItems().paintEverything();
            new BrowseOrdersC(frame, view.getOrderItems(), Store.getInstance());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}