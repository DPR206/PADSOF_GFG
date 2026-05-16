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
import java.util.List;

public class PackDiscMiniC implements Controller {
    private final PackDiscMiniP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final BrowsePacksDiscC browserController;
    private final BrowsePacksDiscP browserPanel;
    private final List<Pack> alreadyChosenPacks;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public PackDiscMiniC(App frame, Store model, PackDiscMiniP view, BrowsePacksDiscC browserController,
                         BrowsePacksDiscP browserPanel, List<Pack> alreadyChosenPacks) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.browserController = browserController;
        this.browserPanel = browserPanel;
        this.alreadyChosenPacks = alreadyChosenPacks;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.setFocusable(true);
        view.setCursor(new Cursor(Cursor.HAND_CURSOR));

        view.getButton().addActionListener(e -> {
            alreadyChosenPacks.remove(view.getPack());
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