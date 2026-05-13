package controller.clientControllers;

import javax.swing.text.BadLocationException;

import controller.Controller;
import controller.browserControllers.BrowseOrdersC;
import model.store.Store;
import view.App;
import view.clientPanels.OrdersP;

public class OrdersC implements Controller{
	
	private App frame;
    private OrdersP view;

	public OrdersC(OrdersP pagOrders, App frame) {
		
		this.frame = frame;
		this.view = pagOrders;
		
		initializeActions();
	}

	@Override
	public void initializeActions() {
		
	}

	private void updateInterface() {
        try {
            System.out.println("Updating cart..");
            OrdersP orderVista = new OrdersP();
            new OrdersC(orderVista, frame);
            new BrowseOrdersC(frame, orderVista.getOrderItems(), Store.getInstance());
            frame.addCard(orderVista, "ORDERS");
            frame.changeVisibleCard("ORDERS");
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }
}
