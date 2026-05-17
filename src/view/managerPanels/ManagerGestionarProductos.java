package view.managerPanels;

import view.App;
import view.browserPanels.BrowseStoreProducts;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

/**
 * The type Manager gestionar productos.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerGestionarProductos extends JPanel {
    private final BrowseStoreProducts products;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager gestionar productos.
     * @param app the app
     * @throws BadLocationException the bad location exception
     */
    public ManagerGestionarProductos(App app) throws BadLocationException {
        super();

        this.setLayout(new BorderLayout());

        this.products = new BrowseStoreProducts("MANAGE", "");

        JPanel auxiliar = new JPanel();

        this.add(this.products, BorderLayout.CENTER);
        this.add(auxiliar, BorderLayout.EAST);

    }

    /**
     * It gets the products panel
     * @return the products panel
     */
    public BrowseStoreProducts getProductsPanel() {
        return products;
    }
}