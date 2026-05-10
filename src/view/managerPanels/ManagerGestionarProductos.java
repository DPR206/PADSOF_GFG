package view.managerPanels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;

import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseStoreP;
import view.browserPanels.BrowseStorePEdit;
import view.miniPanels.StoreProductMiniEdit;

public class ManagerGestionarProductos extends JPanel{
	private JButton newProduct = new JButton("Crear nuevo producto");
	private final BrowseStorePEdit products;

	public ManagerGestionarProductos(App app) throws BadLocationException {
		super();

		this.setLayout(new BorderLayout());

		this.products = new BrowseStorePEdit(app);


    	this.newProduct.setPreferredSize(new Dimension(120, 30));

    	JPanel auxiliar = new JPanel();
    	auxiliar.add(newProduct);

    	this.add(this.products, BorderLayout.CENTER);
    	this.add(auxiliar, BorderLayout.EAST);

	}
	public BrowseStorePEdit getProductsPanel() {
		return products;
	}
	public void setController(ActionListener c) {
		this.newProduct.addActionListener(c);
	}
}