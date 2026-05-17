package controller.employeeControllers;

import javax.swing.text.BadLocationException;

import controller.Controller;
import controller.browserControllers.BrowseOrdersC;
import controller.browserControllers.BrowseStoreOrdersC;
import model.store.Store;
import view.App;
import view.employeePanels.EmployeeOrder;

public class EmployeOrderC implements Controller{
	private EmployeeOrder panel;
	private App frame;
	
	public EmployeOrderC(App frame, EmployeeOrder panel) {
		this.frame = frame;
		this.panel = panel;
		try {
			initializeActions();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initializeActions() throws BadLocationException {
		//inicializamos el controlador de browser de pedidos
		new BrowseStoreOrdersC(this.panel.getBrowser(), this.frame);
	}
}
