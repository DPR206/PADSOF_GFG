package controller.browserControllers;

import java.util.List;

import javax.swing.text.BadLocationException;

import controller.Controller;
import model.order.Order;
import model.product.Pack;
import model.product.StoreProduct;
import view.browserPanels.BrowseInOrder;

public class BrowseInOrderController implements Controller{

	private BrowseInOrder browser;
	private Order o;
	
	public BrowseInOrderController(BrowseInOrder browser, Order o) {
		this.browser = browser;
		this.o = o;
		try {
			initializeActions();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initializeActions() throws BadLocationException {
		List<Pack> packList = o.getP();
		browser.setFirstItemList(packList);
		List<StoreProduct> productList = o.getSp();
		browser.setSecondItemList(productList);
		browser.paintEverything();
	}
}
