package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.AbstractMixedBrowserC;
import model.product.Pack;
import model.product.StoreProduct;
import view.App;
import view.browserPanels.AbstractMixedBrowserP;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Store product edit mini c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class StoreProductEditMiniC implements Controller {
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
    public StoreProductEditMiniC(App frame, StoreProductMiniP view,
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

        view.getButton().addActionListener(e -> {
            browserController.refreshCurrentPage();
            browserController.initializeActionsForMiniPanels();
        });
    }
}