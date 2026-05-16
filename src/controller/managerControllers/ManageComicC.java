package controller.managerControllers;

import controller.Controller;
import model.product.Category;
import model.product.Comic;
import model.store.Store;
import view.managerPanels.ManagerIndividualComicP;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.time.Year;

/**
 * The type Manage comic c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManageComicC implements Controller {
    private final ManagerIndividualComicP manageComic;
    private final Comic comic;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manage comic c.
     * @param miniPanel   the mini panel
     * @param manageComic the manage comic
     */
    public ManageComicC(StoreProductMiniP miniPanel, ManagerIndividualComicP manageComic) {
        this.manageComic = manageComic;
        this.comic = (Comic) miniPanel.getStoreProduct();
    }

    @Override
    public void initializeActions() throws BadLocationException {
        manageComic.getBtnConfirmarNombre().addActionListener(e -> {
            if (manageComic.getTxtNombreProducto().getText().isEmpty()) {
                return;
            }
            comic.setName(manageComic.getTxtNombreProducto().getText());
            JOptionPane.showMessageDialog(null, "Nombre cambiado.");
        });

        manageComic.getBtnConfirmarAutor().addActionListener(e -> {
            if (manageComic.getTxtAutor().getText().isEmpty()) {
                return;
            }
            comic.setAuthor(manageComic.getTxtAutor().getText());
            JOptionPane.showMessageDialog(null, "Autor cambiado.");
        });

        manageComic.getBtnConfirmarEditorial().addActionListener(e -> {
            if (manageComic.getTxtEditorial().getText().isEmpty()) {
                return;
            }
            comic.setEditorial(manageComic.getTxtEditorial().getText());
            JOptionPane.showMessageDialog(null, "Editorial cambiada.");
        });

        manageComic.getBtnConfirmarAnio().addActionListener(e -> {
            if (manageComic.getTxtAnioPublicacion().getText().isEmpty()) {
                return;
            }
            comic.setYear(Year.parse(manageComic.getTxtEditorial().getText()));
            JOptionPane.showMessageDialog(null, "Año cambiado.");
        });

        manageComic.getBtnConfirmarPaginas().addActionListener(e -> {
            if (manageComic.getTxtNumPaginas().getText().isEmpty()) {
                return;
            }
            comic.setNumPages(Integer.parseInt(manageComic.getTxtNumPaginas().getText()));
            JOptionPane.showMessageDialog(null, "Número de páginas cambiado.");
        });

        manageComic.getBtnConfirmarPrecio().addActionListener(e -> {
            if (manageComic.getTxtPrecio().getText().isEmpty()) {
                return;
            }
            comic.setPrice(Double.parseDouble(manageComic.getTxtPrecio().getText()));
            JOptionPane.showMessageDialog(null, "Número de páginas cambiado.");
        });

        manageComic.getBtnConfirmarCategorias().addActionListener(e -> {
            if (manageComic.getTxtCategorias().getText().isEmpty()) {
                return;
            }
            Category c = Store.getInstance().getCategoryFromName(manageComic.getTxtCategorias().getText());
            if (c == null) {
                return;
            }
            JOptionPane.showMessageDialog(null, "Categoría añadida correctamente.");
            comic.addCategory(c);
        });

        manageComic.getBtnConfirmarDescripcion().addActionListener(e -> {
            if (manageComic.getTxtDescripcion().getText().isEmpty()) {
                return;
            }
            comic.setDescription(manageComic.getTxtDescripcion().getText());
            JOptionPane.showMessageDialog(null, "Descripción cambiada.");
        });

        manageComic.getBtnConfirmarStock().addActionListener(e -> {
            if (manageComic.getTxtStock().getText().isEmpty()) {
                return;
            }
            comic.setStock(Integer.parseInt(manageComic.getTxtStock().getText()));
            JOptionPane.showMessageDialog(null, "Stock cambiado.");
        });
    }

}