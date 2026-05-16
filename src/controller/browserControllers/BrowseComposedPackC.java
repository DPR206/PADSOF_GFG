package controller.browserControllers;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import controller.Controller;
import model.product.ComposedPack;
import model.product.Pack;
import model.product.StoreProduct;
import view.browserPanels.BrowsePacksComposed;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.PackMiniP;
import view.miniPanels.StoreProductMiniP;

public class BrowseComposedPackC implements Controller{
	private BrowsePacksComposed browser;
	private ComposedPack pack;
	
	public BrowseComposedPackC(BrowsePacksComposed browser, ComposedPack pack) {
		this.browser = browser;
		this.pack = pack;
		try {
			initializeActions();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initializeActions() throws BadLocationException {
		/*Creamos la lista de minipacks*/
		int i = 0;
		for(Pack p: this.pack.getPacks()) {
			this.browser.addFirstMiniPanel(p, i); 
		}
		/*Inicializamos la lista de productos*/
		i = 0;
		for(StoreProduct sp: this.pack.getProducts()) {
			this.browser.addSecondMiniPanel(sp, i); 
		}
		
		/*Ahora inicializamos el controlador de los packs*/
		List<AbstractMiniP> packMinis = this.browser.getFirstMiniPanels();
		for(AbstractMiniP packMin: packMinis) {
			PackMiniP miniPack = (PackMiniP)packMin;
			miniPack.getButton().addActionListener(e->{
				this.pack.getPacks().remove(miniPack.getPack());
				JOptionPane.showMessageDialog(null, "Pack borrado con éxito.");
			});
			
		}
		/*Ahora para los productos*/
		List<AbstractMiniP> productsMini = this.browser.getSecondMiniPanels();
		for(AbstractMiniP productMini: productsMini) {
			StoreProductMiniP miniProduct = (StoreProductMiniP)productMini;
				miniProduct.getButton().addActionListener(e->{
				this.pack.getProducts().remove(miniProduct.getStoreProduct());
				JOptionPane.showMessageDialog(null, "Producto borrado con éxito.");
			});
		}
	}
}
