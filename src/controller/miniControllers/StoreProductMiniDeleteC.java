package controller.miniControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.product.Pack;
import model.store.Store;
import view.miniPanels.PackMiniDelete;
import view.miniPanels.StoreProductMiniDelete;

public class StoreProductMiniDeleteC implements ActionListener{

	private StoreProductMiniDelete smpd;
	private Pack pack;
	
	public StoreProductMiniDeleteC(StoreProductMiniDelete spmd, Pack p) {
		this.smpd = spmd;
		this.pack = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("ELIMINAR DEL PACK")) {
			pack.getProducts().remove(this.smpd);
		}
	}

}
