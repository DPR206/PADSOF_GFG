package controller.clientControllers;


import controller.Controller;
import model.product.SecondHandProduct;
import view.App;
import view.clientPanels.SecondHandOthersP;

public class SecondHandOthersC implements Controller{
	
	private App frame;
    private SecondHandOthersP view;
    private SecondHandProduct product; 

	/**
	 * @param frame
	 * @param view
	 * @param comic
	 */
	public SecondHandOthersC(App frame, SecondHandOthersP view, SecondHandProduct product) {
		this.frame = frame;
		this.view = view;
		this.product = product;
		
		initializeActions();
	}
	
	@Override
	public void initializeActions() {
		
		this.view.setName(product.getName());
	    this.view.setImage(product.getPhoto());
	    this.view.setDescriptionText(product.getDescription());
	    this.view.setValuation(product.getPrice(), product.getStatus());
	    
	    if(product.isAvailable()) {
	    	this.view.getBtnOffer().setVisible(true);
	    }
	    else {
	        this.view.getBtnOffer().setVisible(false); 
	    }
		
	    this.view.getBtnReturn().addActionListener(e -> {
	    	frame.goBack();
	    });
	    

	    this.view.getBtnOffer().addActionListener(e -> {
	    	//aquí se abre offer
	    });
	}

}
