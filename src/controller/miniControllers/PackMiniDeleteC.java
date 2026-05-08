package controller.miniControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.product.Pack;
import model.store.Store;
import view.miniPanels.PackMiniDelete;

public class PackMiniDeleteC implements ActionListener{
	
	private Pack p;
	public PackMiniDelete pmd;
	
	public PackMiniDeleteC(PackMiniDelete pmc, Pack p) {
		this.pmd = pmc;
		this.p = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("ELIMINAR DEL PACK")) {
			this.p.getPacks().remove(this.pmd);
		}
	}
}
