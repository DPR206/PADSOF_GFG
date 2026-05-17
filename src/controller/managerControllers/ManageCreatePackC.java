package controller.managerControllers;

import controller.Controller;
import view.App;
import view.managerPanels.*;

import javax.swing.text.BadLocationException;

/**
 * The type Manage create pack c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManageCreatePackC implements Controller {
    private final ManagerCreatePackP panel;
    private final App frame;
    private ManagerCreateSimplePack sp;
    private ManagerCreateComposedPackP pp;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manage create pack c.
     * @param panel the panel
     * @param frame the frame
     */
    public ManageCreatePackC(ManagerCreatePackP panel, App frame) {
        this.panel = panel;
        this.frame = frame;
        initializeActions();
    }

    @Override
    public void initializeActions() {
        this.panel.getSimplePackButton().addActionListener(e -> {
            sp = new ManagerCreateSimplePack();
            new ManagerCreateSimplePackC(sp);
            this.frame.addCard(sp, "CREATING SIMPLE PACK");
            try {
                this.frame.changeVisibleCard("CREATING SIMPLE PACK");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        this.panel.getComposedPackButton().addActionListener(e -> {
            pp = new ManagerCreateComposedPackP();
            new ManagerCreateComposedPackC(pp);
            this.frame.addCard(pp, "CREATING COMPOSED PACK");
            try {
                this.frame.changeVisibleCard("CREATING COMPOSED PACK");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}