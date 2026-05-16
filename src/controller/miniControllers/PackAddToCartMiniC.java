package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.AbstractMixedBrowserC;
import controller.clientControllers.PackC;
import model.product.Pack;
import model.product.StoreProduct;
import model.user.*;
import view.App;
import view.browserPanels.AbstractMixedBrowserP;
import view.clientPanels.PackP;
import view.miniPanels.PackToBuyMiniP;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Pack add to cart mini c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class PackAddToCartMiniC implements Controller {

    private final PackToBuyMiniP view;
    private final App frame;
    private final AbstractMixedBrowserC<Pack, StoreProduct> browserController;
    private final AbstractMixedBrowserP<Pack, StoreProduct> browserPanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Pack add to cart mini c.
     * @param frame             the frame
     * @param view              the view
     * @param browserController the browser controller
     * @param browserPanel      the browser panel
     */
    public PackAddToCartMiniC(App frame, PackToBuyMiniP view,
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

        view.getButton().addActionListener(e -> {
            if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).addCart(view.getPack());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).addCart(view.getPack());
            }
            JOptionPane.showMessageDialog(frame, view.getPack().getId() + " was added to Cart", "Added To Cart",
                    JOptionPane.INFORMATION_MESSAGE);

            browserController.refreshCurrentPage();
            browserController.initializeActionsForMiniPanels();
        });
    }
}