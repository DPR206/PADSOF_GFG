package graphicInt;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class SignIn extends JPanel{
	public SignIn() {
		super();
		this.setLayout(new BorderLayout());
		
		JPanel titles = new JPanel();
		titles.setLayout(new GridLayout(1,3));
		JPanel home = new JPanel();
		home.add(new JButton("Home"));
		titles.add(home);
		
		JPanel name = new JPanel();
		name.add(new JLabel("GIFTS FOR GEEKS"));
		titles.add(name);
		
		this.add(titles, BorderLayout.NORTH);
		
		JPanel credentials = new JPanel();
		credentials.setLayout(new GridLayout(4,1));
		credentials.setPreferredSize(new Dimension(300, 200));
		JPanel userName = new JPanel();
		userName.setLayout(new GridLayout(2,1));
		userName.add(new JLabel("USERNAME"));
		userName.add(new JTextField());
		
		
		JPanel DNI = new JPanel();
		DNI.setLayout(new GridLayout(2,1));
		DNI.add(new JLabel("DNI O NIE*"));
		DNI.add(new JTextField());
		
		JPanel pwd = new JPanel();
		pwd.setLayout(new GridLayout(2,1));
		pwd.add(new JLabel("PASSWORD"));
		pwd.add(new JTextField());
		
		credentials.add(userName);
		credentials.add(DNI);
		credentials.add(pwd);
		
		credentials.add(new JButton("CONTINUAR"));
		
		this.add(credentials, BorderLayout.WEST);
		
		/*Añadimos la opción de iniciar sesión*/
		JPanel moreText = new JPanel();
		moreText.setLayout(new GridLayout(2,1));
		moreText.add(new JLabel("¿Ya tienes cuenta?"));
		moreText.add(new JButton("Inicia sesión aquí"));
		
		this.add(moreText, BorderLayout.EAST);
	}
}
