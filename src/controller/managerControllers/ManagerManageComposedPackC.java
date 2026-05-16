package controller.managerControllers;

import controller.Controller;
import controller.browserControllers.BrowseComposedPackC;
import model.product.*;
import model.store.Store;
import view.managerPanels.ManagerIndividualComposedPack;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.util.List;

/**
 * The type Manager manage composed pack c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManagerManageComposedPackC implements Controller {

    private final ManagerIndividualComposedPack panel;
    private final ComposedPack p;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager manage composed pack c.
     * @param panel the panel
     * @param p     the p
     */
    public ManagerManageComposedPackC(ManagerIndividualComposedPack panel, ComposedPack p) {
        this.panel = panel;
        this.p = p;
        try {
            initializeActions();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initializeActions() throws BadLocationException {
        new BrowseComposedPackC(panel.getBrowser(), p);

        /*Para cambiar precio*/
        panel.getConfirmarPrecio().addActionListener(e -> {
            if (panel.getPackPriceText().getText().isEmpty()) {
                return;
            }
            double price = Double.parseDouble(panel.getPackPriceText().getText());
            this.p.setPrice(price);
            JOptionPane.showMessageDialog(null, "Precio cambiado correctamente.");
        });

        panel.getConfirmarProduct().addActionListener(e -> {
            if (panel.getIdProductText().getText().isEmpty()) {
                return;
            }
            String name = panel.getIdProductText().getText();
            /*Buscamos el producto*/
            StoreProduct toAdd = null;
            List<StoreProduct> toSearch = Store.getInstance().getStoreProductList();

            for (StoreProduct sp : toSearch) {
                if (sp.getName().equals(name)) {
                    toAdd = sp;
                }
            }
            if (toAdd == null) {
                JOptionPane.showMessageDialog(null, "Este producto no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.p.addProduct(toAdd);
            JOptionPane.showMessageDialog(null, "Producto añadido correctamente.");
        });

        panel.getConfirmarPackId().addActionListener(e -> {
            if (panel.getPackIdText().getText().isEmpty()) {
                return;
            }

            int id = Integer.parseInt(panel.getPackIdText().getText());
            Pack toAdd = Store.getInstance().getPackById(id);
            if (toAdd == null) {
                JOptionPane.showMessageDialog(null, "El pack con este id no existe.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.p.addPack(toAdd);
            JOptionPane.showMessageDialog(null, "Pack añadido correctamente.");
        });
    }

}