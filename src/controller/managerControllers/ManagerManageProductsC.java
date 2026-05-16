package controller.managerControllers;

import javax.swing.text.BadLocationException;

import controller.Controller;
import controller.browserControllers.BrowseStoreProductC;
import view.App;
import view.managerPanels.ManagerGestionarProductos;

public class ManagerManageProductsC implements Controller{

	private ManagerGestionarProductos mgproduct;
	private App frame;
	
	public ManagerManageProductsC(ManagerGestionarProductos mgproduct, App frame) {
		this.mgproduct = mgproduct;
		this.frame = frame;
		try {
			initializeActions();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initializeActions() throws BadLocationException {
		/*Inicializamos el controlador del browser*/
		new BrowseStoreProductC(this.mgproduct.getProductsPanel(), frame);		
	}
	
}
