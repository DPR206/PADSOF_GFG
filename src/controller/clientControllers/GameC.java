package controller.clientControllers;

import controller.Controller;
import model.order.Order;
import model.order.OrderState;
import model.product.Game;
import model.product.Pack;
import model.product.StoreProduct;
import model.user.*;
import view.App;
import view.clientPanels.GameP;
import view.clientPanels.ReviewP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * The type Game c.
 * @author Duna P.R.
 * @version 1.0
 */
public class GameC implements Controller {

    private final App frame;
    private final GameP view;
    private final Game game;
    private final RegisteredClient user;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Game c.
     * @param frame the frame
     * @param view  the view
     * @param game  the game
     */
    public GameC(App frame, GameP view, Game game) {
        this.frame = frame;
        this.view = view;
        this.game = game;
        this.user = (RegisteredClient) frame.getUser();

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
            try {
                frame.goBack();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        this.view.getBtnaddCart().addActionListener(e -> {
            if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).getC().addProductUds(game, (int) view.getUnitSpinner().getValue());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).getCart()
                                                      .addProductUds(game, (int) view.getUnitSpinner().getValue());
            }
            JOptionPane.showMessageDialog(frame, game.getName() + " was added to Cart", "Added To Cart",
                    JOptionPane.INFORMATION_MESSAGE);
            try {
                updateInterface();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
        
        this.view.getBtnaddReview().addActionListener(e -> {
        	boolean hasPurchased = userHasPurchased();

        	this.view.getBtnaddReview().setVisible(hasPurchased);
        	
        	if (hasPurchased) {
        	    this.view.getBtnaddReview().addActionListener(r -> {
        	        openReviewDialog(); 
        	        try {
						updateInterface();
					} catch (BadLocationException e1) {
						throw new RuntimeException(e1);
					}
        	    });
        	}
        		
        });
    }
    
    /**
     * Opens the review section
     */
    private void openReviewDialog() {
    	ReviewP vistaReview = new ReviewP();
        
        ReviewC controladorReview = new ReviewC(vistaReview, game, user);
        
        vistaReview.setVisible(true);
	}

	/**
     * Check if the user has purchased the product
     * @return true if purchased, false if else
     */
    private boolean userHasPurchased() {
    	
    	if (user == null || user.getOrderHistory() == null || user.getOrderHistory().getOrders() == null) {
            return false;
        }
    	
    	for (Order order : user.getOrderHistory().getOrders()) {
    		
    		if(order.getState() != OrderState.PICKED_UP)
    			return false;
    		
            if (order.getP() != null) {
                for (Pack pack : order.getP()) {
                    if (checkPacks(pack)) return true;    
                }
            }
            
            if (order.getSp() != null) {
                for (StoreProduct sp : order.getSp()) {
                    if (sp.equals(game)) return true;
                }
            }
        }
        return false;
	}

	/**
	 * Checks the pack to look for the product
	 * @param pack the pack to check
	 */
	private boolean checkPacks(Pack pack) {
		if (pack == null) return false;

	    if (pack.getProducts() != null) {
	        for (StoreProduct sp : pack.getProducts()) {
	            if (sp.equals(game)) return true;
	        }
	    }
	    
	    if (pack.getPacks() != null) {
	        for (Pack p : pack.getPacks()) {
	            if (checkPacks(p)) return true;
	        }
	    }
	    
	    return false;
	}

    /**
     * Update interface.
     */
    public void updateInterface() throws BadLocationException {
        GameP gameVista = new GameP();
        new GameC(frame, gameVista, game);
        frame.addCard(gameVista, "GAME");
        frame.changeVisibleCard("GAME");
    }
}