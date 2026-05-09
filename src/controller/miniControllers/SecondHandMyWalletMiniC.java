package controller.miniControllers;

import controller.browserControllers.BrowserController;
import model.product.SecondHandProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowserPanel;
import view.miniPanels.SecondHandMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.*;

public class SecondHandMyWalletMiniC extends SecondHandMiniC {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame             the controller's frame
     * @param model             the controller's model
     * @param view
     * @param browserController
     * @param browserPanel
     */
    public SecondHandMyWalletMiniC(App frame, Store model, SecondHandMiniP view,
                                   BrowserController<SecondHandProduct> browserController,
                                   BrowserPanel<SecondHandProduct> browserPanel) {
        super(frame, model, view, browserController, browserPanel);

        view.getProductImage().addMouseListener(new MouseAdapter() {
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
        if (e.getActionCommand().equals("Add to Offer")) {
            JOptionPane.showMessageDialog(super.getFrame(),
                    super.getView().getSecondHandProduct().getName() + " was " + "added to " + "the Offer",
                    "Added To Offer", JOptionPane.INFORMATION_MESSAGE);
            try {
                super.getBrowserPanel().paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            super.getBrowserController().updateControllers();
        }
    }
}