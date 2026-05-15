package controller.managerControllers;

import javax.swing.text.BadLocationException;

import controller.Controller;
import model.product.Pack;
import model.store.Store;
import view.App;
import view.managerPanels.ManagerCreatePackP;
import view.managerPanels.ManagerGestionarPacks;

public class ManageCreatePacksC implements Controller{
	private ManagerGestionarPacks gestionar;
	private App frame;
	private ManagerCreatePackP packP = new ManagerCreatePackP();
	
	public ManageCreatePacksC(ManagerGestionarPacks m, App frame) {
		this.gestionar = m;
		this.frame = frame;
		int i = 1;
		
		for(Pack p: Store.getInstance().getPacks()) {
			try {
				this.gestionar.getBrowser().addMiniPanel(p, i);
				i++;
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		initializeActions();
	}
	
	@Override
	public void initializeActions() {
		this.gestionar.getConfirmacion().addActionListener(e->{
			frame.addCard(packP, "CREAR NUEVO PACK");
			this.frame.changeVisibleCard("CREAR NUEVO PACK");
		});	
	}
	public void addPack() {
		int i = 1;
		for(Pack p: Store.getInstance().getPacks()) {
			try {
				this.gestionar.getBrowser().addMiniPanel(p, i);
				i++;
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
