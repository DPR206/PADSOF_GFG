package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.BrowseCategoriesDiscC;
import model.product.Category;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseCategoriesDiscP;
import view.miniPanels.CategoryDiscMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.List;

public class CategoryDiscMiniC implements Controller {
    private final CategoryDiscMiniP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final BrowseCategoriesDiscC browserController;
    private final BrowseCategoriesDiscP browserPanel;
    private final List<Category> alreadyChosenProducts;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public CategoryDiscMiniC(App frame, Store model, CategoryDiscMiniP view, BrowseCategoriesDiscC browserController,
                             BrowseCategoriesDiscP browserPanel, List<Category> alreadyChosenProducts) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.browserController = browserController;
        this.browserPanel = browserPanel;
        this.alreadyChosenProducts = alreadyChosenProducts;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.setFocusable(true);
        view.setCursor(new Cursor(Cursor.HAND_CURSOR));

        view.getButton().addActionListener(e -> {
            // DUE: Añadir el producto
            JOptionPane.showMessageDialog(frame, view.getCategory() + " was added to the discount", "Added To Discount",
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