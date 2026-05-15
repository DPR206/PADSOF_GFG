package view.clientPanels;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.*;

import model.product.Pack;
import model.product.StoreProduct;

public class PackP extends JPanel{

	private JLabel name;
	private JLabel price;
	private JLabel stock;
	private JButton btnaddCart = new JButton("Add to cart");
	private JButton btnReturn = new JButton("Return");
	private JLabel lblImagen;
	private JSpinner unitSpinner;
	private JList<String> listProducts;
	
	private static final long serialVersionUID = 1L;

	public PackP() {
		configurarEstructura();
	}

	private void configurarEstructura() {
		
		setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JPanel imageWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        lblImagen = new JLabel("Cargando imagen..."); 
        lblImagen.setPreferredSize(new Dimension(250, 350));
        lblImagen.setVerticalAlignment(SwingConstants.TOP); 
        imageWrapper.add(lblImagen);
        add(imageWrapper, BorderLayout.WEST);
        
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        name = new JLabel("");
        name.setFont(new Font("Tahoma", Font.PLAIN, 15));
        price = new JLabel("");
        price.setForeground(new Color(0, 128, 128));
        price.setFont(new Font("SansSerif", Font.BOLD, 16));
        
        listProducts = new JList<>();
        JScrollPane scrollProducts = new JScrollPane(listProducts);
        scrollProducts.setMaximumSize(new Dimension(1800, 400));
        scrollProducts.setBorder(BorderFactory.createTitledBorder("Pack's content"));
        scrollProducts.setAlignmentX(Component.LEFT_ALIGNMENT);

        infoPanel.add(name);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(price);
        infoPanel.add(Box.createVerticalStrut(15)); 
        infoPanel.add(scrollProducts);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        Font fuenteBotones = new Font("SansSerif", Font.PLAIN, 18);

        btnaddCart.setMaximumSize(new Dimension(250, 100)); 
        btnaddCart.setFont(fuenteBotones); 
        
        btnReturn.setMaximumSize(new Dimension(250, 100));
        btnReturn.setFont(fuenteBotones);

        btnaddCart.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReturn.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(Box.createVerticalGlue()); 
        buttonPanel.add(btnaddCart);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnReturn);
        buttonPanel.add(Box.createVerticalGlue());
        
        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);
        
        JPanel containerSouth = new JPanel(new BorderLayout(20, 10));

        JPanel purchasePanel = new JPanel();
        purchasePanel.setLayout(new BoxLayout(purchasePanel, BoxLayout.Y_AXIS));
        
        stock = new JLabel("Stock: ");
        stock.setFont(new Font("SansSerif", Font.BOLD, 20));
        stock.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        SpinnerModel model = new SpinnerNumberModel(1, 1, 100, 1);
        unitSpinner = new JSpinner(model);
        unitSpinner.setFont(new Font("SansSerif", Font.PLAIN, 15));
        unitSpinner.setMaximumSize(new Dimension(120, 100));
        unitSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);

        purchasePanel.add(stock);
        purchasePanel.add(Box.createVerticalStrut(10)); 
        purchasePanel.add(unitSpinner);
        
        containerSouth.add(purchasePanel, BorderLayout.WEST);

        add(containerSouth, BorderLayout.SOUTH);
		
	}

	/**
	 * @return the btnaddCart
	 */
	public JButton getBtnaddCart() {
		return btnaddCart;
	}

	/**
	 * @return the btnReturn
	 */
	public JButton getBtnReturn() {
		return btnReturn;
	}

	/**
	 * @return the unitSpinner
	 */
	public JSpinner getUnitSpinner() {
		return unitSpinner;
	}

	/**
	 * @param i the name to set
	 */
	public void setName(int i) {
		this.name.setText("ID: " + i);
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price.setText(String.format("Price: %.2f€", price));
	}

	/**
	 * @param stock the stock to set
	 */
	public void setStock(int stock) {
		if(stock == 0) {
			this.stock.setText("Out of stock");
			this.stock.setForeground(Color.RED);
		} else
			this.stock.setText("Stock: " + stock);
	}

	/**
	 * @param lblImagen the lblImagen to set
	 */
	public void setImage(String ruta) {
		try {
	        ImageIcon iconOriginal = new ImageIcon(ruta);
	        
	        int width = lblImagen.getPreferredSize().width;
	        int height = lblImagen.getPreferredSize().height;

	        Image imgEscalada = iconOriginal.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
	        
	        lblImagen.setIcon(new ImageIcon(imgEscalada));
	        lblImagen.setText(""); 
	        
	    } catch (Exception e) {
	        lblImagen.setText("Error al cargar imagen");
	        System.err.println("No se pudo cargar: " + ruta);
	    }
	}

	
	public void setMaxStock(int realStock) {
	    SpinnerNumberModel model = (SpinnerNumberModel) unitSpinner.getModel();
	    
	    model.setMaximum(realStock);
	    
	    if (realStock <= 0) {
	        unitSpinner.setValue(0);
	        unitSpinner.setEnabled(false);
	        btnaddCart.setEnabled(false);
	        stock.setText("OUT OF STOCK!");
	        stock.setForeground(Color.RED);
	    } else {
	        unitSpinner.setEnabled(true);
	        btnaddCart.setEnabled(true);
	        if ((int)unitSpinner.getValue() > realStock) {
	            unitSpinner.setValue(realStock);
	        }
	    }
	}
	
	public void setProductsInPack(ArrayList<StoreProduct> products) {
	    if (products == null || products.isEmpty()) {
	        listProducts.setListData(new String[]{"Este pack no contiene productos."});
	    } else {
	        String[] data = products.stream()
	                .map(p -> p.getName() + " - (" + String.format("%.2f", p.getPrice()) + "€)")
	                .toArray(String[]::new);
	        listProducts.setListData(data);
	    }
	}
	
	public void setPackssInPack(HashSet<Pack> packs) {
	    if (packs == null || packs.isEmpty()) {
	        listProducts.setListData(new String[]{"Este pack no contiene productos."});
	    } else {
	        String[] data = packs.stream()
	                .map(p -> p.getId() + " - (" + String.format("%.2f", p.getDiscountedPrice()) + "€ - " 
	        + p.getDiscount().toString() + ")")
	                .toArray(String[]::new);
	        listProducts.setListData(data);
	    }
	}
}
