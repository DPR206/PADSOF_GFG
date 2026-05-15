package controller.managerControllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controller.Controller;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.managerPanels.ManagerCreateSimplePack;

public class ManagerCreateSimplePackC implements Controller{

	private ManagerCreateSimplePack p;
	private App frame;
	private Pack creatingPack;
	private ArrayList<StoreProduct> productsToAdd = new ArrayList<>();
	
	public ManagerCreateSimplePackC(ManagerCreateSimplePack p, App frame) {
		this.p = p;
		this.frame = frame;
		initializeActions();
	}

	@Override
	public void initializeActions() {
		/*Inicializamos el botón para buscar un producto*/
		this.p.getId().addActionListener(e->{
			
			if(p.getProductNameAdd().getText().isEmpty()) {
				JOptionPane.showMessageDialog(
					    null,
					    "Debes insertar el nombre del producto que quieres buscar.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE
					);
				return;
			}
			
			StoreProduct toAdd = null;
			String name = p.getProductNameAdd().getText().trim();
			
			List<StoreProduct> pr = Store.getInstance().getStoreProductList();
			
			/*DEBUG*/
			for(StoreProduct sp: pr) {
				System.out.println(sp.getName() + "\n");
			}
			
			for(StoreProduct sp: pr) {
				if(sp.getName().trim().equalsIgnoreCase(name)) {
					toAdd = sp;
					productsToAdd.add(toAdd);
					break;
				}
			}
			
			if(toAdd == null) {
				JOptionPane.showMessageDialog(
					    null,
					    "Ese producto no existe.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE
					);
				return;
			}
			
		});
		
		this.p.getConfirmar().addActionListener(e->{
			if(p.getPackName().getText().isEmpty() || p.getPrice().getText().isEmpty() || p.getPictureDirectory().getText().isEmpty()) {
				JOptionPane.showMessageDialog(
					    null,
					    "Debes insertar el nombre del pack y su precio primero.",
					    "Error",
					    JOptionPane.ERROR_MESSAGE
					);
				return;
			}
			double price = Double.parseDouble(p.getPrice().getText());
			String directory = p.getPictureDirectory().getText();
			
			ArrayList<StoreProduct> products = this.productsToAdd;
			Pack newPack = new Pack(price, products, directory);
			Store.getInstance().addPack(newPack);
			
			JOptionPane.showMessageDialog(
				    null,
				    "Pack creado correctamente.",
				    "Éxito",
				    JOptionPane.INFORMATION_MESSAGE
				);
		});
	}
	
}
