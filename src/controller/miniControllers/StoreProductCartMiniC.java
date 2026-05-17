package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.MixedBrowseCartC;
import controller.clientControllers.*;
import model.product.*;
import model.store.Store;
import model.user.*;
import view.App;
import view.clientPanels.*;
import view.miniPanels.StoreProductMiniCart;

import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Store product cart mini c.
 * @author Duna P.R. & Ana O.R.
 * @version 1.0
 */
public class StoreProductCartMiniC implements Controller {
    private final StoreProductMiniCart view;
    private final App frame;
    private final Store model;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     * @param view  the view
     */
    public StoreProductCartMiniC(App frame, Store model, StoreProductMiniCart view) {
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

        view.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    switch (view.getStoreProduct().getType()) {
                        case ProductType.COMIC:
                            ComicP comicV = new ComicP();
                            new ComicC(frame, comicV, (Comic) view.getStoreProduct());
                            frame.addCard(comicV, "COMIC_VIEW");
                            try {
                                frame.changeVisibleCard("COMIC_VIEW");
                            } catch (BadLocationException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        case ProductType.GAME:
                            GameP gameV = new GameP();
                            new GameC(frame, gameV, (Game) view.getStoreProduct());
                            frame.addCard(gameV, "GAME_VIEW");
                            try {
                                frame.changeVisibleCard("GAME_VIEW");
                            } catch (BadLocationException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        case ProductType.FIGURINE:
                            FigurineP figurineV = new FigurineP();
                            new FigurineC(frame, figurineV, (Figurine) view.getStoreProduct());
                            frame.addCard(figurineV, "FIGURINE_VIEW");
                            try {
                                frame.changeVisibleCard("FIGURINE_VIEW");
                            } catch (BadLocationException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        view.getProductInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    switch (view.getStoreProduct().getType()) {
                        case ProductType.COMIC:
                            ComicP comicV = new ComicP();
                            new ComicC(frame, comicV, (Comic) view.getStoreProduct());
                            frame.addCard(comicV, "COMIC_VIEW");
                            try {
                                frame.changeVisibleCard("COMIC_VIEW");
                            } catch (BadLocationException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        case ProductType.GAME:
                            GameP gameV = new GameP();
                            new GameC(frame, gameV, (Game) view.getStoreProduct());
                            frame.addCard(gameV, "GAME_VIEW");
                            try {
                                frame.changeVisibleCard("GAME_VIEW");
                            } catch (BadLocationException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        case ProductType.FIGURINE:
                            FigurineP figurineV = new FigurineP();
                            new FigurineC(frame, figurineV, (Figurine) view.getStoreProduct());
                            frame.addCard(figurineV, "FIGURINE_VIEW");
                            try {
                                frame.changeVisibleCard("FIGURINE_VIEW");
                            } catch (BadLocationException ex) {
                                throw new RuntimeException(ex);
                            }
                            break;
                        default:
                            break;
                    }
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
        });
    }
}