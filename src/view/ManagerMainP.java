package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ManagerMainP extends JPanel {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerMainP() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Change this

        this.add(new JLabel("Work in progres..."));
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        //DUE
    }
}