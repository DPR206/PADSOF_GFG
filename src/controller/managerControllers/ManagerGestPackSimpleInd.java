package controller.managerControllers;

import controller.Controller;
import controller.miniControllers.StoreProductDeleteMiniC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.managerPanels.ManagerGestionarPacks;
import view.managerPanels.ManagerIndividualSimplePack;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.util.List;

/**
 * The type Manager gest pack simple ind.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManagerGestPackSimpleInd implements Controller {

    private final Pack p;
    private final ManagerIndividualSimplePack panel;
    private final ManagerGestionarPacks gestionar;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager gest pack simple ind.
     * @param p         the p
     * @param panel     the panel
     * @param gestionar the gestionar
     */
    public ManagerGestPackSimpleInd(Pack p, ManagerIndividualSimplePack panel, ManagerGestionarPacks gestionar) {
        this.p = p;
        this.panel = panel;
        this.gestionar = gestionar;
        initializeActions();
    }

    @Override
    public void initializeActions() {
        int i = 1;
        List<StoreProduct> products = p.getProducts();
        for (StoreProduct sp : products) {
            try {
                this.panel.getBrowser().addMiniPanel(sp, i);
                i++;
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
        }
        /*Asigno los controladores*/
        List<AbstractMiniP> list = this.panel.getBrowser().getMiniPanels();

        for (AbstractMiniP abs : list) {
            StoreProductMiniP sp = (StoreProductMiniP) abs;
            new StoreProductDeleteMiniC(sp, panel.getBrowser(), p);
        }

        /*asigno los action listeners*/

        panel.getConfirmarProduct().addActionListener(e -> {
            if (panel.getNameProductText().getText().isEmpty()) {
                return;
            }
            String name = panel.getNameProductText().getText();

            /*Busco producto*/

            StoreProduct toAdd = null;

            List<StoreProduct> pr = Store.getInstance().getStoreProductList();
            for (StoreProduct sp : pr) {
                if (sp.getName().equals(name)) {
                    toAdd = sp;
                    p.addProduct(toAdd);
                }
            }
            if (toAdd == null) {
                JOptionPane.showMessageDialog(null, "Debes insertar un nombre válido.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null, "Producto añadido correctamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        /*Ahora el botón del precio*/

        panel.getConfirmarPrecio().addActionListener(e -> {
            if (panel.getPackPriceText().getText().isEmpty()) {
                return;
            }

            double price = Double.parseDouble(panel.getPackPriceText().getText());
            this.p.setPrice(price);

            JOptionPane.showMessageDialog(null, "Precio cambiado correctamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            try {
                this.gestionar.getBrowser().paintEverything();
            } catch (BadLocationException e1) {
                throw new RuntimeException(e1);
            }
        });
    }

}