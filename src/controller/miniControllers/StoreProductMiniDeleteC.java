package controller.miniControllers;

import controller.Controller;
import model.product.Pack;
import view.miniPanels.StoreProductMiniDelete;

public class StoreProductMiniDeleteC implements Controller {

    private StoreProductMiniDelete smpd;
    private Pack pack;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public StoreProductMiniDeleteC(StoreProductMiniDelete spmd, Pack p) {
        this.smpd = spmd;
        this.pack = p;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        smpd.getAddToCart().addActionListener(e -> {
            pack.getProducts().remove(this.smpd);
        });
    }
}