package controller.managerControllers;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import controller.Controller;
import controller.browserControllers.BrowseComposedPackC;
import model.product.ComposedPack;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.managerPanels.ManagerIndividualComposedPack;

public class ManagerManageComposedPackC implements Controller{
	
	private ManagerIndividualComposedPack panel;
	private ComposedPack p;
	
	public ManagerManageComposedPackC(ManagerIndividualComposedPack panel, ComposedPack p) {
		this.panel = panel;
		this.p = p;
		try {
			initializeActions();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void initializeActions() throws BadLocationException {
		new BrowseComposedPackC(panel.getBrowser(), p);
		
		/*Para cambiar precio*/
		panel.getConfirmarPrecio().addActionListener(e->{
			if(panel.getPackPriceText().getText().isEmpty()) {
				return;
			}
			double price = Double.parseDouble(panel.getPackPriceText().getText());
			this.p.setPrice(price);
			JOptionPane.showMessageDialog(null, "Precio cambiado correctamente.");
		});
		
		panel.getConfirmarProduct().addActionListener(e->{
			if(panel.getIdProductText().getText().isEmpty()) {
				return;
			}
			String name = panel.getIdProductText().getText();
			/*Buscamos el producto*/
			StoreProduct toAdd = null;
			List<StoreProduct> toSearch = Store.getInstance().getStoreProductList();
			
			for(StoreProduct sp: toSearch) {
				if(sp.getName().equals(name)) {
					toAdd = sp;
				}
			}
			if(toAdd == null) {
				JOptionPane.showMessageDialog(
					    null,                                  
					    "Este producto no existe.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE 
					);
				return;
			}
			this.p.addProduct(toAdd);
			JOptionPane.showMessageDialog(null, "Producto añadido correctamente.");
		});
		
		panel.getConfirmarPackId().addActionListener(e->{
			if(panel.getPackIdText().getText().isEmpty()) {
				return;
			}
			
			int id = Integer.parseInt(panel.getPackIdText().getText());
			Pack toAdd = Store.getInstance().getPackById(id);
			if(toAdd == null) {
				JOptionPane.showMessageDialog(
					    null,                                  
					    "El pack con este id no existe.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE 
					);
				return;
			}
			this.p.addPack(toAdd);
			JOptionPane.showMessageDialog(null, "Pack añadido correctamente.");
		});
	}

}
