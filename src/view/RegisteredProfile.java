package view;

import java.awt.*;

import javax.swing.*;

import view.banners.BannerRegistered;

public class RegisteredProfile extends JPanel{

	private static final long serialVersionUID = 1L;
	private BannerRegistered banner;
	private JLabel nom, dni, pwd;
	private JButton btnMostrar;
	private JButton btnCambiar;

	public RegisteredProfile(BannerRegistered banner) {
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
		
		JLabel tituloDni = new JLabel("DNI OR NIE");
		tituloDni.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloDni.setAlignmentX(Component.LEFT_ALIGNMENT);
        
		dni.setFont(new Font("SansSerif", Font.PLAIN, 14));
        dni.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel tituloPwd = new JLabel("PASSWORD");
		tituloPwd.setFont(new Font("SansSerif", Font.BOLD, 14));
        tituloPwd.setAlignmentX(Component.LEFT_ALIGNMENT);
       
        JPanel panelPassword = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
        panelPassword.setOpaque(false);
        panelPassword.setAlignmentX(Component.LEFT_ALIGNMENT);

        pwd = new JLabel("********"); // Estado inicial oculto
        pwd.setFont(new Font("SansSerif", Font.PLAIN, 14));

        btnMostrar = new JButton("👁️");
        btnMostrar.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        btnMostrar.setBorderPainted(false);
        btnMostrar.setContentAreaFilled(false);
        btnMostrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnCambiar = new JButton("Cambiar");
        btnCambiar.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnCambiar.setBorderPainted(true);
        btnCambiar.setContentAreaFilled(true);
        btnCambiar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelPassword.add(pwd);
        panelPassword.add(btnMostrar);
        panelPassword.add(btnCambiar);
		
		information.add(tituloNom);
		information.add(nom);
		
		information.add(Box.createVerticalStrut(40));
		
		information.add(tituloDni);
		information.add(dni);
		
		information.add(Box.createVerticalStrut(40));
		
		information.add(tituloPwd);
		information.add(panelPassword);
		
		add(information, BorderLayout.CENTER);
	}

	/**
	 * @return the banner
	 */
	public BannerRegistered getBanner() {
		return banner;
	}

	/**
	 * @param banner the banner to set
	 */
	public void setBanner(BannerRegistered banner) {
		this.banner = banner;
	}

	/**
	 * @return the nom
	 */
	public JLabel getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom.setText(nom);
	}

	/**
	 * @return the dni
	 */
	public JLabel getDni() {
		return dni;
	}

	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni.setText(dni);
	}

	public void actualizarPasswordVista(String texto) {
	    pwd.setText(texto);
	}

	public JButton getBtnMostrar() {
	    return btnMostrar;
	}

	/**
	 * @return the pwd
	 */
	public JLabel getPwd() {
		return pwd;
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd.setText(pwd);
	}

	/**
	 * @return the btnCambiar
	 */
	public JButton getBtnCambiar() {
		return btnCambiar;
	}
	
	
}
