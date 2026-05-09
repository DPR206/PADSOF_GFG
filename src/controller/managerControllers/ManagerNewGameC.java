package controller.managerControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.product.Category;
import model.product.Game;
import model.product.GameStyle;
import model.store.Store;
import view.App;
import view.managerPanels.ManagerCreateFiguritaP;
import view.managerPanels.ManagerCreateGameP;

public class ManagerNewGameC implements ActionListener{
	
	private ManagerCreateGameP mnc;
	private App frame;
	
	public ManagerNewGameC(ManagerCreateGameP mnc, App frame) {
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
			String rangoEdad = mnc.getTxtAgeRange().getText();
			if(rangoEdad == null) return;
			String numPlayers = mnc.getTxtNumPlayers().getText();
			if(numPlayers == null) return;
			Integer num = Integer.parseInt(numPlayers);
			Game g;
			
			if(mnc.getRbCartas().isSelected()) {
				g = new Game(price, nombre, description, ".\\resources\\app\\cart.png",  stockk, num,
                rangoEdad, GameStyle.CARDS, cat);
			}
			else if(mnc.getRbMesa().isSelected()) {
				g = new Game(price, nombre, description, ".\\resources\\app\\cart.png",  stockk, num,
		                rangoEdad, GameStyle.GAMEBOARD, cat);
			}
			else if(mnc.getRbRol().isSelected()) {
				g = new Game(price, nombre, description, ".\\resources\\app\\cart.png",  stockk, num,
		                rangoEdad, GameStyle.DICE, cat);
			}
		}
	}

}
