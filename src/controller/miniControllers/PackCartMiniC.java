package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.MixedBrowseCartC;
import controller.clientControllers.CarritoC;
import controller.clientControllers.PackC;
import model.store.Store;
import model.user.*;
import view.App;
import view.clientPanels.CarritoP;
import view.clientPanels.PackP;
import view.miniPanels.PackCartMiniP;

import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Pack cart mini c.
 * @author Ana O.R.
 * @version 1.0
 */
public class PackCartMiniC implements Controller {

    private final PackCartMiniP view;
    private final App frame;
    private final Store model;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Pack cart mini c.
     * @param frame the frame
     * @param model the model
     * @param view  the view
     */
    public PackCartMiniC(App frame, Store model, PackCartMiniP view) {
        this.frame = frame;
        this.view = view;
        this.model = model;

        initializeActions();
    }

    /**
     * Update interface.
     */
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

        view.getPackImage().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    PackP packV = new PackP();
                    new PackC(frame, packV, view.getPack());
                    frame.addCard(packV, "PACK_V");
                    try {
                        frame.changeVisibleCard("PACK_V");
                    } catch (BadLocationException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        view.getPackInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    PackP packV = new PackP();
                    new PackC(frame, packV, view.getPack());
                    frame.addCard(packV, "PACK_V");
                    try {
                        frame.changeVisibleCard("PACK_V");
                    } catch (BadLocationException ex) {
                        throw new RuntimeException(ex);
                    }
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
        });
    }
}