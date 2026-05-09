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

	public ManagerGestionarProductos(App app) {
		super();

		this.setLayout(new BorderLayout());

		BrowseStorePEdit products = null;
		try {
			products = new BrowseStorePEdit(app);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	this.newProduct.setPreferredSize(new Dimension(120, 30));

    	JPanel auxiliar = new JPanel();
    	auxiliar.add(newProduct);

    	this.add(products, BorderLayout.CENTER);
    	this.add(auxiliar, BorderLayout.EAST);

	}

	public void setController(ActionListener c) {
		this.newProduct.addActionListener(c);
	}
}