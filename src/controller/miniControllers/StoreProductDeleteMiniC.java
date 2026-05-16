package controller.miniControllers;

import controller.Controller;
import model.product.Pack;
import view.browserPanels.BrowsePackProductsP;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.StoreProductMiniP;

import javax.swing.text.BadLocationException;
import java.util.List;

/**
 * The type Store product delete mini c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class StoreProductDeleteMiniC implements Controller {

    private final StoreProductMiniP smpd;
    private final Pack pack;
    private final BrowsePackProductsP browser;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Store product delete mini c.
     * @param smpd    the smpd
     * @param browser the browser
     * @param pack    the pack
     */
    public StoreProductDeleteMiniC(StoreProductMiniP smpd, BrowsePackProductsP browser, Pack pack) {
        this.smpd = smpd;
        this.pack = pack;
        this.browser = browser;
        initializeActions();
    }

    @Override
    public void initializeActions() {
        smpd.getButton().addActionListener(e -> {
            pack.eliminateProduct(smpd.getStoreProduct());
            /*Buscamos el minipanel*/

            List<AbstractMiniP> panels = this.browser.getMiniPanels();

            panels.removeIf(panel -> panel instanceof StoreProductMiniP &&
                                     ((StoreProductMiniP) panel).getStoreProduct().getId()
                                                                .equals(this.smpd.getStoreProduct().getId()));
            try {
                this.browser.paintEverything();
            } catch (BadLocationException e1) {
                throw new RuntimeException(e1);
            }
            System.out.println("DELETED");
        });

    }
}