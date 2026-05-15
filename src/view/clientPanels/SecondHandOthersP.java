package view.clientPanels;

import java.awt.*;

import javax.swing.*;

import model.product.ConservationStatus;

public class SecondHandOthersP extends JPanel{

	private JLabel name;
	private JLabel valuation;
	private JLabel description;
	private JButton btnOffer = new JButton("Make an offer");
	private JButton btnReturn = new JButton("Return");
	private JLabel lblImagen;
	
	private static final long serialVersionUID = 1L;

	public SecondHandOthersP() {
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

        btnOffer.setMaximumSize(new Dimension(250, 100)); 
        btnOffer.setFont(fuenteBotones); 
        
        btnReturn.setMaximumSize(new Dimension(250, 100));
        btnReturn.setFont(fuenteBotones);

        btnOffer.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReturn.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(Box.createVerticalGlue()); 
        buttonPanel.add(btnOffer);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnReturn);
        buttonPanel.add(Box.createVerticalGlue());
        
        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);
		
	}

	/**
	 * @return the btnaddCart
	 */
	public JButton getBtnOffer() {
		return btnOffer;
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
	public void setName(String s) {
		this.name.setText(s);
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
	                             "Description: " + texto + 
	                             "</body></html>");
	}
	
}
