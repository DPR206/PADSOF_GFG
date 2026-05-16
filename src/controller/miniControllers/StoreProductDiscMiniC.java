package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.BrowseStoreProductsDiscC;
import model.product.StoreProduct;
import view.App;
import view.browserPanels.BrowseStoreProductsDiscP;
import view.miniPanels.StoreProductDiscMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.List;

/**
 * The type Store product disc mini c.
 * @author Ana O.R.
 * @version 1.0
 */
public class StoreProductDiscMiniC implements Controller {
    private final StoreProductDiscMiniP view;
    private final App frame;
    private final BrowseStoreProductsDiscC browserController;
    private final BrowseStoreProductsDiscP browserPanel;
    private final boolean onlyOnce;
    private final List<StoreProduct> alreadyChosenProducts;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame                 the controller's frame
     * @param view                  the view
     * @param browserController     the browser controller
     * @param browserPanel          the browser panel
     * @param alreadyChosenProducts the already chosen products
     * @param onlyOnce              the only once
     */
    public StoreProductDiscMiniC(App frame, StoreProductDiscMiniP view, BrowseStoreProductsDiscC browserController,
                                 BrowseStoreProductsDiscP browserPanel, List<StoreProduct> alreadyChosenProducts,
                                 boolean onlyOnce) {
        this.frame = frame;
        this.view = view;
        this.browserController = browserController;
        this.browserPanel = browserPanel;
        this.alreadyChosenProducts = alreadyChosenProducts;
        this.onlyOnce = onlyOnce;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.setFocusable(true);
        view.setCursor(new Cursor(Cursor.HAND_CURSOR));

        view.getButton().addActionListener(e -> {
            alreadyChosenProducts.add(view.getStoreProduct());
            JOptionPane.showMessageDialog(frame, view.getStoreProduct().getName() + " was added to the discount",
                    "Added To Discount", JOptionPane.INFORMATION_MESSAGE);
            if (onlyOnce) {
                frame.changeVisibleCard("MANAGER_DISCOUNTS");
            } else {
                try {
                    browserPanel.paintEverything();
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                browserController.initializeActionsForMiniPanels();
            }
        });
    }
}