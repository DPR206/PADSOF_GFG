package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class ControllableJPanel extends JPanel {
    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public abstract void setController(ActionListener c);
}