package controller;

import model.store.Store;
import view.App;
import view.EmployeeMainP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeMainC implements ActionListener {
    private final EmployeeMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public EmployeeMainC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getEmployeeMainPanel();
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Manage Packs" ->
                    JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Manage Packs", "Manage Packs",
                            JOptionPane.INFORMATION_MESSAGE);
            case "Manage Store Products" ->
                    JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Manage Store Products", "Manage Store",
                            JOptionPane.INFORMATION_MESSAGE);
            case "Add Store Products" ->
                    JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Add Store Products", "Add Store",
                            JOptionPane.INFORMATION_MESSAGE);
            case "Manage Orders" ->
                    JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Manage Orders", "Manage Orders",
                            JOptionPane.INFORMATION_MESSAGE);
            case "Manage Exchanges" ->
                    JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Manage Exchanges", "Manage Exchange",
                            JOptionPane.INFORMATION_MESSAGE);
            case "Valuate Products" ->
                    JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Valuate Products", "Valuate Products",
                            JOptionPane.INFORMATION_MESSAGE);
        }
    }
}