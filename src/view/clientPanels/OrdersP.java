package view.clientPanels;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import view.browserPanels.BrowseOrdersP;

public class OrdersP extends JPanel{

	private static final long serialVersionUID = 1L;
	private BrowseOrdersP orderItems;
	
	public OrdersP() throws BadLocationException {

		this.setLayout(new BorderLayout());
		try {
			incializarEstrctura();
        } catch (BadLocationException e) {
            System.err.println("Error al inicializar la vista de pedidos: " + e.getMessage());
            this.add(new JLabel("Could not load orders. Please try again."), BorderLayout.CENTER);
        }
	}
	
	private void incializarEstrctura() throws BadLocationException {

		orderItems = new BrowseOrdersP();
		this.add(orderItems,  BorderLayout.CENTER);

	}

	public BrowseOrdersP getOrderItems() {
		return orderItems;
	}
}
