package controller;

import model.store.Store;
import view.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowseStoreC implements ActionListener {
    private final BrowseStoreP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
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
                    //((RegisteredClient) frame.getUser()).addCart(miniPanel.getStoreProduct());
                    System.out.println("Selected product was: " + miniPanel.getStoreProduct().getName());
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