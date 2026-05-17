package controller.miniControllers;

import controller.Controller;
import model.product.Pack;
import view.miniPanels.PackMiniP;

/**
 * The type Pack delete mini c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class PackDeleteMiniC implements Controller {

    private final Pack p;
    public PackMiniP pmd;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Pack delete mini c.
     * @param pmc the pmc
     * @param p   the p
     */
    public PackDeleteMiniC(PackMiniP pmc, Pack p) {
        this.pmd = pmc;
        this.p = p;

        initializeActions();
    }

    public void initializeActions() {
        pmd.getButton().addActionListener(e -> {
            this.p.getPacks().remove(this.pmd);
        });
    }
}