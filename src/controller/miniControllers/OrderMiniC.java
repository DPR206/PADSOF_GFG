package controller.miniControllers;

import java.util.List;

import controller.Controller;
import controller.browserControllers.AbstractBrowserC;
import model.order.Order;
import model.order.OrderState;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.AbstractBrowserP;
import view.miniPanels.OrderMini;

public class OrderMiniC implements Controller {

	private final OrderMini view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model;
    private final Order order;
    private final AbstractBrowserC<Order> abstractBrowserC;
    private final AbstractBrowserP<Order> abstractBrowserP;
    
	/**
	 * @param view
	 * @param frame
	 * @param model
	 */
	public OrderMiniC(OrderMini view, App frame, Store model, Order order, AbstractBrowserC<Order> abstractBrowserC,
            AbstractBrowserP<Order> abstractBrowserP) {
		this.view = view;
		this.frame = frame;
		this.model = model;
		this.order = order;
		this.abstractBrowserC = abstractBrowserC;
		this.abstractBrowserP = abstractBrowserP;
		
		if(order.getState() != OrderState.PICKED_UP) {
			view.getAddReview().setVisible(false);
		} else
			view.setVisible(true);
		
		initializeActions();
	}

	 @Override
	 public void initializeActions() {
		 
		 cargarProductos();
		 
		 view.setOrderState(order.getState().getString());
		 
		 view.getAddReview().addActionListener(e -> {
			 abrirReview();
		 });
	 }

	 private void cargarProductos() {
		 StringBuilder sb = new StringBuilder();
	     List<StoreProduct> productos = order.getSp();
	     List<Pack> packs = order.getP();
	        
	     for (int i = 0; i < productos.size(); i++) {
	       sb.append(productos.get(i).getName());
	       if (i < productos.size() - 1) {
	         sb.append(", ");
	       }
	     }
	     
	     for (int i = 0; i < packs.size(); i++) {
		   sb.append("Pack " + i);
		   if (i < packs.size() - 1) {
		     sb.append(", ");
		   }
		 }
		
	     view.setOrderDetails(sb.toString(), order.getPrice());
	}

	 private void abrirReview() {
		
		
	 }

}
