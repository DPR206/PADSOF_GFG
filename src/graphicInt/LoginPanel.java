package graphicInt;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;
public class LoginPanel extends JPanel{
	
	public LoginPanel() {
		super();
		this.setLayout(new BorderLayout());
		/*TÍTULO*/
		JPanel title = new JPanel();
		title.add(new JLabel("GIFTS FOR GEEKS"));
		this.add(title, BorderLayout.NORTH);
		
		/*Cosas de inicio*/
		
		JPanel credentials = new JPanel();
		credentials.setLayout(new GridLayout(6,1));
		
		credentials.add(new JLabel("INICIO DE SESIÓN"));
		credentials.add(new JLabel("NOMBRE DE USUARIO:"));
		credentials.add(new JTextField());
		credentials.add(new JLabel("CONTRASEÑA:"));
		credentials.add(new JTextField());
		credentials.add(new JButton("INICIAR SESIÓN"));
		
		this.add(credentials, BorderLayout.CENTER);
	}
}
