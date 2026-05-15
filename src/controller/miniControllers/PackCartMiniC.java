package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.AbstractMixedBrowserC;
import controller.browserControllers.MixedBrowseCartC;
import controller.clientControllers.CarritoC;
import controller.clientControllers.PackC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.*;
import view.App;
import view.browserPanels.AbstractMixedBrowserP;
import view.clientPanels.CarritoP;
import view.clientPanels.PackP;
import view.miniPanels.PackCartMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PackCartMiniC implements Controller {

    private final PackCartMiniP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final AbstractMixedBrowserC<Pack, StoreProduct> browserController;
    private final AbstractMixedBrowserP<Pack, StoreProduct> browserPanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public PackCartMiniC(App frame, Store model, PackCartMiniP view,
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

        view.getPackImage().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                   PackP packV = new PackP();
                   new PackC(frame, packV, view.getPack());
                   frame.addCard(packV, "PACK_V");
                   frame.changeVisibleCard("PACK_V");
                }
            }
        });

        view.getPackInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                	PackP packV = new PackP();
                    new PackC(frame, packV, view.getPack());
                    frame.addCard(packV, "PACK_V");
                    frame.changeVisibleCard("PACK_V");
                }
            }
        });

        view.getDeleteFromCart().addActionListener(e -> {
            if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).deleteCart(view.getPack());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).deleteCart(view.getPack());
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
                ((RegisteredClient) frame.getUser()).getC().changePackUds(view.getPack(),
                        (int) view.getUnitSpinner().getValue());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).getCart().changePackUds(view.getPack(),
                        (int) view.getUnitSpinner().getValue());
            }
            updateInterface();
//            try {
//                CarritoP carritoVista = new CarritoP();
//                new CarritoC(carritoVista, frame);
//                new MixedBrowseCartC(frame, model, carritoVista.getCartItems());
//                frame.addCard(carritoVista, "CART");
//                frame.changeVisibleCard("CART");
//                frame.getLastShownPanels().removeLast();
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