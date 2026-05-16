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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Discount disc mini c.
 * @author Ana O.R.
 * @version 1.0
 */
public class DiscountDiscMiniC implements Controller {
    private final DiscountMiniP view;
    private final App frame;
    private final Store model;
    private final BrowseDiscountsC browserController;
    private final BrowseDiscountsP browserPanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame             the controller's frame
     * @param model             the controller's model
     * @param view              the view
     * @param browserController the browser controller
     * @param browserPanel      the browser panel
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
                    JFrame panel = new JFrame();
                    JList<String> list = new JList<>();
                    view.getDiscount().getProducts().forEach(product -> list.add(new JLabel(product.getName())));
                    panel.add(list);
                    panel.pack();
                    panel.setVisible(true);
                }
            }
        });

        view.getDiscountInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JDialog dialog = new JDialog(frame, "Products in the discount", false);
                    dialog.setSize(400, 250);
                    dialog.setLocationRelativeTo(frame);
                    DefaultListModel<String> listModel = new DefaultListModel<>();

                    AtomicInteger i = new AtomicInteger(1);
                    view.getDiscount().getProducts()
                        .forEach(product -> listModel.addElement((i.getAndIncrement()) + ". " + product.getName()));

                    JList<String> list = new JList<>(listModel);

                    dialog.add(new JScrollPane(list));

                    dialog.setVisible(true);
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