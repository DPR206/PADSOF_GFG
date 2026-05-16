package controller.managerControllers;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import controller.Controller;
import model.product.Category;
import model.product.Game;
import model.product.GameStyle;
import model.store.Store;
import view.managerPanels.ManagerIndividualGameP;
import view.miniPanels.StoreProductMiniP;

public class ManageGameC implements Controller {
	private ManagerIndividualGameP manageGame;
	private StoreProductMiniP miniPanel;
	private Game game;
	
	public ManageGameC(StoreProductMiniP miniPanel, ManagerIndividualGameP manageGame) {
		this.miniPanel = miniPanel;
		this.manageGame = manageGame;
		this.game = (Game) miniPanel.getStoreProduct();
		try {
			initializeActions();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initializeActions() throws BadLocationException {
		manageGame.getBtnConfirmarNombre().addActionListener(e -> {
			if (manageGame.getTxtNombreProducto().getText().isEmpty()) return;
			game.setName(manageGame.getTxtNombreProducto().getText());
			JOptionPane.showMessageDialog(null, "Nombre cambiado.");
		});
		
		manageGame.getBtnConfirmarTipoJuego().addActionListener(e -> {
			if (manageGame.getTxtTipoJuego().getText().isEmpty()) return;
			try {
				GameStyle style = GameStyle.valueOf(manageGame.getTxtTipoJuego().getText().toUpperCase());
				game.setGameStyle(style);
				JOptionPane.showMessageDialog(null, "Tipo de juego cambiado.");
			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(null, "Tipo de juego inválido.");
			}
		});
		
		manageGame.getBtnConfirmarJugadores().addActionListener(e -> {
			if (manageGame.getTxtNumJugadores().getText().isEmpty()) return;
			game.setNumPlayers(Integer.parseInt(manageGame.getTxtNumJugadores().getText()));
			JOptionPane.showMessageDialog(null, "Número de jugadores cambiado.");
		});
		
		manageGame.getBtnConfirmarEdad().addActionListener(e -> {
			if (manageGame.getTxtRangoEdad().getText().isEmpty()) return;
			game.setAgeRange(manageGame.getTxtRangoEdad().getText());
			JOptionPane.showMessageDialog(null, "Rango de edad cambiado.");
		});
		
		manageGame.getBtnConfirmarPrecio().addActionListener(e -> {
			if (manageGame.getTxtPrecio().getText().isEmpty()) return;
			game.setPrice(Double.parseDouble(manageGame.getTxtPrecio().getText()));
			JOptionPane.showMessageDialog(null, "Precio cambiado.");
		});
		
		manageGame.getBtnConfirmarCategorias().addActionListener(e -> {
			if (manageGame.getTxtCategorias().getText().isEmpty()) return;
			Category c = Store.getInstance().getCategoryFromName(manageGame.getTxtCategorias().getText());
			if (c == null) {
				JOptionPane.showMessageDialog(null, "Categoría no encontrada.");
				return;
			}
			game.addCategory(c);
			JOptionPane.showMessageDialog(null, "Categoría añadida correctamente.");
		});
	}
}
