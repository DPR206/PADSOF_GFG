package controller.managerControllers;

import controller.Controller;
import model.product.Category;
import model.product.Figurine;
import model.store.Store;
import view.managerPanels.ManageIndividualFiguraP;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * The type Manage figurine c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManageFigurineC implements Controller {
    private final ManageIndividualFiguraP manageFigurine;
    private final Figurine figurine;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manage figurine c.
     * @param miniPanel      the mini panel
     * @param manageFigurine the manage figurine
     */
    public ManageFigurineC(StoreProductMiniP miniPanel, ManageIndividualFiguraP manageFigurine) {
        this.manageFigurine = manageFigurine;
        this.figurine = (Figurine) miniPanel.getStoreProduct();
        try {
            initializeActions();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initializeActions() throws BadLocationException {
        manageFigurine.getBtnConfirmarNombre().addActionListener(e -> {
            if (manageFigurine.getTxtNombre().getText().isEmpty()) {
                return;
            }
            figurine.setName(manageFigurine.getTxtNombre().getText());
            JOptionPane.showMessageDialog(null, "Nombre cambiado.");
        });

        manageFigurine.getBtnConfirmarMarca().addActionListener(e -> {
            if (manageFigurine.getTxtMarca().getText().isEmpty()) {
                return;
            }
            figurine.setBrand(manageFigurine.getTxtMarca().getText());
            JOptionPane.showMessageDialog(null, "Marca cambiada.");
        });

        manageFigurine.getBtnConfirmarMaterial().addActionListener(e -> {
            if (manageFigurine.getTxtMaterial().getText().isEmpty()) {
                return;
            }
            figurine.setMaterial(manageFigurine.getTxtMaterial().getText());
            JOptionPane.showMessageDialog(null, "Material cambiado.");
        });

        manageFigurine.getBtnConfirmarDimensiones().addActionListener(e -> {
            if (manageFigurine.getTxtDimensiones().getText().isEmpty()) {
                return;
            }
            figurine.setDimension(manageFigurine.getTxtDimensiones().getText());
            JOptionPane.showMessageDialog(null, "Dimensiones cambiadas.");
        });

        manageFigurine.getBtnConfirmarPrecio().addActionListener(e -> {
            if (manageFigurine.getTxtPrecio().getText().isEmpty()) {
                return;
            }
            figurine.setPrice(Double.parseDouble(manageFigurine.getTxtPrecio().getText()));
            JOptionPane.showMessageDialog(null, "Precio cambiado.");
        });

        manageFigurine.getBtnConfirmarCategorias().addActionListener(e -> {
            if (manageFigurine.getTxtCategorias().getText().isEmpty()) {
                return;
            }
            Category c = Store.getInstance().getCategoryFromName(manageFigurine.getTxtCategorias().getText());
            if (c == null) {
                JOptionPane.showMessageDialog(null, "Categoría no encontrada.");
                return;
            }
            figurine.addCategory(c);
            JOptionPane.showMessageDialog(null, "Categoría añadida correctamente.");
        });
    }
}