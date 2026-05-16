package controller.browserControllers;

import java.util.List;

import javax.swing.text.BadLocationException;

import controller.Controller;
import controller.managerControllers.ManageComicC;
import model.product.Comic;
import model.product.Figurine;
import model.product.Game;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseStoreProducts;
import view.managerPanels.ManageIndividualFiguraP;
import view.managerPanels.ManagerGestionarProductos;
import view.managerPanels.ManagerIndividualComicP;
import view.managerPanels.ManagerIndividualGameP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.StoreProductMiniP;

public class BrowseStoreProductC implements Controller{
	
	private BrowseStoreProducts browser;
	private App frame;
	private ManageIndividualFiguraP manageFigurine = null;
	private ManagerIndividualGameP manageGame = null;
	private ManagerIndividualComicP manageComic = null;
	
	public BrowseStoreProductC(BrowseStoreProducts browser, App frame) {
		this.browser = browser;
		this.frame = frame;
		try {
			initializeActions();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void initializeActions() throws BadLocationException {
		List<StoreProduct> products = Store.getInstance().getStoreProductList();
		int i = 0;
		for(StoreProduct sp: products) {
			this.browser.addMiniPanel(sp, i);
			i++;
		}
		/*Añadimos el controlador*/
		List<AbstractMiniP> panels = this.browser.getMiniPanels();
		
		/*Asignamos acción*/
		for(AbstractMiniP panel: panels) {
			StoreProductMiniP miniSp = (StoreProductMiniP)panel;
			miniSp.getButton().addActionListener(e->{
				if(miniSp.getStoreProduct() instanceof Comic) {
					this.manageComic = new ManagerIndividualComicP();
					new ManageComicC(miniSp, this.manageComic);
					this.frame.addCard(panel, "MANAGE COMIC");
					this.frame.changeVisibleCard("MANAGE COMIC");
				}
				else if(miniSp.getStoreProduct() instanceof Figurine) {
					this.manageFigurine = new  ManageIndividualFiguraP();
					new ManageFigurineC(miniSp, this.manageFigurine);
					this.frame.addCard(panel, "MANAGE FIGURINE");
					this.frame.changeVisibleCard("MANAGE FIGURINE");
				}
				else if(miniSp.getStoreProduct() instanceof Game) {
					this.manageGame = new ManagerIndividualGameP();
					new ManageGameC(miniSp, this.manageGame);
					this.frame.addCard(panel, "MANAGE GAME");
					this.frame.changeVisibleCard("MANAGE GAME");
				}
			});
		}
		
	}

}
