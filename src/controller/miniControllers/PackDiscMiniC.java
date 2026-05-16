package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.BrowsePacksDiscC;
import model.product.Pack;
import view.App;
import view.browserPanels.BrowsePacksDiscP;
import view.miniPanels.PackDiscMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.List;

/**
 * The type Pack disc mini c.
 * @author Ana O.R.
 * @version 1.0
 */
public class PackDiscMiniC implements Controller {
    private final PackDiscMiniP view;
    private final App frame;
    private final BrowsePacksDiscC browserController;
    private final BrowsePacksDiscP browserPanel;
    private final List<Pack> alreadyChosenPacks;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame              the controller's frame
     * @param view               the view
     * @param browserController  the browser controller
     * @param browserPanel       the browser panel
     * @param alreadyChosenPacks the already chosen packs
     */
    public PackDiscMiniC(App frame, PackDiscMiniP view, BrowsePacksDiscC browserController,
                         BrowsePacksDiscP browserPanel, List<Pack> alreadyChosenPacks) {
        this.frame = frame;
        this.view = view;
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