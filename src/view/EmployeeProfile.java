package view;

import java.awt.*;

import javax.swing.*;

import view.banners.BannerEmployee;

public class EmployeeProfile extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BannerEmployee banner;
	private JLabel nom, pwd;
	private JButton btnMostrar;
	private JCheckBox exchanges;
	private JCheckBox orders;
	private JCheckBox store;

	public EmployeeProfile(BannerEmployee banner) {
		this.banner = banner;
		configurarEstructura();
	}

	private void configurarEstructura() {
		setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		if (banner != null) {
            add(banner, BorderLayout.NORTH);
        }
		
		JPanel information = new JPanel();
		information.setLayout(new BoxLayout(information, BoxLayout.Y_AXIS));
		information.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		JLabel tituloNom = new JLabel("USERNAME");
		tituloNom.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloNom.setAlignmentX(Component.LEFT_ALIGNMENT);
        
		nom.setFont(new Font("SansSerif", Font.PLAIN, 14));
        nom.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel tituloPwd = new JLabel("PASSWORD");
		tituloPwd.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloPwd.setAlignmentX(Component.LEFT_ALIGNMENT);
       
        JPanel panelPassword = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, panelPassword.getPreferredSize().height));
        panelPassword.setOpaque(false);
        panelPassword.setAlignmentX(Component.LEFT_ALIGNMENT);

        pwd = new JLabel("********"); // Estado inicial oculto
        pwd.setFont(new Font("SansSerif", Font.PLAIN, 14));

        btnMostrar = new JButton("👁️");
        btnMostrar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        btnMostrar.setBorderPainted(false);
        btnMostrar.setContentAreaFilled(false);
        btnMostrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelPassword.add(pwd);
        panelPassword.add(btnMostrar);
        
		JLabel tituloPerm = new JLabel("PERMISSIONS");
		tituloPerm.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloPerm.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JPanel permissions = new JPanel();
        permissions.setLayout(new BoxLayout(permissions, BoxLayout.Y_AXIS));
        permissions.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        store = new JCheckBox("Work with products");
        exchanges = new JCheckBox("Work with exchanges");
        orders = new JCheckBox("Work with orders");
        
        permissions.add(store);
        permissions.add(exchanges);
        permissions.add(orders);
		
		information.add(tituloNom);
		information.add(nom);
		
		information.add(Box.createVerticalStrut(40));
		
		information.add(tituloPwd);
		information.add(panelPassword);
		
		information.add(Box.createVerticalStrut(40));
		
		information.add(tituloPerm);
		information.add(permissions);
		
		information.add(Box.createVerticalGlue());
		
		add(information, BorderLayout.CENTER);
	}

	/**
	 * @return the banner
	 */
	public BannerEmployee getBanner() {
		return banner;
	}

	/**
	 * @return the btnMostrar
	 */
	public JButton getBtnMostrar() {
		return btnMostrar;
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
	 * @return the store
	 */
	public JCheckBox getStore() {
		return store;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(JLabel nom) {
		this.nom = nom;
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd.setText(pwd);;
	}

	
}
