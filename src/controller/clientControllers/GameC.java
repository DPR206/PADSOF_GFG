package controller.clientControllers;

import javax.swing.JOptionPane;

import controller.Controller;
import model.product.Game;
import model.user.RegisteredClient;
import model.user.UnregisteredClient;
import model.user.UserType;
import view.App;
import view.clientPanels.GameP;

public class GameC implements Controller{

	private App frame;
    private GameP view;
    private Game game; 

	/**
	 * @param frame
	 * @param view
	 * @param comic
	 */
	public GameC(App frame, GameP view, Game game) {
		this.frame = frame;
		this.view = view;
		this.game = game;
		
		initializeActions();
	}
	
	@Override
	public void initializeActions() {
		
		this.view.setName(game.getName());
		this.view.setGameType(game.getGameStyle().toString());
		this.view.setPlayers(game.getNumPlayers());
	    this.view.setAge(game.getAgeRange()); 
	    this.view.setCategories(game.getPrintCategories());
	    
	    this.view.setPrice(game.getPrice());
	    this.view.setDescriptionText(game.getDescription());
	    this.view.setStock(game.getStock()); 
	    this.view.setRating(game.getAveragePunctuation()); 
	    
	    this.view.setImage(game.getPhoto());
	    this.view.setValoraciones(game.getReviewsList());
	    this.view.setMaxStock(game.getStock());
		
	    this.view.getBtnReturn().addActionListener(e -> {
	    	frame.goBack();
	    });
	    
	    this.view.getBtnaddCart().addActionListener(e -> {
	    	if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).getC().addProductUds(game, (int) view.getUnitSpinner().getValue() );
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).getCart().addProductUds(game, (int) view.getUnitSpinner().getValue() );
            }
            JOptionPane.showMessageDialog(frame, game.getName() + " was added to Cart",
                    "Added To Cart", JOptionPane.INFORMATION_MESSAGE);
            updateInterface();
	    });
	}
	
	public void updateInterface() {
        GameP gameVista = new GameP();
		new GameC(frame, gameVista, game);
		frame.addCard(gameVista, "GAME");
		frame.changeVisibleCard("GAME");
    }
}
