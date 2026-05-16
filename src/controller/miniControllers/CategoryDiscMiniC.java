package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.BrowseCategoriesDiscC;
import model.product.Category;
import view.App;
import view.browserPanels.BrowseCategoriesDiscP;
import view.miniPanels.CategoryDiscMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.List;

/**
 * The type Category disc mini c.
 * @author Ana O.R.
 * @version 1.0
 */
public class CategoryDiscMiniC implements Controller {
    private final CategoryDiscMiniP view;
    private final App frame;
    private final BrowseCategoriesDiscC browserController;
    private final BrowseCategoriesDiscP browserPanel;
    private final List<Category> alreadyChosenCategories;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame                   the controller's frame
     * @param view                    the view
     * @param browserController       the browser controller
     * @param browserPanel            the browser panel
     * @param alreadyChosenCategories the already chosen categories
     */
    public CategoryDiscMiniC(App frame, CategoryDiscMiniP view, BrowseCategoriesDiscC browserController,
                             BrowseCategoriesDiscP browserPanel, List<Category> alreadyChosenCategories) {
        this.frame = frame;
        this.view = view;
        this.browserController = browserController;
        this.browserPanel = browserPanel;
        this.alreadyChosenCategories = alreadyChosenCategories;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.setFocusable(true);
        view.setCursor(new Cursor(Cursor.HAND_CURSOR));

        view.getButton().addActionListener(e -> {
            alreadyChosenCategories.add(view.getCategory());
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