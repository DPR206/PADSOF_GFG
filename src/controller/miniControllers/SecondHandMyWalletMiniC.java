package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.AbstractBrowserC;
import controller.clientControllers.RegisteredSecondHandC;
import model.product.SecondHandProduct;
import model.store.Store;
import view.App;
import view.browserPanels.AbstractBrowserP;
import view.clientPanels.RegisteredMainP;
import view.MaxiSecondHandP;
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
     * @param frame             the controller's frame
     * @param model             the controller's model
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
                try {
                    MaxiSecondHandP newView =
                            new MaxiSecondHandP(frame, view.getSecondHandProduct(), "Add to Offer");
                    new RegisteredSecondHandC(frame, model, newView);
                    ((RegisteredMainP) frame.getViewFromName("REGISTERED_MAIN")).getBottom()
                                                                                .add(newView, "SECONDHAND_PRODUCT");
                    ((RegisteredMainP) frame.getViewFromName("REGISTERED_MAIN")).getCardLayout()
                                                                                .show(((RegisteredMainP) frame.getViewFromName(
                                                                                                "REGISTERED_MAIN")).getBottom(),
                                                                                        "SECONDHAND_PRODUCT");
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        view.getProductImage().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    try {
                        MaxiSecondHandP newView =
                                new MaxiSecondHandP(frame, view.getSecondHandProduct(), "Add to Offer");
                        new RegisteredSecondHandC(frame, model, newView);
                        ((RegisteredMainP) frame.getViewFromName("REGISTERED_MAIN")).getBottom()
                                                                                    .add(newView, "SECONDHAND_PRODUCT");
                        ((RegisteredMainP) frame.getViewFromName("REGISTERED_MAIN")).getCardLayout()
                                                                                    .show(((RegisteredMainP) frame.getViewFromName(
                                                                                                    "REGISTERED_MAIN")).getBottom(),
                                                                                            "SECONDHAND_PRODUCT");
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
            try {
                abstractBrowserP.paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            abstractBrowserC.initializeActionsForMiniPanels();
        });

        /* Request valuation */
        view.getSecondButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Valuation requested", "Request valuation",
                    JOptionPane.INFORMATION_MESSAGE);
            // DUE: Pagar
            view.getSecondHandProduct().setPaidValuation(true);
            try {
                abstractBrowserP.paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            abstractBrowserC.initializeActionsForMiniPanels();
        });

        /* Remove from wallet */
        view.getThirdButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Product removed", "Remove from wallet",
                    JOptionPane.INFORMATION_MESSAGE);
            view.getSecondHandProduct().setRemoved(true);
            try {
                abstractBrowserP.paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            abstractBrowserC.initializeActionsForMiniPanels();
        });
    }
}