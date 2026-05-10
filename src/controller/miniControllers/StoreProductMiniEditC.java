package controller.miniControllers;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import controller.browserControllers.MixedBrowserController;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import model.user.RegisteredClient;
import model.user.UnregisteredClient;
import model.user.UserType;
import view.App;
import view.browserPanels.MixedBrowserPanel;
import view.miniPanels.StoreProductMiniEdit;
import view.miniPanels.StoreProductMiniP;

public class StoreProductMiniEditC implements ActionListener{
	private final StoreProductMiniEdit view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final MixedBrowserController<Pack, StoreProduct> browserController;
    private final MixedBrowserPanel<Pack, StoreProduct> browserPanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public StoreProductMiniEditC(App frame, Store model, StoreProductMiniEdit view,
                             MixedBrowserController<Pack, StoreProduct> browserController,
                             MixedBrowserPanel<Pack, StoreProduct> browserPanel) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.browserController = browserController;
        this.browserPanel = browserPanel;

        view.setFocusable(true);
        view.setCursor(new Cursor(Cursor.HAND_CURSOR));
        view.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
                }
            }
        });

        view.getProductInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add to Cart")) {
            try {
                browserPanel.paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            browserController.setActions();
        }

    }

}
