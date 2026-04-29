package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RegisteredMainP extends JPanel {
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public RegisteredMainP() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Change this

        this.add(new JLabel("Work in progress!!!..."));
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        //DUE
    }
}