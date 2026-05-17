package controller.employeeControllers;

import controller.Controller;
import controller.browserControllers.BrowseValuationProductsC;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseSecondHandProductsP;
import view.employeePanels.EmployeeMainP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * The type Employee main c.
 * @author Ana O.R.
 * @version 1.0
 */
public class EmployeeMainC implements Controller {
    private final EmployeeMainP view;
    private final App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public EmployeeMainC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getEmployeeMainPanel();

        try {
            BrowseSecondHandProductsP valuateView = new BrowseSecondHandProductsP("Valuate", null);
            this.frame.addCard(valuateView, "BROWSE_VALUATION_PRODUCTS");
            new BrowseValuationProductsC(this.frame, valuateView, model);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.getManagePacks().addActionListener(
                e -> JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Manage Packs", "Manage Packs",
                        JOptionPane.INFORMATION_MESSAGE));

        view.getManageStoreProducts().addActionListener(
                e -> JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Manage Store Products", "Manage Store",
                        JOptionPane.INFORMATION_MESSAGE));

        view.getAddStoreProducts().addActionListener(
                e -> JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Add Store Products", "Add Store",
                        JOptionPane.INFORMATION_MESSAGE));

        view.getManageExchanges().addActionListener(
                e -> JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Manage Exchanges", "Manage Exchange",
                        JOptionPane.INFORMATION_MESSAGE));

        view.getValuateProducts().addActionListener(e -> {
            try {
                frame.changeVisibleCard("BROWSE_VALUATION_PRODUCTS");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}