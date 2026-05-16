package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.AbstractMixedBrowserC;
import controller.clientControllers.*;
import model.product.*;
import model.user.*;
import view.App;
import view.browserPanels.AbstractMixedBrowserP;
import view.clientPanels.*;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Mixed store product mini c.
 * @author Ana O.R.
 * @version 1.0
 */
public class MixedStoreProductMiniC implements Controller {
    private final StoreProductMiniP view;
    private final App frame;
    private final AbstractMixedBrowserC<Pack, StoreProduct> browserController;
    private final AbstractMixedBrowserP<Pack, StoreProduct> browserPanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame             the controller's frame
     * @param view              the view
     * @param browserController the browser controller
     * @param browserPanel      the browser panel
     */
    public MixedStoreProductMiniC(App frame, StoreProductMiniP view,
                                  AbstractMixedBrowserC<Pack, StoreProduct> browserController,
                                  AbstractMixedBrowserP<Pack, StoreProduct> browserPanel) {
        this.frame = frame;
        this.view = view;
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
                    switch (view.getStoreProduct().getType()) {
                        case ProductType.COMIC:
                            ComicP comicV = new ComicP();
                            new ComicC(frame, comicV, (Comic) view.getStoreProduct());
                            frame.addCard(comicV, "COMIC_VIEW");
                            frame.changeVisibleCard("COMIC_VIEW");
                            break;
                        case ProductType.GAME:
                            GameP gameV = new GameP();
                            new GameC(frame, gameV, (Game) view.getStoreProduct());
                            frame.addCard(gameV, "GAME_VIEW");
                            frame.changeVisibleCard("GAME_VIEW");
                            break;
                        case ProductType.FIGURINE:
                            FigurineP figurineV = new FigurineP();
                            new FigurineC(frame, figurineV, (Figurine) view.getStoreProduct());
                            frame.addCard(figurineV, "FIGURINE_VIEW");
                            frame.changeVisibleCard("FIGURINE_VIEW");
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
                            frame.changeVisibleCard("COMIC_VIEW");
                            break;
                        case ProductType.GAME:
                            GameP gameV = new GameP();
                            new GameC(frame, gameV, (Game) view.getStoreProduct());
                            frame.addCard(gameV, "GAME_VIEW");
                            frame.changeVisibleCard("GAME_VIEW");
                            break;
                        case ProductType.FIGURINE:
                            FigurineP figurineV = new FigurineP();
                            new FigurineC(frame, figurineV, (Figurine) view.getStoreProduct());
                            frame.addCard(figurineV, "FIGURINE_VIEW");
                            frame.changeVisibleCard("FIGURINE_VIEW");
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        view.getButton().addActionListener(e -> {
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
            browserController.initializeActionsForMiniPanels();
        });
    }
}