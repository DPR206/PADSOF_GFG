package controller.managerControllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;

import controller.Controller;
import model.product.ComposedPack;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.managerPanels.ManagerCreateComposedPackP;

public class ManagerCreateComposedPackC implements Controller{

	private List<StoreProduct> products = new ArrayList<>();
	private HashSet<Pack> packs = new HashSet<>();
	private ManagerCreateComposedPackP pp;
	private App frame;
	
	public ManagerCreateComposedPackC(ManagerCreateComposedPackP pp, App frame) {
		this.pp = pp;
		this.frame = frame;
		initializeActions();
	}
	
	public void initializeActions() {
		pp.getNombre().addActionListener(e->{
			if(pp.getProductNameAdd().getText().isEmpty()) {
				JOptionPane.showMessageDialog(
					    null,
					    "Debes insertar el nombre del producto que quieres buscar.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE
					);
				return;
			}
			String name = pp.getProductNameAdd().getText();
			StoreProduct toAdd = null;
			
			List<StoreProduct> productsList = Store.getInstance().getStoreProductList();
			for(StoreProduct sp: productsList) {
				if(sp.getName().equals(name)) {
					toAdd = sp;
					break;
				}
			}
			
			if(toAdd == null) {
				JOptionPane.showMessageDialog(
					    null,
					    "Ese producto no se encuentra en la tienda.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE
				);
				return;
			}
			this.products.add(toAdd);
		});
		
		/*Ahora para el de packs*/
		pp.getAddPackButton().addActionListener(e->{
			if(pp.getPackIdAdd().getText().isEmpty()) {
				JOptionPane.showMessageDialog(
					    null,
					    "Debes insertar el ID del pack que quieres buscar.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE
				);
				return;
			}
			int id;
			try {
				id = Integer.parseInt(pp.getPackIdAdd().getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(
					    null,
					    "El ID del pack debe ser un número válido.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE
				);
				return;
			}
			Pack toAdd = Store.getInstance().getPackById(id);
			if(toAdd == null) {
				JOptionPane.showMessageDialog(
					    null,
					    "No existe un pack con ese ID.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE
				);
				return;
			}
			this.packs.add(toAdd);
		});
		
		pp.getConfirmar().addActionListener(e->{
			if(pp.getPackName().getText().isEmpty() || pp.getPrice().getText().isEmpty() || pp.getPictureDirectory().getText().isEmpty()) {
				JOptionPane.showMessageDialog(
					    null,
					    "Debes rellenar todos los datos.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE
					);
				return;
			}
			double price = Double.parseDouble(pp.getPrice().getText());
			
			ComposedPack newPack = new ComposedPack(price, this.packs, pp.getPictureDirectory().getText());
			newPack.getProducts().addAll(products);
			
			JOptionPane.showMessageDialog(
				    null,
				    "Pack creado correctamente.",
				    "Éxito",
				    JOptionPane.INFORMATION_MESSAGE
				);
		});
	}

}
