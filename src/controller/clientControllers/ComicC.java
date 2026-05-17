package controller.clientControllers;

import controller.Controller;
import model.order.Order;
import model.order.OrderState;
import model.product.Comic;
import model.product.Pack;
import model.product.StoreProduct;
import model.user.*;
import view.App;
import view.clientPanels.ComicP;
import view.clientPanels.ReviewP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * The type Comic c.
 * @author Duna P.R.
 * @version 1.0
 */
public class ComicC implements Controller {

    private final App frame;
    private final ComicP view;
    private final Comic comic;
    private final RegisteredClient user;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Comic c.
     * @param frame the frame
     * @param view  the view
     * @param comic the comic
     */
    public ComicC(App frame, ComicP view, Comic comic) {
        this.frame = frame;
        this.view = view;
        this.comic = comic;
        this.user = (RegisteredClient) frame.getUser();
        initializeActions();
    }

    @Override
    public void initializeActions() {

        this.view.setName(comic.getName());
        this.view.setAuthor(comic.getAuthor());
        this.view.setEditorial(comic.getEditorial());
        this.view.setYear(String.valueOf(comic.getYear()));
        this.view.setPages(comic.getNumPages());
        this.view.setCategories(comic.getPrintCategories());

        this.view.setPrice(comic.getPrice());
        this.view.setDescriptionText(comic.getDescription());
        this.view.setStock(comic.getStock());
        this.view.setRating(comic.getAveragePunctuation());

        this.view.setImage(comic.getPhoto());
        this.view.setValoraciones(comic.getReviewsList());
        this.view.setMaxStock(comic.getStock());

        this.view.getBtnReturn().addActionListener(e -> {
            try {
                frame.goBack();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        this.view.getBtnaddCart().addActionListener(e -> {
            if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).getC()
                                                    .addProductUds(comic, (int) view.getUnitSpinner().getValue());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).getCart()
                                                      .addProductUds(comic, (int) view.getUnitSpinner().getValue());
            }
            JOptionPane.showMessageDialog(frame, comic.getName() + " was added to Cart", "Added To Cart",
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
        
        ReviewC controladorReview = new ReviewC(vistaReview, comic, user);
        
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
                    if (sp.equals(comic)) return true;
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
	            if (sp.equals(comic)) return true;
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
        ComicP comicVista = new ComicP();
        new ComicC(frame, comicVista, comic);
        frame.addCard(comicVista, "COMIC");
        frame.changeVisibleCard("COMIC");
    }

}