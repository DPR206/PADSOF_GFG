package controller.browserControllers;

import java.util.List;

import javax.swing.text.BadLocationException;

import controller.miniControllers.OrderMiniC;
import model.order.Order;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.BrowseOrdersP;
import view.miniPanels.MiniPanel;
import view.miniPanels.OrderMini;

public class BrowseOrdersC extends AbstractBrowserC<Order>{

	BrowseOrdersC(App frame, BrowseOrdersP view, Store model) {
		super(frame, view, model);
		initializeActionsForMiniPanels();
	}

	@Override
	public void initializeActionsForMiniPanels() {
		try {
	        List<Order> orders = ((RegisteredClient) super.getFrame().getUser()).getOrderHistory().getOrders();
	        super.getView().setItemList(orders);
	    } catch (BadLocationException ex) {
	        throw new RuntimeException(ex);
	    }

		List<MiniPanel> miniPanels = super.getView().getMiniPanels();
	    List<Order> itemList = super.getView().getItemList();

	    for (int i = 0; i < miniPanels.size(); i++) {
	        if (i < itemList.size()) {
	            Order currentOrder = (Order) itemList.get(i);
	            MiniPanel currentPanel = miniPanels.get(i);

	            new OrderMiniC(
	                (OrderMini) currentPanel, 
	                super.getFrame(), 
	                super.getModel(), 
	                currentOrder, 
	                this, 
	                super.getView()
	            );
	        }
		
	    }
	}
}
