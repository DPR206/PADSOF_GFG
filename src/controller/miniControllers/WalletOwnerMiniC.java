package controller.miniControllers;

import controller.browserControllers.BrowseSomeonesWalletC;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseSomeonesWalletP;
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
                BrowseSomeonesWalletP newView = new BrowseSomeonesWalletP(frame, view.getWalletOwner());
                BrowseSomeonesWalletC controller = new BrowseSomeonesWalletC(frame, model, newView);
                //frame.addCard(newView, "BROWSE_SOMEONES_WALLET", controller);
                //frame.changeVisibleCard("BROWSE_SOMEONES_WALLET");
                // DUE
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}