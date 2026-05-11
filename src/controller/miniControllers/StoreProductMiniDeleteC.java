package controller.miniControllers;

import controller.Controller;
import model.product.Pack;
import view.miniPanels.StoreProductMiniP;

public class StoreProductMiniDeleteC implements Controller {

    private StoreProductMiniP smpd;
    private Pack pack;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public StoreProductMiniDeleteC(StoreProductMiniP spmd, Pack p) {
        this.smpd = spmd;
        this.pack = p;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        smpd.getButton().addActionListener(e -> {
            pack.getProducts().remove(this.smpd);
        });
    }
}