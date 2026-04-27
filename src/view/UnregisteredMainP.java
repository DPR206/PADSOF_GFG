package view;

import model.product.StoreProduct;
import model.store.Store;
import model.user.UnregisteredClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class UnregisteredMainP extends JPanel {
    private JPanel banner;
    private List<JButton> botones = new ArrayList<>();
    private JTextField toSearch;
    private JScrollPane scrolling;
    private JPanel products;
    private JPanel productSearch;
    private List<StoreProduct> p;
    private UnregisteredClient mainU;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public UnregisteredMainP(UnregisteredClient mainU) {
        super();
    	this.mainU = mainU;
        this.banner = new JPanel();
        this.productSearch = new JPanel();
        this.productSearch.setLayout(new BorderLayout());
    	this.toSearch = new JTextField();
        
        Store s = Store.getInstance();
        this.p = this.mainU.searchStoreProduct();
        
        this.setLayout(new BorderLayout());
        
        this.banner.add(new JLabel("GIFTS FOR GEEKS"));
        
        this.products = new JPanel(new GridLayout(0,4));
        this.add(banner, BorderLayout.NORTH);
        
        for(StoreProduct sp: p) {
        	JButton boton = new JButton(sp.getName());
        	products.add(boton);
        	this.botones.add(boton);
        }
        
        JScrollPane scrolling = new JScrollPane(products);
        
        this.productSearch.add(toSearch, BorderLayout.NORTH);
        this.productSearch.add(scrolling, BorderLayout.CENTER);
        this.add(productSearch, BorderLayout.CENTER);
    }
    
    public void setFoundProduct() {
    	String id = this.toSearch.getText();
    	
    	this.botones.clear();
    	this.products.removeAll();
    	
    	mainU.getSearcher().getStoreSearcher();
    	
    	this.products.revalidate();
        this.products.repaint();
    }
    
    public void restoreView() {
    	this.products.removeAll();

        for (StoreProduct sp : p) {
        	JButton boton = new JButton(sp.getName());
        	products.add(boton);
        	this.botones.add(boton);
        }

        this.products.revalidate();
        this.products.repaint();
    }
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        this.toSearch.addActionListener(c);
        for(JButton boton: this.botones) {
        	boton.addActionListener(c);
        }
    }
}