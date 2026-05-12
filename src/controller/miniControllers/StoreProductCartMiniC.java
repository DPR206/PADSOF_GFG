package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.AbstractMixedBrowserC;
import controller.browserControllers.MixedBrowseCartC;
import controller.clientControllers.CarritoC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.*;
import view.App;
import view.browserPanels.AbstractMixedBrowserP;
import view.clientPanels.CarritoP;
import view.miniPanels.StoreProductMiniCart;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StoreProductCartMiniC implements Controller {
    private final StoreProductMiniCart view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final AbstractMixedBrowserC<Pack, StoreProduct> browserController;
    private final AbstractMixedBrowserP<Pack, StoreProduct> browserPanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public StoreProductCartMiniC(App frame, Store model, StoreProductMiniCart view,
                                 AbstractMixedBrowserC<Pack, StoreProduct> browserController,
                                 AbstractMixedBrowserP<Pack, StoreProduct> browserPanel) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.browserController = browserController;
        this.browserPanel = browserPanel;

        initializeActions();
    }

    public void updateInterface() {
        try {
            CarritoP carritoVista = new CarritoP();
            new CarritoC(carritoVista, frame);
            new MixedBrowseCartC(frame, model, carritoVista.getCartItems());
            frame.addCard(carritoVista, "CART");
            frame.changeVisibleCard("CART");
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
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
            updateInterface();
//            try {
//                CarritoP carritoVista = new CarritoP();
//                new CarritoC(carritoVista, frame);
//                new MixedBrowseCartC(frame, Store.getInstance(), carritoVista.getCartItems());
//            } catch (BadLocationException ex) {
//                throw new RuntimeException(ex);
//            }
//            try {
//                browserPanel.paintEverything();
//            } catch (BadLocationException ex) {
//                throw new RuntimeException(ex);
//            }
//            browserController.initializeActionsForMiniPanels();
        });

        view.getApplyChanges().addActionListener(e -> {
            if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).getC().changeProductUds(view.getStoreProduct(),
                        (int) view.getUnitSpinner().getValue());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).getCart().changeProductUds(view.getStoreProduct(),
                        (int) view.getUnitSpinner().getValue());
            }
            updateInterface();
//            try {
//                CarritoP carritoVista;
//                carritoVista = new CarritoP();
//                new CarritoC(carritoVista, frame);
//                new MixedBrowseCartC(frame, model, carritoVista.getCartItems());
//                frame.addCard(carritoVista, "CART");
//                frame.changeVisibleCard("CART");
//            } catch (BadLocationException ex) {
//                throw new RuntimeException(ex);
//            }

//            try {
//                browserPanel.paintEverything();
//            } catch (BadLocationException ex) {
//                throw new RuntimeException(ex);
//            }
//            browserController.initializeActionsForMiniPanels();
        });
    }
}