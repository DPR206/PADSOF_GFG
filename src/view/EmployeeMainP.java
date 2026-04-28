package view;

import model.user.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EmployeeMainP extends JPanel {
    private final JButton managePacks;
    private final JButton manageStoreProducts;
    private final JButton addStoreProducts;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public EmployeeMainP(User user) throws IOException {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        managePacks = new JButton("Manage Packs");
        manageStoreProducts = new JButton("Manage Store Products");
        addStoreProducts = new JButton("Add Store Products");

        if (user.getType() == UserType.EMPLOYEE) {
            if (((Employee) user).getSp() != null) {
                this.add(managePacks);
                this.add(manageStoreProducts);
                this.add(addStoreProducts);
            }
        }

    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        //DUE
    }
}