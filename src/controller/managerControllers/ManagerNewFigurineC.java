package controller.managerControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.product.Category;
import model.product.Figurine;
import model.store.Store;
import view.App;
import view.managerPanels.ManagerCreateComicP;
import view.managerPanels.ManagerCreateFiguritaP;

public class ManagerNewFigurineC implements ActionListener{
	
	private ManagerCreateFiguritaP mnc;
	private App frame;
	
	public ManagerNewFigurineC(ManagerCreateFiguritaP mnc, App frame) {
		this.mnc = mnc;
		this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("CONFIRMAR")) {
			String category = mnc.getTxtCategoria().getText();
			Category cat = Store.getInstance().getCategoryFromName(category);
			if(cat == null) {
				 JOptionPane.showMessageDialog(
					        null,
					        "LA CATEGORÍA NO EXISTE",
					        "ERROR",
					        JOptionPane.ERROR_MESSAGE
					    );
				 return;
			}
			String nombre = mnc.getTxtNombre().getText();
			if(nombre == null) return;
			String precio = mnc.getTxtPrecio().getText();
			if(precio == null) return;
			Double price = Double.parseDouble(precio);
			String stock = mnc.getTxtStock().getText();
			if(stock == null) return;
			Integer stockk = Integer.parseInt(stock);
			String description = mnc.getTxtDescripcion().getText();
			if(description == null) return;
			String marca = mnc.getTxtMarca().getText();
			if(marca == null) return;
			String material = mnc.getTxtMaterial().getText();
			if(material == null) return;
			String largo = mnc.getTxtLargo().getText();
			if(largo == null) return;
			
			String ancho = mnc.getTxtAncho().getText();
			if(ancho == null) return;
			
			String alto = mnc.getTxtAlto().getText();
			if(alto == null) return;
			String dimensions = largo + "x" + ancho + "x" + alto;
			
			Figurine f = new Figurine(price, nombre, description, ".\\resources\\app\\cart.png", stockk, dimensions, marca, material, cat);
			
			Store.getInstance().addStoreProduct(f);
		}
	}
}
