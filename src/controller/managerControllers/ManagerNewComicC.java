package controller.managerControllers;

import controller.Controller;
import model.product.Category;
import model.product.Comic;
import model.store.Store;
import view.App;
import view.managerPanels.ManagerCreateComicP;

import javax.swing.*;
import java.time.Year;

public class ManagerNewComicC implements Controller {
    private ManagerCreateComicP mnc;
    private App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerNewComicC(ManagerCreateComicP mnc, App frame) {
        this.mnc = mnc;
        this.frame = frame;

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
            String pages = mnc.getTxtNumPages().getText();
            if (pages == null) {
                return;
            }
            int numPages = Integer.parseInt(pages);
            String y = mnc.getTxtYear().getText();
            if (y == null) {
                return;
            }
            Year year = Year.parse(y);
            String author = mnc.getTxtAuthor().getText();
            if (author == null) {
                return;
            }
            String edit = mnc.getTxtEditorial().getText();
            if (edit == null) {
                return;
            }

            Comic c =
                    new Comic(price, nombre, description, ".\\resources\\app\\cart.png", stockk, numPages, year, author,
                            edit, cat);
            Store.getInstance().addStoreProduct(c);
            JOptionPane.showMessageDialog(null, "Cómic añadido correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}