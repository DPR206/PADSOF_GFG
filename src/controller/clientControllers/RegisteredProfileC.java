package controller.clientControllers;

import java.awt.Frame;

import javax.swing.SwingUtilities;

import model.user.RegisteredClient;
import view.clientPanels.RegisteredChangePwd;
import view.clientPanels.RegisteredProfile;

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

		Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(vista);

	    RegisteredChangePwd pagPwd = new RegisteredChangePwd(parentFrame);

	    new RegisteredChangePwdC(pagPwd, user);

	    pagPwd.setVisible(true);
	    actualizarVista();
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


	private void actualizarVista() {
	    vista.getNom().setText(user.getUserName());
	    vista.revalidate();
	    vista.repaint();
	}
}