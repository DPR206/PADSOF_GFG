package controller;

import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.BrowseStoreP;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseStoreC implements ActionListener {
    private final BrowseStoreP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public BrowseStoreC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getBrowseStorePanel();
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case "Add to Cart" -> {
                    // Inspirado en: https://stackoverflow.com/a/16192146
                    StoreProductMiniP miniPanel = ((StoreProductMiniP) ((JButton) e.getSource()).getParent());
                    ((RegisteredClient) frame.getUser()).addCart(miniPanel.getStoreProduct());
                    JOptionPane.showMessageDialog(frame, "miniPanel.getStoreProduct().getName() was added to Cart",
                            "Added To Cart", JOptionPane.INFORMATION_MESSAGE);
                }
                case "<< First Page" -> view.setCurrentPageNum(1);
                case "< Previous Page" -> view.setCurrentPageNum(view.getCurrentPageNum() - 1);
                case "Next Page >" -> view.setCurrentPageNum(view.getCurrentPageNum() + 1);
                case "Last Page >>" -> view.setCurrentPageNum(view.getMaxPageNum());
            }
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }
}