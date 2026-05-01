package controller.miniControllers;

import controller.BigController;
import model.store.Store;
import model.user.*;
import view.App;
import view.BigView;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreProductMiniC implements ActionListener {
    private final StoreProductMiniP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final BigController bigController;
    private final BigView bigView;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public StoreProductMiniC(App frame, Store model, StoreProductMiniP view, BigController bigController,
                             BigView bigView) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.bigController = bigController;
        this.bigView = bigView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add to Cart")) {
            if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).addCart(view.getStoreProduct());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).addCart(view.getStoreProduct());
            }
            JOptionPane.showMessageDialog(frame, view.getStoreProduct().getName() + " was added to Cart",
                    "Added To Cart", JOptionPane.INFORMATION_MESSAGE);
            try {
                bigView.paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            bigController.updateControllers();
        }

    }
}