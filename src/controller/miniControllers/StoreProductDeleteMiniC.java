package controller.miniControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.product.Pack;
import view.miniPanels.StoreProductMiniP;

public class StoreProductDeleteMiniC implements ActionListener {

    private StoreProductMiniP smpd;
    private Pack pack;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public StoreProductDeleteMiniC(StoreProductMiniP spmd, Pack p) {
        this.smpd = spmd;
        this.pack = p;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("DELETE")) {
			pack.eliminateProduct(smpd.getStoreProduct());
		}

	}
}