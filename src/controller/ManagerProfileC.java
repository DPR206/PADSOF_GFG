package controller;

import java.awt.Window;

import javax.swing.SwingUtilities;

import model.user.Manager;
import view.GestorChangePwd;
import view.ManagerProfile;

public class ManagerProfileC {
	
	private ManagerProfile vista;
	private Manager user;
	//private App frame;
	private boolean passwordRevelada = false;
	
	/**
	 * @param vista
	 * @param user
	 */
	public ManagerProfileC(ManagerProfile vista, Manager user) {
		this.vista = vista;
		this.user = user;
		inicializarEventos();
	}

	private void inicializarEventos() {
		
		vista.setName(user.getUserName());
		
		vista.getBtnMostrar().addActionListener(e -> {
			showPassword();
		});
		
		vista.getBtnCambiar().addActionListener(e -> {
			cambiarPwd();
		});
	}
	
	private void cambiarPwd() {
		Window ventanaActual = SwingUtilities.getWindowAncestor(vista);
	    
	    if (ventanaActual != null) {
	        ventanaActual.dispose(); 
	    }
		
	    GestorChangePwd pagPwd = new GestorChangePwd(vista.getBanner());
	    
	    new GestorChangePwdC(pagPwd, user);
	    
	    pagPwd.setVisible(true);
	}

	private void showPassword() {
		if (passwordRevelada) {
		    vista.setPwd("********");
		    vista.getBtnMostrar().setText("👁️");
		} else {
		    vista.setPwd(user.getPassword());
		    vista.getBtnMostrar().setText("🔒");
		}
		passwordRevelada = !passwordRevelada;
	}
}
