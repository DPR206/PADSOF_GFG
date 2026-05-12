package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.BrowseCartC;
import controller.browserControllers.MixedBrowserController;
import controller.clientControllers.CarritoC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.*;
import view.App;
import view.browserPanels.MixedBrowserPanel;
import view.clientPanels.CarritoP;
import view.miniPanels.StoreProductMiniCart;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StoreProductMiniCartC implements Controller {
    private final StoreProductMiniCart view; /* view -> panel */
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
    public StoreProductMiniCartC(App frame, Store model, StoreProductMiniCart view,
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
//        if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
//            view.setCart(((RegisteredClient) frame.getUser()).getC());
//        } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
//            view.setCart(((UnregisteredClient) frame.getUser()).getCart());
//        }
//        try {
//            browserPanel.paintEverything();
//        } catch (BadLocationException ex) {
//            throw new RuntimeException(ex);
//        }
//        browserController.initializeActionsForMiniPanels();

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

        view.getDeleteFromCart().addActionListener(e -> {
            if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).deleteCart(view.getStoreProduct());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).deleteCart(view.getStoreProduct());
            }
            try {
                browserPanel.paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            browserController.initializeActionsForMiniPanels();
        });

        view.getApplyChanges().addActionListener(e -> {
            for (int i = 0; i < (int) view.getUnitSpinner().getValue(); i++) {
                if (view.getStoreProduct().getStock() != 0) {
                    if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {

                        ((RegisteredClient) frame.getUser()).getC().addProduct(view.getStoreProduct());
                    } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                        ((UnregisteredClient) frame.getUser()).getCart().addProduct(view.getStoreProduct());
                    }
                }
            }
            CarritoP carritoVista;
            try {
                carritoVista = new CarritoP();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }

            new CarritoC(carritoVista, frame);
            try {
                new BrowseCartC(frame, model, carritoVista.getCartItems());
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }

            frame.addCard(carritoVista, "CART");
            frame.changeVisibleCard("CART");
//            try {
//                browserPanel.paintEverything();
//            } catch (BadLocationException ex) {
//                throw new RuntimeException(ex);
//            }
//            browserController.initializeActionsForMiniPanels();
        });
    }
}