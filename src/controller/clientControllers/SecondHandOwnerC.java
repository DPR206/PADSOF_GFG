package controller.clientControllers;

import javax.swing.JOptionPane;

import controller.Controller;
import es.uam.eps.padsof.telecard.*;
import model.product.SecondHandProduct;
import model.store.Store;
import view.App;
import view.clientPanels.PaymentP;
import view.clientPanels.SecondHandOwnerP;

public class SecondHandOwnerC implements Controller {
	
	private App frame;
    private SecondHandOwnerP view;
    private SecondHandProduct product; 

	/**
	 * @param frame
	 * @param view
	 * @param comic
	 */
	public SecondHandOwnerC(App frame, SecondHandOwnerP view, SecondHandProduct product) {
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
	    
	    if(product.isPaidValuation()) {
	    	this.view.setValuation(product.getPrice(), product.getStatus());
	    	this.view.getBtnValorationt().setVisible(false);
	    }
	    else {
	        this.view.setValuation("Pending payment");
	        this.view.getBtnValorationt().setVisible(true); 
	    }
	    	
	    
		
	    this.view.getBtnReturn().addActionListener(e -> {
	    	frame.goBack();
	    });
	    
	    
	    this.view.getBtnValorationt().addActionListener(e -> {
	    	
	    	PaymentP payment = new PaymentP(frame, Store.getInstance().getParameters().getValuationCost());
	    	
	    	payment.getBtnCancelar().addActionListener(c -> {
	    		payment.dispose();
	    	});
	    	
	    	payment.getBtnConfirmar().addActionListener(p -> {
		    	
		    	String tarjeta = payment.getNumeroTarjeta();
		    	if (tarjeta == null || tarjeta.trim().isEmpty()) {
	                JOptionPane.showMessageDialog(payment, "Please enter a card number");
	                return;
	            }

	            try {
	                product.payValuation(tarjeta);
	                JOptionPane.showMessageDialog(view, "Payment successful!");
	                payment.dispose();
	                actualizarEstadoPago();
	            } catch (InvalidCardNumberException e1) {
	                JOptionPane.showMessageDialog(view, "Invalid card number", "Error", JOptionPane.ERROR_MESSAGE);
	            } catch (FailedInternetConnectionException e1) {
	                JOptionPane.showMessageDialog(view, "Failed Internet connection", "Error", JOptionPane.ERROR_MESSAGE);
	            } catch (OrderRejectedException e1) {
	                JOptionPane.showMessageDialog(view, "Order rejected", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	            
		    	
	    	});
	    	
	    	payment.setVisible(true);
	    });
	    
	    view.getBtnDelete().addActionListener(e -> {
	    	JOptionPane.showMessageDialog(frame, "Product removed", "Remove from wallet",
                    JOptionPane.INFORMATION_MESSAGE);
            product.setRemoved(true);
            frame.goBack();
	    });
	}
	

	private void actualizarEstadoPago() {
	    if (product.isPaidValuation()) {
	        this.view.setValuation(product.getPrice(), product.getStatus());
	        this.view.getBtnValorationt().setVisible(false);
	    } else {
	        this.view.setValuation("Pending payment");
	        this.view.getBtnValorationt().setVisible(true);
	    }

	    SecondHandOwnerP SHVista = new SecondHandOwnerP();
		new SecondHandOwnerC(frame, SHVista, product);
		frame.addCard(SHVista, "SECOND_HAND_OWNER");
		frame.changeVisibleCard("SECOND_HAND_OWNER");
	}
}
