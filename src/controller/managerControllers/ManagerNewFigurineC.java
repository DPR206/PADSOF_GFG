package controller.managerControllers;

import controller.Controller;
import model.product.Category;
import model.product.Figurine;
import model.store.Store;
import view.managerPanels.ManagerCreateFiguritaP;

import javax.swing.*;

/**
 * The type Manager new figurine c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManagerNewFigurineC implements Controller {

    private final ManagerCreateFiguritaP mnc;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager new figurine c.
     * @param mnc the mnc
     */
    public ManagerNewFigurineC(ManagerCreateFiguritaP mnc) {
        this.mnc = mnc;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        mnc.getBtnConfirmar().addActionListener(e -> {
            String category = mnc.getTxtCategoria().getText();
            Category cat = Store.getInstance().getCategoryFromName(category);
            if (cat == null) {
                JOptionPane.showMessageDialog(null, "LA CATEGORÍA NO EXISTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String nombre = mnc.getTxtNombre().getText();
            if (nombre == null) {
                return;
            }
            String precio = mnc.getTxtPrecio().getText();
            if (precio == null) {
                return;
            }
            double price = Double.parseDouble(precio);
            String stock = mnc.getTxtStock().getText();
            if (stock == null) {
                return;
            }
            int stockk = Integer.parseInt(stock);
            String description = mnc.getTxtDescripcion().getText();
            if (description == null) {
                return;
            }
            String marca = mnc.getTxtMarca().getText();
            if (marca == null) {
                return;
            }
            String material = mnc.getTxtMaterial().getText();
            if (material == null) {
                return;
            }
            String largo = mnc.getTxtLargo().getText();
            if (largo == null) {
                return;
            }

            String ancho = mnc.getTxtAncho().getText();
            if (ancho == null) {
                return;
            }

            String alto = mnc.getTxtAlto().getText();
            if (alto == null) {
                return;
            }
            String dimensions = largo + "x" + ancho + "x" + alto;

            Figurine f =
                    new Figurine(price, nombre, description, ".\\resources\\app\\cart.png", stockk, dimensions, marca,
                            material, cat);

            Store.getInstance().addStoreProduct(f);
            JOptionPane.showMessageDialog(null, "Figura añadida correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}