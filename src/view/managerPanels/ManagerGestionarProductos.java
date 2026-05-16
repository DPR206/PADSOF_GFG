package view.managerPanels;

import view.App;
import view.browserPanels.BrowseStoreProducts;
import view.browserPanels.MixedBrowseStoreEditP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerGestionarProductos extends JPanel {
    private final BrowseStoreProducts products;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerGestionarProductos(App app) throws BadLocationException {
        super();

        this.setLayout(new BorderLayout());

        this.products = new BrowseStoreProducts("MANAGE", "");


        JPanel auxiliar = new JPanel();

        this.add(this.products, BorderLayout.CENTER);
        this.add(auxiliar, BorderLayout.EAST);

    }

    public BrowseStoreProducts getProductsPanel() {
        return products;
    }
}