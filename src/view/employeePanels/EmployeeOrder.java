package view.employeePanels;

import java.awt.BorderLayout;

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
		this.setLayout(new BorderLayout());
		this.add(browser, BorderLayout.CENTER);
	}

	public BrowseStoreOrdersP getBrowser() {
		return this.browser;
	}
}