package controller;

import model.store.Store;
import view.App;
import view.EmployeeMainP;

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
            case "Manage Packs" -> System.out.println("Manage Packs"); //DUE
            case "Manage Store Products" -> System.out.println("Manage Store Products"); //DUE
            case "Add Store Products" -> System.out.println("Add Store Products"); //DUE
            case "Manage Orders" -> System.out.println("Manage orders"); //DUE
            case "Manage Exchanges" -> System.out.println("Manage exchanges"); //DUE
            case "Valuate Products" -> System.out.println("Valuate Products"); //DUE
        }
    }
}