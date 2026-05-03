package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class NotificationsSettingsEmployeeP extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton btnVolver;
	private JCheckBox exchanges;
	private JCheckBox orders;
	private JCheckBox valuation;
	private JPanel banner;
	

	/**
	 * Create the panel.
	 */
	public NotificationsSettingsEmployeeP(ActionListener volverAction, JPanel banner) {
		
		setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel panelCabecera = new JPanel();
        panelCabecera.setLayout(new BoxLayout(panelCabecera, BoxLayout.Y_AXIS));
        
        this.banner = banner;
        if (banner != null) {
            panelCabecera.add(this.banner);
        }

        // Título
        JLabel titulo = new JLabel("Ajustes notificaciones empleado", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT); 
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        
        panelCabecera.add(titulo);
        add(panelCabecera, BorderLayout.NORTH);
        
        //Ajustes
        
        JPanel contenedorCentral = new JPanel();
        contenedorCentral.setLayout(new BoxLayout(contenedorCentral, BoxLayout.Y_AXIS));
        contenedorCentral.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JPanel exchange = new JPanel();
        exchange.setLayout(new BoxLayout(exchange, BoxLayout.Y_AXIS));
        exchange.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel tituloExc = new JLabel("EXCHANGES");
        tituloExc.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloExc.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel filaChecksExc = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        filaChecksExc.setAlignmentX(Component.LEFT_ALIGNMENT);
        exchanges = new JCheckBox("New exchanges to manage");
        filaChecksExc.add(exchanges);
        
        exchange.add(tituloExc);
        exchange.add(filaChecksExc);
        
        JPanel order = new JPanel();
        order.setLayout(new BoxLayout(order, BoxLayout.Y_AXIS));
        order.setAlignmentX(Component.LEFT_ALIGNMENT);
        order.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        
        JLabel tituloOrd = new JLabel("ORDERS");
        tituloOrd.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloOrd.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel filaChecksOrd = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        filaChecksOrd.setAlignmentX(Component.LEFT_ALIGNMENT);
        orders = new JCheckBox("New orders to manage");
        filaChecksOrd.add(orders);
        
	    order.add(tituloOrd);
	    order.add(filaChecksOrd);
	    
	    JPanel valuations = new JPanel();
	    valuations.setLayout(new BoxLayout(valuations, BoxLayout.Y_AXIS));
	    valuations.setAlignmentX(Component.LEFT_ALIGNMENT);
	    valuations.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
	    
	    JLabel tituloVal = new JLabel("VALUATIONS");
	    tituloVal.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloVal.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel filaChecksVal = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        filaChecksVal.setAlignmentX(Component.LEFT_ALIGNMENT);
        valuation = new JCheckBox("Valuations to be made");
        
        filaChecksVal.add(valuation);
        
        
        valuations.add(tituloVal);
        valuations.add(filaChecksVal);
        
	    contenedorCentral.add(exchange);
	    contenedorCentral.add(order);
	    contenedorCentral.add(valuations);
        
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
	 * @return the exchanges
	 */
	public JCheckBox getExchanges() {
		return exchanges;
	}


	/**
	 * @return the orders
	 */
	public JCheckBox getOrders() {
		return orders;
	}


	/**
	 * @return the valuation
	 */
	public JCheckBox getValuation() {
		return valuation;
	}
	
	

}
