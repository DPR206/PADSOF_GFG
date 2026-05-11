package controller.employeeControllers;

import controller.Controller;
import model.store.Store;
import view.App;
import view.employeePanels.EmployeeMainP;

import javax.swing.*;

public class EmployeeMainC implements Controller {
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

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.getManagePacks().addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Manage Packs", "Manage Packs",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        view.getManageStoreProducts().addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Manage Store Products", "Manage Store",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        view.getAddStoreProducts().addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Add Store Products", "Add Store",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        view.getManageExchanges().addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Manage Exchanges", "Manage Exchange",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        view.getValuateProducts().addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Valuate Products", "Valuate Products",
                    JOptionPane.INFORMATION_MESSAGE);
        });

    }
}