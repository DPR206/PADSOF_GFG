package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.AbstractBrowserC;
import controller.clientControllers.SecondHandOwnerC;
import es.uam.eps.padsof.telecard.*;
import model.product.SecondHandProduct;
import model.store.Store;
import view.App;
import view.browserPanels.AbstractBrowserP;
import view.clientPanels.PaymentP;
import view.clientPanels.SecondHandOwnerP;
import view.miniPanels.ThreeButtonSecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Second hand my wallet mini c.
 * @author Ana O.R.
 * @version 1.0
 */
public class SecondHandMyWalletMiniC implements Controller {
    private final ThreeButtonSecondHandMiniP view;
    private final App frame;
    private final AbstractBrowserC<SecondHandProduct> abstractBrowserC;
    private final AbstractBrowserP<SecondHandProduct> abstractBrowserP;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame            the controller's frame
     * @param view             the view
     * @param abstractBrowserC the abstract browser c
     * @param abstractBrowserP the abstract browser p
     */
    public SecondHandMyWalletMiniC(App frame, ThreeButtonSecondHandMiniP view,
                                   AbstractBrowserC<SecondHandProduct> abstractBrowserC,
                                   AbstractBrowserP<SecondHandProduct> abstractBrowserP) {
        this.frame = frame;
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
                    try {
                        frame.changeVisibleCard("SECONDHAND_OWNER");
                    } catch (BadLocationException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        view.getProductImage().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    SecondHandOwnerP shView = new SecondHandOwnerP();
                    new SecondHandOwnerC(frame, shView, view.getSecondHandProduct());
                    frame.addCard(shView, "SECONDHAND_OWNER");
                    try {
                        frame.changeVisibleCard("SECONDHAND_OWNER");
                    } catch (BadLocationException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        view.getProductInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    SecondHandOwnerP shView = new SecondHandOwnerP();
                    new SecondHandOwnerC(frame, shView, view.getSecondHandProduct());
                    frame.addCard(shView, "SECONDHAND_OWNER");
                    try {
                        frame.changeVisibleCard("SECONDHAND_OWNER");
                    } catch (BadLocationException ex) {
                        throw new RuntimeException(ex);
                    }
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
            if (tarjeta == null) {
                return;
            }

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
        abstractBrowserC.refreshCurrentPage();
        abstractBrowserC.initializeActionsForMiniPanels();
    }
}