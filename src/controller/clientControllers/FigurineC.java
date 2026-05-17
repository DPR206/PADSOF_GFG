package controller.clientControllers;

import controller.Controller;
import model.order.Order;
import model.product.Figurine;
import model.product.Pack;
import model.product.StoreProduct;
import model.user.*;
import view.App;
import view.clientPanels.FigurineP;
import view.clientPanels.ReviewP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * The type Figurine c.
 * @author Duna P.R.
 * @version 1.0
 */
public class FigurineC implements Controller {

    private final App frame;
    private final FigurineP view;
    private final Figurine figurine;
    private final RegisteredClient user;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Figurine c.
     * @param frame    the frame
     * @param view     the view
     * @param figurine the figurine
     */
    public FigurineC(App frame, FigurineP view, Figurine figurine) {
        this.frame = frame;
        this.view = view;
        this.figurine = figurine;
        this.user = (RegisteredClient) frame.getUser();

        initializeActions();
    }

    @Override
    public void initializeActions() {

        this.view.setName(figurine.getName());
        this.view.setBrand(figurine.getBrand());
        this.view.setMaterial(figurine.getMaterial());
        this.view.setDimensions(figurine.getDimension());
        this.view.setCategories(figurine.getPrintCategories());

        this.view.setPrice(figurine.getPrice());
        this.view.setDescriptionText(figurine.getDescription());
        this.view.setStock(figurine.getStock());
        this.view.setRating(figurine.getAveragePunctuation());

        this.view.setImage(figurine.getPhoto());
        this.view.setValoraciones(figurine.getReviewsList());
        this.view.setMaxStock(figurine.getStock());

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
                                                    .addProductUds(figurine, (int) view.getUnitSpinner().getValue());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).getCart()
                                                      .addProductUds(figurine, (int) view.getUnitSpinner().getValue());
            }
            JOptionPane.showMessageDialog(frame, figurine.getName() + " was added to Cart", "Added To Cart",
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
        
        ReviewC controladorReview = new ReviewC(vistaReview, figurine, user);
        
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
            if (order.getP() != null) {
                for (Pack pack : order.getP()) {
                    if (checkPacks(pack)) return true;    
                }
            }
            
            if (order.getSp() != null) {
                for (StoreProduct sp : order.getSp()) {
                    if (sp.equals(figurine)) return true;
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
	            if (sp.equals(figurine)) return true;
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
        FigurineP figurineVista = new FigurineP();
        new FigurineC(frame, figurineVista, figurine);
        frame.addCard(figurineVista, "FIGURINE");
        frame.changeVisibleCard("FIGURINE");
    }

}