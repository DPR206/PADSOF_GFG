package controller.miniControllers;

import controller.browserControllers.BrowseSomeonesWalletC;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseSomeonesWalletP;
import view.clientPanels.RegisteredMainP;
import view.miniPanels.UserMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.*;

public class WalletOwnerMiniC implements ActionListener {
    private final UserMiniP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public WalletOwnerMiniC(App frame, Store model, UserMiniP view) {
        this.frame = frame;
        this.view = view;
        this.model = model;

        view.getUserImage().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Browse Wallet")) {
            try {
                BrowseSomeonesWalletP newView = new BrowseSomeonesWalletP(view.getWalletOwner());
                new BrowseSomeonesWalletC(frame, model, newView);
                ((RegisteredMainP) frame.getViewFromName("REGISTERED_MAIN")).getBottom()
                                                                            .add(newView, "BROWSE_SOMEONES_WALLET");
                ((RegisteredMainP) frame.getViewFromName("REGISTERED_MAIN")).getCardLayout()
                                                                            .show(((RegisteredMainP) frame.getViewFromName(
                                                                                            "REGISTERED_MAIN")).getBottom(),
                                                                                    "BROWSE_SOMEONES_WALLET");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}