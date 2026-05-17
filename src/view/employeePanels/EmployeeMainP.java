package view.employeePanels;

import model.user.*;
import view.App;

import javax.swing.*;
import java.awt.*;

/**
 * The type Employee main p.
 * @author Ana O.R.
 * @version 1.0
 */
public class EmployeeMainP extends JPanel {
    private final JButton managePacks = new JButton("Manage Packs");
    private final JButton manageStoreProducts = new JButton("Manage Store Products");
    private final JButton addStoreProducts = new JButton("Add Store Products");
    private final JButton manageOrders = new JButton("Manage Orders");
    private final JButton manageExchanges = new JButton("Manage Exchanges");
    private final JButton valuateProducts = new JButton("Valuate Products");
    private final App app;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     * @param app the app
     */
    public EmployeeMainP(App app) {
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());

        this.app = app;

        paintEverything();
    }

    /**
     * Paint everything.
     */
    public void paintEverything() {
        this.removeAll();

        JPanel botones = new JPanel(new GridLayout(2, 3));

        User user = app.getUser();

        if (user.getType() == UserType.EMPLOYEE) {
            if (((Employee) user).getSp() != null) {
                botones.add(managePacks);
                botones.add(manageStoreProducts);
                botones.add(addStoreProducts);
            }
            if (((Employee) user).getOp() != null) {
                botones.add(manageOrders);
            }
            if (((Employee) user).getEp() != null) {
                botones.add(manageExchanges);
                botones.add(valuateProducts);
            }
        }

        this.add(botones, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    /**
     * It gets the add store products
     * @return the add store products
     */
    public JButton getAddStoreProducts() {
        return addStoreProducts;
    }

    /**
     * It gets the app
     * @return the app
     */
    public App getApp() {
        return app;
    }

    /**
     * It gets the manage exchanges
     * @return the manage exchanges
     */
    public JButton getManageExchanges() {
        return manageExchanges;
    }

    /**
     * It gets the manage packs
     * @return the manage packs
     */
    public JButton getManagePacks() {
        return managePacks;
    }

    /**
     * It gets the manage store products
     * @return the manage store products
     */
    public JButton getManageStoreProducts() {
        return manageStoreProducts;
    }

    /**
     * It gets the valuate products
     * @return the valuate products
     */
    public JButton getValuateProducts() {
        return valuateProducts;
    }
}