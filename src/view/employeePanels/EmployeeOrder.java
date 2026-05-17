package view.employeePanels;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import view.browserPanels.BrowseOrdersP;
import view.browserPanels.BrowseStoreOrdersP;

public class EmployeeOrder extends JPanel {
	private BrowseStoreOrdersP browser;
	
	public EmployeeOrder() {
		super();
		try {
			this.browser = new BrowseStoreOrdersP();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.add(browser);
	}

	public BrowseStoreOrdersP getBrowser() {
		return this.browser;
	}
}