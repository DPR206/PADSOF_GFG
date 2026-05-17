package controller.browserControllers;

import controller.Controller;
import model.product.*;
import view.browserPanels.BrowsePacksComposed;
import view.miniPanels.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.util.List;

/**
 * The type Browse composed pack c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class BrowseComposedPackC implements Controller {
    private final BrowsePacksComposed browser;
    private final ComposedPack pack;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Browse composed pack c.
     * @param browser the browser
     * @param pack    the pack
     */
    public BrowseComposedPackC(BrowsePacksComposed browser, ComposedPack pack) {
        this.browser = browser;
        this.pack = pack;
        try {
            initializeActions();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initializeActions() throws BadLocationException {
        /*Creamos la lista de minipacks*/
        int i = 0;
        for (Pack p : this.pack.getPacks()) {
            this.browser.addFirstMiniPanel(p, i);
        }
        /*Inicializamos la lista de productos*/
        for (StoreProduct sp : this.pack.getProducts()) {
            this.browser.addSecondMiniPanel(sp, i);
        }

        /*Ahora inicializamos el controlador de los packs*/
        List<AbstractMiniP> packMinis = this.browser.getFirstMiniPanels();
        for (AbstractMiniP packMin : packMinis) {
            PackMiniP miniPack = (PackMiniP) packMin;
            miniPack.getButton().addActionListener(e -> {
                this.pack.getPacks().remove(miniPack.getPack());
                JOptionPane.showMessageDialog(null, "Pack borrado con éxito.");
            });

        }
        /*Ahora para los productos*/
        List<AbstractMiniP> productsMini = this.browser.getSecondMiniPanels();
        for (AbstractMiniP productMini : productsMini) {
            StoreProductMiniP miniProduct = (StoreProductMiniP) productMini;
            miniProduct.getButton().addActionListener(e -> {
                this.pack.getProducts().remove(miniProduct.getStoreProduct());
                JOptionPane.showMessageDialog(null, "Producto borrado con éxito.");
            });
        }
    }
}