package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.AbstractMixedBrowserC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.*;
import view.App;
import view.browserPanels.AbstractMixedBrowserP;
import view.miniPanels.PackToBuyMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PackAddToCartMiniC implements Controller {

    private final PackToBuyMiniP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final AbstractMixedBrowserC<Pack, StoreProduct> browserController;
    private final AbstractMixedBrowserP<Pack, StoreProduct> browserPanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public PackAddToCartMiniC(App frame, Store model, PackToBuyMiniP view,
                              AbstractMixedBrowserC<Pack, StoreProduct> browserController,
                              AbstractMixedBrowserP<Pack, StoreProduct> browserPanel) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.browserController = browserController;
        this.browserPanel = browserPanel;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.getPackImage().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
                }
            }
        });

        view.getPackInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
                }
            }
        });

        view.getButton().addActionListener(e -> {
            if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).addCart(view.getPack());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).addCart(view.getPack());
            }
            JOptionPane.showMessageDialog(frame, view.getPack().getId() + " was added to Cart", "Added To Cart",
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