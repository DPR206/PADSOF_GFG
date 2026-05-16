package controller.managerControllers;

import controller.Controller;
import model.product.*;
import model.store.Store;
import view.managerPanels.ManagerCreateGameP;

import javax.swing.*;

/**
 * The type Manager new game c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManagerNewGameC implements Controller {

    private final ManagerCreateGameP mnc;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager new game c.
     * @param mnc the mnc
     */
    public ManagerNewGameC(ManagerCreateGameP mnc) {
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
            String rangoEdad = mnc.getTxtAgeRange().getText();
            if (rangoEdad == null) {
                return;
            }
            String numPlayers = mnc.getTxtNumPlayers().getText();
            if (numPlayers == null) {
                return;
            }
            int num = Integer.parseInt(numPlayers);
            Game g;

            if (mnc.getRbCartas().isSelected()) {
                g = new Game(price, nombre, description, ".\\resources\\app\\cart.png", stockk, num, rangoEdad,
                        GameStyle.CARDS, cat);
            } else if (mnc.getRbMesa().isSelected()) {
                g = new Game(price, nombre, description, ".\\resources\\app\\cart.png", stockk, num, rangoEdad,
                        GameStyle.GAMEBOARD, cat);
            } else if (mnc.getRbRol().isSelected()) {
                g = new Game(price, nombre, description, ".\\resources\\app\\cart.png", stockk, num, rangoEdad,
                        GameStyle.DICE, cat);
            }
        });
    }
}