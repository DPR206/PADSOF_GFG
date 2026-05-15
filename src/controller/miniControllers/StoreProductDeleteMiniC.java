package controller.miniControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.text.BadLocationException;

import controller.Controller;
import model.product.Pack;
import view.browserPanels.BrowsePackProductsP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.StoreProductMiniP;

public class StoreProductDeleteMiniC implements Controller {

    private StoreProductMiniP smpd;
    private Pack pack;
    private BrowsePackProductsP browser;

	public StoreProductDeleteMiniC(StoreProductMiniP smpd, BrowsePackProductsP browser, Pack pack) {
		this.smpd = smpd;
		this.pack = pack;
		this.browser = browser;
		initializeActions();
	}

	@Override
	public void initializeActions() {
		smpd.getButton().addActionListener(e-> {
			pack.eliminateProduct(smpd.getStoreProduct());
			/*Buscamos el minipanel*/
			
			List<AbstractMiniP> panels = this.browser.getMiniPanels();
			
			panels.removeIf(panel ->
            panel instanceof StoreProductMiniP &&
            ((StoreProductMiniP) panel)
                .getStoreProduct()
                .getId()
                .equals(this.smpd.getStoreProduct().getId())
					);
			try {
				this.browser.paintEverything();
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("DELETED");
		});

	}
}