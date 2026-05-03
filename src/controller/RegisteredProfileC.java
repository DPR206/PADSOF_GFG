package controller;

import java.awt.Window;

import javax.swing.SwingUtilities;

import model.user.RegisteredClient;
import view.App;
import view.RegisteredProfile;

public class RegisteredProfileC {
	
	private RegisteredProfile vista;
	private RegisteredClient user;
	//private App frame;
	private boolean passwordRevelada = false;

	/**
	 * 
	 */
	public RegisteredProfileC(RegisteredProfile vista, RegisteredClient user/*, App frame*/) {
		
		this.vista = vista;
		this.user = user;
		//this.user = frame.getUser();
		//this.frame = frame;
		inicializarEventos();
	}

	private void inicializarEventos() {
		
		vista.setNom(user.getUserName());
		
		vista.setDni(user.getDni());
		
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
		
	    RegisteredChangePwd pagPwd = new RegisteredChangePwd(user, vista.getBanner());
	    
	    new RegisteredChangePwdC(pagPwd, user);
	    
	    pagPwd.setVisible(true);
	}

	private void showPassword() {
		if (passwordRevelada) {
		    vista.actualizarPasswordVista("********");
		    vista.getBtnMostrar().setText("👁️");
		} else {
		    vista.actualizarPasswordVista(user.getPassword());
		    vista.getBtnMostrar().setText("🔒");
		}
		passwordRevelada = !passwordRevelada;
	}

	
}
