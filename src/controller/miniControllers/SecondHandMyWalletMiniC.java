package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.AbstractBrowserC;
import controller.clientControllers.CartPaymentC;
import controller.clientControllers.SecondHandOthersC;
import controller.clientControllers.SecondHandOwnerC;
import controller.maxiPanels.MaxiSecondHandAddToOfferC;
import es.uam.eps.padsof.telecard.FailedInternetConnectionException;
import es.uam.eps.padsof.telecard.InvalidCardNumberException;
import es.uam.eps.padsof.telecard.OrderRejectedException;
import model.product.SecondHandProduct;
import model.store.Parameter;
import model.store.Store;
import view.App;
import view.browserPanels.AbstractBrowserP;
import view.clientPanels.PaymentP;
import view.clientPanels.RegisteredMainP;
import view.clientPanels.SecondHandOthersP;
import view.clientPanels.SecondHandOwnerP;
import view.maxiPanels.MaxiSecondHandP;
import view.miniPanels.ThreeButtonSecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SecondHandMyWalletMiniC implements Controller {
    private final ThreeButtonSecondHandMiniP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final AbstractBrowserC<SecondHandProduct> abstractBrowserC;
    private final AbstractBrowserP<SecondHandProduct> abstractBrowserP;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame            the controller's frame
     * @param model            the controller's model
     * @param view
     * @param abstractBrowserC
     * @param abstractBrowserP
     */
    public SecondHandMyWalletMiniC(App frame, Store model, ThreeButtonSecondHandMiniP view,
                                   AbstractBrowserC<SecondHandProduct> abstractBrowserC,
                                   AbstractBrowserP<SecondHandProduct> abstractBrowserP) {
        this.frame = frame;
        this.model = model;
        this.view = view;
        this.abstractBrowserC = abstractBrowserC;
        this.abstractBrowserP = abstractBrowserP;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.setFocusable(true);
        view.setCursor(new Cursor(Cursor.HAND_CURSOR));
        view.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	if (e.getClickCount() == 2) {
            		SecondHandOwnerP shView = new SecondHandOwnerP();
                    new SecondHandOwnerC(frame, shView, view.getSecondHandProduct());
                    frame.addCard(shView, "SECONDHAND_OWNER");
            		frame.changeVisibleCard("SECONDHAND_OWNER");
                }
            }
        });

        view.getProductImage().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                	 SecondHandOwnerP shView = new SecondHandOwnerP();
                     new SecondHandOwnerC(frame, shView, view.getSecondHandProduct());
                     frame.addCard(shView, "SECONDHAND_OWNER");
             		 frame.changeVisibleCard("SECONDHAND_OWNER");
                }
            }
        });
        
        view.getProductInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                	SecondHandOwnerP shView = new SecondHandOwnerP();
                    new SecondHandOwnerC(frame, shView, view.getSecondHandProduct());
                    frame.addCard(shView, "SECONDHAND_OWNER");
            		frame.changeVisibleCard("SECONDHAND_OWNER");
                }
            }
        });


        /* Add to Offer */
        view.getFirstButton().addActionListener(e -> {
            // DUE: Add to Offer
            JOptionPane.showMessageDialog(frame, view.getSecondHandProduct().getName() + " was added to the Offer",
                    "Added To Offer", JOptionPane.INFORMATION_MESSAGE);
            updateInterface();
        });

        /* Request valuation */
        view.getSecondButton().addActionListener(e -> {
        	PaymentP payment = new PaymentP(frame, Store.getInstance().getParameters().getValuationCost());
	    	
	    	String tarjeta = payment.getNumeroTarjeta();
	    	if (tarjeta == null) return;

            try {
                view.getSecondHandProduct().payValuation(tarjeta);
                JOptionPane.showMessageDialog(view, "Payment successful!");
                payment.dispose();
                updateInterface();
            } catch (InvalidCardNumberException e1) {
                JOptionPane.showMessageDialog(view, "Invalid card number", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (FailedInternetConnectionException e1) {
                JOptionPane.showMessageDialog(view, "Failed Internet connection", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (OrderRejectedException e1) {
                JOptionPane.showMessageDialog(view, "Order rejected", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        /* Remove from wallet */
        view.getThirdButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Product removed", "Remove from wallet",
                    JOptionPane.INFORMATION_MESSAGE);
            view.getSecondHandProduct().setRemoved(true);
            updateInterface();
        });
    }

    private void updateInterface() {
        try {
            abstractBrowserP.paintEverything();
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
        abstractBrowserC.initializeActionsForMiniPanels();
    }
}