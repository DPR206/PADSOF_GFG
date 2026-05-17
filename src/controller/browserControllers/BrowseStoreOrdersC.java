package controller.browserControllers;

import java.util.List;

import javax.swing.text.BadLocationException;

import controller.Controller;
import controller.managerControllers.ManageComicC;
import controller.managerControllers.ManageFigurineC;
import controller.managerControllers.ManageGameC;
import controller.miniControllers.OrderMiniC;
import model.order.Order;
import model.product.Comic;
import model.product.Figurine;
import model.product.Game;
import model.product.StoreProduct;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.AbstractBrowserP;
import view.browserPanels.BrowseStoreOrdersP;
import view.browserPanels.BrowseStoreProducts;
import view.employeePanels.ManageIndividualOrderP;
import view.managerPanels.ManageIndividualFiguraP;
import view.managerPanels.ManagerIndividualComicP;
import view.managerPanels.ManagerIndividualGameP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.OrderMini;
import view.miniPanels.OrderMiniManageP;
import view.miniPanels.StoreProductMiniP;

public class BrowseStoreOrdersC implements Controller{

	    private final App frame;
	    private ManageIndividualOrderP toDo = null;
	    private BrowseStoreOrdersP panel;

	    
	    public BrowseStoreOrdersC(BrowseStoreOrdersP browser, App frame) {
	        this.panel = browser;
	        this.frame = frame;
	        try {
	            initializeActions();
	        } catch (BadLocationException e) {
	            throw new RuntimeException(e);
	        }
	    }
	    @Override
		public void initializeActions() throws BadLocationException {
	    	List<Order> orders = Store.getInstance().getOrders();
	        this.panel.setItemList(orders);
	        this.panel.setCurrentPageNum(1);

	        /*Añadimos el controlador*/
	        List<AbstractMiniP> panels = this.panel.getMiniPanels();

	        /*Asignamos acción*/
	        for (AbstractMiniP panel : panels) {
	            OrderMiniManageP miniSp = (OrderMiniManageP) panel;
	            miniSp.getButton().addActionListener(e -> {
	            	this.toDo = new ManageIndividualOrderP(miniSp.getItem());
	            	//new ManageIndividualOrderC(miniSp.getItem());
	            	
	            	this.frame.addCard(this.toDo, "MANAGE INDIVIDUAL ORDER");
	            	try {
						this.frame.changeVisibleCard("MANAGE INDIVIDUAL ORDER");
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            });
		
	      }
	 }
}
