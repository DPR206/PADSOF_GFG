package controller.miniControllers;

import controller.browserControllers.BrowserController;
import controller.clientControllers.RegisteredSecondHandC;
import model.product.SecondHandProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowserPanel;
import view.clientPanels.RegisteredMainP;
import view.clientPanels.RegisteredSecondHandP;
import view.miniPanels.SecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SecondHandMyWalletMiniC extends SecondHandMiniC {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame             the controller's frame
     * @param model             the controller's model
     * @param view
     * @param browserController
     * @param browserPanel
     */
    public SecondHandMyWalletMiniC(App frame, Store model, SecondHandMiniP view,
                                   BrowserController<SecondHandProduct> browserController,
                                   BrowserPanel<SecondHandProduct> browserPanel) {
        super(frame, model, view, browserController, browserPanel);

        initializeActions();
    }

    @Override
    public void initializeActions() {
        // DUE: Debería ser un panel con opciones de "Valuate", "Add to Offer" (quizás) y "Remove from wallet"
        App frame = super.getFrame();
        Store model = super.getModel();
        SecondHandMiniP view = super.getView();
        view.setFocusable(true);
        view.setCursor(new Cursor(Cursor.HAND_CURSOR));
        view.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    RegisteredSecondHandP newView =
                            new RegisteredSecondHandP(frame, view.getSecondHandProduct(), "Add to Offer");
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
                        RegisteredSecondHandP newView =
                                new RegisteredSecondHandP(frame, view.getSecondHandProduct(), "Add to Offer");
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

        super.getView().getButton().addActionListener(e -> {
            //DUE: Aceptar oferta
            JOptionPane.showMessageDialog(super.getFrame(),
                    super.getView().getSecondHandProduct().getName() + " was " + "added to " + "the Offer",
                    "Added To Offer", JOptionPane.INFORMATION_MESSAGE);
            try {
                super.getBrowserPanel().paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            super.getBrowserController().initializeActionsForMiniPanels();
        });
    }
}