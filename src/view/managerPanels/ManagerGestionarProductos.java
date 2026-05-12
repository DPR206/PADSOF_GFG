package view.managerPanels;

import view.App;
import view.browserPanels.MixedBrowseStoreEditP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerGestionarProductos extends JPanel {
    private final MixedBrowseStoreEditP products;
    private JButton newProduct = new JButton("Crear nuevo producto");

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerGestionarProductos(App app) throws BadLocationException {
        super();

        this.setLayout(new BorderLayout());

        this.products = new MixedBrowseStoreEditP();

        this.newProduct.setPreferredSize(new Dimension(120, 30));

        JPanel auxiliar = new JPanel();
        auxiliar.add(newProduct);

        this.add(this.products, BorderLayout.CENTER);
        this.add(auxiliar, BorderLayout.EAST);

    }

    public MixedBrowseStoreEditP getProductsPanel() {
        return products;
    }

    public void setController(ActionListener c) {
        this.newProduct.addActionListener(c);
    }
}