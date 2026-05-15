package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.BrowsePacksDiscC;
import model.product.Pack;
import model.store.Store;
import view.App;
import view.browserPanels.BrowsePacksDiscP;
import view.miniPanels.PackDiscMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PackDiscMiniC implements Controller {
    private final PackDiscMiniP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final BrowsePacksDiscC browserController;
    private final BrowsePacksDiscP browserPanel;
    private final List<Pack> alreadyChosenProducts;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public PackDiscMiniC(App frame, Store model, PackDiscMiniP view, BrowsePacksDiscC browserController,
                         BrowsePacksDiscP browserPanel, List<Pack> alreadyChosenProducts) {
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

        view.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
                }
            }
        });

        view.getPackInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
                }
            }
        });

        view.getButton().addActionListener(e -> {
            // DUE: Añadir el producto
            JOptionPane.showMessageDialog(frame, "Pack: " + view.getPack().getId() + " was added to the discount",
                    "Added To Discount", JOptionPane.INFORMATION_MESSAGE);
            try {
                browserPanel.paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            browserController.initializeActionsForMiniPanels();
        });
    }
}