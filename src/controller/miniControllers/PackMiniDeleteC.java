package controller.miniControllers;

import controller.Controller;
import model.product.Pack;
import view.miniPanels.PackMiniDelete;

public class PackMiniDeleteC implements Controller {

    public PackMiniDelete pmd;
    private Pack p;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public PackMiniDeleteC(PackMiniDelete pmc, Pack p) {
        this.pmd = pmc;
        this.p = p;

        initializeActions();
    }

    public void initializeActions() {
        pmd.getGestionar().addActionListener(e -> {
            this.p.getPacks().remove(this.pmd);
        });
    }
}