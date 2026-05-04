package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;

import model.user.Manager;
import view.banners.BannerManager;

import javax.swing.*;

public class GestorChangePwd extends JPanel{
	private String username;
	//private JPanel title;
	private JPanel main;
	private BannerManager banner;
	
	public GestorChangePwd(/*String username,*/ BannerManager banner) {
		super();
		
		this.banner = banner;
		add(this.banner, BorderLayout.NORTH);
		
		//this.username = username;
		//this.title = new JPanel();
		//this.title.add(new JLabel("GIFTS FOR GEEKS: GESTOR"), BorderLayout.NORTH);
		//this.add(title, BorderLayout.CENTER);
		
		this.main = new JPanel();
		this.main.setLayout(new GridLayout(3,1));
		
		JPanel aux1 = new JPanel();
		aux1.setLayout(new GridLayout(2,1));
		aux1.add(new JLabel("NOMBRE DE USUARIO:"));
		aux1.add(new JLabel(this.username));
		
		this.main.add(aux1);
		
		JPanel aux2 = new JPanel();
		aux2.setLayout(new GridLayout(2,1));
		aux2.add(new JLabel("CONTRASEÑA:"));
		aux2.add(new JPasswordField());
		
		this.main.add(aux2);
		
		JPanel aux3 = new JPanel();
		aux3.add(new JButton("CAMBIAR"));
		
		this.main.add(aux3);
		//title.add(main);
		this.add(main, BorderLayout.CENTER);
	}


}
