package view.managerPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The type Manager create pack p.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerCreatePackP extends JPanel {
    private final JButton createComposedPack = new JButton("CREATE COMPOSED PACK");
    private final JButton createSimplePack = new JButton("CREATE SIMPLE PACK");

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager create pack p.
     */
    public ManagerCreatePackP() {
        super();
        this.setLayout(new GridLayout(1, 2));
        this.add(createComposedPack);
        this.add(createSimplePack);
    }

    /**
     * It gets the composed pack button
     * @return the composed pack button
     */
    public JButton getComposedPackButton() {
        return this.createComposedPack;
    }

    /**
     * It gets the simple pack button
     * @return the simple pack button
     */
    public JButton getSimplePackButton() {
        return this.createSimplePack;
    }

    /**
     * It sets the controller composed
     * @param e the e
     */
    public void setControllerComposed(ActionListener e) {
        this.createComposedPack.addActionListener(e);
    }

    /**
     * It sets the controller simple
     * @param e the e
     */
    public void setControllerSimple(ActionListener e) {
        this.createSimplePack.addActionListener(e);
    }
}