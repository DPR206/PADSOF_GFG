package controller;

import model.store.Store;
import view.App;
import view.BrowseStoreP;

import javax.swing.*;
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
        if (e.getActionCommand().equals("Add to Cart")) {
            JOptionPane.showMessageDialog(frame, "Product added to cart");
        }
    }
}