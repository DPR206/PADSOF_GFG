package controller.miniControllers;

import controller.Controller;
import controller.maxiPanels.MaxiValuateSecondHandC;
import controller.browserControllers.AbstractBrowserC;
import model.product.SecondHandProduct;
import model.store.Store;
import view.App;
import view.browserPanels.AbstractBrowserP;
import view.maxiPanels.MaxiValuateSecondHandP;
import view.miniPanels.SecondHandMiniP;

import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SecondHandValuateMiniC implements Controller {
    private final SecondHandMiniP view; /* view -> panel */
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
    public SecondHandValuateMiniC(App frame, Store model, SecondHandMiniP view,
                                  AbstractBrowserC<SecondHandProduct> abstractBrowserC,
                                  AbstractBrowserP<SecondHandProduct> abstractBrowserP) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.abstractBrowserC = abstractBrowserC;
        this.abstractBrowserP = abstractBrowserP;

        initializeActions();
    }

    private void seeProduct() {
        try {
            MaxiValuateSecondHandP newView = new MaxiValuateSecondHandP(frame, view.getSecondHandProduct());
            new MaxiValuateSecondHandC(frame, model, newView);
            frame.addCard(newView, "VALUATE_SECOND_HAND");
            frame.changeVisibleCard("VALUATE_SECOND_HAND");
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
//        try {
//            browserPanel.paintEverything();
//        } catch (BadLocationException ex) {
//            throw new RuntimeException(ex);
//        }
//        browserController.initializeActionsForMiniPanels();
    }

    @Override
    public void initializeActions() {
        view.setFocusable(true);
        view.setCursor(new Cursor(Cursor.HAND_CURSOR));
        view.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                seeProduct();
            }
        });

        view.getProductImage().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    seeProduct();
                }
            }
        });

        view.getProductInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    seeProduct();
                }
            }
        });

        view.getButton().addActionListener(e -> {
            seeProduct();
        });
    }
}