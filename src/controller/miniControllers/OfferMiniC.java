package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.BrowseOffersC;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseOffersP;
import view.miniPanels.OfferMiniP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OfferMiniC implements Controller {
    private final OfferMiniP view;
    private final App frame;
    private final Store model;
    private final BrowseOffersC browserController;
    private final BrowseOffersP browserPanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     * @param view  the view
     */
    public OfferMiniC(App frame, Store model, OfferMiniP view, BrowseOffersC browserController,
                      BrowseOffersP browserPanel) {
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
                JOptionPane.showMessageDialog(null, "Work in progress too.... :c");
            }
        });

        view.getOfferInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(null, "Work in progress too.... :c");
                }
            }
        });

        view.getAcceptButton().addActionListener(e -> {
            view.getOffer().acceptOffer();

            JOptionPane.showMessageDialog(frame, "Offer was accepted", "Offer accepted",
                    JOptionPane.INFORMATION_MESSAGE);

            browserController.refreshCurrentPage();
            browserController.initializeActionsForMiniPanels();
        });

        view.getDeclineButton().addActionListener(e -> {
            if (view.getOffer().getOrigin() == frame.getUser()) {
                view.getOffer().cancelOffer();
            } else {
                view.getOffer().rejectOffer();
            }

            JOptionPane.showMessageDialog(frame, "Offer was rejected", "Offer rejected",
                    JOptionPane.INFORMATION_MESSAGE);

            browserController.refreshCurrentPage();
            browserController.initializeActionsForMiniPanels();
        });
    }
}