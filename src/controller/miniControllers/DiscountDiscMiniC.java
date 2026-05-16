package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.BrowseDiscountsC;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseDiscountsP;
import view.miniPanels.DiscountMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DiscountDiscMiniC implements Controller {
    private final DiscountMiniP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final BrowseDiscountsC browserController;
    private final BrowseDiscountsP browserPanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public DiscountDiscMiniC(App frame, Store model, DiscountMiniP view, BrowseDiscountsC browserController,
                             BrowseDiscountsP browserPanel) {
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

        view.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
                }
            }
        });

        view.getDiscountInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
                }
            }
        });

        view.getButton().addActionListener(e -> {
            for (StoreProduct product : view.getDiscount().getProducts()) {
                product.setDiscount(null);
            }
            model.getDiscounts().remove(view.getDiscount());

            JOptionPane.showMessageDialog(frame,
                    "Discount: " + view.getDiscount().getId() + " was deleted from the store", "Delete Discount",
                    JOptionPane.INFORMATION_MESSAGE);

            try {
                browserPanel.paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            browserController.initializeActionsForMiniPanels();

        });
    }
}