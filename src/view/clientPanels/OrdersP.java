package view.clientPanels;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import view.browserPanels.BrowseOrdersP;

public class OrdersP extends JPanel{

	private static final long serialVersionUID = 1L;
	private BrowseOrdersP orderItems;
	
	public OrdersP() throws BadLocationException {

		this.setLayout(new BorderLayout());
		incializarEstrctura();
	}
	
	private void incializarEstrctura() throws BadLocationException {

		orderItems = new BrowseOrdersP();
		this.add(orderItems,  BorderLayout.CENTER);

	}

	public BrowseOrdersP getOrderItems() {
		return orderItems;
	}
}
