package controller.managerControllers;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import controller.Controller;
import controller.miniControllers.StoreProductDeleteMiniC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.managerPanels.ManagerGestionarPacks;
import view.managerPanels.ManagerIndividualSimplePack;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.StoreProductMiniP;

public class ManagerGestPackSimpleInd implements Controller{

	private Pack p;
	private ManagerIndividualSimplePack panel;
	private ManagerGestionarPacks gestionar;
	
	public ManagerGestPackSimpleInd(Pack p, ManagerIndividualSimplePack panel, ManagerGestionarPacks gestionar) {
		this.p = p;
		this.panel = panel;
		this.gestionar = gestionar;
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
		
		/*asigno los action listeners*/
		
		panel.getConfirmarProduct().addActionListener(e->{
			if(panel.getNameProductText().getText().isEmpty()) {
				return;
			}
			String name = panel.getNameProductText().getText();
			
			/*Busco producto*/
			
			StoreProduct toAdd = null;
			
			List<StoreProduct> pr = Store.getInstance().getStoreProductList();
			for(StoreProduct sp: pr) {
				if(sp.getName().equals(name)) {
					toAdd = sp;
					p.addProduct(toAdd);
				}
			}
			if(toAdd == null) {
				JOptionPane.showMessageDialog(
					    null,
					    "Debes insertar un nombre válido.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE
					);
				return;
			}
			JOptionPane.showMessageDialog(
				    null,
				    "Producto añadido correctamente.",
				    "Éxito",
				    JOptionPane.INFORMATION_MESSAGE
				);
		});
		
		/*Ahora el botón del precio*/
		
		panel.getConfirmarPrecio().addActionListener(e->{
			if(panel.getPackPriceText().getText().isEmpty()) return;
			
			double price = Double.parseDouble(panel.getPackPriceText().getText());
			this.p.setPrice(price);	
			
			JOptionPane.showMessageDialog(
				    null,
				    "Precio cambiado correctamente.",
				    "Éxito",
				    JOptionPane.INFORMATION_MESSAGE
				);
			try {
				this.gestionar.getBrowser().paintEverything();
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}

}