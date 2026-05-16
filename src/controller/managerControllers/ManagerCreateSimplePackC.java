package controller.managerControllers;

import controller.Controller;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.managerPanels.ManagerCreateSimplePack;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Manager create simple pack c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManagerCreateSimplePackC implements Controller {

    private final ManagerCreateSimplePack p;
    private final ArrayList<StoreProduct> productsToAdd = new ArrayList<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager create simple pack c.
     * @param p the p
     */
    public ManagerCreateSimplePackC(ManagerCreateSimplePack p) {
        this.p = p;
        initializeActions();
    }

    @Override
    public void initializeActions() {
        /*Inicializamos el botón para buscar un producto*/
        this.p.getId().addActionListener(e -> {

            if (p.getProductNameAdd().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debes insertar el nombre del producto que quieres buscar.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            StoreProduct toAdd = null;
            String name = p.getProductNameAdd().getText().trim();

            List<StoreProduct> pr = Store.getInstance().getStoreProductList();

            for (StoreProduct sp : pr) {
                if (sp.getName().trim().equalsIgnoreCase(name)) {
                    toAdd = sp;
                    productsToAdd.add(toAdd);
                    break;
                }
            }

            if (toAdd == null) {
                JOptionPane.showMessageDialog(null, "Ese producto no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        this.p.getConfirmar().addActionListener(e -> {
            if (p.getPackName().getText().isEmpty() || p.getPrice().getText().isEmpty() ||
                p.getPictureDirectory().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debes insertar el nombre del pack y su precio primero.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            double price = Double.parseDouble(p.getPrice().getText());
            String directory = p.getPictureDirectory().getText();

            Pack newPack = new Pack(price, this.productsToAdd, directory);
            Store.getInstance().addPack(newPack);

            JOptionPane.showMessageDialog(null, "Pack creado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        });
    }

}