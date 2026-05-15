package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.AbstractBrowserC;
import controller.clientControllers.SecondHandOthersC;
import controller.clientControllers.SecondHandOwnerC;
import controller.maxiPanels.MaxiSecondHandAddToOfferC;
import model.product.SecondHandProduct;
import model.store.Store;
import view.App;
import view.browserPanels.AbstractBrowserP;
import view.clientPanels.RegisteredMainP;
import view.clientPanels.SecondHandOthersP;
import view.clientPanels.SecondHandOwnerP;
import view.maxiPanels.MaxiSecondHandP;
import view.miniPanels.SecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SecondHandAddToOfferMiniC implements Controller {
    private final SecondHandMiniP view; /* view -> panel */
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
    public SecondHandAddToOfferMiniC(App frame, Store model, SecondHandMiniP view,
                                     AbstractBrowserC<SecondHandProduct> abstractBrowserC,
                                     AbstractBrowserP<SecondHandProduct> abstractBrowserP) {
        this.frame = frame;
        this.view = view;
        this.model = model;
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
                	SecondHandOthersP shView = new SecondHandOthersP();
                    new SecondHandOthersC(frame, shView, view.getSecondHandProduct());
                    frame.addCard(shView, "SECONDHAND_OTHER");
            		frame.changeVisibleCard("SECONDHAND_OTHER");
                }
            }
        });

        view.getProductImage().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                	SecondHandOthersP shView = new SecondHandOthersP();
                    new SecondHandOthersC(frame, shView, view.getSecondHandProduct());
                    frame.addCard(shView, "SECONDHAND_OTHER");
            		frame.changeVisibleCard("SECONDHAND_OTHER");
                }
            }
        });

        view.getProductInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                	SecondHandOthersP shView = new SecondHandOthersP();
                    new SecondHandOthersC(frame, shView, view.getSecondHandProduct());
                    frame.addCard(shView, "SECONDHAND_OTHER");
            		frame.changeVisibleCard("SECONDHAND_OTHER");
                }
            }
        });

        view.getButton().addActionListener(e -> {
            //DUE: Aceptar oferta
            JOptionPane.showMessageDialog(frame,
                    view.getSecondHandProduct().getName() + " was " + "added to " + "the Offer", "Added To Offer",
                    JOptionPane.INFORMATION_MESSAGE);
            try {
                abstractBrowserP.paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            abstractBrowserC.initializeActionsForMiniPanels();
        });
    }
}