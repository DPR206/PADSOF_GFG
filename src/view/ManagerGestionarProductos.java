package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;

import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.banners.BannerManager;
import view.miniPanels.PackMiniEdit;
import view.miniPanels.StoreProductMiniEdit;

public class ManagerGestionarProductos extends JPanel{
	private BannerManager banner = new BannerManager();
	private JButton newProduct = new JButton("Crear nuevo producto");
	
	public ManagerGestionarProductos(App app) {
		super();
		
		this.setLayout(new BorderLayout());
		this.add(banner, BorderLayout.NORTH);
    	
		JPanel mainThings = new JPanel();
    	mainThings.setLayout(new BoxLayout(mainThings, BoxLayout.Y_AXIS));
    	
    	List<StoreProduct> products = Store.getInstance().getStoreProductList();
    	
    	JScrollPane scroll = new JScrollPane(mainThings);
    	
    	int index = 1;
    	
    	for(StoreProduct sp: products) {
    		try {
				mainThings.add(new StoreProductMiniEdit(sp, index));
				index++;
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
    	}
    	
    	this.newProduct.setPreferredSize(new Dimension(120, 30));
    	
    	JPanel auxiliar = new JPanel();
    	auxiliar.add(newProduct);
    	
    	this.add(scroll, BorderLayout.CENTER);
    	this.add(auxiliar, BorderLayout.EAST);
    	
	}	
	
	public void setController(ActionListener c) {
		this.newProduct.addActionListener(c);
	}
	
	public BannerManager getBanner() {
		return this.banner;
	}
}
