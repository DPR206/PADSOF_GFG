package view.employeePanels;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import view.browserPanels.BrowseOrdersP;

public class EmployeeOrder extends JPanel {
	private BrowseOrdersP browser;
	
	public EmployeeOrder() {
		super();
		try {
			this.browser = new BrowseOrdersP();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.add(browser);
	}
}