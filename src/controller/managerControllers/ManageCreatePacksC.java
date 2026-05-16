package controller.managerControllers;

import controller.Controller;
import model.product.Pack;
import model.store.Store;
import view.App;
import view.managerPanels.ManagerCreatePackP;
import view.managerPanels.ManagerGestionarPacks;

import javax.swing.text.BadLocationException;

/**
 * The type Manage create packs c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManageCreatePacksC implements Controller {
    private final ManagerGestionarPacks gestionar;
    private final App frame;
    private final ManagerCreatePackP packP = new ManagerCreatePackP();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manage create packs c.
     * @param m     the m
     * @param frame the frame
     */
    public ManageCreatePacksC(ManagerGestionarPacks m, App frame) {
        this.gestionar = m;
        this.frame = frame;
        int i = 1;

        for (Pack p : Store.getInstance().getPacks()) {
            try {
                this.gestionar.getBrowser().addMiniPanel(p, i);
                i++;
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
        }

        initializeActions();
    }

    @Override
    public void initializeActions() {
        this.gestionar.getConfirmacion().addActionListener(e -> {
            new ManageCreatePackC(packP, frame);
            frame.addCard(packP, "CREAR NUEVO PACK");
            this.frame.changeVisibleCard("CREAR NUEVO PACK");
        });
    }

    /**
     * Add pack.
     */
    public void addPack() {
        int i = 1;
        for (Pack p : Store.getInstance().getPacks()) {
            try {
                this.gestionar.getBrowser().addMiniPanel(p, i);
                i++;
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
        }
    }
}