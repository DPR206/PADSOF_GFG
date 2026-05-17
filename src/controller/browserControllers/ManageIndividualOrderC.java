package controller.browserControllers;

import javax.swing.text.BadLocationException;

import controller.Controller;
import model.order.Order;
import view.employeePanels.ManageIndividualOrderP;

public class ManageIndividualOrderC implements Controller{

	private Order o;
	private ManageIndividualOrderP panel;
	
	public ManageIndividualOrderC(Order item, ManageIndividualOrderP toDo) {
		this.panel = toDo;
		this.o = item;
		try {
			initializeActions();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initializeActions() throws BadLocationException {
		//inicializamos el browser controler:
		new BrowseInOrderController(this.panel.getBrowser(), this.o);
		
	}
	
}
