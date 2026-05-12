package controller.miniControllers;

import controller.Controller;
import model.product.Pack;
import view.miniPanels.PackMiniP;

public class PackDeleteMiniC implements Controller {

    public PackMiniP pmd;
    private Pack p;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
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