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
import java.util.concurrent.atomic.AtomicInteger;

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
                JDialog dialog = new JDialog(frame, "Origin's products", false);
                dialog.setSize(400, 250);
                dialog.setLocationRelativeTo(frame);
                DefaultListModel<String> listModel = new DefaultListModel<>();

                AtomicInteger i = new AtomicInteger(1);
                view.getOffer().getProducts().get(view.getOffer().getOrigin().getUserName())
                    .forEach(product -> listModel.addElement((i.getAndIncrement()) + ". " + product.getName()));

                JList<String> list = new JList<>(listModel);

                dialog.add(new JScrollPane(list));

                dialog.setVisible(true);

                JDialog dialog2 = new JDialog(frame, "Destination's products", false);
                dialog2.setSize(400, 250);
                dialog2.setLocationRelativeTo(frame);
                DefaultListModel<String> listModel2 = new DefaultListModel<>();

                AtomicInteger i2 = new AtomicInteger(1);
                view.getOffer().getProducts().get(view.getOffer().getDestination().getUserName())
                    .forEach(product -> listModel2.addElement((i2.getAndIncrement()) + ". " + product.getName()));

                JList<String> list2 = new JList<>(listModel2);

                dialog2.add(new JScrollPane(list2));

                dialog2.setVisible(true);
            }
        });

        view.getOfferInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JDialog dialog = new JDialog(frame, "Origin's products", false);
                    dialog.setSize(400, 250);
                    dialog.setLocationRelativeTo(frame);
                    DefaultListModel<String> listModel = new DefaultListModel<>();

                    AtomicInteger i = new AtomicInteger(1);
                    view.getOffer().getProducts().get(view.getOffer().getOrigin().getUserName())
                        .forEach(product -> listModel.addElement((i.getAndIncrement()) + ". " + product.getName()));

                    JList<String> list = new JList<>(listModel);

                    dialog.add(new JScrollPane(list));

                    dialog.setVisible(true);

                    JDialog dialog2 = new JDialog(frame, "Destination's products", false);
                    dialog2.setSize(400, 250);
                    dialog2.setLocationRelativeTo(frame);
                    DefaultListModel<String> listModel2 = new DefaultListModel<>();

                    AtomicInteger i2 = new AtomicInteger(1);
                    view.getOffer().getProducts().get(view.getOffer().getDestination().getUserName())
                        .forEach(product -> listModel2.addElement((i2.getAndIncrement()) + ". " + product.getName()));

                    JList<String> list2 = new JList<>(listModel2);

                    dialog2.add(new JScrollPane(list2));

                    dialog2.setVisible(true);
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