package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.AbstractBrowserC;
import controller.clientControllers.SecondHandOthersC;
import model.product.SecondHandProduct;
import view.App;
import view.browserPanels.AbstractBrowserP;
import view.clientPanels.SecondHandOthersP;
import view.miniPanels.SecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Second hand add to offer mini c.
 * @author Ana O.R.
 * @version 1.0
 */
public class SecondHandAddToOfferMiniC implements Controller {
    private final SecondHandMiniP view;
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
    public SecondHandAddToOfferMiniC(App frame, SecondHandMiniP view,
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
                    SecondHandOthersP shView = new SecondHandOthersP();
                    new SecondHandOthersC(frame, shView, view.getSecondHandProduct());
                    frame.addCard(shView, "SECONDHAND_OTHER");
                    try {
                        frame.changeVisibleCard("SECONDHAND_OTHER");
                    } catch (BadLocationException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        view.getProductImage().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    SecondHandOthersP shView = new SecondHandOthersP();
                    new SecondHandOthersC(frame, shView, view.getSecondHandProduct());
                    frame.addCard(shView, "SECONDHAND_OTHER");
                    try {
                        frame.changeVisibleCard("SECONDHAND_OTHER");
                    } catch (BadLocationException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        view.getProductInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    SecondHandOthersP shView = new SecondHandOthersP();
                    new SecondHandOthersC(frame, shView, view.getSecondHandProduct());
                    frame.addCard(shView, "SECONDHAND_OTHER");
                    try {
                        frame.changeVisibleCard("SECONDHAND_OTHER");
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

            abstractBrowserC.refreshCurrentPage();
            abstractBrowserC.initializeActionsForMiniPanels();
        });
    }
}