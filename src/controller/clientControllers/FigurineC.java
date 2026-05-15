package controller.clientControllers;

import javax.swing.JOptionPane;

import controller.Controller;
import model.product.Figurine;
import model.user.RegisteredClient;
import model.user.UnregisteredClient;
import model.user.UserType;
import view.App;
import view.clientPanels.FigurineP;

public class FigurineC implements Controller {

	private App frame;
    private FigurineP view;
    private Figurine figurine; 

	/**
	 * @param frame
	 * @param view
	 * @param comic
	 */
	public FigurineC(App frame, FigurineP view, Figurine figurine) {
		this.frame = frame;
		this.view = view;
		this.figurine = figurine;
		
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
	    	frame.goBack();
	    });
	    
	    this.view.getBtnaddCart().addActionListener(e -> {
	    	if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).getC().addProductUds(figurine, (int) view.getUnitSpinner().getValue() );
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).getCart().addProductUds(figurine, (int) view.getUnitSpinner().getValue() );
            }
            JOptionPane.showMessageDialog(frame, figurine.getName() + " was added to Cart",
                    "Added To Cart", JOptionPane.INFORMATION_MESSAGE);
            updateInterface();
	    });
	}
	
	public void updateInterface() {
        FigurineP figurineVista = new FigurineP();
		new FigurineC(frame, figurineVista, figurine);
		frame.addCard(figurineVista, "FIGURINE");
		frame.changeVisibleCard("FIGURINE");
    }

}
