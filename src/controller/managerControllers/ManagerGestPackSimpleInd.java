package controller.managerControllers;

import java.util.List;

import javax.swing.text.BadLocationException;

import controller.Controller;
import controller.miniControllers.StoreProductDeleteMiniC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.managerPanels.ManagerIndividualSimplePack;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.StoreProductMiniP;

public class ManagerGestPackSimpleInd implements Controller{

	private Pack p;
	private ManagerIndividualSimplePack panel;
	
	public ManagerGestPackSimpleInd(Pack p, ManagerIndividualSimplePack panel) {
		this.p = p;
		this.panel = panel;
		initializeActions();
	}


	@Override
	public void initializeActions() {
		/*for(Pack p: Store.getInstance().getPacks()) {
			try {
				this.gestionar.getBrowser().addMiniPanel(p, i);
				i++;
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		int i = 1;
		List<StoreProduct> products = p.getProducts();
		for(StoreProduct sp: products) {
			try {
				this.panel.getBrowser().addMiniPanel(sp, i);
				i++;
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*Asigno los controladores*/
		List<AbstractMiniP> list = this.panel.getBrowser().getMiniPanels();
		
		for(AbstractMiniP abs: list) {
			StoreProductMiniP sp = (StoreProductMiniP)abs;
			new StoreProductDeleteMiniC(sp, panel.getBrowser(), p);
		}

	}

}