package controller.miniControllers;

import controller.Controller;
import controller.clientControllers.RegisteredMakeOfferC;
import controller.clientControllers.SecondHandOthersC;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.clientPanels.RegisteredMakeOfferP;
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
    private final Store model;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the model
     * @param view  the view
     */
    public SecondHandAddToOfferMiniC(App frame, Store model, SecondHandMiniP view) {
        this.frame = frame;
        this.view = view;
        this.model = model;

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
            JOptionPane.showMessageDialog(frame,
                    view.getSecondHandProduct().getName() + " was " + "added to " + "the Offer", "Added To Offer",
                    JOptionPane.INFORMATION_MESSAGE);

            try {
                RegisteredMakeOfferP registeredMakeOfferP =
                        new RegisteredMakeOfferP(frame, view.getSecondHandProduct().getOwner(),
                                (RegisteredClient) frame.getUser());
                RegisteredMakeOfferC controller = new RegisteredMakeOfferC(frame, model, registeredMakeOfferP,
                        view.getSecondHandProduct().getOwner());

                if (view.getSecondHandProduct().getOwner() == frame.getUser()) {
                    controller.addProductFromMyWallet(view.getSecondHandProduct());
                } else {
                    controller.addProductFromTheirWallet(view.getSecondHandProduct());
                }

                frame.addCard(registeredMakeOfferP, "MAKE_OFFER");
                frame.changeVisibleCard("MAKE_OFFER");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}