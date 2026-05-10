package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.MixedBrowserController;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.*;
import view.App;
import view.browserPanels.MixedBrowserPanel;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StoreProductMiniC implements Controller {
    private final StoreProductMiniP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final MixedBrowserController<Pack, StoreProduct> browserController;
    private final MixedBrowserPanel<Pack, StoreProduct> browserPanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public StoreProductMiniC(App frame, Store model, StoreProductMiniP view,
                             MixedBrowserController<Pack, StoreProduct> browserController,
                             MixedBrowserPanel<Pack, StoreProduct> browserPanel) {
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
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
                }
            }
        });

        view.getProductInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
                }
            }
        });

        view.getAddToCart().addActionListener(e -> {
            System.out.println("AAAAAAAAAAA");
            if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).addCart(view.getStoreProduct());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).addCart(view.getStoreProduct());
            }
            JOptionPane.showMessageDialog(frame, view.getStoreProduct().getName() + " was added to Cart",
                    "Added To Cart", JOptionPane.INFORMATION_MESSAGE);
            try {
                browserPanel.paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            browserController.initializeActions();
        });
    }
}