package controller.managerControllers;

import controller.Controller;
import model.product.*;
import model.store.Store;
import view.App;
import view.managerPanels.*;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.PackMiniP;

import javax.swing.text.BadLocationException;

/**
 * The type Manage packs c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManagePacksC implements Controller {
    private final ManagerGestionarPacks gestionar;
    private final App frame;
    private final ManagerCreatePackP packP = new ManagerCreatePackP();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manage packs c.
     * @param m     the m
     * @param frame the frame
     */
    public ManagePacksC(ManagerGestionarPacks m, App frame) {
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

        for (AbstractMiniP ap : this.gestionar.getBrowser().getMiniPanels()) {
            PackMiniP miniPack = (PackMiniP) ap;
            miniPack.setController(e -> {
                Pack pack = miniPack.getPack();
                if (pack instanceof ComposedPack) {
                    ManagerIndividualComposedPack misp = new ManagerIndividualComposedPack(pack);
                    new ManagerManageComposedPackC(misp, (ComposedPack) pack);
                    this.frame.addCard(misp, "COMPOSED PACKS");
                    this.frame.changeVisibleCard("COMPOSED PACKS");
                } else if (pack instanceof SimplePack || !(pack instanceof ComposedPack)) {
                    ManagerIndividualSimplePack misp = new ManagerIndividualSimplePack(pack);
                    new ManagerGestPackSimpleInd(pack, misp, this.gestionar);
                    this.frame.addCard(misp, "SIMPLE PACKS");
                    this.frame.changeVisibleCard("SIMPLE PACKS");
                }
            });
        }

        initializeActions();
    }

    @Override
    public void initializeActions() {
        this.gestionar.getConfirmacion().addActionListener(e -> {
            frame.addCard(packP, "CREAR NUEVO PACK");
            new ManageCreatePackC(packP, frame);
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