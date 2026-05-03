package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NotificationsSettingsClientP extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnVolver;
	private JCheckBox disc;
	private JCheckBox offers;
	private JCheckBox newSecondHand;
	private JCheckBox payment;
	private JCheckBox orderState;
	private JCheckBox packCart;
	private JCheckBox productCart;
	private JPanel banner;
	

	/**
	 * Create the panel.
	 */
	public NotificationsSettingsClientP(ActionListener volverAction, JPanel banner) {
		
		setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel panelCabecera = new JPanel();
        panelCabecera.setLayout(new BoxLayout(panelCabecera, BoxLayout.Y_AXIS));
        
        this.banner = banner;
        if (banner != null) {
            panelCabecera.add(this.banner);
        }
		

        // Título
        JLabel titulo = new JLabel("Ajustes notificaciones", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT); 
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        
        panelCabecera.add(titulo);
        add(panelCabecera, BorderLayout.NORTH);
        
        //Ajustes
        
        JPanel contenedorCentral = new JPanel();
        contenedorCentral.setLayout(new BoxLayout(contenedorCentral, BoxLayout.Y_AXIS));
        contenedorCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JPanel discounts = new JPanel();
        discounts.setLayout(new BoxLayout(discounts, BoxLayout.Y_AXIS));
        discounts.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel tituloDisc = new JLabel("DISCOUNTS");
        tituloDisc.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloDisc.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel filaChecksDisc = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        filaChecksDisc.setAlignmentX(Component.LEFT_ALIGNMENT);
        disc = new JCheckBox("New discounts");
        filaChecksDisc.add(disc);
        
        discounts.add(tituloDisc);
        discounts.add(filaChecksDisc);
        
        JPanel exchanges = new JPanel();
        exchanges.setLayout(new BoxLayout(exchanges, BoxLayout.Y_AXIS));
        exchanges.setAlignmentX(Component.LEFT_ALIGNMENT);
        exchanges.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        
        JLabel tituloExc = new JLabel("EXCHANGES");
        tituloExc.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloExc.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel filaChecksExc = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        filaChecksExc.setAlignmentX(Component.LEFT_ALIGNMENT);
        offers = new JCheckBox("Offers");
        newSecondHand = new JCheckBox("New second-hand products");
        filaChecksExc.add(offers);
        filaChecksExc.add(newSecondHand);
        
	    exchanges.add(tituloExc);
	    exchanges.add(filaChecksExc);
	    
	    JPanel orders = new JPanel();
	    orders.setLayout(new BoxLayout(orders, BoxLayout.Y_AXIS));
	    orders.setAlignmentX(Component.LEFT_ALIGNMENT);
	    orders.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
	    
	    JLabel tituloOrder = new JLabel("ORDERS");
	    tituloOrder.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloOrder.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel filaChecksOrd = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        filaChecksOrd.setAlignmentX(Component.LEFT_ALIGNMENT);
        payment = new JCheckBox("Payments");
        orderState = new JCheckBox("Order state");
        packCart = new JCheckBox("Expired packs");
        productCart = new JCheckBox("Expired products");
        
        filaChecksOrd.add(payment);
        filaChecksOrd.add(orderState);
        filaChecksOrd.add(packCart);
        filaChecksOrd.add(productCart);
        
        
        orders.add(tituloOrder);
        orders.add(filaChecksOrd);
        
	    contenedorCentral.add(discounts);
	    contenedorCentral.add(exchanges);
	    contenedorCentral.add(orders);
        
        //Botón volver
        btnVolver = new JButton("← Volver");
        btnVolver.setPreferredSize(new Dimension(0, 50));
        btnVolver.addActionListener(volverAction);

        add(new JScrollPane(contenedorCentral), BorderLayout.CENTER);
        add(btnVolver, BorderLayout.SOUTH);
	}


	/**
	 * @return the btnVolver
	 */
	public JButton getBtnVolver() {
		return btnVolver;
	}


	/**
	 * @return the disc
	 */
	public JCheckBox getDisc() {
		return disc;
	}


	/**
	 * @return the offers
	 */
	public JCheckBox getOffers() {
		return offers;
	}


	/**
	 * @return the newSecondHand
	 */
	public JCheckBox getNewSecondHand() {
		return newSecondHand;
	}


	/**
	 * @return the payment
	 */
	public JCheckBox getPayment() {
		return payment;
	}


	/**
	 * @return the orderState
	 */
	public JCheckBox getOrderState() {
		return orderState;
	}


	/**
	 * @return the packCart
	 */
	public JCheckBox getPackCart() {
		return packCart;
	}


	/**
	 * @return the productCart
	 */
	public JCheckBox getProductCart() {
		return productCart;
	}

}
