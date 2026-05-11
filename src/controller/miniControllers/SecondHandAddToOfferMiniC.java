package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.BrowserController;
import controller.clientControllers.RegisteredSecondHandC;
import model.product.SecondHandProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowserPanel;
import view.clientPanels.RegisteredMainP;
import view.MaxiSecondHandP;
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
    private final BrowserController<SecondHandProduct> browserController;
    private final BrowserPanel<SecondHandProduct> browserPanel;
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame             the controller's frame
     * @param model             the controller's model
     * @param view
     * @param browserController
     * @param browserPanel
     */
    public SecondHandAddToOfferMiniC(App frame, Store model, SecondHandMiniP view,
                                     BrowserController<SecondHandProduct> browserController,
                                     BrowserPanel<SecondHandProduct> browserPanel) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.browserController = browserController;
        this.browserPanel = browserPanel;

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

        view.getProductInfo().addMouseListener(new MouseAdapter() {
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

        view.getButton().addActionListener(e -> {
            //DUE: Aceptar oferta
            JOptionPane.showMessageDialog(frame,
                    view.getSecondHandProduct().getName() + " was " + "added to " + "the Offer", "Added To Offer",
                    JOptionPane.INFORMATION_MESSAGE);
            try {
                browserPanel.paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            browserController.initializeActionsForMiniPanels();
        });
    }
}