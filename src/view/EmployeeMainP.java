package view;

import model.user.*;
import view.banners.BannerEmployee;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeMainP extends JPanel {
    private final JButton managePacks;
    private final JButton manageStoreProducts;
    private final JButton addStoreProducts;
    private final JButton manageOrders;
    private final JButton manageExchanges;
    private final JButton valuateProducts;
    private final App app;
    private JPanel banner;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This panel's constructor
     */
    public EmployeeMainP(App app) {
        //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());

        this.app = app;

        managePacks = new JButton("Manage Packs");
        manageStoreProducts = new JButton("Manage Store Products");
        addStoreProducts = new JButton("Add Store Products");
        manageOrders = new JButton("Manage Orders");
        manageExchanges = new JButton("Manage Exchanges");
        valuateProducts = new JButton("Valuate Products");
        banner = new BannerEmployee();

        paintEverything();
    }

    public void paintEverything() {
        this.removeAll();
        this.add(banner, BorderLayout.NORTH);

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