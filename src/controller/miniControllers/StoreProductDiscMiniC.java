package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.BrowseStoreProductsDiscC;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseStoreProductsDiscP;
import view.miniPanels.StoreProductDiscMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.List;

public class StoreProductDiscMiniC implements Controller {
    private final StoreProductDiscMiniP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final BrowseStoreProductsDiscC browserController;
    private final BrowseStoreProductsDiscP browserPanel;
    private final boolean onlyOnce;
    private final List<StoreProduct> alreadyChosenProducts;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public StoreProductDiscMiniC(App frame, Store model, StoreProductDiscMiniP view,
                                 BrowseStoreProductsDiscC browserController, BrowseStoreProductsDiscP browserPanel,
                                 List<StoreProduct> alreadyChosenProducts, boolean onlyOnce) {
        this.frame = frame;
        this.view = view;
        this.model = model;
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