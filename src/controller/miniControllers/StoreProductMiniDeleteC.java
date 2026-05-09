package controller.miniControllers;

import model.product.Pack;
import view.miniPanels.StoreProductMiniDelete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StoreProductMiniDeleteC implements ActionListener {

    private StoreProductMiniDelete smpd;
    private Pack pack;

/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public StoreProductMiniDeleteC(StoreProductMiniDelete spmd, Pack p) {
        this.smpd = spmd;
        this.pack = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ELIMINAR DEL PACK")) {
            pack.getProducts().remove(this.smpd);
        }
    }

}