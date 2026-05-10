package view.employeePanels;

import model.user.*;
import view.App;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

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
     */
    public EmployeeMainP(App app) {
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());

        this.app = app;

        paintEverything();
    }

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

    public JButton getAddStoreProducts() {
        return addStoreProducts;
    }

    public App getApp() {
        return app;
    }

    public JButton getManageExchanges() {
        return manageExchanges;
    }

    public JButton getManageOrders() {
        return manageOrders;
    }

    public JButton getManagePacks() {
        return managePacks;
    }

    public JButton getManageStoreProducts() {
        return manageStoreProducts;
    }

    public JButton getValuateProducts() {
        return valuateProducts;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        managePacks.addActionListener(c);
        manageStoreProducts.addActionListener(c);
        addStoreProducts.addActionListener(c);
        manageOrders.addActionListener(c);
        manageExchanges.addActionListener(c);
        valuateProducts.addActionListener(c);
    }
}