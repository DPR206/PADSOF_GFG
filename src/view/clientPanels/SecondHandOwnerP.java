package view.clientPanels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

import model.product.ConservationStatus;

public class SecondHandOwnerP extends JPanel {
	
	private JLabel name;
	private JLabel valuation;
	private JLabel description;
	private JButton btnaddCart = new JButton("Request Valoration");
	private JButton btnReturn = new JButton("Return");
	private JLabel lblImagen;
	
	private static final long serialVersionUID = 1L;

	public SecondHandOwnerP() {
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
        valuation = new JLabel("");
        valuation.setFont(new Font("Tahoma", Font.PLAIN, 15));
        description = new JLabel("");
        description.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        description.setVerticalAlignment(SwingConstants.TOP);

        infoPanel.add(name);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(valuation);
        infoPanel.add(Box.createVerticalStrut(10)); 
        infoPanel.add(description);
        
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
		
	}

	/**
	 * @return the btnaddCart
	 */
	public JButton getBtnValorationt() {
		return btnaddCart;
	}

	/**
	 * @return the btnReturn
	 */
	public JButton getBtnReturn() {
		return btnReturn;
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
	public void setValuation(double price, ConservationStatus status) {
		if(price > 0 && status != null)
			this.valuation.setText(String.format("Valuation: %.2f€ - %s", price, status.toString()));
		this.valuation.setText("Valuation: Pending valuation");
	}

	public void setValuation(String info) {
		this.valuation.setText("Valuation: " + info);
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

	/**
	 * @param description the description to set
	 */
	public void setDescriptionText(String texto) {
	    this.description.setText("<html><body style='width: 300px;'>" +
	                             "<b>Description:</b> " + texto + 
	                             "</body></html>");
	}
	
}
