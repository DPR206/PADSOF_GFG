package controller.clientControllers;

import javax.swing.JOptionPane;

import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import model.user.RegisteredClient;
import view.App;
import view.clientPanels.PaymentP;

public class PaymentC {
	
	private App frame;
	private PaymentP view;
	private RegisteredClient user;
	
	/**
	 * @param frame
	 * @param view
	 */
	public PaymentC(App frame, PaymentP view) {
		this.frame = frame;
		this.view = view;
		this.user = (RegisteredClient) frame.getUser();
		
		inicializarEventos();
	}

	private void inicializarEventos() {

	    // 2. Asignar eventos
	    view.getBtnCancelar().addActionListener(e -> view.dispose());

	    view.getBtnConfirmar().addActionListener(e -> {
	        String tarjeta = view.getNumeroTarjeta();
	        
	        try {
				user.getC().payOrder(tarjeta);
				JOptionPane.showMessageDialog(view, "Payment successful!");
				view.dispose();
	            user.getC().emptyCart();
			} catch (InvalidCardNumberException e1) {
				JOptionPane.showMessageDialog(view, "Invalid card number", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (FailedInternetConnectionException e1) {
				JOptionPane.showMessageDialog(view, "Failed Internet connection", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (OrderRejectedException e1) {
				JOptionPane.showMessageDialog(view, "Order rejected", "Error", JOptionPane.ERROR_MESSAGE);
			}
	        
	        
	    });

	    frame.changeVisibleCard("CART");
		
	}
	
	

}
